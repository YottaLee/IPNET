package com.ipnet.log;

import com.alibaba.fastjson.JSON;
import com.ipnet.dao.LogDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogDao logDao;

    //定义切点@Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.ipnet.log.MyLog)")
    public void logPointCut(){}

    //切面 配置通知
    @AfterReturning("logPointCut()")
    public void saveLog(JoinPoint joinPoint){
        System.out.println("切面………………………………");

        //保存日志
        Log log=new Log();

        //从切面织入点处通过反射机制获得织入点处的方法
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        //获取切入点所在的方法
        Method method=signature.getMethod();
        //获取操作
        MyLog myLog=method.getAnnotation(MyLog.class);
        if(myLog!=null){
            String value=myLog.value();
            System.out.println("操作value^^^^^^^^^^^^^^^^^^^^"+value);
            log.setOperation(value);
        }
        //获取请求的类名
        String className=joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName=method.getName();
        System.out.println("请求的类名:-------------"+className+"\n"+"请求的方法名："+methodName);
        log.setMethod(className+"."+methodName);
        //获取请求的参数
        Object[] args=joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params= JSON.toJSONString(args);
        log.setParam(params);
        //获取当前系统时间
        log.setTime(new Date());
        //获取用户名
        log.setUsername("暂时不知道怎么实现");
        //获取用户的IP地址
//        HttpServletRequest request=HttpContextU
        log.setIp("还不知道怎么实现");

        logDao.save(log);

    }
}
