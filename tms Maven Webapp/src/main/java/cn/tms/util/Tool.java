package cn.tms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;

/**
 * 工具类 ：将指定格式的文本文件 转换成数据库中的表数据
 */
public class Tool {
	public static void main(String[] args) {
		// 批量插入数据的方式
		BaseDao dao = new BaseDao();
		// 读取一个文本文件
		try {
			String encoding = "GBK"; // 简体中文 utf-8
			File file = new File("f:\\file.txt");
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int i = 4;
				String syscode = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String[] types = lineTxt.split(">");

					// 有父级节点
					if (types[0] != null && !types[0].equals("")) {
						dao.executeUpdate("INSERT INTO `cm_column` VALUES (UUID(),"
								+ i
								+ " , '"
								+ types[0]
								+ "', null, '0', '0', null)");
						i++;
					}

					ResultSet rs = dao.executeQuery(
							"select syscode from cm_column where columnname=?",
							types[0]);
					if (rs.next()) {
						syscode = rs.getString("syscode");
					}
					dao.executeUpdate("INSERT INTO `cm_column` VALUES (UUID(),"
							+ i + " , '" + types[1] + "', null, '" + syscode
							+ "', '0', null)");
					i++;
					rs.close();

				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
