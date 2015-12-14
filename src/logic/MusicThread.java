package logic;

import utility.AudioUtility;
import utility.DrawingUtility;

public class MusicThread implements Runnable{
	private int soundLength, count;
	
	public MusicThread() {
		// TODO Auto-generated constructor stub
		this.soundLength = 24800;
		count = 1;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
//			System.out.println("run");
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){}
			if(DrawingUtility.isMute){
				AudioUtility.bgm.stop();
				synchronized (AudioUtility.bgm) {
					try{
						AudioUtility.bgm.wait();
					}catch(InterruptedException e){	}
				}
				AudioUtility.bgm.play();
				count = 24800;
			}
			count--;
			if(count == 0){
				AudioUtility.bgm.play();
				count = 24800;
			}
			
		}
		
	}

}
