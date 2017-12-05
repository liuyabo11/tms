package cn.tms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tms.service.NewsService;
import cn.tms.util.PageUtil;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
	private Logger logger = Logger.getLogger(NewsController.class);

	@Resource
	private NewsService newsService;

	/**
	 * 根据ColumnType类型 查询新闻
	 * 
	 * @param columntype
	 * @return
	 */
	@RequestMapping(value = "/findNewsByColumn")
	@ResponseBody
	public Object findNewsByColumn(
			@RequestParam(defaultValue = "1", value = "page") Integer pageIndex,
			@RequestParam(defaultValue = "2", value = "rows") Integer pageSize,
			String columntype) {
		logger.debug("进入了findNewsByColumn方法！columntype:" + columntype
				+ "\tpageIndex:" + pageIndex + "\tpageSize:" + pageSize);
		// 调用service方法 进行分页查询
		PageUtil pageUtil = newsService.findNewsByColumnType(columntype,
				pageIndex, pageSize);
		// 创建map集合 封装前台需要的数据结构
		Map<String, Object> map = new HashMap<String, Object>();
		logger.debug("根据ColumnType类型查询到的newsList：" + pageUtil.getNewsList());
		Integer count = pageUtil.getTotalCount();
		map.put("total", count);
		map.put("rows", pageUtil.getNewsList());
		return map;
	}

}
