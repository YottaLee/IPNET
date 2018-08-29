package com.ipnet.bl.communitybl;

import com.ipnet.blservice.communityservice.PostBLService;
import com.ipnet.dao.communitydao.PostDao;
import com.ipnet.entity.communityentity.Post;
import com.ipnet.entity.communityentity.Remark;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostBL implements PostBLService {
    @Autowired
    private PostDao postDao;

    @Override
    public String createID(String author) {
        Post post=new Post(author);
        postDao.save(post);
        return post.getPost_id();
    }


    @Override
    public ResultMessage publishArticle(String post_id,String author, String post_name, Post_tag post_tag, String content_url) {
        Post post=new Post(post_id,author,post_name,post_tag,content_url);
        post.setPublish_time(new Date());
        post.setVisits(0);
        post.setRemark_num(0);
        post.setRemark_content(new ArrayList<Remark>());
        postDao.save(post);
        return ResultMessage.Success;
    }



    @Override
    public ResultMessage edit(String post_id, String post_name, Post_tag post_tag, String content_url) {
        Post post=postDao.getOne(post_id);
        post.setPost_name(post_name);
        post.setPost_tag(post_tag);
        post.setContent_url(content_url);
        postDao.save(post);
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
        return ResultMessage.Success;
    }

    @Override
    public PostVO readArticle(String post_id) {
        Post post=postDao.getOne(post_id);
        PostVO postVO=new PostVO(post);
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
}
