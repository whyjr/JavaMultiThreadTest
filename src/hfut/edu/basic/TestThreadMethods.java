package hfut.edu.basic;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020年7月1日
 * @Description:测试线程的基本常用方法和一些基本的概念
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

		// start()方法，使线程进入ready状态，获取cpu执行权即开始run()方法
		// 注意，现在stop()方法已经弃用，不建议使用,一般较好的方式就是等待线程自然结束

//测试wait()和notify()/notifyAll();	
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

		// 测试线程的join()方法
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
