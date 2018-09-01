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

import java.io.*;
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

    private void saveAsFile(String content) {
        String savefile = "E:\\test.txt";
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
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return uploadUrl;
    }


    @Override
    public ResultMessage publishArticle(String post_id,String author, String post_name, ArrayList<Post_tag> post_tag, String brief_intro,String content) throws IOException {
        saveAsFile(content);
        File file=new File("E:\\test.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",input);
        String content_url=uploadFile(post_id,multipartFile);
        Post post=new Post(post_id,author,post_name,post_tag,brief_intro,content_url);
        post.setPublish_time(new Date());
        post.setVisits(0);
        post.setRemark_num(0);
        post.setRemark_content(new ArrayList<>());
        post.setPost_state(Post_state.Published);
        postDao.save(post);
        communityUserBLService.releasePost(author,post_id);
        return ResultMessage.Success;
    }



    @Override
    public ResultMessage edit(String post_id, String post_name, ArrayList<Post_tag> post_tag, String content) {
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
    public PostVO readArticle(String post_id,String reader) {
        Post post=postDao.getOne(post_id);
        PostVO postVO=new PostVO(post);
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

    private ResultMessage addRemarkNum(String post_id) {
        Post post=postDao.getOne(post_id);
        post.setRemark_num(post.getRemark_num()+1);
        postDao.save(post);
        return ResultMessage.Success;
    }
}
