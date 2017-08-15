package pers.cc.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pers.cc.blog.model.Comment;
import pers.cc.blog.repository.CommentRepo;
import pers.cc.blog.service.CommentService;

/**
 * 评论Service实现类
 * 
 * @author Administrator
 *
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    public static final String COMMENT = "commont_";

    @Resource
    private CommentRepo commentRepo;

    @Override
    public List<Comment> list(Map<String, Object> map) {
        return commentRepo.list(map);
    }

    @Override
    @CachePut(value = "comment", key = "#root.target.COMMENT+#comment.id.toString()", unless = "#result eq null")
    public Comment add(Comment comment) {
        return commentRepo.add(comment) == 1 ? comment : null;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return commentRepo.getTotal(map);
    }

    @Override
    @Cacheable(value = "comment", key = "#root.target.COMMENT+#id.toString()", unless = "#result eq null")
    public Comment update(Comment comment) {
        return commentRepo.update(comment) == 1 ? comment : null;
    }

    @Override
    @Cacheable(value = "comment", key = "#root.target.COMMENT+#id.toString()", condition = "#result eq 1")
    public Integer delete(Integer id) {
        return commentRepo.delete(id);
    }

}
