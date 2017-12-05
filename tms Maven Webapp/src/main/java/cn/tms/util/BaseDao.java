package cn.tms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 操作数据库的基类--静态类 因为每个线程都需要单独的connection，而不是大家共用一个connection 静态非全局唯一 单例全局唯一
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
	// 四要素
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/tms";
	private final static String username = "root";
	private final static String pwd = "666888";

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			Class.forName(driver);
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection(url, username, pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭流 先开的后关
	 */
	public void closeResource() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 03.查询的方法
	public ResultSet executeQuery(String sql, Object... paras) {
		try {
			// 01.获取连接
			con = getConnection();
			// 02.获取PreparedStatement
			ps = con.prepareStatement(sql);
			// 03.入参
			if (paras.length > 0) {
				for (int i = 0; i < paras.length; i++) {
					ps.setObject(i + 1, paras[i]);
				}
			}
			// 04.执行
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	// 04.增删改的方法
	public int executeUpdate(String sql, Object... paras) {
		int count = 0;
		try {
			// 01.获取连接
			con = getConnection();
			// 02.获取PreparedStatement
			ps = con.prepareStatement(sql);
			// 03.入参
			if (paras.length > 0) {
				for (int i = 0; i < paras.length; i++) {
					ps.setObject(i + 1, paras[i]);
				}
			}
			// 04.执行
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
