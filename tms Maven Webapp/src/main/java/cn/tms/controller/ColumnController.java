package cn.tms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tms.pojo.Column;
import cn.tms.service.ColumnService;

@Controller
@RequestMapping(value = "/col")
public class ColumnController {
	private Logger logger = Logger.getLogger(ColumnController.class);

	@Resource
	private ColumnService columnService;

	/**
	 * 跳转到columnList.jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showColumnPage")
	public ModelAndView showColumnPage() {
		logger.debug("进入了showColumnPage方法！");
		return new ModelAndView("redirect:/columnList.jsp");
	}

	/**
	 * 获取所有的栏目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllColumnsJson")
	@ResponseBody
	public Object getAllColumnsJson() {
		logger.debug("进入了getAllColumnsJson方法！");
		List<Column> columns = columnService.getAllColumns();
		logger.debug("查询到的所有平级的栏目：columns" + columns);

		// 02.新的容器 保存有父子关系的栏目
		List<Column> rootColumns = new ArrayList<Column>();
		// 03.工具：转成有父子关系的栏目
		for (Column item : columns) {
			Column childMenu = item;
			String pCode = childMenu.getParentcode();
			if (pCode.equals("0")) {
				rootColumns.add(item);
			} else {
				for (Column innerMenu : columns) {
					String code = innerMenu.getSyscode();
					if (code.equals(pCode)) {
						Column parentMenu = innerMenu;
						parentMenu.getChildren().add(childMenu);
						break;
					}
				}
			}
		}
		logger.debug("查询到的所有有层级关系的栏目：rootColumns" + rootColumns);

		return rootColumns;
	}

}
