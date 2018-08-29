package com.ipnet.bl.communitybl;

import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.dao.communitydao.CommunityUserDao;
import com.ipnet.entity.communityentity.CommunityUser;
import com.ipnet.entity.communityentity.Mine;
import com.ipnet.entity.communityentity.MineTag;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.utility.TransHelper;
import com.ipnet.vo.communityvo.CUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CommunityUserBL implements CommunityUserBLService {

    @Autowired
    private CommunityUserDao communityUserDao;
    @Autowired
    private TransHelper transHelper;

    @Override
    public void addUser(String userID) {
        CommunityUser newUser=new CommunityUser(userID,"","",0,0,
                0,0,0,new ArrayList<>());
        communityUserDao.save(newUser);
    }

    @Override
    public CUserVO getUserInfo(String userID) {
        Optional<CommunityUser> user=communityUserDao.findById(userID);
        if(user.isPresent()){
            CUserVO cUserVO=(CUserVO) this.transHelper.transTO(user.get(),CUserVO.class);
            cUserVO.setCredits(0);
            cUserVO.setWallet(0);
            cUserVO.setMyTags(user.get().getTags().split(","));
            return cUserVO;
        }
        return null;
    }

    @Override
    public void modifySignature(String username, String signature) {
        communityUserDao.modifySignature(username,signature);
    }

    @Override
    public void modifyTag(String username, ArrayList<Post_tag> tags) {
        StringBuilder newSign= new StringBuilder();
        for(Post_tag tag:tags){
            newSign.append(tag.toString());
        }
        communityUserDao.modifyTag(username, newSign.toString());
    }

    @Override
    public void releasePost(String username, String postID) {
        Optional<CommunityUser> o_user=communityUserDao.findById(username);
        if(o_user.isPresent()){
            CommunityUser user=o_user.get();
            user.setReleasedpost(user.getReleasedpost()+1);
            user.setMines(this.addMine(user.getMines(),postID,MineTag.Release));
            communityUserDao.saveAndFlush(user);
        }
    }

    @Override
    public void interestPost(String username, String postID) {
        Optional<CommunityUser> o_user=communityUserDao.findById(username);
        if(o_user.isPresent()){
            CommunityUser user=o_user.get();
            user.setInterestedpost(user.getInterestedpost()+1);
            user.setMines(this.addMine(user.getMines(),postID,MineTag.InterestPost));
            communityUserDao.saveAndFlush(user);
        }
    }

    @Override
    public void collectPost(String username, String postID) {
        Optional<CommunityUser> o_user=communityUserDao.findById(username);
        if(o_user.isPresent()){
            CommunityUser user=o_user.get();
            user.setCollectedpost(user.getCollectedpost()+1);
            user.setMines(this.addMine(user.getMines(),postID,MineTag.Collect));
            communityUserDao.saveAndFlush(user);
        }
    }

    @Override
    public void interestUser(String starID, String fanID) {
        Optional<CommunityUser> s_user=communityUserDao.findById(starID);
        Optional<CommunityUser> f_user=communityUserDao.findById(fanID);
        if(s_user.isPresent() && f_user.isPresent()){
            //被关注用户的粉丝数+1
            CommunityUser star=s_user.get();
            star.setFans(star.getFans()+1);
            star.setMines(this.addMine(star.getMines(),fanID,MineTag.Fan));
            communityUserDao.saveAndFlush(star);

            //粉丝的关注用户数+1
            CommunityUser fan=f_user.get();
            fan.setInteresteduser(fan.getInteresteduser()+1);
            fan.setMines(this.addMine(fan.getMines(),starID,MineTag.InterestUser));
            communityUserDao.saveAndFlush(fan);
        }
    }

    private List<Mine> addMine(List<Mine> mines,String toAddId,MineTag tag){
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Mine newMine=new Mine(0,toAddId,time,tag);
        mines.add(newMine);
        return mines;
    }

    @Override
    public void deletePost(String username, String postID) {
        Optional<CommunityUser> o_user=communityUserDao.findById(username);
        if(o_user.isPresent()){
            CommunityUser user=o_user.get();
            user.setReleasedpost(user.getReleasedpost()-1);
            user.setMines(this.removeMine(user.getMines(),postID,MineTag.Release));
            communityUserDao.saveAndFlush(user);
        }
    }

    @Override
    public void cancelInterest(String username, String postID) {
        Optional<CommunityUser> o_user=communityUserDao.findById(username);
        if(o_user.isPresent()){
            CommunityUser user=o_user.get();
            user.setInterestedpost(user.getInterestedpost()-1);
            user.setMines(this.removeMine(user.getMines(),postID,MineTag.InterestPost));
            communityUserDao.saveAndFlush(user);
        }
    }

    @Override
    public void cancelCollect(String username, String postID) {
        Optional<CommunityUser> o_user=communityUserDao.findById(username);
        if(o_user.isPresent()){
            CommunityUser user=o_user.get();
            user.setCollectedpost(user.getCollectedpost()-1);
            user.setMines(this.removeMine(user.getMines(),postID,MineTag.Collect));
            communityUserDao.saveAndFlush(user);
        }
    }

    @Override
    public void cancelInterestUser(String starID, String fanID) {
        Optional<CommunityUser> s_user=communityUserDao.findById(starID);
        Optional<CommunityUser> f_user=communityUserDao.findById(fanID);
        if(s_user.isPresent() && f_user.isPresent()){
            //被取关用户的粉丝数-1
            CommunityUser star=s_user.get();
            star.setFans(star.getFans()-1);
            star.setMines(this.removeMine(star.getMines(),fanID,MineTag.Fan));
            communityUserDao.saveAndFlush(star);
            //取消关注的用户关注用户列表-1
            CommunityUser fan=f_user.get();
            fan.setInteresteduser(fan.getInteresteduser()-1);
            fan.setMines(this.removeMine(fan.getMines(),starID,MineTag.InterestUser));
            communityUserDao.saveAndFlush(fan);
        }
    }

    private List<Mine> removeMine(List<Mine> mines,String toRemoveId,MineTag tag){
        for(Mine mine:mines){
            if(mine.getTag()==tag && mine.getTid().equals(toRemoveId)){
                mines.remove(mine);
                break;
            }
        }
        return mines;
    }

    @Override
    public List<Mine> getReleased(String username) {
        return this.getMine(username,MineTag.Release);
    }

    @Override
    public List<Mine> getInterestedpost(String username) {
        return this.getMine(username,MineTag.InterestPost);
    }

    @Override
    public List<Mine> getCollected(String username) {
        return this.getMine(username,MineTag.Collect);
    }

    @Override
    public List<Mine> getInterestedUser(String username) {
        return this.getMine(username,MineTag.InterestUser);
    }

    private List<Mine> getMine(String username,MineTag tag){
        List<Mine> result=new ArrayList<>();
        for(Mine mine:communityUserDao.getMine(username)){
            if(mine.getTag()==tag){
                result.add(mine);
            }
        }
        return result;
    }
}
