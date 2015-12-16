package logic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import utility.DrawingUtility;
import utility.InputUtility;

public class Shell implements IRenderable {
	private float hpMax = 3500;
	private int price = 2;
	private int coolDown = 50;
	
	public int x,y,z,hp;
	private int defaultX = 50, defaultY = 275+150+75/2;
	private boolean canBuy;
	public boolean dead;
	private boolean bought;
	private AlphaComposite tran;
	private int currentFrame , frameDelayCount;
	public int column = -1;
	private int coolDownCounter;
	private int stageLock=0;
	
	public Shell(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.hp = (int) hpMax;
		this.dead = false;
		
	}
	
	public void decreaseHP(int s){
		if(hp > s) hp -= s;
		else hp = 0;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
//		g.drawString(Integer.toString(column), x, y);
//		g.drawString(Integer.toString(hp), x, y+10);
		if(coolDown > coolDownCounter){
			coolDownCounter++;
			tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) coolDownCounter/coolDown);
			
		}else tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) hp/hpMax);
		g.setComposite(tran);
		if(!dead)
			DrawingUtility.drawShell(g, x, y, currentFrame);
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
		if(bought){
			if(frameDelayCount==2){
				currentFrame++;
				frameDelayCount = 0;
			}else frameDelayCount++;
			if(currentFrame == 7) currentFrame = 1;
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(!canBuy) getShell();
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
					GameLogic.newShell = true;

				}else{
					this.x = defaultX;
					this.y = defaultY;
					this.canBuy = false;
					this.z = 0;
				}
			}
		}
		if(bought){
			if(hp == 0){
				
				GameLogic.playingArea.dead((y-125)/75, (x-175)/75);
				dead = true;
			}
		}
		
		
		
	}
	
	public void getShell(){
		if(coolDown != coolDownCounter) return;
		if(InputUtility.isMouseLeftDownTrigger()&& GameLogic.playerStatus.getMoney()>=this.price && GameLogic.playerStatus.getStage()>this.stageLock){
			
			if(defaultX <= InputUtility.getMouseX() && defaultX+75 >= InputUtility.getMouseX()){
								
				if(defaultY <= InputUtility.getMouseY() && defaultY+75 >= InputUtility.getMouseY()){
					canBuy = true;
					InputUtility.setMouseLeftDownTrigger(false);
				}
			}
		}
		else canBuy = false;
	}

}
