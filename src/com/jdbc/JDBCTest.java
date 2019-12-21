package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBCѧϰDemo
 */
public class JDBCTest {
	public static void main(String[] args) {
		Connection c = null;
		Statement s = null;
		try {
			// ��ʼ��������
			// ò���°汾jdk���ڲ���Ҫ��ʼ��������
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("JDBC�������سɹ�");

			// ��ָ�����ݿ⽨������
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8", 
					"root",
					"**************");

			// ����Statement
			s = c.createStatement();
			System.out.println("���ݿ����ӳɹ�����ȡ���Ӷ���" + s);
			
			// ѭ��ִ�ж���sql���
			for (int i = 0; i < 100; i++) {
				// ����100������
				String sql = "insert into student(id, name, gender, age, math)"+ "values("+ i + ", '����', 'Ů', 16, 87)";
				s.execute(sql);
				System.out.println("��" + (i + 1) + "��sql���ִ�гɹ�");
			}
			System.out.println("------------------------------");
			
			// ɾ�����
			String delSql = "delete from student where name = '����'";
			s.execute(delSql);
			System.out.println("ɾ������ɹ�");
			System.out.println("------------------------------");
			
			// �޸����
			String modifySql = "update student set age = '27' where name = '�����'";
			s.execute(modifySql);
			System.out.println("�޸����������ɹ�");
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
		
		// ����ͨ��execute������ִ��sql���
		String testSql = "insert into student(id, name, gender, age, math) values(886, '��������', '��', null, 88)";
		execute(testSql);
		System.out.println("����execute����ִ��sql���ɹ�");
	}
	
	// �÷�����sql�����Ϊ����������sql�����ø÷�������ִ�����
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
			// ����ر�Statement��Connection��ʱ��Ҳ���쳣��Ҫ���������Ͻ���������Ҫ�ж�s��c�Ƿ�Ϊnull
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
