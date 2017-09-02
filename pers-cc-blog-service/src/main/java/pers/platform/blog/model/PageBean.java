package pers.platform.blog.model;

/**
 * ��ҳModel��
 * 
 * @author
 *
 */
public class PageBean {

    private Integer currentPage; // 当前页
    private Integer pageSize; // 每页记录数
    private Integer totalPages; // 总页数

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public PageBean(Integer currentPage, Integer pageSize) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

}
