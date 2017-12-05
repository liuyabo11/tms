package cn.tms.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.tms.dao.PrivilegeMapper;
import cn.tms.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	private Logger logger = Logger.getLogger(PrivilegeServiceImpl.class);

	@Resource
	private PrivilegeMapper privilegeMapper;

	/**
	 * 根据角色id 在权限和角色表中 删除角色对应的权限
	 * 
	 * @param rid
	 * @return
	 */
	public boolean delPrivilegeByRoleId(Integer rid) {
		int row = privilegeMapper.delPrivilegeByRoleId(rid);
		if (row > 0) {
			logger.debug("进入了delPrivilegeByRoleId方法！删除成功！");
			return true;
		}
		return false;
	}

	/**
	 * 在权限和角色表中 新增角色对应的权限 分配权限
	 * 
	 * @param map
	 * @return
	 */
	public boolean addDataToRolePrivilege(Map<String, Object> map) {
		int row = privilegeMapper.addDataToRolePrivilege(map);
		if (row > 0) {
			logger.debug("进入了addDataToRolePrivilege方法！新增成功！");
			return true;
		}
		return false;
	}

}
