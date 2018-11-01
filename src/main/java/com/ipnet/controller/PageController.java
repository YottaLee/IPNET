package com.ipnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("ipnet")
public class PageController {

    private static String finance = "finance/";

    @RequestMapping("head")
    public String head(){
        return "frame/head";
    }

    @RequestMapping("head2")
    public String head2(){
        return "frame/another_head";
    }

    @RequestMapping("footer")
    public String footer(){
        return "frame/footer";
    }

    @RequestMapping("home")
    public String home(){
        return "home";
    }

    @RequestMapping("detail")
    public String detail(){
        return "detail";
    }

    @RequestMapping("file")
    public String file(){
        return "file";
    }

    @RequestMapping("introduce")
    public String introduce(){
        return "introduce";
    }

    @RequestMapping("search_patent")
    public String search_patent(){
        return "search_product";
    }

    @RequestMapping("frame")
    public String frame(){
        return "frame";
    }

    @RequestMapping("assessment")
    public String assessment(){
        return "assessment/assessment";
    }

    @RequestMapping("assessment_result")
    public String assessment_result(){
        return "assessment/assessment_result";
    }

    @RequestMapping("securities")
    public String securities(){
        return "securities/securities";
    }

    @RequestMapping("community_home")
    public  String community_home(){
        return "community/community_home";
    }

    @RequestMapping("community_detail")
    public String community_detail(){
        return "community/community_detail";
    }

    @RequestMapping("community_publish")
    public String community_publish(){
        return "community/community_publish";
    }

    @RequestMapping("community_search")
    public String community_search(){
        return "community/community_search";
    }

    @RequestMapping("community_interest")
    public String community_interest(){
        return "community/community_interest";
    }

    @RequestMapping("community_new")
    public String community_new(){
        return "community/community_new";
    }

    @RequestMapping("community_person")
    public String community_person(){
        return "community/community_person";
    }

    @RequestMapping("pay")
    public String pay(){
        return "pay";
    }

    @RequestMapping("ownership_trade_contract")
    public String ownership_trade_contract(){
        return "contract/ownership_trade_contract";
    }

    @RequestMapping("proxy_contract")
    public String proxy_contract(){
        return "contract/proxy_contract";
    }

    @RequestMapping("usage_trade_contract")
    public String usage_trade_contract(){
        return "contract/usage_trade_contract";
    }

    @RequestMapping("chain_view")
    public String chain_view(){
        return "block-chain/chain-view";
    }

    /* 登录 注册 */
    @RequestMapping("login")
    public String loginIndex(){
        return "login/index";
    }

    @RequestMapping("login_byEmail")
    public String loginByEmail(){
        return "login/loginByEmail";
    }

    @RequestMapping("register")
    public String register(){
        return "login/register";
    }

    @RequestMapping("register_byEmail")
    public String registerByEmail(){
        return "login/registerByEmail";
    }

    /* 个人中心-电子钱包 */
    @RequestMapping("pc_eWallet")
    public String pcenter_e_wallet(){
        return "pcenter/pcenter_e_wallet";
    }

    /* 个人中心-身份信息/身份认证 */
    @RequestMapping("pc_info")
    public String pcenter_info(){
        return "pcenter/pcenter_info";
    }

    @RequestMapping("pc_info_identify")
    public String pcenter_info_iden2(){
        return "pcenter/pcenter_info_iden2";
    }

    @RequestMapping("pc_info_idenE")
    public String pcenter_info_idenE(){
        return "pcenter/pcenter_info_idenE";
    }

    @RequestMapping("pc_info_idenP")
    public String pcenter_info_idenP(){
        return "pcenter/pcenter_info_idenP";
    }

    @RequestMapping("pc_identifiedE")
    public String pcenter_info_identifiedE(){
        return "pcenter/pcenter_info_identifiedE";
    }

    @RequestMapping("pc_identifiedP")
    public String pcenter_info_identifiedP(){
        return "pcenter/pcenter_info_identifiedP";
    }

    /* 个人中心-安全设置 */
    @RequestMapping("pc_info_security")
    public String pcenter_info_security(){
        return "pcenter/pcenter_info_security";
    }

    @RequestMapping("pc_security_username")
    public String pcenter_info_secu_1(){
        return "pcenter/pcenter_info_secu_1";
    }

    @RequestMapping("pc_security_email")
    public String pcenter_info_secu_2(){
        return "pcenter/pcenter_info_secu_2";
    }

    @RequestMapping("pc_security_phone")
    public String pcenter_info_secu_3(){
        return "pcenter/pcenter_info_secu_3";
    }

    @RequestMapping("pc_security_loginCode")
    public String pcenter_info_secu_4(){
        return "pcenter/pcenter_info_secu_4";
    }

    @RequestMapping("pc_security_payCode")
    public String pcenter_info_secu_5(){
        return "pcenter/pcenter_info_secu_5";
    }

