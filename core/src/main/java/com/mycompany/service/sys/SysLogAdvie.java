package com.mycompany.service.sys;

import com.mycompany.model.sys.SysLog;
import com.mycompany.model.sys.SysUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by liaoxiang on 2016/6/15.
 */
//@Aspect
public class SysLogAdvie  {

    private Log log = LogFactory.getLog(SysLogAdvie.class);
    @Autowired
    SysLogManager sysLogManager;

    /**
            * 必须为final String类型的,注解里要使用的变量只能是静态常量类型的
    */
    public static final String EDP = "execution(* *..service.impl.GenericManagerImpl.save*(..))";

    @Before(EDP)    //spring中Before通知
    public void logBefore() {
        System.out.println("logBefore:现在时间是:"+new Date());
    }

    @After(EDP)    //spring中After通知
    public void logAfter() {
        System.out.println("logAfter:现在时间是:"+new Date());
    }

    @Around(EDP)   //spring中Around通知
    public Object logAround(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        if(!(args[0] instanceof SysLog)){
            try {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if(auth==null) return null;
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                if(request==null) return null;
                SysLog sysLog = new SysLog();
                sysLog.setUrl(request.getRequestURI());
                sysLog.setIp(request.getRemoteAddr());
                sysLog.setUsername(auth.getName());
                sysLog.setUserId(((SysUser) auth.getPrincipal()).getId());
                sysLog.setRole(auth.getAuthorities().toString());
                sysLogManager.save(sysLog);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        System.out.println("logAround开始:现在时间是:"+new Date()); //方法执行前的代理处理

        Object obj = null;
        try {
            obj = joinPoint.proceed(args);

        } catch (Throwable e) {
            e.printStackTrace();
        }


        System.out.println("logAround结束:现在时间是:"+new Date());  //方法执行后的代理处理
        return obj;
    }

}
