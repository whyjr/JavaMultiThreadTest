package hfut.edu.interview.i_01;

import java.util.concurrent.locks.LockSupport;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020年7月2日
 * @Description:实现两个线程运行，一个线程循环遍历0-9， 当到达5的时候，
 * 该线程暂停，另一个线程给出提示后结束，本线程继续执行结束
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
					//注意两者的位置不能调换
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
