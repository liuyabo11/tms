package cn.tms.pojo;

public class User {
	private Integer userid; // 用户编号
	private String username; // 用户名
	private String userpwd; // 用户密码
	private String usertype; // 用户类型

	public User() {
		super();
	}

	public User(Integer userid, String username, String userpwd, String usertype) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.usertype = usertype;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", userpwd=" + userpwd + ", usertype=" + usertype + "]";
	}

}
