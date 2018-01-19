package pers.platform.core;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by cc on  2018/1/9
 */
@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
@EntityScan(basePackages={"pers.platform.core.**.model","pers.platform.common.model"})
@ComponentScan(basePackages = {"pers.platform.common.config","pers.platform.core"})
@DubboComponentScan(basePackages = {"pers.platform.core.**.service"})
public class PlatFormCoreApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(PlatFormCoreApplication.class, args);
    }

}
