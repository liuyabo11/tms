package cn.tms.dao;

import java.util.List;

import cn.tms.pojo.Column;

public interface ColumnMapper {
	/**
	 * 查询所有的栏目
	 */
	List<Column> getAllColumns();
}
