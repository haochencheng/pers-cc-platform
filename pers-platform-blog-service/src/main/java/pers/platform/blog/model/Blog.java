package pers.platform.blog.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 博客实体
 * 
 * @author Administrator
 *
 */
public class Blog {

    private String id; // 编号
    private String title; // 博客标题
    private String summary; // 摘要
    private Date releaseDate; // 发布日期
    private Integer clickHit; // 查看次数
    private Integer replyHit; // 回复次数
    private String content; // 博客内容
    private String contentNoTag; // 博客内容五网页标签，lucene分词用到

    private BlogType blogType; // 博客类型

    private String keyWord; // 关键字 空格隔开
    private Integer blogCount; // 博客数量 非博客实际属性 主要是根据日期归档查询
    private String releaseDateStr; // 发布日期的字符串 只取年和月

    private List<String> imageList = new LinkedList<>(); // 主要用于列表展示的缩略图

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

}
