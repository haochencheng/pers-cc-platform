package pers.platform.monitor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cc on 2017/9/28.
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    /*
     * 请求直接跳转页面 跳转页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index"); // index页面
        registry.addViewController("/user/register.html")
                .setViewName("/user/register"); // 注册页面
        registry.addViewController("/user/forgetPassword.html")
                .setViewName("/user/forgetPassword");
        registry.addViewController("/404.html").setViewName("/error/404");
        registry.addViewController("/500.html").setViewName("/error/500");
        registry.addViewController("/400.html").setViewName("/error/400");
        super.addViewControllers(registry);
    }
}
