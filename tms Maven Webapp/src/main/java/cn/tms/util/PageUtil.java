package cn.tms.util;

import java.util.ArrayList;
import java.util.List;

import cn.tms.pojo.News;
import cn.tms.pojo.Role;

public class PageUtil {
	private Integer totalCount;// 总记录数 从数据库获取
	private Integer pageSize = 3;// 页大小
	private Integer pageCount;// 总页数
	private Integer pageIndex;// 当前页
	// 封装角色集合
	private List<Role> roleList = new ArrayList<Role>();
	// 封装新闻集合
	private List<News> newsList = new ArrayList<News>();

	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * 在我们得到总记录数的时候 因为我们已经知道了pageSize 所以我们可以求出总页数pageCount
	 * 
	 * @param totalCount
	 *            总记录数
	 * 
	 */
	public void setTotalCount(Integer totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			// 总页数
			this.pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
		}

	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public PageUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public PageUtil(Integer totalCount, Integer pageSize, Integer pageCount,
			Integer pageIndex, List<Role> roleList, List<News> newsList) {
		super();
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.pageIndex = pageIndex;
		this.roleList = roleList;
		this.newsList = newsList;
	}

	@Override
	public String toString() {
		return "PageUtil [totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + ", pageIndex=" + pageIndex
				+ ", roleList=" + roleList + ", newsList=" + newsList + "]";
	}

}
