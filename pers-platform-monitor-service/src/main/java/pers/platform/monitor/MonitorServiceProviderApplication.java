package pers.platform.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by cc on  2018/1/9
 */
@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class MonitorServiceProviderApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MonitorServiceProviderApplication.class, args);
    }

}
