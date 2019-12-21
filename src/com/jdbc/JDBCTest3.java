package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * ����PreparedStatement
 * PreparedStatement��Statement����ȫ,Ч�ʸ���,�Ƽ�ʹ��
 */
public class JDBCTest3 {
	public static void main(String[] args) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8", 
					"root", 
					"***************");
			
			String sql = "select * from student where name = ?";
			
			ps = c.prepareStatement(sql);
			// ע�⣺����Ҳ�ǻ�Ϊ1,��1��ͷ
			ps.setString(1, "�����");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				int math = rs.getInt("math");
				System.out.printf("%d-%s-%s-%d-%d\n", id, name, gender, age, math);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
