package pers.platform.common.config;

import org.aopalliance.intercept.Joinpoint;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.reger.dubbo.rpc.filter.ConsumerFilter;

@Configuration
public class ConsumerFilterConfig {

    private static final Logger log = LoggerFactory.getLogger(ConsumerFilterConfig.class);

    @Bean
    public ConsumerFilter consumerFilter() {
        return joinPoint -> {
            log.info("调用接口 ------>> {}.{}", joinPoint.getInterface(),joinPoint.getMethodName());
            return joinPoint.proceed();
        };
    }

}
