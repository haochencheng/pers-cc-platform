package pers.cc.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import pers.cc.blog.model.Blogger;
import pers.cc.blog.repository.BloggerRepo;
import pers.cc.blog.service.BloggerService;

/**
 * 博主实现类
 * 
 * @author Administrator
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    private BloggerRepo bloggerRepo;

    /*

    @param username
     * @return
     */
    @Override
    public Blogger getByUserName(String userName) {
        return bloggerRepo.getByUserName(userName);
    }

    @Override
    public Blogger find() {
        return bloggerRepo.find();
    }

    /**
     * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
     * @param person
     * @return
     */
    @Override
    @CachePut(value = "blogger", key = "#root.targetClass+#id", condition = "#result eq 1")
    public Integer update(Blogger blogger) {
        return bloggerRepo.update(blogger);
    }

}
