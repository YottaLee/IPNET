package com.ipnet.bl.userbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.PersonalUserDao;
import com.ipnet.entity.CompanyUser;
import com.ipnet.entity.PersonalUser;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.utility.MD5Util;
import com.ipnet.vo.financevo.Evaluator;
import com.ipnet.vo.uservo.CompanyVerify;
import com.ipnet.vo.uservo.EmailRegister;
import com.ipnet.vo.uservo.PersonVerify;
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
    private PersonalUserDao personalUserDao;
    @Autowired
    private CompanyUserDao companyUserDao;
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
        if(personalUserDao.existsById(telephone)){
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
          time--限制时间(之前后端传到前端的)
          hash--哈希值(之前后端传到前端的)
          code--用户填写的验证码
    */
    private ResultMessage verifyCode(Map<String, String> info) {
        String requestHash=info.get("hash");

        String time=info.get("time");
        String code=info.get("code");
        String hash=md5Util.md5Encode(this.key+"@"+time+"@"+code);

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c=Calendar.getInstance();
        String currentTime=sf.format(c.getTime());

        if (time.compareTo(currentTime)>0){
            if(requestHash.equalsIgnoreCase(hash)){
                return ResultMessage.Success;
            }else{//验证码错误
                return ResultMessage.CodeError;
            }
        }else{//验证码超时
            return ResultMessage.Timeout;
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
        ResultMessage resultMessage=this.verifyCode(request);
        if(resultMessage.equals(ResultMessage.Success)){
            String phoneNum=request.get("phoneNum");
            PersonalUser newUser=new PersonalUser();
            newUser.setId(phoneNum);
            newUser.setPassword(request.get("pass"));
            newUser.setTelephone(phoneNum);
            newUser.setRegisterTime(new Date());
            newUser.setVerified(false);
            newUser.setActive(true);

            personalUserDao.save(newUser);
            //自动为用户生成社区用户的实体
            communityUserBLService.addUser(phoneNum);
            return ResultMessage.Success;
        }else{
            return resultMessage;
        }
    }

    @Override
    public ResultMessage personalEmailRegister(EmailRegister register) {
        if(personalUserDao.existsById(register.getUsername())|| companyUserDao.existsById(register.getUsername())){
            //该邮箱已经被注册过
            return ResultMessage.Exist;
        }else{
            //邮箱可用，生成并存储用户实体并设置激活状态为false
            PersonalUser newPersonalUser =new PersonalUser();
            newPersonalUser.setId(register.getUsername());
            newPersonalUser.setPassword(register.getPassword());
            newPersonalUser.setActive(false);

//            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Calendar c=Calendar.getInstance();
//            String currentTime=sf.format(c.getTime());

            newPersonalUser.setRegisterTime(new Date());
            newPersonalUser.setEmail(register.getUsername());

            StringBuilder code= new StringBuilder();
            for(int i=0;i<this.codeLength;i++){
                int random=(int)(Math.random()*10);
                code.append(random);
            }
            String activeCode=md5Util.md5Encode(this.key+register.getUsername()+code.toString());
            newPersonalUser.setActiveCode(activeCode);
            newPersonalUser.setVerified(false);

            if(this.sendEmail(register.getUsername(),activeCode)){
                personalUserDao.save(newPersonalUser);
                communityUserBLService.addUser(register.getUsername());
                return ResultMessage.Success;
            }else{
                return ResultMessage.Fail;
            }

        }
    }

    @Override
    public ResultMessage companyRegister(EmailRegister register) {
        if(personalUserDao.existsById(register.getUsername())|| companyUserDao.existsById(register.getUsername())){
            //该邮箱已经被注册过
            return ResultMessage.Exist;
        }else{
            //邮箱可用，生成并存储用户实体并设置激活状态为false
            CompanyUser newCompanyUser =new CompanyUser();
            newCompanyUser.setId(register.getUsername());
            newCompanyUser.setPassword(register.getPassword());
            newCompanyUser.setActive(false);

            //SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //Calendar c=Calendar.getInstance();
            //String currentTime=sf.format(c.getTime());

            newCompanyUser.setRegisterTime(new Date());
            newCompanyUser.setEmail(register.getUsername());
            newCompanyUser.setRole(register.getRole());

            StringBuilder code= new StringBuilder();
            for(int i=0;i<this.codeLength;i++){
                int random=(int)(Math.random()*10);
                code.append(random);
            }
            String activeCode=md5Util.md5Encode(this.key+register.getUsername()+code.toString());
            newCompanyUser.setActiveCode(activeCode);
            newCompanyUser.setVerified(false);

            if(!this.sendEmail(register.getUsername(),activeCode)){
                return ResultMessage.Fail;
            }else{
                companyUserDao.save(newCompanyUser);
                communityUserBLService.addUser(register.getUsername());
                return ResultMessage.Success;
            }
        }
    }

    @Override
    public ResultMessage checkEmail(String email, String activeCode) {
        Optional<CompanyUser> c_user= companyUserDao.findById(email);
        Optional<PersonalUser> p_user= personalUserDao.findById(email);
        if(c_user.isPresent()){
            CompanyUser user=c_user.get();
            if(user.getActiveCode().equals(activeCode)){
                user.setActive(true);
                companyUserDao.saveAndFlush(user);
                return ResultMessage.Success;
            }
        }else if(p_user.isPresent()){
            PersonalUser personalUser =p_user.get();
            if(personalUser.getActiveCode().equals(activeCode)){
                personalUser.setActive(true);
                personalUserDao.saveAndFlush(personalUser);
                return ResultMessage.Success;
            }
        }
        return ResultMessage.Fail;
    }

    private boolean sendEmail(String toEmail,String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("测试邮件");

        //发送邮件
        String sb = "点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！\n" + "http://localhost:8000/user/checkEmail?email=" +
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
        Optional<PersonalUser> o_user= personalUserDao.findById(telephone);
        if(o_user.isPresent()){
            PersonalUser user=o_user.get();
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
        Optional<CompanyUser> c_user= companyUserDao.findById(email);
        Optional<PersonalUser> p_user= personalUserDao.findById(email);
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
    public Role getUserRole(String userID) {
        Optional<PersonalUser> optionalPersonalUser=personalUserDao.findById(userID);
        if(optionalPersonalUser.isPresent()){
            return Role.PersonalUser;
        }else {
            Optional<CompanyUser> optionalCompanyUser=companyUserDao.findById(userID);
            if(optionalCompanyUser.isPresent()){
                return optionalCompanyUser.get().getRole();
            }
        }
        return null;
    }

    @Override
    public Evaluator getEvaluationName(){
        ArrayList<CompanyUser> evaluators=companyUserDao.getEvaluators();
        if(evaluators==null || evaluators.size()==0){
            return null;
        }else{
            for(CompanyUser eva:evaluators){
//                if(eva.isVerified()){
                    return new Evaluator(eva.getId(),eva.getName());
//                }
            }
            return null;
        }
    }

    @Override
    public boolean personVerify(PersonVerify personVerify) {
        Optional<PersonalUser> person= personalUserDao.findById(personVerify.getId());
        if(person.isPresent()){
            PersonalUser toPersonalUser =person.get();
            toPersonalUser.setIdPhoto(personVerify.getIdPhoto());
            toPersonalUser.setName(personVerify.getName());
            toPersonalUser.setSex(personVerify.getSex());
            toPersonalUser.setTelephone(personVerify.getTelephone());
            toPersonalUser.setDescription(personVerify.getDescription());
            toPersonalUser.setCompany(personVerify.getCompany());
            toPersonalUser.setIndustry(personVerify.getIndustry());
            toPersonalUser.setRegion(personVerify.getRegion());

            //调用系统管理员审核方法
            personalUserDao.saveAndFlush(toPersonalUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean companyVerify(CompanyVerify companyVerify) {
        Optional<CompanyUser> companyOptional= companyUserDao.findById(companyVerify.getId());
        if(companyOptional.isPresent()){
            CompanyUser companyUser =companyOptional.get();
            companyUser.setRepresentative(companyVerify.getRepresentative());
            companyUser.setCreditCode(companyVerify.getCreditCode());
            companyUser.setPersonPhoto(companyVerify.getPersonPhoto());
            companyUser.setTel(companyVerify.getTel());
            companyUser.setType(companyVerify.getType());
            companyUser.setEstablishDate(companyVerify.getEstablishDate());
            companyUser.setAddress(companyVerify.getAddress());
            companyUser.setName(companyVerify.getName());
            companyUser.setFund(companyVerify.getFund());
            companyUser.setBusTerm(companyVerify.getBusTerm());
            companyUser.setStatement(companyVerify.getStatement());
            companyUser.setField(companyVerify.getField());
            companyUser.setBusinessNum(companyVerify.getBusinessNum());
            companyUser.setLicence(companyVerify.getLicence());
            companyUser.setWebsite(companyVerify.getWebsite());

            //调用系统管理员的验证
            companyUserDao.saveAndFlush(companyUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean isVerified(String userID) {
        Optional<PersonalUser> personOptional=personalUserDao.findById(userID);
        if(personOptional.isPresent()){
            return personOptional.get().isVerified();
        }else{
            Optional<CompanyUser> companyOptional= companyUserDao.findById(userID);
            if(companyOptional.isPresent()){
                return companyOptional.get().isVerified();
            }
        }
        return false;
    }

    @Override
    public boolean hasTelephone(String userID) {
        return false;
    }

    @Override
    public String getImageUrl(String username) {
        String image=null;
        Optional<PersonalUser> person= personalUserDao.findById(username);
        Optional<CompanyUser> company= companyUserDao.findById(username);
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

    @Override
    public String getXingMing(String userID){
        Optional<PersonalUser> person= personalUserDao.findById(userID);
        Optional<CompanyUser> company= companyUserDao.findById(userID);
        if(person.isPresent()){
            return person.get().getName();
        }else if(company.isPresent()){
            return company.get().getName();
        }
        return null;
    }

    @Override
    public String getCompanyId(String name) {
        CompanyUser companyUser=companyUserDao.findCompanyUserByName(name);
        if(companyUser==null){
            return null;
        }else{
            return companyUser.getId();
        }
    }

    /**
     * 最近六个月的IP成员数
     * @return 每个月的数量
     */
    @Override
    public List<Integer> getMemberSum() {
        Calendar c=Calendar.getInstance();

        Integer[] temp=new Integer[6];
        temp[5]=personalUserDao.count(new Date());
        for(int i=0;i<5;i++){
            c.add(Calendar.MONTH,-1);
            Date date=c.getTime();
            temp[4-i]=personalUserDao.count(date);
        }

        Calendar c2=Calendar.getInstance();
        temp[5]=companyUserDao.countUser(new Date())+temp[5];
        for(int i=0;i<5;i++){
            c2.add(Calendar.MONTH,-1);
            Date d=c2.getTime();
            temp[4-i]=companyUserDao.countUser(d)+temp[4-i];
        }
        return Arrays.asList(temp);
    }

    /**
     * 最近六个月的IPNet总用户数
     * @return 每个月的数量
     */
    @Override
    public List<Integer> getUserSum() {
        Integer[] temp=new Integer[6];
        temp[5]=personalUserDao.count(new Date());
        Calendar c=Calendar.getInstance();
        for(int i=0;i<5;i++){
            c.add(Calendar.MONTH,-1);
            Date date=c.getTime();
            temp[4-i]=personalUserDao.count(date);
        }

        Calendar c2=Calendar.getInstance();
        temp[5]=companyUserDao.countAll(new Date())+temp[5];
        for(int i=0;i<5;i++){
            c2.add(Calendar.MONTH,-1);
            Date d=c2.getTime();
            temp[4-i]=companyUserDao.countAll(d)+temp[4-i];
        }
        return Arrays.asList(temp);
    }


}
