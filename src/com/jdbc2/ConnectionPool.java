package com.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * ���ݿ����ӳ�
 */
public class ConnectionPool {
	// ���ݿ����ӳ���List�洢
	private List<Connection> cs = new ArrayList<Connection>();
	
	// ���ݿ����ӳصĴ�С
	private int size;
	
	public ConnectionPool(int size) {
		this.size = size;
		init();
	}

	private void init() {
		for (int i = 0; i < this.size; i++) {
			try {
				// ע�����ﴴ�������Ӻ�㲻��Ҫ�ٹرգ�ֻ��Ž�List
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8",
						"root",
						"*************");
				
				this.cs.add(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("���ݿ����ӳش����ɹ�");
	}
	
	public synchronized Connection getConnection() {
		// �����ǰ�̳߳�ȫ��������,Ӧ���õ�ǰ�̵߳ȴ�
		// ���ﲻ����if,Ӧ��while��
		while (this.cs.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.cs.remove(0);
	}
	
	public synchronized void returnConnection(Connection c) {
		this.cs.add(c);
		this.notifyAll();
	}
}
