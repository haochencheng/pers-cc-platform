package pers.cc.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pers.cc.blog.model.Link;
import pers.cc.blog.repository.LinkRepo;
import pers.cc.blog.service.LinkService;

/**
 * 友情链接Service实现类
 * 
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceimpl implements LinkService {

    public static final String LINK_LIST = "linkList";

    public static final String LINK = "link_";

    @Resource
    private LinkRepo linkRepo;

    @Override
    @Cacheable(value = "link", key = "#root.target.LINK+#root.target.LINK_LIST", unless = "#result eq null")
    public List<Link> list(Map<String, Object> map) {
        return linkRepo.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return linkRepo.getTotal(map);
    }

    @Override
    @CachePut(value = "link", key = "#root.target.LINK+#link.id.toString()", unless = "#result eq 0")
    public Integer add(Link link) {
        return linkRepo.add(link);
    }

    @Override
    @CachePut(value = "link", key = "#root.target.LINK+#link.id.toString()", unless = "#result eq 0")
    public Integer update(Link link) {
        return linkRepo.update(link);
    }

    @Override
    @CacheEvict(value = "link", key = "#root.target.LINK+#id.toString()", condition = "#result eq 1")
    public Integer delete(Integer id) {
        return linkRepo.delete(id);
    }

}
