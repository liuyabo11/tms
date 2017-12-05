package cn.tms.service;

import java.util.List;

import cn.tms.pojo.Privilege;
import cn.tms.pojo.User;

public interface UserService {
	/**
	 * 登录的方法
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * 根据用户id 获取所有的权限
	 * 
	 * @return
	 */
	public List<Privilege> findAllPrivileges(int userid);

	/**
	 * 查询所有的权限列表
	 * 
	 * @return
	 */
	public List<Privilege> getAllPrivileges();

	/**
	 * 根据角色id查询所有的权限
	 * 
	 * @param parseInt
	 * @return
	 */
	public List<Privilege> findAllPrivilegesByRoleId(int rid);

}
