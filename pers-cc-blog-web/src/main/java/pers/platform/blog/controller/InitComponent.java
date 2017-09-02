package pers.platform.blog.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import pers.platform.blog.config.SpringConfig;
import pers.platform.blog.model.Blog;
import pers.platform.blog.model.BlogType;
import pers.platform.blog.model.Blogger;
import pers.platform.blog.model.Link;
import pers.platform.blog.service.BlogService;
import pers.platform.blog.service.BlogTypeService;
import pers.platform.blog.service.BloggerService;
import pers.platform.blog.service.LinkService;

@Controller
@AutoConfigureAfter(SpringConfig.class)
public class InitComponent
        implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /*
     * 博客和评论放入redis缓存. 博客分类,博主信息,友情链接公共页面放入application缓存 (non-Javadoc)
     * 
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
     * ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent ServletContextEvent) {
        ServletContext application = ServletContextEvent.getServletContext();
        BloggerService bloggerService = (BloggerService) applicationContext
                .getBean("bloggerService");
        Blogger blogger = bloggerService.find("1");
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);
        LinkService linkService = (LinkService) applicationContext
                .getBean("linkService");
        List<Link> linkList = linkService.list(null); // 查询所有友情链接
        application.setAttribute("linkList", linkList);
        // 查询博客类别及博客数量
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext
                .getBean("blogTypeService");
        List<BlogType> blogTypeCountList = blogTypeService.countList();
        application.setAttribute("blogTypeCountList", blogTypeCountList);
        // 根据日期分组查询博客
        BlogService blogService = (BlogService) applicationContext
                .getBean("blogService");
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute("blogCountList", blogCountList);
        System.out.println("初始化公共信息完成!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SecurityUtils.getSubject().logout();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        InitComponent.applicationContext = applicationContext;
    }

}
