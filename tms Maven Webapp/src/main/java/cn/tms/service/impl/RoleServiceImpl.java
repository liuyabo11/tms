package cn.tms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.tms.dao.RoleMapper;
import cn.tms.pojo.Role;
import cn.tms.pojo.User;
import cn.tms.service.RoleService;
import cn.tms.util.PageUtil;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;

	private Logger logger = Logger.getLogger(RoleServiceImpl.class);

	/**
	 * 根据userid查询用户的角色+分页
	 */
	public PageUtil findRoleByUserId(Integer pageIndex, Integer pageSize,
			User user) {
		// 封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", (pageIndex - 1) * pageSize);
		map.put("pageSize", pageSize);
		if (user != null) {
			map.put("userid", user.getUserid());
		}
		logger.debug("进入了findRoleByUserId方法，参数为：pageindex:" + pageIndex + "\t"
				+ "pagesize:" + pageSize + "\t" + "userid:" + user.getUserid());
		// 调用dao层方法
		List<Role> roleList = roleMapper.findRoleByUserId(map);
		logger.info("查询到的rolelist：" + roleList);
		// 创建PageUtil对象
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPageIndex(pageIndex);
		pageUtil.setPageSize(pageSize);
		pageUtil.setTotalCount(roleMapper.getTotalCount(user));
		logger.info("总记录数：" + roleMapper.getTotalCount(user));
		pageUtil.setRoleList(roleList);
		return pageUtil;
	}

}
