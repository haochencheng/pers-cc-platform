package pers.cc.blog.service.impl;

import org.springframework.stereotype.Service;
import pers.cc.blog.model.Link;
import pers.cc.blog.repository.LinkRepo;
import pers.cc.blog.service.LinkService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 友情链接Service实现类
 * 
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceimpl implements LinkService {

    @Resource
    private LinkRepo linkRepo;

    @Override
    public List<Link> list(Map<String, Object> map) {
        return linkRepo.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return linkRepo.getTotal(map);
    }

    @Override
    public Integer add(Link link) {
        // TODO Auto-generated method stub
        return linkRepo.add(link);
    }

    @Override
    public Integer update(Link link) {
        // TODO Auto-generated method stub
        return linkRepo.update(link);
    }

    @Override
    public Integer delete(Integer id) {
        // TODO Auto-generated method stub
        return linkRepo.delete(id);
    }

}
