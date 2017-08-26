package pers.cc.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pers.cc.blog.model.BlogType;
import pers.cc.blog.repository.BlogTypeRepo;
import pers.cc.blog.service.BlogTypeService;

/**
 * 博客类型Service实现类
 * 
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    public static final String BlOGTYPE = "blogType_";

    @Resource
    private BlogTypeRepo blogTypeRepo;

    @Override
    public List<BlogType> countList() {
        return blogTypeRepo.countList();
    }

    @Override
    public BlogType findById(Integer id) {
        return blogTypeRepo.findById(id);
    }

    @Override
    public List<BlogType> list(Map<String, Object> map) {
        return blogTypeRepo.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return blogTypeRepo.getTotal(map);
    }

    @Override
    @CachePut(value = "blogType", key = "#root.target.BlOGTYPE+#blog.id.toString()", unless = "#result eq 0")
    public Integer add(BlogType blogType) {
        return blogTypeRepo.add(blogType);
    }

    @Override
    @CachePut(value = "blogType", key = "#root.target.BlOGTYPE+#blogType.id.toString()", unless = "#result eq 0")
    public Integer update(BlogType blogType) {
        return blogTypeRepo.update(blogType);
    }

    @Override
    @Cacheable(value = "blogType", key = "#root.target.BlOGTYPE+#id.toString()", condition = "result eq 1")
    public Integer delete(Integer id) {
        return blogTypeRepo.delete(id);
    }

}
