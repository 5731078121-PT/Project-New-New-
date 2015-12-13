package main;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import logic.GameLogic;
import render.GameScreen;
import render.GameTitle;
import render.GameWindow;



public class Main {
	public static JFrame frame;
	public static GameLogic gameLogic;
	public static GameScreen gameScreen;
	public static GameTitle gameTitle;
	public static boolean isStart;
	public static GameWindow gameWindow;
	public static JComponent nextScene = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gameTitle = new GameTitle();
		gameWindow = new GameWindow(gameTitle);
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {}
			gameWindow.getCurrentScene().repaint();
			if(gameWindow.getCurrentScene() instanceof GameScreen){
				 gameLogic.logicUpdate();
			}
			if(nextScene != null){
				
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
	
}