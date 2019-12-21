package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * JDBC��������ϰ�⣬ɾ��student���е�ǰ10������
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
			
			// �ر������Զ��ύ
			c.setAutoCommit(false);
			
			// ��ѯ���е�ǰ10������
			String selectSql = "select id from student limit 10";
			ps = c.prepareStatement(selectSql);
			
			// �ҵ�ǰ10�����ݵ�id,ɾ��ǰ������ʾ��Ϣ
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("������ͼɾ��id=" + rs.getInt("id") + "������");
			}
			
			ps.close();
			
			// ɾ��ǰ10������
			String deleteSql = "delete from student limit 10";
			ps = c.prepareStatement(deleteSql);
			ps.execute();
			
			// ���������������
			sc = new Scanner(System.in);
			
			// ����Y���ύ���ɾ��ǰ10�����ݡ�����N���ύ�����ɾ�����ݡ����������ַ����ظ���ʾ
			while (true) {
				System.out.println("�Ƿ�Ҫɾ������Y/N");
				String s = sc.nextLine();
				if (s.equals("Y")) {
					c.commit();
					System.out.println("����ɾ���ɹ�");
					break;
				} else if (s.equals("N")) {
					System.out.println("����δɾ��");
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