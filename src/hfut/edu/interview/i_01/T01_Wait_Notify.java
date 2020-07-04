package hfut.edu.interview.i_01;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020��7��2��
 * @Description: ʵ�������߳����У�һ���߳�ѭ������0-9�� ������5��ʱ��
 * ���߳���ͣ����һ���̸߳�����ʾ����������̼߳���ִ�н���
 */
public class T01_Wait_Notify {

	public static void main(String[] args) {

		Object lock = new Object();
		Thread t1 = new Thread(() -> {

			synchronized (lock) {
				System.out.println("t1 start...");
				for (int i = 0; i < 10; i++) {
					System.out.println("t1----" + i);
					if (i == 5) {
						lock.notify();
						try {
							lock.wait();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		

		Thread t2 = new Thread(() -> {
			synchronized (lock) {
				System.out.println("t2 start...");
				try {
					lock.wait();
					System.out.println("t2--------");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					lock.notify();
				}
			}
		});
		
		//����Ҫ��֤t2��ִ��
		t2.start();
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.start();
		
	}

}
