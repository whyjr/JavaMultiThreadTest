package hfut.edu.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020年7月2日  
* @Description:一种有固定容量的容器，当容器内部容量变为0时，通过await()阻塞的线程
* 开始执行
* @testAnnotation:这里有一个场景，我们在下载一个文件的时候，如果一个文件较大，我们可以通过
* 开启多线程进行下载，只有等所有线程下载完成之后，我们才能进行读写操作，下面是模拟逻辑实现
* 
*/
public class TestCountDownLatch {

	public static void main(String[] args) {
		int threadSize=5;
		CountDownLatch trigger=new CountDownLatch(threadSize);
		
		List<Thread> threadPool=new ArrayList();
		for(int i=0;i<threadSize;i++) {
			threadPool.add(new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						TimeUnit.SECONDS.sleep((long) (5*Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"执行下载任务结束！");
					trigger.countDown();
					
				}
			},"Download"+i));
		}
		
		//启动下载线程
		for(Thread t:threadPool)t.start();
		
		
		/**
		 * 文件下载完成的读写线程
		 */
		Thread readOrWriteThread=new Thread(new Runnable() {	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					trigger.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("开始进行读写文件操作");
			}
		});
		
		//启动读写线程
		readOrWriteThread.start();
		
		
	}
	
}
