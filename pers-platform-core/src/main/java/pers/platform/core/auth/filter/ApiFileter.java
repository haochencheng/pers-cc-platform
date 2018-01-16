package pers.platform.core.auth.filter;


import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang3.StringUtils;
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

import java.beans.Expression;
import java.util.Objects;

@Component
@Order(6)
@Aspect
public class ApiFileter {


    private static final Logger logger = LoggerFactory.getLogger(RpcLogAspect.class);

    @Pointcut("execution(* *..service..*.*(..))")
    public void service(){

    }

    @Before("service()")
    public BaseResult doFilterBeforeService(JoinPoint joinPoint){
        BaseResult baseResult=new BaseResult();
        Object[] objects =joinPoint.getArgs();
        if (Objects.isNull(objects[0])||Objects.isNull(objects[1])){
           baseResult.setMessage("api权限参数校验失败，！");
        }
        if ("".equals(objects[0])||"".equals(objects[1])){
           baseResult.setMessage("api权限参数不可为空，！");
        }
        return baseResult;
    }

}
