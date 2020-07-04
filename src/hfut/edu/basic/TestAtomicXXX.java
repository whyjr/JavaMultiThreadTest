package hfut.edu.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020年7月2日
 * @Description:
 */
public class TestAtomicXXX {

	//private static int sum = 0;

	public static void main(String[] args) {

		//测试AtomicInteger，通过CAS实现数据同步
		AtomicInteger sum=new AtomicInteger();
		List<Thread> l = new ArrayList<Thread>();

		// 添加线程到容器
		for (int i = 0; i < 10; i++) {
			l.add(new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 10000; i++) {
						sum.addAndGet(1);
//						sum++;
						System.out.println("---" + sum);
					}
				}
			}));
		}

		for (Thread t : l) {
			t.start();
		}
		
	}
}
