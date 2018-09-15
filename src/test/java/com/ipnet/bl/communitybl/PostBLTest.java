package com.ipnet.bl.communitybl;

import com.ipnet.blservice.communityservice.PostBLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PostBLTest {

    PostBLService postBLService;

    public PostBLTest(){
        postBLService=new PostBL();
    }

    @Test
    public void createID() {
        String id=postBLService.createID("jane");
        System.out.println(id);

    }

    @Test
    public void uploadFile() {
    }

    @Test
    public void publishArticle() {
    }

    @Test
    public void edit() {
    }

    @Test
    public void deleteArticle() {
    }

    @Test
    public void remark() {
    }

    @Test
    public void readArticle() {
    }

    @Test
    public void readArticleList() {
    }

    @Test
    public void getAllArticleList() {
    }

    @Test
    public void recommend() {
    }

    @Test
    public void searchArticle() {
    }

    @Test
    public void addInterestNum() {
    }

    @Test
    public void minusInterestNum() {
    }

    @Test
    public void getBriefPostByID() {
    }

    @Test
    public void downLoadFromUrl() {
    }
}