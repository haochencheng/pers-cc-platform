package pers.platform.core.auth.interceptor;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pers.platform.common.aspect.RpcLogAspect;
import pers.platform.common.base.BaseResult;
import pers.platform.common.utils.common.StringUtil;

import java.util.Objects;

@Component
@Order(9)
@Aspect
public class ApiInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(RpcLogAspect.class);

    @Pointcut("execution(* *..service..*.*(..))")
    public void service(){

    }

    @Before("service()")
    public Object doInterceptorBeforeService(JoinPoint joinPoint){
        BaseResult baseResult=new BaseResult();
        Object[] objects =joinPoint.getArgs();
        //TODO  异常信息统一
        Object apikey = objects[0];
        Object apiSecurity = objects[1];
        if (Objects.isNull(apikey)||Objects.isNull(apiSecurity)){
           baseResult.setMessage("api权限参数校验失败！");
            return baseResult;
        }
        if ("".equals(apikey.toString())||"".equals(apiSecurity.toString())){
           baseResult.setMessage("api权限参数不可为空！");
            return baseResult;
        }
        for (Object object:objects){
            //过滤非法字符
            if (!Objects.isNull(object)&&StringUtil.filterSpecialChar(object.toString())){
                baseResult.setMessage("参数中含有非法参数！");
                return baseResult;
            }
        }
        //shiro权限验证
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken userNametoken=new UsernamePasswordToken(apikey.toString(),apiSecurity.toString(),false);
        subject.login(userNametoken);
        return baseResult;
    }

}
