package cn.tms.service;

import java.util.List;

import cn.tms.pojo.Column;

/**
 * 栏目service层
 * 
 * @author Administrator
 * @2017年12月2日
 */
public interface ColumnService {
	/**
	 * 查询所有的栏目
	 */
	List<Column> getAllColumns();

}
