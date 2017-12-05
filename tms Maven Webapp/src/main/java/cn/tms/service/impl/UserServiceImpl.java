package cn.tms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.tms.dao.UserMapper;
import cn.tms.pojo.Privilege;
import cn.tms.pojo.User;
import cn.tms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Resource
	private UserMapper userMapper;

	/**
	 * 用户登录
	 */
	public User login(User user) {
		logger.debug("进入了UserServiceImpl中的login方法：user:" + user);
		return userMapper.login(user);
	}

	/**
	 * 根据用户id获取所有的 权限
	 */
	public List<Privilege> findAllPrivileges(int userid) {

		return userMapper.findAllPrivileges(userid);
	}

	/**
	 * 获取所有的权限
	 */
	public List<Privilege> getAllPrivileges() {
		return userMapper.getAllPrivileges();
	}

	/**
	 * 根据角色id查询所有的权限
	 * 
	 */
	public List<Privilege> findAllPrivilegesByRoleId(int rid) {
		return userMapper.findAllPrivilegesByRoleId(rid);
	}

}
