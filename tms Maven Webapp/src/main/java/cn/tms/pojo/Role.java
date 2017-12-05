package cn.tms.pojo;

public class Role {
	private Integer rid;// 编号
	private String rolename;// 角色名称

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer rid, String rolename) {
		super();
		this.rid = rid;
		this.rolename = rolename;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rolename=" + rolename + "]";
	}

}
