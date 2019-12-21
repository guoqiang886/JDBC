package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ���һ�����������з�ҳ��ѯ
 */
public class JDBCTest2 {
	public static void main(String[] args) {
		// Ϊ�˱�֤jdbc_test���ݿ��student�����㹻�����ݣ������ȶ�student�����100������
		Connection c = null;
		Statement s = null;
		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8", 
					"root",
					"**********");

			s = c.createStatement();

			for (int i = 0; i < 100; i++) {
				String addSql = "insert into student values(" + i + ", '����', 'Ů', 24, 100)";
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

		// ����list��ҳ��ѯ����
		list(20, 10);
	}

	// ��ҳ��ѯ
	public static void list(int start, int count) {
		// start��ʾ�ӵ�start�����ݿ�ʼ��count��ʾ��ѯ����������
		// ������Ѿ������õ�jdbc_test���ݿ�����ѯ����
		Connection c = null;
		Statement s = null;
		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8",
					"root",
					"ygq1813297517");

			s = c.createStatement();

			String selectSql = "select * from student limit " + start + ", " + count;
			// System.out.println(selectSql);
			ResultSet rs = s.executeQuery(selectSql);

			// ����ResultSet�����Եõ���ѯ�Ľ��
			while (rs.next()) {
				int id = rs.getInt("id"); // ����ʹ���ֶ���
				String name = rs.getString(2); // Ҳ����ʹ���ֶ�˳��ע���ֶ�˳���Ǵ�1��ʼ��
				String gender = rs.getString("gender");
				int age = rs.getInt(4);
				int math = rs.getInt("math");
				// �������õ�printf��ʽ�����
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
