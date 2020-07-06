package hfut.edu.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: V1.0
 * @author: why
 * @date: 2020年7月2日
 * @Description:测试ReentrantLock中的Condition的作用，它可以对不同的任务线程 进行分组处理
 * @testAnnotation:这里举一个面试题，比如在生产者和消费者模型中，我们有 一组线程是生成者，
 *                                            另一组线程是消费者，生产者和消费者存取的在一个定长的List中
 */
public class TestCondition {

	public static void main(String[] args) {

		AtomicInteger data = new AtomicInteger();

		MyBlockingList<Integer> bl = new MyBlockingList<Integer>(8);
		List<Thread> consumers = new ArrayList<>();
		List<Thread> producers = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			consumers.add(new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						TimeUnit.SECONDS.sleep((long) (4 * Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("consumer get: " + bl.get());
				}
			}));
		}

		for (int i = 0; i < 3; i++) {
			producers.add(new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						TimeUnit.SECONDS.sleep((long) (2 * Math.random()));
						System.out.println("producers add: " + data.get());
						bl.add(data.getAndIncrement());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}));

		}

		for (Thread t : consumers)
			t.start();

		for (Thread t : producers)
			t.start();
	}

	/**
	 * 自定义一个阻塞队列
	 * 
	 * @version: V1.0
	 * @author: why
	 * @date: 2020年7月6日
	 * @Description:
	 */
	static class MyBlockingList<T> {
		ReentrantLock lock = new ReentrantLock();

		Condition consumerLock = lock.newCondition();
		Condition producerLock = lock.newCondition();

		List<T> container = new ArrayList<>();
		int limitSize = 10;

		public MyBlockingList(int size) {
			limitSize = size;
		}

		/**
		 * 添加数据
		 * 
		 * @param t
		 */
		public void add(T t) {
		   lock.lock();
			while (container.size() >= 10) {
				try {
					lock.unlock();
					producerLock.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					consumerLock.notifyAll();
				}
			} 
		  container.add(t);
          lock.unlock();
		}

		/**
		 * 取数据
		 * 
		 * @return
		 */
		public T get() {
			lock.lock();
			T t = null;
			while (container.size() > 0) {
				t = container.remove(0);
				lock.unlock();
				return t;
			}
			try {
				consumerLock.await();
				producerLock.notifyAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.unlock();
			return t;
		}
	}
}
