package pers.cc.blog.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import pers.cc.blog.config.SpringConfig;
import pers.cc.blog.model.BlogType;
import pers.cc.blog.model.Blogger;
import pers.cc.blog.model.Link;
import pers.cc.blog.service.BlogTypeService;
import pers.cc.blog.service.BloggerService;
import pers.cc.blog.service.LinkService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

@Controller
@AutoConfigureAfter(SpringConfig.class)
public class InitComponent
        implements ServletContextListener, ApplicationContextAware {

    @Autowired
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
        Blogger blogger = bloggerService.find();
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
        // BlogService blogService = (BlogService) applicationContext
        // .getBean("blogService");
        // List<Blog> blogCountList = blogService.countList();
        // System.out.println(blogCountList.size());
        // application.setAttribute("blogCountList", blogCountList); //

    }

    @Override
    public void contextDestroyed(ServletContextEvent ServletContextEvent) {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        InitComponent.applicationContext = applicationContext;
    }

}
