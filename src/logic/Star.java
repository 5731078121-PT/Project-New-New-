package logic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import input.InputUtility;
import render.GameScreen;
import render.IRenderable;
import utility.DrawingUtility;

public class Star implements IRenderable{
	private float gone = 250;
	
	private int x, y, goneCount;
	private int speedX, speedY;
	private boolean dead;
	private int i,count;
	private boolean onFloor;

	
	public Star(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.speedY = RandomUtility.random(1, 2);
		this.speedX = RandomUtility.random(-1, 1);
		this.dead = false;
		this.i = 0;
		this.goneCount = (int) gone;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) goneCount/gone);
		g.setComposite(tran);
		
		if(!dead){
			if(isClick(InputUtility.getMouseX(), InputUtility.getMouseY())){
				GameLogic.playerStatus.collectStar();
				dead = true;
			}
			
		}
		DrawingUtility.drawStar(g, x, y, i);
		
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
			i++;
		if(i == 7) i = 0;
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !dead;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(!dead){
			if(isClick(InputUtility.getMouseX(), InputUtility.getMouseY())){
				GameLogic.playerStatus.collectStar();
				dead = true;
			}
			
		}
		if(!dead){
			y += speedY;
			x += speedX;
			
			if(x<0){
				x = Math.abs(x);
				speedX = -speedX;
			}
			else if(x>GameScreen.WIDTH-30){
				x = GameScreen.WIDTH - 30;
				speedX = -speedX;
			}
			if(y> GameScreen.HEIGHT-30){
				y = GameScreen.HEIGHT - 30;
				onFloor = true;
			}			
			
		}
		if(onFloor){
			if(0 == goneCount){
				this.dead = true;
				onFloor = false;
				
			}else goneCount--;
		}
		
	}
	
	
	public boolean isClick(int x, int y){
		if(InputUtility.isMouseLeftDownTrigger() && !GameLogic.playerStatus.isPause() && !GameLogic.playerStatus.isEnd){
			
			if(this.x-30<=x && this.x+60>=x){
				
				if(this.y-30<=y && this.y+60>=y) {
					InputUtility.setMouseLeftDownTrigger(false);
					return true;
				}
			}			
		}
//		InputUtility.setMouseLeftDownTrigger(false);
		return false;
	}
	
}
