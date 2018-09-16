package com.ipnet.bl.communitybl;

import com.ipnet.bl.ali.AliServiceImpl;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.blservice.communityservice.PostBLService;
import com.ipnet.dao.communitydao.PostDao;
import com.ipnet.entity.communityentity.Post;
import com.ipnet.entity.communityentity.Record;
import com.ipnet.entity.communityentity.Remark;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_state;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.PostVO;
import com.ipnet.vo.communityvo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.SimpleBeanInfo;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PostBL implements PostBLService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private CommunityUserBLService communityUserBLService;

    @Autowired
    private UserBLService userBLService;

    @Autowired
    private AliServiceImpl aliService;

    @Override
    public String createID(String author) {
        Post post=new Post(author);
        postDao.save(post);
        return post.getPost_id();
    }

    private void saveAsFile(String content) throws IOException {
        String savefile = "E:\\test.txt";
        File file=new File(savefile);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(savefile);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String uploadFile(String post_id,MultipartFile file) {
        String filename = post_id+".txt";
        String uploadUrl="";
        try {
            if (file!=null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliService.upLoad(newFile);
                    if(uploadUrl.charAt(7)!='/'){
                        String str1=uploadUrl.substring(7);
                        uploadUrl="https://"+str1;
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return uploadUrl;
    }


    @Override
    public ResultMessage publishArticle(String post_id,String author, String post_name, ArrayList<String> post_tag, String brief_intro,String content) throws IOException {
        this.saveAsFile(content);
        File file=new File("E:\\test.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",input);
        String content_url=uploadFile(post_id,multipartFile);
        Post post=postDao.getOne(post_id);
        post.setAuthor(author);
        post.setPost_name(post_name);
        post.setPost_tag(post_tag);
        post.setBrief_intro(brief_intro);
        post.setContent_url(content_url);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String day=df.format(new Date());
        post.setPublish_time(day);
        post.setVisits(0);
        post.setRemark_num(0);
        post.setRemark_content(new ArrayList<>());
        post.setPost_state(Post_state.Published);
        postDao.save(post);
        communityUserBLService.releasePost(author,post_id);
        return ResultMessage.Success;
    }



    @Override
    public ResultMessage edit(String post_id, String post_name, ArrayList<String> post_tag, String content) {
        Post post=postDao.getOne(post_id);
        post.setPost_name(post_name);
        post.setPost_tag(post_tag);
        post.setContent_url(content);
        postDao.save(post);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage deleteArticle(String post_id) {
        Post post=postDao.getOne(post_id);
        postDao.deleteById(post_id);
        communityUserBLService.deletePost(post.getAuthor(),post_id);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage remark(String post_id, String reviewer, String remark_content) {
        Remark remark=new Remark(post_id,reviewer,remark_content);
        Post post=postDao.getOne(post_id);
        post.setRemark_num(post.getRemark_num()+1);
        List<Remark> remarks=post.getRemark_content();
        remarks.add(remark);
        post.setRemark_content(remarks);
        postDao.save(post);
        addRemarkNum(post_id);
        return ResultMessage.Success;
    }

    @Override
    public PostVO readArticle(String post_id,String reader) throws IOException {
        Post post=postDao.getOne(post_id);
        String content=downLoadFromUrl(post.getContent_url());
        PostVO postVO=new PostVO(post.getPost_id(),post.getAuthor(),post.getPost_name(),new ArrayList<>(post.getPost_tag()),content,post.getPublish_time(),post.getVisits(),post.getRemark_num(),post.getInterest_num(),new ArrayList<>(post.getRemark_content()));
        communityUserBLService.browsePost(reader,post_id,post.getPost_name());
        return postVO;
    }

    @Override
    public ArrayList<BriefPost> readArticleList(String author) {
        ArrayList<Post> posts=postDao.getPostByAuthor(author);
        ArrayList<BriefPost> briefPosts=new ArrayList<>();
        for(Post p:posts){
            briefPosts.add(new BriefPost(p));
        }
        return briefPosts;
    }

    @Override
    public ArrayList<BriefPost> getAllArticleList() {
        List<Post> posts=postDao.findAll();
        ArrayList<BriefPost> briefPosts=new ArrayList<>();
        for(Post p:posts){
            briefPosts.add(new BriefPost(p));
        }
        return briefPosts;
    }

    @Override
    public ArrayList<RecordVO> recommend(String author) {
        ArrayList<Post> posts=new ArrayList<>(postDao.findAll());
        ArrayList<RecordVO> recordVOS=new ArrayList<>();
        ArrayList<RecordVO> res=new ArrayList<>();
        for(Post p:posts){
            if(!p.getAuthor().equals(author)){
                recordVOS.add(new RecordVO(p.getPost_id(),p.getPost_name(),userBLService.getImageUrl(p.getAuthor())));
            }
        }
        Collections.shuffle(recordVOS);//打乱数组
        if(recordVOS.size()<=7){
            return recordVOS;
        }else{
            for(int i=0;i<7;i++){
                res.add(recordVOS.get(i));
            }
            return res;
        }
    }

    @Override
    public ArrayList<BriefPost> searchArticle(String keywords) {
        ArrayList<Post> posts=postDao.searchArticle(keywords);
        ArrayList<BriefPost> briefPosts=new ArrayList<>();
        for(Post p:posts){
            briefPosts.add(new BriefPost(p));
        }
        return briefPosts;
    }


    @Override
    public ResultMessage addInterestNum(String post_id) {
        Post post=postDao.getOne(post_id);
        post.setInterest_num(post.getInterest_num()+1);
        postDao.save(post);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage minusInterestNum(String post_id) {
        Post post=postDao.getOne(post_id);
        post.setInterest_num(post.getInterest_num()-1);
        postDao.save(post);
        return ResultMessage.Success;
    }

    @Override
    public BriefPost getBriefPostByID(String post_id) {
        Post post=postDao.getOne(post_id);
        return new BriefPost(post);
    }


    private  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    @Override
    //根据远端url下载文件到指定路径,然后读出文件的内容，再将文件删除
    public String downLoadFromUrl(String urlStr) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        //文件保存位置
        File saveDir = new File("D:");
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
//        File file = new File(saveDir+File.separator+fileName);
        File file=new File(saveDir+File.separator+"temp.txt");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        System.out.println("info:"+url+" download success");

        BufferedReader in = null;
        BufferedWriter out = null;
        String res="";
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
            String line = "";
            while((line = in.readLine())!=null){
                res=res+line+"\r\n";
//                System.out.println(line);
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.out.println("file is not fond");
        } catch (IOException e) {
            System.out.println("Read or write Exceptioned");
        }finally{
            if(null!=in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
            if(null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
//        if (file.exists() && file.isFile()) {
//            if (file.delete()) {
//                System.out.println("删除单个文件成功！");
//            } else {
//                System.out.println("删除单个文件失败！");
//            }
//        } else {
//            System.out.println("删除单个文件失败,不存在！");
//        }
        return res;
    }

    private ResultMessage addRemarkNum(String post_id) {
        Post post=postDao.getOne(post_id);
        post.setRemark_num(post.getRemark_num()+1);
        postDao.save(post);
        return ResultMessage.Success;
    }
}
