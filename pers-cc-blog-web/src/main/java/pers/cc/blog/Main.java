package pers.cc.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.cc.blog.config.SpringConfig;

@Controller
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "forward:/a.jsp";
    }
}
