package hfut.edu.interview.i_01;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020年7月2日
 * @Description: 实现两个线程运行，一个线程循环遍历0-9， 当到达5的时候，
 * 该线程暂停，另一个线程给出提示后结束，本线程继续执行结束
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
		
		//必须要保证t2先执行
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
