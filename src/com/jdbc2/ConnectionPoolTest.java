package com.jdbc2;

/*
 * 测试线程池
 */
public class ConnectionPoolTest {
	public static void main(String[] args) {
		// 创建线程大小为3的线程池
		ConnectionPool cp = new ConnectionPool(3);

		// 创建100个线程进行测试
		WorkingThread wt = new WorkingThread(cp);
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(wt, "Thread-" + i);
			t.start();
		}
	}
}
