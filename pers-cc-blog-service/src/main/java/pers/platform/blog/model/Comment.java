package pers.platform.blog.model;

import java.util.Date;

/**
 * 评论实体
 * 
 * @author Administrator
 *
 */
public class Comment {

    private String id; // 编号
    private String userIp; // 用户IP
    private Blog blog; // 被评论的博客
    private String content; // 评论内容
    private Date commentDate; // 评论日期
    private Integer state; // 审核状态 0 待审核 1 审核通过 2 审核未通过

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
