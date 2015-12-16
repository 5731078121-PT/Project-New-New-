package logic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import render.GameScreen;
import render.IRenderable;
import utility.AudioUtility;
import utility.DrawingUtility;
import utility.RandomUtility;

public class Dragon implements IRenderable{
	protected float hpMax = 50;
	protected int power = 5;
	protected int speed = 1;

	protected int x;
	public int y;
	protected int hp;
	protected int currentFrame , frameDelayCount;
	protected boolean dead;
	protected AlphaComposite tran;
	public int column = 0;
	public boolean isFrozen;
	public int frozenTime = 10;
	public int frozenCount;
	
	public Dragon(int x) {
		// TODO Auto-generated constructor stub
		this.x = GameLogic.playingArea.placedX(x);
		this.column = (x-175)/75 - 1;
		if(column == -1) column = 0;
		this.y = GameScreen.HEIGHT-RandomUtility.random(0, 50);
		this.hp = (int) hpMax;
		
	}
	
	public void attackDuck(Duck duck){
		if(duck.hp%50 == 0) {
			if(!AudioUtility.isMute) AudioUtility.dragonBiteSound.play();
		}
		duck.decreaseHP(this.power);
		y += speed;
		
	}
	
	public void attackShell(Shell shell){
		if(shell.hp%50 == 0) {
			if(!AudioUtility.isMute) AudioUtility.dragonBiteSound.play();
		}
		shell.decreaseHP(this.power);
		y+=speed;
	}
	
	public void decreaseHP(int s){
		
		if(hp >= s) hp -= s;
		else hp = 0;
	}
	 
	public void update(){
		if(isFrozen){
			if(currentFrame%4 == 0 ){
				this.y += speed; 			
			}else if(currentFrame%4 ==1){}
			else this.y -= speed;
		}else{			
			if(currentFrame%4 == 0){
				this.y += speed; 			
			}else this.y -= speed;
		}
		
		if(hp == 0 ){
			dead = true;

		}
		if(y<= 100){
			GameLogic.playerStatus.isEnd = true;
			GameLogic.playerStatus.isWin = false;
			dead = true;
		}

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
//		(float) hp/hpMax
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) hp/hpMax);
		g.setComposite(tran);
		
//		g.drawString(Integer.toString(column), x, y);
//		g.drawString(Integer.toString(hp), x, y+10);

		DrawingUtility.drawDragon(g, x, y, currentFrame);
		if(isFrozen){
			tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) ((float) hp/hpMax + 0.1));
			g.setComposite(tran);
			
			DrawingUtility.drawCoverDragon(g, x, y, currentFrame);
			if(frozenCount == frozenTime){
				frozenCount = 0;
				isFrozen = false;
			}else frozenCount++;
		}
		
		
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
		if(frameDelayCount==0){
			currentFrame++;
			frameDelayCount = 0;
		}else frameDelayCount++;
		if(currentFrame == 8) currentFrame = 0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !dead;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -1;
	}

}
