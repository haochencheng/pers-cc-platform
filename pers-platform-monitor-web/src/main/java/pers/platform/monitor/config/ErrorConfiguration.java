package pers.platform.monitor.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 返回错误页面
 * Created by cc on 2017/9/28.
 */
@Configuration
public class ErrorConfiguration {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/400.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
        };
    }

}
