package com.ipnet.blservice.communityservice;

import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.BriefUser;
import com.ipnet.vo.communityvo.CUserVO;
import com.ipnet.vo.communityvo.RecordVO;

import java.util.ArrayList;
import java.util.List;

public interface  CommunityUserBLService {

    //模糊查找，根据关键词搜素用户
    List<BriefUser> searchByKeyword(String keyword);
    //当用户注册后，自动为其生成一个社区实体
    void addUser(String userID);
    //获得用户的基本信息,包括积分，钱包，签名，关注数……
    CUserVO getUserInfo(String userID);
    //用户修改社区中个性签名
    void modifyNickname(String username, String signature);
    //用户选择他感兴趣的标签
    void modifyTag(String username, ArrayList<Post_tag> tags);
    //用户发布帖子
    void releasePost(String username, String postID);
    //用户删除自己的帖子
    void deletePost(String username, String postID);
    //用户关注帖子
    void interestPost(String username, String postID);
    //用户取消关注帖子
    void cancelInterest(String username, String postID);

    /*用户收藏帖子
    void collectPost(String username, String postID);
    //用户取消收藏帖子
    void cancelCollect(String username, String postID);*/

    //用户关注其他用户
    void interestUser(String starID, String fanID);
    //用户取消关注某用户
    void cancelInterestUser(String starID, String fanID);
    //用户浏览帖子
    void browsePost(String userID,String postID,String postname);

    //获取用户发布的帖子列表
    List<BriefPost> getReleased(String username);
    //获取用户关注的帖子列表
    List<BriefPost> getInterestedpost(String username);
    //获取用户关注的用户列表
    List<BriefUser> getInterestedUser(String username);
    //获取用户的浏览记录
    List<RecordVO> getHistory(String userID);
    //获取用户收藏的帖子列表
    //List<Mine> getCollected(String username);


}
