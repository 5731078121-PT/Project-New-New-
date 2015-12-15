package logic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import render.RenderableHolder;
import utility.AudioUtility;
import utility.DrawingUtility;
import utility.InputUtility;

public class Duck implements IRenderable{
	protected float hpMax = 500;
	protected int price = 4;
	protected int coolDown = 70;

	protected int defaultX = 50, defaultY = 125+75/2;
	public int x, y;
	protected int z;
	protected int hp;
	protected int eggDelay , eggDelayCounter;
	protected boolean canBuy;
	public boolean dead;
	protected boolean bought;
	public boolean haveDragon;
	protected AlphaComposite tran;
	protected int currentFrame=0, frameDelayCount = 0;
	public int column= -1;
	protected int coolDownCounter;
	protected int stageLock=0;
	
	public Duck(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.hp = (int) hpMax;
		this.eggDelay = 40;
		this.dead = false;
		this.bought = false;
		this.haveDragon = false;
	}
	
	public void update(){
		if(!dead){
			
			if(!canBuy) getDuck();
			if(canBuy && !bought){
				if(InputUtility.isMouseLeftDown()){
					InputUtility.setMouseLeftDownTrigger(false);
					this.x = InputUtility.getMouseX()-75/2;
					this.y = InputUtility.getMouseY()-75/2;
					this.z = Integer.MAX_VALUE;
					
				}
				if(!InputUtility.isMouseLeftDown() && InputUtility.isMouseLeftDownUp()){
					if(GameLogic.playingArea.canBePlaced(InputUtility.getMouseX(), InputUtility.getMouseY())){
						this.x = GameLogic.playingArea.placedX(InputUtility.getMouseX());
						this.y = GameLogic.playingArea.placedY(InputUtility.getMouseY());
						this.column = (x-175)/75;
						GameLogic.playingArea.placed((y-125)/75, column);
						this.bought = true;
						GameLogic.playerStatus.setMoney(GameLogic.playerStatus.getMoney()-this.price);
						this.z = 0;
						GameLogic.newDuck = true;
					}else{
						this.x = defaultX;
						this.y = defaultY;
						this.canBuy = false;
						this.z = 0;
					}
				}
			}
			
			
			if(bought&&haveDragon){
				if(eggDelay == eggDelayCounter){
					eggDelayCounter = 0;
					if(!AudioUtility.isMute) AudioUtility.layEggSound.play();
					RenderableHolder.getInstance().add(new Egg(this));
				
				}else eggDelayCounter++;
				if(hp == 0 ){
					GameLogic.playingArea.dead((y-125)/75, (x-175)/75);
					dead = true;
				}
				
				
			}
			
		}
		
		
		
	}

	public void getDuck(){
		if(coolDown != coolDownCounter) return;
		if(InputUtility.isMouseLeftDownTrigger() && GameLogic.playerStatus.getMoney()>=this.price && GameLogic.playerStatus.getStage()>this.stageLock){
//			GameLogic.newDuck = false;
			if(defaultX <= InputUtility.getMouseX() && defaultX+75 >= InputUtility.getMouseX()){
			
				if(defaultY <= InputUtility.getMouseY() && defaultY+75 >= InputUtility.getMouseY()){
					canBuy = true;
					InputUtility.setMouseLeftDownTrigger(false);
				}
			}
		}
		else canBuy = false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if(coolDown > coolDownCounter){
			coolDownCounter++;
			tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) coolDownCounter/coolDown);
			
		}else tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) hp/hpMax);
		g.setComposite(tran);
		
		DrawingUtility.drawDuck(g, x, y, currentFrame);
//		g.drawString(Integer.toString(column), x, y);
//		g.drawString(Integer.toString(hp), x, y+10);
		
		
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
		if(bought){
			if(currentFrame == 0) currentFrame = 3;
			if(frameDelayCount==2){
				currentFrame++;
				frameDelayCount = 0;
			}else frameDelayCount++;
			if(currentFrame == 9) currentFrame = 3;
		}
		
		if(!(GameLogic.playerStatus.getStage()>this.stageLock)){
			DrawingUtility.drawLockDuck(g, defaultY, stageLock);
			
		}
		
				
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !dead;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	public void decreaseHP(int power) {
		// TODO Auto-generated method stub
		if(this.hp > power){
			this.hp -= power;
		}
		else this.hp = 0;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
	
}