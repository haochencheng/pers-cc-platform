package pers.platform.blog.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pers.platform.blog.Application;
import pers.platform.blog.config.SpringConfig;
import pers.platform.blog.model.Blog;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, SpringConfig.class })
public class BlogServiceTest {

    @Resource
    private BlogService blogService;

    @Test
    public void test() {
        Blog blog = blogService.findById("8");
        Blog blog2 = blogService.getNextBlog(blog.getReleaseDate());
        System.out.println(blog2.getId());
    }

}
