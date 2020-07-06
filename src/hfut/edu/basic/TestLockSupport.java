package hfut.edu.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020年7月2日  
* @Description:阻塞或者唤醒指定线程，类似于wait/notify；但是，
* 这个不需要使用锁对象，类似于一个工具类
* @testAnotation:这里测试模拟一种类似回调场景，小明和妈妈说出去玩，
* 等她妈妈饭做好了，让她妈妈喊他回来吃饭；其实就是线程间通讯的案例，
* 和interview.i_01一样道理
*/
public class TestLockSupport {
	
	public static void main(String[] args) {
		
		Thread xiaoming=new Thread(new Runnable() {	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("xiaoming is playing...");
				LockSupport.park();
				System.out.println("mom call xiaoming back to dinner!");
			}
		},"xiaoming") ;
		
		Thread mm=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("mom is cooking...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("mom complete cooking dinner!");
				LockSupport.unpark(xiaoming);
				
			}
		},"mm"); 
		mm.start();
		xiaoming.start();
		
	}
}
