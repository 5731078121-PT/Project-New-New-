package render;

import input.InputUtility;
import logic.Dragon;
import logic.Duck;
import logic.DuckAggressive;
import logic.GameLogic;
import logic.PlayerStatus;
import logic.RandomUtility;
import main.Main;
import utility.DrawingUtility;
import utility.GameSaveUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;

public class GameScreen extends JComponent {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 700;
	public int ranBg;
	
	public GameScreen() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		DrawingUtility.createBg();
		ranBg = RandomUtility.random(0, 6);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
					if(e.getButton() == 1){
						if(InputUtility.isMouseLeftDown()){
							InputUtility.setMouseLeftDownUp(true);
							if(InputUtility.isMouseOnScreen()){
								InputUtility.setMouseX(e.getX());
								InputUtility.setMouseY(e.getY());
							}
						}else{
							InputUtility.setMouseLeftDownUp(false);
						}
						InputUtility.setMouseLeftDownTrigger(false);
						InputUtility.setMouseLeftDown(false);
					}
					else InputUtility.setMouseRightClickUp(true);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("mouse press");
				if(e.getButton() == 1){
					if(InputUtility.isMouseRightClickUp()){
						InputUtility.setMouseRightClickUp(false);
						if(!GameLogic.playerStatus.isPause() && !GameLogic.playerStatus.isEnd)
							GameLogic.playerStatus.setPause(true);
						InputUtility.setMouseLeftDown(false);
						InputUtility.setMouseLeftDownTrigger(false);
						InputUtility.setMouseLeftDownUp(false);
						
						return;
					}
					
					if(!InputUtility.isMouseLeftDown()){
						InputUtility.setMouseLeftDownTrigger(true);
					}else{
						InputUtility.setMouseLeftDownTrigger(false);
					}
					InputUtility.setMouseLeftDown(true);
					
					if(InputUtility.isMouseOnScreen()){
						InputUtility.setMouseX(e.getX());
						InputUtility.setMouseY(e.getY());
					}
					InputUtility.setMouseLeftDownUp(false);
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
					InputUtility.setMouseOnScreen(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
					InputUtility.setMouseOnScreen(true);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				if(e.getButton() == 1){
//					if(!InputUtility.isMouseLeftDown()){
//						InputUtility.setMouseLeftDownTrigger(true);
//					}else{
//						InputUtility.setMouseLeftDownTrigger(false);
//					}
//					InputUtility.setMouseLeftDown(true);
//					InputUtility.setMouseLeftDownUp(false);
//					
//				}
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
					if(InputUtility.isMouseOnScreen()){
						InputUtility.setMouseX(e.getX());
						InputUtility.setMouseY(e.getY());
					}					

			}
		});
		
		requestFocus();
		setDoubleBuffered(true);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		DrawingUtility.drawBg(g2, ranBg);
//		g2.setColor(Color.GRAY);
//		g2.fillRect(0, 0, 600, 700);
		DrawingUtility.drawGameName(g2, 160, 20);
		
