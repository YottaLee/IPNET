package com.ipnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:zhangping
 * @Description:
 * @CreateData: 2018/7/20 23:49
 */

@Controller
public class testController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
