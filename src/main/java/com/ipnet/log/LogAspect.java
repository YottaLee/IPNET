package com.ipnet.log;

import com.ipnet.dao.LogDao;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogDao logDao;

    //定义切点@Pointcut
    //在注解的位置切入代码
    @Pointcut(value = "@annotation(com.ipnet.log.MyLog)")
    public void logPointCut(){}

    //切面 配置通知
    @AfterReturning("logPointCut()")
    public void saveLog(JoinPoint joinPoint){
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
            log.setOperation(value);
        }
        //获取请求的类名
        String className=joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName=method.getName();
        log.setMethod(className+"."+methodName);
        //获取请求的参数
        Object[] args=joinPoint.getArgs();
        //获取请求的方法以及对应的参数名\
        Map<String, Object> nameAndArgs = null;//获取被切参数名称及参数值
        try {
            Class<?> clazz = Class.forName(className);
            String clazzName = clazz.getName();
            nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (nameAndArgs!=null){
            log.setParam(nameAndArgs.toString());
        }else {
            log.setParam(null);
        }
        //获取当前系统时间
        log.setTime(new Date());
        //获取用户的IP地址
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.setIp(request.getRemoteAddr());
        logDao.save(log);

    }

    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String, Object> map = new HashMap<>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
//        if (attr == null) {
//            // exception
//        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            map.put(attr.variableName(i + pos), args[i]);//paramNames即参数名
        }
        return map;
    }
}
