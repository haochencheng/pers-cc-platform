package pers.platform.core.auth.interceptor;


import com.alibaba.dubbo.rpc.RpcContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.platform.core.auth.exception.ApiException;
import pers.platform.core.auth.service.RecordApiReuestCountService;
import pers.platform.core.log.RpcLogAspect;
import pers.platform.common.base.BaseResult;
import pers.platform.common.utils.common.StringUtil;

import java.lang.annotation.Annotation;
import java.util.Objects;

@Component
@Order(9)
@Aspect
public class ApiInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RpcLogAspect.class);

    @Autowired
    private RecordApiReuestCountService recordApiReuestCountService;

    @Pointcut("execution(* *..service..*.*(..))")
    public void service(){

    }

    @Before("service()")
    public void doInterceptorBeforeService(JoinPoint joinPoint){
        // 是否是提供方side
        boolean providerSide = RpcContext.getContext().isProviderSide();
        Annotation c=joinPoint.getSignature().getDeclaringType().getAnnotation(Service.class);
        if (providerSide&&c==null){
            //记录调用api次数
            recordApiReuestCountService.sendIncrementApiCountMessage("1");
            Object[] objects =joinPoint.getArgs();
            if (objects.length>2&&providerSide){
                //TODO  异常信息统一
                Object apikey = objects[0];
                Object apiSecurity = objects[1];
                if (Objects.isNull(apikey)||Objects.isNull(apiSecurity)){
                    throw  ApiException.BAD_ARGUMENTS;
                }
                if ("".equals(apikey.toString())||"".equals(apiSecurity.toString())){
                    throw  ApiException.BAD_ARGUMENTS;
                }
                for (Object object:objects){
                    //过滤非法字符
                    if (!Objects.isNull(object)&&!StringUtil.filterSpecialChar(object.toString())){
                        throw  ApiException.BAD_ARGUMENTS;
                    }
                }
                //shiro权限验证
                Subject subject= SecurityUtils.getSubject();
                UsernamePasswordToken userNametoken=new UsernamePasswordToken(apikey.toString(),apiSecurity.toString(),false);
                subject.login(userNametoken);
            }
        }

    }

    @AfterThrowing("service()")
    public Object afterServiceThrowing() {
        BaseResult baseResult=new BaseResult();

        return baseResult;
    }

    private boolean isChecked(){
        return true;
    }

}
