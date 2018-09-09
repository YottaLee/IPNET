package com.ipnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ipnet")
public class PageController {

    @RequestMapping("community_home")
    public  String community_home(){
        return "/community/community_home";
    }

    @RequestMapping("community_detail")
    public String community_detail(){
        return "/community/community_detail";
    }

    @RequestMapping("community_publish")
    public String community_publish(){
        return "/community/community_publish";
    }

    @RequestMapping("community_search")
    public String community_search(){
        return "/community/community_search";
    }

    @RequestMapping("community_interest")
    public String community_interest(){
        return "/community/community_interest";
    }

    @RequestMapping("community_new")
    public String community_new(){
        return "/community/community_new";
    }

    @RequestMapping("community_person")
    public String community_person(){
        return "/community/community_person";
    }

    @RequestMapping("pay")
    public String pay(){
        return "pay";
    }

}
