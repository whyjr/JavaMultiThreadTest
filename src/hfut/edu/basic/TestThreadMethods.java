package hfut.edu.basic;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020��7��1��
 * @Description:�����̵߳Ļ������÷�����һЩ�����ĸ���
 */
public class TestThreadMethods {

	public static void main(String[] args) {

//		Thread t1=new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("This is thread t1");
//			}
//		},"t1");
//		
//		t1.start();

		// start()������ʹ�߳̽���ready״̬����ȡcpuִ��Ȩ����ʼrun()����
		// ע�⣬����stop()�����Ѿ����ã�������ʹ��,һ��Ϻõķ�ʽ���ǵȴ��߳���Ȼ����

//����wait()��notify()/notifyAll();	
		Object o = new Object();
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o) {
					// TODO Auto-generated method stub
					System.out.println("t2 start");
					try {
						o.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("t2 end");
				}
			}
		}, "t2");
		t2.start();
	
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (o) {
					System.out.println("t3 start");
					try {
						Thread.sleep(1000);
						o.notify();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("t3 end");
				}
			}
		}, "t3");

		t3.start();

		// �����̵߳�join()����
//		Thread t4 = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("t4----start");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				System.out.println("t4----is over");
//			}
//		}, "t4");
//
//		Thread t5 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("t5----start");
//				try {
//					t4.join();
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("t5----is over");
//			}
//		}, "t5");
//
//		t5.start();
//		t4.start();

		
		
	}

}