		RenderableHolder.sort();
		synchronized (RenderableHolder.getInstance()) {
			for(IRenderable renderable : RenderableHolder.getRenderableList()){
				if(renderable.isVisible()){
					renderable.draw(g2);
				}
				else {
					if(renderable instanceof Dragon){
						Dragon dragon = (Dragon) renderable;
						for(int i = 0; i<RenderableHolder.getRenderableList().size(); i++){
							if(RenderableHolder.getRenderableList().get(i) instanceof Duck){
								Duck duck = (Duck) RenderableHolder.getRenderableList().get(i);
								
								if(duck.column == ((Dragon) renderable).column){
									duck.haveDragon = false;
								}
							}
							if(RenderableHolder.getRenderableList().get(i) instanceof DuckAggressive){
								DuckAggressive aggressDuck = (DuckAggressive) RenderableHolder.getRenderableList().get(i);
								
								if(!aggressDuck.dead && aggressDuck.column == dragon.column){
									aggressDuck.setJig(false);
								}
							}
						}
					}
					RenderableHolder.getRenderableList().remove(renderable);
				}
				
			}
		}
		if(GameLogic.playerStatus.isEnd){
			RenderableHolder.clear();
			if(GameLogic.playerStatus.isPause()) return;
			if(GameLogic.playerStatus.isWin){
//				WIN SCREEN
				GameSaveUtility.updatePlayer(GameLogic.playerStatus.getName(), GameLogic.playerStatus.getState(), GameLogic.playerStatus.getMoney());
				GameSaveUtility.recordData();
				DrawingUtility.drawWinScreen(g2);
//				g2.fillRect(375/2, 475/2 + 50, 100, 100);
				if(InputUtility.getMouseY() >= 475/2 + 50 && InputUtility.getMouseY() <= 475/2 + 150 ){
//					back to HOME
					if(InputUtility.getMouseX() >= 375/2 && InputUtility.getMouseX() <= 375/2 + 100){
						GameLogic.playerStatus.isEnd = true;
						System.out.println("in");
						Main.titleScene();
						
					}
//					NEXT STAGE
					else if(InputUtility.getMouseX() >= 375/2 + 125 && InputUtility.getMouseX() <= 375/2 +205){
//						new player up STAGE and STAR
						GameLogic.playerStatus = new PlayerStatus(GameLogic.playerStatus.getName(), GameLogic.playerStatus.getState()+1, GameLogic.playerStatus.getMoney()+10);
						Main.runGame();
					}
				}
			}else {
//			LOSE SCREEN
				DrawingUtility.drawLoseScreen(g2,GameLogic.playerStatus.getTime() == 0);
				if(InputUtility.getMouseY() >= 475/2 + 50 && InputUtility.getMouseY() <= 475/2 + 150 ){
//					back to HOME
					if(InputUtility.getMouseX() >= 375/2 && InputUtility.getMouseX() <= 375/2 + 100){
						GameLogic.playerStatus.isEnd = true;
						Main.titleScene();
					}
//					PLAY AGAIN
					if(InputUtility.getMouseX() >= 375/2 + 105 && InputUtility.getMouseX() <= 375/2 +205){
//						new player up STAGE and STAR
						GameLogic.playerStatus = new PlayerStatus(GameLogic.playerStatus.getName(), GameLogic.playerStatus.getState(), GameLogic.playerStatus.getMoney());
						Main.runGame();
					}
				}
			}
			
		}else if(GameLogic.playerStatus.isPause() ){
//			PAUSE SCREEN
			DrawingUtility.drawPauseScreen(g2);
			if(InputUtility.isMouseLeftDownTrigger()){
				if(InputUtility.getMouseY() >= 475/2+125 && InputUtility.getMouseY() <= 475/2+225){
//					back to HOME
					if(InputUtility.getMouseX() >= 375/2 && InputUtility.getMouseX() <= 375/2+100){
						GameLogic.playerStatus.isEnd = true;
						Main.titleScene();
					}
//					sound
					if(InputUtility.getMouseX() >= 125+375/2 && InputUtility.getMouseX() <= 225+375/2){
						DrawingUtility.isMute = !DrawingUtility.isMute;
					}
					
				}
				if(InputUtility.getMouseY() >= 475/2 && InputUtility.getMouseY() <= 475/2+100){
					if(InputUtility.getMouseX() >= 125+375/2 && InputUtility.getMouseX() <= 225+375/2){
						GameLogic.playerStatus.setPause(false);
					}
//					PLAY THIS STAGE AGAIN
					if(InputUtility.getMouseX() >= 375/2 && InputUtility.getMouseX() <= 375/2+100){
						String name = GameLogic.playerStatus.getName();
						int state = GameLogic.playerStatus.getState();
						int star = GameLogic.playerStatus.getMoney();
						RenderableHolder.clear();
						GameLogic.playerStatus.setPause(false);
						GameLogic.playerStatus = new PlayerStatus(name, state, star);
						Main.runGame();
						
					}
				}
			}
		}
		
	}
	
	
}
