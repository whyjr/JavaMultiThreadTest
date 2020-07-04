package hfut.edu.interview.i_01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020��7��2��  
* @Description:ʵ�������߳����У�һ���߳�ѭ������0-9�� ������5��ʱ��
 * ���߳���ͣ����һ���̸߳�����ʾ����������̼߳���ִ�н���
*/
public class T01_CountDownLatch {
	public static void main(String[] args) {
		
		Thread t1=null;
		Thread t2=null;
		
		CountDownLatch t1DownLatch=new CountDownLatch(1);//��һ���������Ȩ��
		CountDownLatch t2DownLatch=new CountDownLatch(1);//��һ���������Ȩ��
		
		t1 = new Thread(() -> {
			System.out.println("t1 start...");
			for (int i = 0; i < 10; i++) {
				System.out.println("t1----" + i);
				if (i == 5) {
					t2DownLatch.countDown();
					try {
						t1DownLatch.await();	
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
										
				}
			}
		});

		t2 = new Thread(() -> {
			System.out.println("t2 start...");
			try {
				t2DownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t1DownLatch.countDown();
			System.out.println("t2--------");
		});	
		
		t1.start();
		t2.start();
	}

}
