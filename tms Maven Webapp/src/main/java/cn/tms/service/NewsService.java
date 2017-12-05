package cn.tms.service;

import cn.tms.util.PageUtil;

public interface NewsService {
	/**
	 * 根据ColumnType类型 分页查询新闻
	 * 
	 * @param cid
	 * @return
	 */

	PageUtil findNewsByColumnType(String cid, Integer pageIndex,
			Integer pageSize);

}
