package main;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import logic.GameLogic;
import logic.MusicThread;
import render.GameBegin;
import render.GameScreen;
import render.GameTitle;
import render.GameWindow;
import render.RenderableHolder;
import utility.AudioUtility;
import utility.InputUtility;

public class Main {
	public static JFrame frame;
	public static GameLogic gameLogic;
	public static GameScreen gameScreen;
	public static GameTitle gameTitle;
	public static GameBegin gameBegin;
	public static boolean isStart;
	public static GameWindow gameWindow;
	public static JComponent nextScene = null;
	public static Thread t;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gameTitle = new GameTitle();
		gameWindow = new GameWindow(gameTitle);
		MusicThread musicThread = new MusicThread(AudioUtility.bgm, 5500);
		t = new Thread(musicThread);
		t.start();
		
		while(true){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {}
			gameWindow.getCurrentScene().repaint();
			
			if(gameWindow.getCurrentScene() instanceof GameScreen){
				
				 gameLogic.logicUpdate();
				 if(gameLogic.playerStatus.isEnd){
					 RenderableHolder.clear();
				 }
				 if(InputUtility.isMouseLeftDown()&& !GameLogic.playerStatus.isPause()){
					 InputUtility.setMouseLeftDownTrigger(false);
				 }
			}
			if(nextScene != null){				
				gameWindow.getCurrentScene().removeAll();
				gameWindow.switchScene(nextScene);
				nextScene = null;
			}
			
		}
		
	}
	
	
	public static void titleScene(){

		nextScene = gameTitle;
		
	}

	public static void runGame(){
		if(gameScreen != null){
			gameScreen.removeAll();
		}
//		gameLogic = null;
		gameScreen = new GameScreen();
		gameLogic = new GameLogic();
		
		nextScene = gameScreen;
		
	}
	public static void startGame(){
		gameBegin = new GameBegin();
		nextScene = gameBegin;
	}
	
}