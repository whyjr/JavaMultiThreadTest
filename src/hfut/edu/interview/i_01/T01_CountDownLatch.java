package hfut.edu.interview.i_01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020年7月2日  
* @Description:实现两个线程运行，一个线程循环遍历0-9， 当到达5的时候，
 * 该线程暂停，另一个线程给出提示后结束，本线程继续执行结束
*/
public class T01_CountDownLatch {
	public static void main(String[] args) {
		
		Thread t1=null;
		Thread t2=null;
		
		CountDownLatch t1DownLatch=new CountDownLatch(1);//有一个运行许可权限
		CountDownLatch t2DownLatch=new CountDownLatch(1);//有一个运行许可权限
		
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