    /* 管理员 */
    @RequestMapping("admin_IPIweight")
    public String admin_IPIweight(){
        return "admin/admin_IPIweight";
    }

    @RequestMapping("admin_IPSETlist")
    public String admin_IPSETlist(){
        return "admin/admin_IPSETlist";
    }

    @RequestMapping("admin_IPSETdetails")
    public String admin_IPSETdetails(){
        return "admin/admin_IPSETdetails";
    }

    @RequestMapping("admin_statisticsCharts")
    public String admin_statisticsCharts(){
        return "admin/admin_statisticsCharts";
    }

    /* 贷款 */
    @RequestMapping("All-loan-check")
    public String All_loan_check(){
        return "finance/All-loan-check";
    }

    @RequestMapping("All-loan-contract")
    public String All_loan_contract(){
        return "finance/All-loan-contract";
    }

    @RequestMapping("Applicant-applicationFinish")
    public String Applicant_applicationFinish(){
        return "finance/Applicant-applicationFinish";
    }

    @RequestMapping("Applicant-checkEvaluation")
    public String Applicant_checkEvaluation(){
        return "finance/Applicant-checkEvaluation";
    }

    @RequestMapping("Applicant-chooseFinish")
    public String Applicant_chooseFinish(){
        return "finance/Applicant-chooseFinish";
    }

    @RequestMapping("Applicant-chooseInsurance2")
    public String Applicant_chooseInsurance2(){
        return "finance/Applicant-chooseInsurance2";
    }

    @RequestMapping("Applicant-evaluation2")
    public String Applicant_evaluation2(){
        return "finance/Applicant-evaluation2";
    }

    @RequestMapping("Applicant-loan2")
    public String Applicant_loan2(){
        return "finance/Applicant-loan2";
    }

    @RequestMapping("Bank-applicationFinish")
    public String Bank_applicationFinish(){
        return "finance/Bank-applicationFinish";
    }

    @RequestMapping("Bank-check2")
    public String Bank_check2(){
        return "finance/Bank-check2";
    }

    @RequestMapping("Bank-insuranceApplication2")
    public String Bank_insuranceApplication2(){
        return "finance/Bank-insuranceApplication2";
    }

    @RequestMapping("Bank-IP-list")
    public String Bank_IP_list(){
        return "finance/Bank-IP-list";
    }

    @RequestMapping("Evaluation-checkBank")
    public String Evaluation_checkBank(){
        return "finance/Evaluation-checkBank";
    }

    @RequestMapping("Evaluation-IP-list")
    public String Evaluation_IP_list(){
        return "finance/Evaluation-IP-list";
    }

    @RequestMapping("Evaluation-report2")
    public String Evaluation_report2(){
        return "finance/Evaluation-report2";
    }

    @RequestMapping("Evaluation-reportFinish")
    public String Evaluation_reportFinish(){
        return "finance/Evaluation-reportFinish";
    }

    @RequestMapping("Insurance-check2")
    public String Insurance_check2(){
        return "finance/Insurance-check2";
    }

    @RequestMapping("Insurance-checkBank")
    public String Insurance_checkBank(){
        return "finance/Insurance-checkBank";
    }

    @RequestMapping("Insurance-checkFinish")
    public String Insurance_checkFinish(){
        return "finance/Insurance-checkFinish";
    }

    @RequestMapping("Insurance-IP-list")
    public String Insurance_IP_list(){
        return "finance/Insurance-IP-list";
    }

    @RequestMapping("IPSET-IP-list")
    public String IPSET_IP_list(){
        return "finance/IPSET-IP-list";
    }

    @RequestMapping("IPSET-list")
    public String IPSET_list(){
        return "finance/IPSET-list";
    }

    @RequestMapping("Permit-Contract")
    public String Permit_Contract(){
        return "finance/Permit-Contract";
    }

    @RequestMapping("Person-IP-list")
    public String Person_IP_list(){
        return "finance/Person-IP-list";
    }

    @RequestMapping("Person-IPSET-list")
    public String Person_IPSET_list(){
        return "finance/Person-IPSET-list";
    }
    
    @RequestMapping("loan_detail")
    public String loan_detail(){
        return "finance/loan_detail";
    }

    @RequestMapping("Applicant-patentEntry")
    public String Applicant_patentEntry(){
        return "finance/Applicant-patentEntry";
    }

    @RequestMapping("Bank-midConfirm")
    public String Bank_midConfirm(){
        return "finance/Bank-midConfirm";
    }

    @RequestMapping("Bank-finalConfirm")
    public String Bank_finalConfirm(){
        return "finance/Bank-finalConfirm";
    }

    @RequestMapping("loading")
    public String Loading(){
        return "loading";
    }

    @RequestMapping("Auction")
    public String Auction(){
        return "auction/Auction";
    }

    @RequestMapping("QRCode")
    public String QRCode(){
        return "QRCode";
    }
}
