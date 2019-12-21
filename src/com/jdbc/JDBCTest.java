package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC学习Demo
 */
public class JDBCTest {
	public static void main(String[] args) {
		Connection c = null;
		Statement s = null;
		try {
			// 初始化驱动类
			// 貌似新版本jdk现在不需要初始化驱动类
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("JDBC驱动加载成功");

			// 与指定数据库建立连接
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8", 
					"root",
					"**************");

			// 創建Statement
			s = c.createStatement();
			System.out.println("数据库连接成功，获取连接对象：" + s);
			
			// 循环执行多条sql语句
			for (int i = 0; i < 100; i++) {
				// 增加100个郭襄
				String sql = "insert into student(id, name, gender, age, math)"+ "values("+ i + ", '郭襄', '女', 16, 87)";
				s.execute(sql);
				System.out.println("第" + (i + 1) + "条sql语句执行成功");
			}
			System.out.println("------------------------------");
			
			// 删除语句
			String delSql = "delete from student where name = '郭襄'";
			s.execute(delSql);
			System.out.println("删除郭襄成功");
			System.out.println("------------------------------");
			
			// 修改语句
			String modifySql = "update student set age = '27' where name = '穆念慈'";
			s.execute(modifySql);
			System.out.println("修改穆念慈年龄成功");
			System.out.println("------------------------------");
			
			// } catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 这里通过execute方法来执行sql语句
		String testSql = "insert into student(id, name, gender, age, math) values(886, '东方不败', '男', null, 88)";
		execute(testSql);
		System.out.println("调用execute方法执行sql语句成功");
	}
	
	// 该方法以sql语句作为参数，输入sql语句调用该方法即可执行语句
	public static void execute(String sql) {
		Connection c = null;
		Statement s = null;
		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8", 
					"root",
					"****************");
			
			s = c.createStatement();
			
			s.execute(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 这里关闭Statement和Connection的时候也有异常需要处理，更加严谨的做法还要判断s和c是否为null
			try {
				s.close();
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
