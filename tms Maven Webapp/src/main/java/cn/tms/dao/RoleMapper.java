package cn.tms.dao;

import java.util.List;
import java.util.Map;

import cn.tms.pojo.Role;
import cn.tms.pojo.User;

public interface RoleMapper {
	/**
	 * 根据userid查询用户的角色+分页
	 * 
	 * @param map
	 * @return
	 */
	List<Role> findRoleByUserId(Map<String, Object> map);

	/**
	 * 根据userid查询用户的角色的个数
	 * 
	 * @param user
	 * @return
	 */
	Integer getTotalCount(User user);

}
