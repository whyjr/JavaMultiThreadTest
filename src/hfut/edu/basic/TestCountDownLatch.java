package hfut.edu.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020��7��2��  
* @Description:һ���й̶��������������������ڲ�������Ϊ0ʱ��ͨ��await()�������߳�
* ��ʼִ��
* @testAnnotation:������һ������������������һ���ļ���ʱ�����һ���ļ��ϴ����ǿ���ͨ��
* �������߳̽������أ�ֻ�е������߳��������֮�����ǲ��ܽ��ж�д������������ģ���߼�ʵ��
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
					System.out.println(Thread.currentThread().getName()+"ִ���������������");
					trigger.countDown();
					
				}
			},"Download"+i));
		}
		
		//���������߳�
		for(Thread t:threadPool)t.start();
		
		
		/**
		 * �ļ�������ɵĶ�д�߳�
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
				System.out.println("��ʼ���ж�д�ļ�����");
			}
		});
		
		//������д�߳�
		readOrWriteThread.start();
		
		
	}
	
}
