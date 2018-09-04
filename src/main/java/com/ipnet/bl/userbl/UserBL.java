package com.ipnet.bl.userbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.dao.CompanyDao;
import com.ipnet.dao.PersonalDao;
import com.ipnet.entity.Company;
import com.ipnet.entity.Person;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.MD5Util;
import com.ipnet.vo.uservo.EmailRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserBL implements UserBLService{

    @Autowired
    private PersonalDao personalDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private AliService aliService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CommunityUserBLService communityUserBLService;
    @Autowired
    private MD5Util md5Util;

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${constant.codeKey}")
    private String key;
    @Value("${constant.codeLength}")
    private int codeLength;

    @Override
    public Map<String,String> getMessageCode(String telephone) {
        //验证改手机号是否已经被注册（查询个人用户列表）
        if(personalDao.existsById(telephone)){
            return null;
        }else{
            //6位随机数作为验证码
            String randomNum=aliService.sendMessageCode(telephone);
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c=Calendar.getInstance();
            c.add(Calendar.MINUTE,5);
            String limitTime=sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
            String hash=md5Util.md5Encode(this.key + "@" + limitTime + "@" + randomNum);//一段简单的hash值
            Map<String,String> resultMap = new HashMap<>();
            resultMap.put("hash", hash);
            resultMap.put("time", limitTime);
            return resultMap; //将hash值和tamp时间返回给前端
        }
    }

    /*
    参数：key--value
          phoneNum--号码
          pass--密码
          time--限制时间(之前后端传到前端的)
          hash--哈希值(之前后端传到前端的)
          code--用户填写的验证码
    */
    @Override
    public ResultMessage registerByPhone(Map<String,String> request) {
        String requestHash=request.get("hash");
        String phoneNum=request.get("phoneNum");

        String time=request.get("time");
        String code=request.get("code");
        String hash=md5Util.md5Encode(this.key+"@"+time+"@"+code);

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c=Calendar.getInstance();
        String currentTime=sf.format(c.getTime());

        if (time.compareTo(currentTime)>0){
            if(requestHash.equalsIgnoreCase(hash)){
                Person newUser=new Person();
                newUser.setId(phoneNum);
                newUser.setPassword(request.get("pass"));
                newUser.setTelephone(phoneNum);
                newUser.setRegisterTime(currentTime);
                //自动为用户生成社区用户的实体
                communityUserBLService.addUser(phoneNum);
                return ResultMessage.Success;
            }else{//验证码错误
                return ResultMessage.CodeError;
            }
        }else{//验证码超时
            return ResultMessage.Timeout;
        }
    }

    @Override
    public ResultMessage personalEmailRegister(EmailRegister register) {
        if(personalDao.existsById(register.getUsername())||companyDao.existsById(register.getUsername())){
            //该邮箱已经被注册过
            return ResultMessage.Exist;
        }else{
            //邮箱可用，生成并存储用户实体并设置激活状态为false
            Person newPerson=new Person();
            newPerson.setId(register.getUsername());
            newPerson.setPassword(register.getPassword());
            newPerson.setActive(false);

            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c=Calendar.getInstance();
            String currentTime=sf.format(c.getTime());

            newPerson.setRegisterTime(currentTime);
            newPerson.setEmail(register.getUsername());

            StringBuilder code= new StringBuilder();
            for(int i=0;i<this.codeLength;i++){
                int random=(int)(Math.random()*10);
                code.append(random);
            }
            String activeCode=md5Util.md5Encode(this.key+register.getUsername()+code.toString());
            newPerson.setActiveCode(activeCode);

            if(this.sendEmail(register.getUsername(),activeCode)){
                personalDao.save(newPerson);
                communityUserBLService.addUser(register.getUsername());
                return ResultMessage.Success;
            }else{
                return ResultMessage.Fail;
            }

        }
    }

    @Override
    public ResultMessage companyRegister(EmailRegister register) {
        if(personalDao.existsById(register.getUsername())||companyDao.existsById(register.getUsername())){
            //该邮箱已经被注册过
            return ResultMessage.Exist;
        }else{
            //邮箱可用，生成并存储用户实体并设置激活状态为false
            Company newCompany=new Company();
            newCompany.setId(register.getUsername());
            newCompany.setPassword(register.getPassword());
            newCompany.setActive(false);

            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c=Calendar.getInstance();
            String currentTime=sf.format(c.getTime());

            newCompany.setRegisterTime(currentTime);
            newCompany.setEmail(register.getUsername());
            newCompany.setRole(register.getRole());

            StringBuilder code= new StringBuilder();
            for(int i=0;i<this.codeLength;i++){
                int random=(int)(Math.random()*10);
                code.append(random);
            }
            String activeCode=md5Util.md5Encode(this.key+register.getUsername()+code.toString());
            newCompany.setActiveCode(activeCode);

            if(!this.sendEmail(register.getUsername(),activeCode)){
                return ResultMessage.Fail;
            }else{
                companyDao.save(newCompany);
                communityUserBLService.addUser(register.getUsername());
                return ResultMessage.Success;
            }
        }
    }

    @Override
    public ResultMessage checkEmail(String email, String activeCode) {
        Optional<Company> c_user=companyDao.findById(email);
        Optional<Person> p_user=personalDao.findById(email);
        if(c_user.isPresent()){
            Company user=c_user.get();
            if(user.getActiveCode().equals(activeCode)){
                user.setActive(true);
                companyDao.saveAndFlush(user);
                return ResultMessage.Success;
            }
        }else if(p_user.isPresent()){
            Person person=p_user.get();
            if(person.getActiveCode().equals(activeCode)){
                person.setActive(true);
                personalDao.saveAndFlush(person);
                return ResultMessage.Success;
            }
        }
        return ResultMessage.Fail;
    }

    /**private boolean sendEmail2(String toEmail,String code){
        MimeMessage message = mailSender.createMimeMessage();
        String register_link = "http://localhost:8000/user/email=" +toEmail+ "/code=" +code;
        //创建邮件正文
        Context context = new Context();
        context.setVariable("register_link", register_link);
        TemplateEngine templateEngine=new SpringTemplateEngine();
        String emailContent = templateEngine.process("UserRegisterTemplate", context);
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Ipnet注册验证");
            helper.setText(emailContent, true);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }*/

    private boolean sendEmail(String toEmail,String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("测试邮件");

        //sb.append("</a>");

        //发送邮件
        String sb = "点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！\n" + "http://localhost:8000/user/register?email=" +
                toEmail +
                "&code=" +
                code;
        message.setText(sb);
        try {
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultMessage loginPhone(String telephone, String password) {
        Optional<Person> o_user= personalDao.findById(telephone);
        if(o_user.isPresent()){
            Person user=o_user.get();
            if(user.getPassword().equals(password)){
                return ResultMessage.PersonLogin;
            }else{//密码错误
                return ResultMessage.PassError;
            }
        }else{//用户不存在
            return ResultMessage.NoUser;
        }
    }

    @Override
    public ResultMessage loginEmail(String email, String password) {
        Optional<Company> c_user= companyDao.findById(email);
        Optional<Person> p_user= personalDao.findById(email);
        if(c_user.isPresent()){//该用户是企业用户
            if(!c_user.get().isActive()){
                return ResultMessage.NotActive;
            }
            if(c_user.get().getPassword().equals(password)){
                switch (c_user.get().getRole()){
                    case Evaluator:
                        return ResultMessage.EvaluatorLogin;
                    case Financial:
                        return ResultMessage.FinancialLogin;
                    case Insurance:
                        return ResultMessage.InsuranceLogin;
                    case CompanyUser:
                        return ResultMessage.CompanyLogin;
                    default:
                        return null;
                }
            }else {
                return ResultMessage.PassError;//密码错误
            }
        }else {//该用户可能是个人用户
            if(p_user.isPresent()){
                if(!p_user.get().isActive()){
                    return ResultMessage.NotActive;
                }
                if(p_user.get().getPassword().equals(password)){
                    return ResultMessage.PersonLogin;
                }else{
                    return ResultMessage.PassError;
                }
            }else{
                return ResultMessage.NoUser;//该用户既不是企业用户，也不是个人用户
            }
        }
    }

    @Override
    public String getImageUrl(String username) {
        String image=null;
        Optional<Person> person=personalDao.findById(username);
        Optional<Company> company=companyDao.findById(username);
        if(person.isPresent()){
            image=person.get().getImage();
        }else if(company.isPresent()){
            image=company.get().getImage();
        }
        if(image==null || image.equals("")){
            return "https://ipnet10.oss-cn-beijing.aliyuncs.com/logo.jpg";
        }
        return image;
    }


}
