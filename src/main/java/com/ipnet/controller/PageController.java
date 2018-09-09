package com.ipnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ipnet")
public class PageController {

    @RequestMapping("community_home")
    public  String community_home(){
        return "community_home";
    }

    @RequestMapping("community_detail")
    public String community_detail(){
        return "community_detail";
    }

    @RequestMapping("community_publish")
    public String community_publish(){
        return "community_publish";
    }

    @RequestMapping("community_search")
    public String community_search(){
        return "community_search";
    }

    @RequestMapping("community_interest")
    public String community_interest(){
        return "community_interest";
    }

    @RequestMapping("community_new")
    public String community_new(){
        return "community_new";
    }

    @RequestMapping("community_person")
    public String community_person(){
        return "community_person";
    }

    /* 登录 注册 */
    @RequestMapping("login")
    public String loginIndex(){
        return "/login/index";
    }
    @RequestMapping("login_byEmail")
    public String loginByEmail(){
        return "/login/loginByEmail";
    }
    @RequestMapping("register")
    public String register(){
        return "/login/register";
    }
    @RequestMapping("register_byEmail")
    public String registerByEmail(){
        return "/login/registerByEmail";
    }

    /* 个人中心-电子钱包 */
    @RequestMapping("pc_eWallet")
    public String pcenter_e_wallet(){
        return "/pcenter/pcenter_e_wallet";
    }
    /* 个人中心-身份信息/身份认证 */
    @RequestMapping("pc_info")
    public String pcenter_info(){
        return "/pcenter/pcenter_info";
    }
    @RequestMapping("pc_info_identify")
    public String pcenter_info_iden2(){
        return "/pcenter/pcenter_info_iden2";
    }
    @RequestMapping("pc_info_idenE")
    public String pcenter_info_idenE(){
        return "/pcenter/pcenter_info_idenE";
    }
    @RequestMapping("pc_info_idenP")
    public String pcenter_info_idenP(){
        return "/pcenter/pcenter_info_idenP";
    }
    @RequestMapping("pc_identifiedE")
    public String pcenter_info_identifiedE(){
        return "/pcenter/pcenter_info_identifiedE";
    }
    @RequestMapping("pc_identifiedP")
    public String pcenter_info_identifiedP(){
        return "/pcenter/pcenter_info_identifiedP";
    }
    /* 个人中心-安全设置 */
    @RequestMapping("pc_info_security")
    public String pcenter_info_security(){
        return "/pcenter/pcenter_info_security";
    }
    @RequestMapping("pc_security_username")
    public String pcenter_info_secu_1(){
        return "/pcenter/pcenter_info_secu_1";
    }
    @RequestMapping("pc_security_email")
    public String pcenter_info_secu_2(){
        return "/pcenter/pcenter_info_secu_2";
    }
    @RequestMapping("pc_security_phone")
    public String pcenter_info_secu_3(){
        return "/pcenter/pcenter_info_secu_3";
    }
    @RequestMapping("pc_security_loginCode")
    public String pcenter_info_secu_4(){
        return "/pcenter/pcenter_info_secu_4";
    }
    @RequestMapping("pc_security_payCode")
    public String pcenter_info_secu_5(){
        return "/pcenter/pcenter_info_secu_5";
    }

    /* 管理员 */
    @RequestMapping("admin_IPIweight")
    public String admin_IPIweight(){
        return "/admin/admin_IPIweight";
    }
    @RequestMapping("admin_IPSETlist")
    public String admin_IPSETlist(){
        return "/admin/admin_IPSETlist";
    }
    @RequestMapping("admin_IPSETdetails")
    public String admin_IPSETdetails(){
        return "/admin/admin_IPSETdetails";
    }
    @RequestMapping("admin_statisticsCharts")
    public String admin_statisticsCharts(){
        return "/admin/admin_statisticsCharts";
    }

}
