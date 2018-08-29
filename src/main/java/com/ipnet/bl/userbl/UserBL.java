package com.ipnet.bl.userbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.dao.UserDao;
import com.ipnet.enums.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class UserBL implements UserBLService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private AliService aliService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CommunityUserBLService communityUserBLService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public String getMessageCode(String telephone) {
        //验证改手机号是否已经被注册（查询个人用户列表）
        if(userDao.existsById(telephone)){
            return ResultMessage.Exist.toString();
        }
        return aliService.sendMessageCode(telephone);
    }

    public void sendHtmlMail(String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {

            TemplateEngine templateEngine=new SpringTemplateEngine();

            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("测试邮件");

            //创建邮件正文
            Context context = new Context();
            context.setVariable("id", "1");
//            String emailContent = "<html>\n" +
//                    "<body>\n" +
//                    "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
//                    "</body>\n" +
//                    "</html>";
            String emailContent=templateEngine.process("test", context);

            helper.setText(emailContent, true);
            mailSender.send(message);
            System.out.println("html邮件发送成功");
        } catch (MessagingException e) {
            System.out.println("发送html邮件时发生异常！");
        }
    }

    @Override
    public String sendEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("测试邮件");
        message.setText("这是一封测试邮件");
        try {
            mailSender.send(message);
            return ResultMessage.Success.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.Fail.toString();
        }
    }

    @Override
    public ResultMessage personalRegister(String telephone, String password) {
        //自动为用户生成社区用户的实体
        communityUserBLService.addUser(telephone);
        return null;
    }

    @Override
    public ResultMessage companyRegister(String email,String password) {
        return null;
    }

    @Override
    public ResultMessage login(String username, String password) {
        return null;
    }

    @Override
    public ResultMessage logout(String username) {
        return null;
    }


}
