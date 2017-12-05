package cn.tms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tms.pojo.Privilege;
import cn.tms.pojo.User;
import cn.tms.service.PrivilegeService;
import cn.tms.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private PrivilegeService privilegeService;

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(User user, HttpSession session) {
		logger.debug("进入了UserController中的login方法！user:" + user);
		if (user != null && user.getUsername() != "") {
			User _user = userService.login(user);
			logger.debug("返回的user：" + _user);
			if (null != _user) {
				session.setAttribute("user", _user);
				return "y";
			}
		}
		return "n";
	}

	/**
	 * 请求main.jsp的方法
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(HttpSession session, Model model) {
		// 获取用户session
		User user = (User) session.getAttribute("user");
		logger.debug("进入了UserController的main方法！用户id为：" + user.getUserid());
		// 01.根据用户id 获取所有的权限 平级的权限
		List<Privilege> privilegeList = userService.findAllPrivileges(user
				.getUserid());
		logger.debug("根据用户id 获取所有的权限列表：list" + privilegeList);

		// 02.新的容器 保存有父子关系的权限
		List<Privilege> rootMenus = new ArrayList<Privilege>();
		// 03.工具：转成有父子关系的权限
		for (Privilege item : privilegeList) {
			Privilege childMenu = item;
			int pid = childMenu.getParent();
			if (pid == 0) {
				rootMenus.add(item);
			} else {
				for (Privilege innerMenu : privilegeList) {
					Integer id = innerMenu.getId();
					if (id == pid) {
						Privilege parentMenu = innerMenu;
						parentMenu.getChildren().add(childMenu);
						break;
					}
				}
			}
		}

		// 04..放入Model 相当于request.setAttribute()
		model.addAttribute("list", rootMenus);
		return new ModelAndView("/main.jsp");
	}

	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllMenusJson")
	@ResponseBody
	public Object getAllMenusJson() {
		logger.debug("进入了getAllMenusJson！！！");

		// 01.获取所有的平级权限
		List<Privilege> privileges = userService.getAllPrivileges();
		logger.debug("进入了getAllMenusJson！！！查询到的所有平级权限集合：" + privileges);
		// 02.新的容器 保存有父子关系的权限
		List<Privilege> privilegeList = new ArrayList<Privilege>();
		// 03.工具：转成有父子关系的权限
		for (Privilege item : privileges) {
			Privilege childMenu = item;
			int pid = childMenu.getParent();
			if (pid == 0) {
				privilegeList.add(item);
			} else {
				for (Privilege innerMenu : privileges) {
					Integer id = innerMenu.getId();
					if (id == pid) {
						Privilege parentMenu = innerMenu;
						parentMenu.getChildren().add(childMenu);
						break;
					}
				}
			}
		}
		logger.debug("进入了getAllMenusJson！！！所有权限，分级的权限：" + privilegeList);
		return privilegeList;
	}

	/**
	 * 根据角色id获取用户所有的权限 以json形式返回
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getPrivilegeJson")
	@ResponseBody
	public Object getPrivilegeJson(String rid) {
		logger.debug("进入了getPrivilegeJson方法！角色id为rid：" + rid);
		// 01.根据用户id 获取所有的权限 平级的权限
		List<Privilege> privilegeList = userService
				.findAllPrivilegesByRoleId(Integer.parseInt(rid));
		logger.debug("根据角色id 获取所有的权限列表：list" + privilegeList);

		return privilegeList;
	}

	/**
	 * 保存权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/savePrivilege")
	@ResponseBody
	public void savePrivilege(String rid, String s) {
		logger.debug("进入了savePrivilege方法！！rid:" + rid + "\t s:" + s);
		// 先将获取的所有的id集合 进行删除 然后再新增 保存
		privilegeService.delPrivilegeByRoleId(Integer.parseInt(rid));
		// 将id拼成的字符串拆分成 数组
		String[] ids = s.split(",");
		for (String id : ids) {
			logger.debug("要新增的权限pid:" + id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rid", Integer.parseInt(rid));
			map.put("pid", Integer.parseInt(id));
			privilegeService.addDataToRolePrivilege(map);

		}
	}
}
