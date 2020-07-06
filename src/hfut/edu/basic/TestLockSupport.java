package hfut.edu.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020��7��2��  
* @Description:�������߻���ָ���̣߳�������wait/notify�����ǣ�
* �������Ҫʹ��������������һ��������
* @testAnotation:�������ģ��һ�����ƻص�������С��������˵��ȥ�棬
* �������跹�����ˣ��������躰�������Է�����ʵ�����̼߳�ͨѶ�İ�����
* ��interview.i_01һ������
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
