package pers.cc.blog.model;

/**
 * ��ҳModel��
 * 
 * @author
 *
 */
public class PageBean {

    private int page; // 起始页
    private int pageSize; // 每页记录数
    @SuppressWarnings("unused")
    private int start; // 每页开始记录数

    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (page - 1) * pageSize;
    }

}
