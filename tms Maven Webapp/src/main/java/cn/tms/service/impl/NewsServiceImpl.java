package cn.tms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.tms.dao.NewsMapper;
import cn.tms.pojo.News;
import cn.tms.service.NewsService;
import cn.tms.util.PageUtil;

@Service
public class NewsServiceImpl implements NewsService {
	private Logger logger = Logger.getLogger(NewsServiceImpl.class);

	@Resource
	private NewsMapper newsMapper;

	/**
	 * 根据ColumnType类型 分页查询新闻
	 */
	public PageUtil findNewsByColumnType(String columntype, Integer pageIndex,
			Integer pageSize) {
		// 封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", (pageIndex - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("columntype", columntype);

		logger.debug("进入了findNewsByColumnType方法，参数为：pageindex:" + pageIndex
				+ "\t" + "pagesize:" + pageSize + "\t" + "columntype:"
				+ columntype);

		// 调用dao层方法
		List<News> news = newsMapper.findNewsByColumnType(map);
		logger.info("查询到的news：" + news);
		// 创建PageUtil对象
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPageIndex(pageIndex);
		pageUtil.setPageSize(pageSize);
		pageUtil.setTotalCount(newsMapper.getTotalCount(columntype));
		logger.info("总记录数：" + newsMapper.getTotalCount(columntype));
		// 将查询到的新闻集合 封装到pageUtil中
		pageUtil.setNewsList(news);
		return pageUtil;
	}
}
