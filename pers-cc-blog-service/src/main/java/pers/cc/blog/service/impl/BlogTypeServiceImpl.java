package pers.cc.blog.service.impl;

import org.springframework.stereotype.Service;
import pers.cc.blog.model.BlogType;
import pers.cc.blog.repository.BlogTypeRepo;
import pers.cc.blog.service.BlogTypeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 博客类型Service实现类
 * 
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

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
    public Integer add(BlogType blogType) {
        return blogTypeRepo.add(blogType);
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeRepo.update(blogType);
    }

    @Override
    public Integer delete(Integer id) {
        return blogTypeRepo.delete(id);
    }

}
