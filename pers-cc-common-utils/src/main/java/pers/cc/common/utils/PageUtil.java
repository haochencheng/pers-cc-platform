package pers.cc.common.utils;


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
	public static String genPagination(String targetUrl, long totalNum, int currentPage, int pageSize, String param) {
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "未查询到数据";
		}else{
			StringBuffer pageCode = new StringBuffer();
			if (totalPage == 1) {
				pageCode.append(
						"<li><span href='" + targetUrl + "?page=1&" + param + "'>jsut one page！</span></li>");
			} else {
				if (currentPage > 1) {
					pageCode.append("<li><a href='" + targetUrl + "?page=1&" + param + "'>首页</a></li>");
					pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "&" + param
							+ "'>上一页</a></li>");
				} else {
					pageCode.append(
							"<li class='disabled'><span class='popover-toggle' data-toggle='popover' title='已经是第一页了'>首页</span></li>");
					pageCode.append("<li class='disabled'><span data-toggle='popover' title='已经是第一页了'>上一页</span></li>");
				}
				for (int i = currentPage - 2; i <= currentPage + 2; i++) {
					if (i < 1 || i > totalPage) {
						continue;
					}
					if (i == currentPage) {
						pageCode.append("<li class='active'><a href='" + targetUrl + "?page=" + i + "&" + param + "'>"
								+ i + "</a></li>");
					} else {
						pageCode.append(
								"<li><a href='" + targetUrl + "?page=" + i + "&" + param + "'>" + i + "</a></li>");
					}
				}
				if (currentPage < totalPage) {
					pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "&" + param
							+ "'>下一页</a></li>");
					pageCode.append("<li><a href='" + targetUrl + "?page=" + totalPage + "&" + param + "'>尾页</a></li>");
				} else {
					pageCode.append(
							"<li class='disabled'><span data-toggle='popover' title='已经是最后一页了'>下一页</span></li>");
					pageCode.append(
							"<li class='disabled'><span data-toggle='popover' title='已经是最后一页了'>尾页</span></li>");
				}
			}
			return pageCode.toString();
		}
	}
	

	
	
}