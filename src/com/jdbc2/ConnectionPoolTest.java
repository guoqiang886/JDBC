package com.jdbc2;

/*
 * �����̳߳�
 */
public class ConnectionPoolTest {
	public static void main(String[] args) {
		// �����̴߳�СΪ3���̳߳�
		ConnectionPool cp = new ConnectionPool(3);

		// ����100���߳̽��в���
		WorkingThread wt = new WorkingThread(cp);
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(wt, "Thread-" + i);
			t.start();
		}
	}
}
