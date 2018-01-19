import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by cc on  2018/1/9
 */
@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml","classpath:spring-dubbo.xml"})
@ComponentScan(basePackages = {"pers.platform.common.config","pers.platform.test.web"})
public class PlatFormTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(PlatFormTestApplication.class, args);
    }

}
