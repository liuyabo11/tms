package cn.tms.service;

import java.util.Map;

public interface PrivilegeService {
	/**
	 * 根据角色id 在权限和角色表中 删除角色对应的权限
	 * 
	 * @param rid
	 * @return
	 */
	boolean delPrivilegeByRoleId(Integer rid);

	/**
	 * 在权限和角色表中 新增角色对应的权限 分配权限
	 * 
	 * @param map
	 * @return
	 */
	boolean addDataToRolePrivilege(Map<String, Object> map);

}
