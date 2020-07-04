package hfut.edu.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020��7��1��  
* @Description:����CyclicBarrier��ͨ��Ӣ�����˵ı�����ʼ����Ϊ�����ܺõ�˵��
* ����ࣻҲ��ֻ��ȫ��Ӣ�ۼ������֮��ſ��Կ�ʼ��Ϸ���������CyclicBarrier�ͺñ�
* һ��դ����ֻ�е������ض������̺߳������̲߳ſ��Կ�ʼִ��
*/
public class TestCyclicBarrier {
	
	public static void main(String[] args) {
		
		AtomicInteger sum=new AtomicInteger();
		
		CyclicBarrier mBarrier=new CyclicBarrier(5);
		List<LOLPlayer> l=new ArrayList<LOLPlayer>();
		
		//��ӽ�ɫ������,�����һ��ֻ���һ�߽�ɫ
		l.add(new LOLPlayer(mBarrier, "�ϵ�����"));
		l.add(new LOLPlayer(mBarrier, "�е�ɳ��"));
		l.add(new LOLPlayer(mBarrier, "��Ұ����"));
		l.add(new LOLPlayer(mBarrier, "ADCŮǹ"));
		l.add(new LOLPlayer(mBarrier, "����̩̹"));
		
		for(LOLPlayer p:l) {
			p.start();
		}
			
	}

}
