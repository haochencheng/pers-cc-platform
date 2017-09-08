package pers.platform.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pers.platform.blog.config.SpringConfig;

// extends SpringBootServletInitializer 
@Controller
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "redirect:index.html";
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpringConfig.class);
    }
}
