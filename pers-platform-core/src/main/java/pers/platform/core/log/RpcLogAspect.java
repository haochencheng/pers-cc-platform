package pers.platform.core.log;

import com.alibaba.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pers.platform.core.auth.service.RecordApiReuestCountService;
import pers.platform.core.auth.service.SendMessageToQueueService;

/**
 * rpclog接入
 * Created by cc on  2018/1/10
 */
@Aspect
@Order(3)
@Component
public class RpcLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(RpcLogAspect.class);

    @Autowired
    private SendMessageToQueueService sendMessageToQueueService;

    // 开始时间
    private long startTime = 0L;
    // 结束时间
    private long endTime = 0L;


    @Pointcut("execution(* *..service..*.*(..))")
    public void rpcLog(){

    }

    @Before("rpcLog()")
    public void doBeforeInServiceRpc(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }

    @After("rpcLog()")
    public void doAfterInServiceRpc(JoinPoint joinPoint) {
    }

    @Around("rpcLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        // 是否是消费端
        boolean consumerSide = RpcContext.getContext().isConsumerSide();
        // 获取最后一次提供方或调用方IP
        String ip = RpcContext.getContext().getRemoteHost();
        if (consumerSide){
            // 服务url
            String rpcUrl = RpcContext.getContext().getUrl().getParameter("application");
            logger.info("consumerSide={}, ip={}, url={}", consumerSide, ip, rpcUrl);
            //记录日志, TODO rpc日志对象
            Object object=new Object();
            sendMessageToQueueService.send("core","log",object);
        }
        return result;
    }





}
