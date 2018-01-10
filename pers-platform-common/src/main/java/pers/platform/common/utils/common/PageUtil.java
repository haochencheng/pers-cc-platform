package pers.platform.common.utils.common;

/**
 * 分页工具类
 * 
 * @author Administrator
 *
 */
public class PageUtil {

    /**
     * 生成分页代码
     * 
     * @param targetUrl
     *            目标地址
     * @param totalNum
     *            总记录数
     * @param currentPage
     *            当前页
     * @param pageSize
     *            每页大小
     * @return
     */
    public static String genPagination(String targetUrl, long totalNum,
            int currentPage, int pageSize, String param) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
                : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "未查询到数据";
        } else {
            StringBuffer pageCode = new StringBuffer();
            pageCode.append(
                    "<nav style=\"text-align: center;\"><ul class=\"pagination pagination-sm\">");
            if (totalPage == 1) {
                pageCode.append("<li><span th:href=\"@{" + targetUrl
                        + "?page=1}\">jsut one page！</span></li>");
            } else {
                if (currentPage > 1) {
                    pageCode.append("<li><a th:href=\"@{" + targetUrl
                            + "?page=1}\">首页</a></li>");
                    pageCode.append("<li><a th:href=\"@{" + targetUrl + "?page="
                            + (currentPage - 1) + "}\">上一页</a></li>");
                } else {
                    pageCode.append(
                            "<li class=\"disabled\"><span class=\"popover-toggle\" data-toggle=\"popover\" title=\"已经是第一页了\">首页</span></li>");
                    pageCode.append(
                            "<li class=\"disabled\"><span data-toggle=\"popover\" title=\"已经是第一页了\">上一页</span></li>");
                }
                for (int i = currentPage - 2; i <= currentPage + 2; i++) {
                    if (i < 1 || i > totalPage) {
                        continue;
                    }
                    if (i == currentPage) {
                        pageCode.append("<li class=\"active\"><a th:href=\"@{"
                                + targetUrl + "?page=" + i + "}\">" + i
                                + "</a></li>");
                    } else {
                        pageCode.append("<li><a th:href=\"@{" + targetUrl
                                + "?page=" + i + "}\">" + i + "</a></li>");
                    }
                }
                if (currentPage < totalPage) {
                    pageCode.append("<li><a th:href=\"@{" + targetUrl + "?page="
                            + (currentPage + 1) + "}\">下一页</a></li>");
                    pageCode.append("<li><a th:href=\"@{" + targetUrl + "?page="
                            + totalPage + "}\">尾页</a></li>");
                } else {
                    pageCode.append(
                            "<li class=\"disabled\"><span data-toggle=\"popover\" title=\"已经是最后一页了\">下一页</span></li>");
                    pageCode.append(
                            "<li class=\"disabled\"><span data-toggle=\"popover\" title=\"已经是最后一页了\">尾页</span></li>");
                }
            }
            pageCode.append("</ul></nav>");
            return pageCode.toString();
        }
    }

}