package logic;

import java.applet.AudioClip;

import utility.AudioUtility;

public class MusicThread implements Runnable{
	private int soundLength, count;
	private AudioClip audio;
	
	public MusicThread(AudioClip audio, int soundLength) {
		// TODO Auto-generated constructor stub
		this.audio = audio;
		this.soundLength = soundLength;
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
			if(AudioUtility.isMute){
				audio.stop();
				synchronized (audio) {
					try{
						audio.wait();
					}catch(InterruptedException e){	}
				}
				audio.play();
				count = soundLength;
			}
			count--;
			if(count == 0){
				audio.play();
				count = soundLength;
			}
			
		}
		
	}

}
