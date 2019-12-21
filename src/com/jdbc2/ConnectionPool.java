package com.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * 数据库连接池
 */
public class ConnectionPool {
	// 数据库连接池用List存储
	private List<Connection> cs = new ArrayList<Connection>();
	
	// 数据库连接池的大小
	private int size;
	
	public ConnectionPool(int size) {
		this.size = size;
		init();
	}

	private void init() {
		for (int i = 0; i < this.size; i++) {
			try {
				// 注意这里创建好连接后便不需要再关闭，只需放进List
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC&characterEncoding=UTF-8",
						"root",
						"*************");
				
				this.cs.add(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("数据库连接池创建成功");
	}
	
	public synchronized Connection getConnection() {
		// 如果当前线程池全部被用完,应该让当前线程等待
		// 这里不能用if,应用while。
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
