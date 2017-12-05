package cn.tms.service;

import cn.tms.pojo.User;
import cn.tms.util.PageUtil;

public interface RoleService {
	/**
	 * 根据userid查询用户的角色
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param userid
	 * @return
	 */
	PageUtil findRoleByUserId(Integer pageIndex, Integer pageSize, User user);

}
