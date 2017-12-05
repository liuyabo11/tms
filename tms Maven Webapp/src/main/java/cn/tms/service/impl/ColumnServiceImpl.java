package cn.tms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tms.dao.ColumnMapper;
import cn.tms.pojo.Column;
import cn.tms.service.ColumnService;

@Service
public class ColumnServiceImpl implements ColumnService {
	@Resource
	private ColumnMapper columnMapper;

	/**
	 * 查询所有的栏目
	 */
	public List<Column> getAllColumns() {
		return columnMapper.getAllColumns();
	}
}
