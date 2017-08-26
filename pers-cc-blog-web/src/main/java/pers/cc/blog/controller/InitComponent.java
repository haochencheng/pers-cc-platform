package pers.cc.blog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import pers.cc.blog.model.BlogType;
import pers.cc.blog.model.Blogger;
import pers.cc.blog.model.Link;
import pers.cc.blog.service.BlogTypeService;
import pers.cc.blog.service.BloggerService;
import pers.cc.blog.service.LinkService;

@Component
public class InitComponent implements CommandLineRunner,
        ApplicationContextAware, ServletContextAware {

    private ApplicationContext applicationContext;

    private ServletContext application;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("初始化页面公共信息");
        BloggerService bloggerService = (BloggerService) applicationContext
                .getBean("bloggerService");
        // 查询博主信息
        Blogger blogger = bloggerService.find(1);
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);
        // 查询友情链接
        LinkService linkService = (LinkService) applicationContext
                .getBean("linkService");
        List<Link> linkList = linkService.list(null); // 查询所有友情链接
        application.setAttribute("linkList", linkList);
        // 查询博客类别及博客数量
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext
                .getBean("blogTypeService");
        List<BlogType> blogTypeCountList = blogTypeService.countList();
        application.setAttribute("blogTypeCountList", blogTypeCountList);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }

}
