package cn.tms.dao;

import java.util.List;
import java.util.Map;

import cn.tms.pojo.News;

public interface NewsMapper {

	/**
	 * 根据ColumnType类型 查询新闻
	 * 
	 * @param cid
	 * @return
	 */
	List<News> findNewsByColumnType(Map<String, Object> map);

	/**
	 * 根据columntype查询总记录数
	 * 
	 * @param columntype
	 * @return
	 */
	Integer getTotalCount(String columntype);
}
