package hfut.edu.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**  
* @version: V1.0 
* @author: why 
* @date: 2020年7月2日  
* @Description:用于模拟LOL英雄角色
*/
public class LOLPlayer {
	
	private CyclicBarrier mBarrier;
	private String mPlayerName;
	public LOLPlayer(CyclicBarrier cyclicBarrier,String name) {
		this.mBarrier=cyclicBarrier;
		this.mPlayerName=name;
	}
	
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(mPlayerName+" is ready loading....");
				try {
					TimeUnit.SECONDS.sleep((int)Math.random()*5+1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(mPlayerName+" complete loading and waiting other players");
				try {
					mBarrier.await();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				System.out.println(mPlayerName+" enter the game!");
			}
		}).start();
		
	}
	
}
