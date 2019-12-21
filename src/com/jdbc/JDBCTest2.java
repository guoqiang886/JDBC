package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 设计一个方法，进行分页查询
 */
public class JDBCTest2 {
	public static void main(String[] args) {
		// 为了保证jdbc_test数据库的student表有足够的数据，这里先对student表添加100组数据
		Connection c = null;
		Statement s = null;
		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8",
					"root",
					"************");

			s = c.createStatement();

			for (int i = 0; i < 100; i++) {
				String addSql = "insert into student values(" + i + ", '赵敏', '女', 24, 100)";
				s.execute(addSql);
			}
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

		// 测试list分页查询函数
		list(20, 10);
	}

	// 分页查询
	public static void list(int start, int count) {
		// start表示从第start行数据开始，count表示查询多少条数据
		// 这里从已经创建好的jdbc_test数据库来查询数据
		Connection c = null;
		Statement s = null;
		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8",
					"root",
					"************");

			s = c.createStatement();

			String selectSql = "select * from student limit " + start + ", " + count;
			// System.out.println(selectSql);
			ResultSet rs = s.executeQuery(selectSql);

			// 遍历ResultSet，可以得到查询的结果
			while (rs.next()) {
				int id = rs.getInt("id"); // 可以使用字段名
				String name = rs.getString(2); // 也可以使用字段顺序，注意字段顺序是从1开始的
				String gender = rs.getString("gender");
				int age = rs.getInt(4);
				int math = rs.getInt("math");
				// 这里是用的printf格式化输出
				System.out.printf("%d-%s-%s-%d-%d\n", id, name, gender, age, math);
				// System.out.println(id + "-" + name + "-" + gender + "-" + age + "-" + math);
			}
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
	}
}
