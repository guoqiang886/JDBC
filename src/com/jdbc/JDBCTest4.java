package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * JDBC，事物练习题，删除student表中的前10条数据
 */
public class JDBCTest4 {
	public static void main(String[] args) {
		Connection c = null;
		PreparedStatement ps = null;
		Scanner sc = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8", 
					"root", 
					"***************");
			
			// 关闭事物自动提交
			c.setAutoCommit(false);
			
			// 查询表中的前10条数据
			String selectSql = "select id from student limit 10";
			ps = c.prepareStatement(selectSql);
			
			// 找到前10条数据的id,删除前给出提示信息
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("正在试图删除id=" + rs.getInt("id") + "的数据");
			}
			
			ps.close();
			
			// 删除前10条数据
			String deleteSql = "delete from student limit 10";
			ps = c.prepareStatement(deleteSql);
			ps.execute();
			
			// 创建键盘输入对象
			sc = new Scanner(System.in);
			
			// 输入Y则提交事物，删除前10行数据。输入N则不提交事物，不删除数据。输入其他字符则重复提示
			while (true) {
				System.out.println("是否要删除数据Y/N");
				String s = sc.nextLine();
				if (s.equals("Y")) {
					c.commit();
					System.out.println("数据删除成功");
					break;
				} else if (s.equals("N")) {
					System.out.println("数据未删除");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}