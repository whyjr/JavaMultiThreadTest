package hfut.edu.interview.i_01;

import java.util.concurrent.locks.LockSupport;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020��7��2��
 * @Description:ʵ�������߳����У�һ���߳�ѭ������0-9�� ������5��ʱ��
 * ���߳���ͣ����һ���̸߳�����ʾ����������̼߳���ִ�н���
 */
public class T01_LockSupport {
	
	private static Thread t1=null;
    private static Thread t2=null;
	public static void main(String[] args) {
        	
		 t1 = new Thread(() -> {
			System.out.println("t1 start...");
			for (int i = 0; i < 10; i++) {
				System.out.println("t1----" + i);
				if (i == 5) {
					//ע�����ߵ�λ�ò��ܵ���
					LockSupport.unpark(t2);
					LockSupport.park();
				}
			}
		});

		t2 = new Thread(() -> {
			System.out.println("t2 start...");
			LockSupport.park();
			System.out.println("t2--------");
			LockSupport.unpark(t1);

		});

		t1.start();
		t2.start();
	}

}
