package pers.cc.blog.model;

/**
 * 友情链接实体
 * 
 * @author Administrator
 *
 */
public class Link {

	private Integer id; // 编号
	private String linkName; // 链接名称
	private String linkUrl; // 链接地址
	private Integer orderNo; // 排序

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}

}
