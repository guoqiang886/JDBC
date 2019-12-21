package com.jdbc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 创建线程，通过实现Runnable实现多线程
 */
public class WorkingThread implements Runnable{
	private ConnectionPool cp;
	
	public WorkingThread(ConnectionPool cp) {
		this.cp = cp;
	}
	
	
	@Override
	public void run() {
		Connection c = cp.getConnection();
		System.out.println(Thread.currentThread().getName() + ":获取了一个连接,并开始工作");
		
		String sql = "select * from student";
		try {
			// 模拟耗时1s的sql查询语句
			Thread.sleep(1000);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 执行完之后将数据库连接返回线程池
		cp.returnConnection(c);
	}
}
