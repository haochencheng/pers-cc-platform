package pers.cc.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.cc.blog.config.SpringConfig;

//
//@Controller
//public class Main extends SpringBootServletInitializer {
@Controller
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "forward:/a.jsp";
    }

    // @Override
    // protected SpringApplicationBuilder configure(
    // SpringApplicationBuilder builder) {
    // // 注意这里要指向原先用main方法执行的Application启动类
    // return builder.sources(SpringConfig.class);
    // }
}
