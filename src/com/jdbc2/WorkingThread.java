package com.jdbc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * �����̣߳�ͨ��ʵ��Runnableʵ�ֶ��߳�
 */
public class WorkingThread implements Runnable{
	private ConnectionPool cp;
	
	public WorkingThread(ConnectionPool cp) {
		this.cp = cp;
	}
	
	
	@Override
	public void run() {
		Connection c = cp.getConnection();
		System.out.println(Thread.currentThread().getName() + ":��ȡ��һ������,����ʼ����");
		
		String sql = "select * from student";
		try {
			// ģ���ʱ1s��sql��ѯ���
			Thread.sleep(1000);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ִ����֮�����ݿ����ӷ����̳߳�
		cp.returnConnection(c);
	}
}
