package hfut.edu.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020年7月1日  
* @Description:测试CyclicBarrier；通过英雄联盟的比赛开始加载为例，很好的说明
* 这个类；也即只有全部英雄加载完成之后才可以开始游戏，这里面的CyclicBarrier就好比
* 一个栅栏，只有当满足特定数量线程后，所有线程才可以开始执行
*/
public class TestCyclicBarrier {
	
	public static void main(String[] args) {
		
		AtomicInteger sum=new AtomicInteger();
		
		CyclicBarrier mBarrier=new CyclicBarrier(5);
		List<LOLPlayer> l=new ArrayList<LOLPlayer>();
		
		//添加角色到容器,这里简化一下只添加一边角色
		l.add(new LOLPlayer(mBarrier, "上单狗熊"));
		l.add(new LOLPlayer(mBarrier, "中单沙皇"));
		l.add(new LOLPlayer(mBarrier, "打野皇子"));
		l.add(new LOLPlayer(mBarrier, "ADC女枪"));
		l.add(new LOLPlayer(mBarrier, "辅助泰坦"));
		
		for(LOLPlayer p:l) {
			p.start();
		}
			
	}

}
