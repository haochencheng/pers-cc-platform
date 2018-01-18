package pers.platform.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by cc on  2018/1/9
 */
@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class PlatFormCoreApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(PlatFormCoreApplication.class, args);
    }

}
