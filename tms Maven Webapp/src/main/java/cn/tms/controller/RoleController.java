package cn.tms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tms.pojo.User;
import cn.tms.service.RoleService;
import cn.tms.util.PageUtil;

@Controller
@RequestMapping("/role")
public class RoleController {
	private Logger logger = Logger.getLogger(RoleController.class);

	@Resource
	private RoleService roleService;

	@RequestMapping("/showRoleList")
	public ModelAndView roleList() {
		return new ModelAndView("/roleList.jsp");
	}

	/**
	 * 查询用户角色 并分页
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@RequestMapping("/selectRole")
	@ResponseBody
	public Object selectRole(
			@RequestParam(defaultValue = "1", value = "page") Integer pageIndex,
			@RequestParam(defaultValue = "2", value = "rows") Integer pageSize,
			HttpSession session) {
		logger.debug("进入了selectRole====>pageIndex:" + pageIndex + "pageSize:"
				+ pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		PageUtil pageUtil = roleService.findRoleByUserId(pageIndex, pageSize,
				user);
		logger.debug("查询到的roleList:" + pageUtil.getRoleList());
		Integer count = pageUtil.getTotalCount();
		map.put("total", count);
		map.put("rows", pageUtil.getRoleList());
		return map;
	}

}
