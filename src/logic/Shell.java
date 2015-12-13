package logic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import input.InputUtility;
import render.IRenderable;
import utility.DrawingUtility;

public class Shell implements IRenderable {
	private float hpMax = 1500;
	private int price = 2;

	public int x,y,z;
	private int defaultX = 50, defaultY = 275+150+75/2;
	private int hp;
	private boolean canBuy;
	public boolean dead;
	private boolean bought;
	private AlphaComposite tran;
	private int i , count;
	public int column = -1;
	
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
		g.drawString(Integer.toString(column), x, y);
		g.drawString(Integer.toString(hp), x, y+10);
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (hp/hpMax));
		g.setComposite(tran);
		if(!dead)
			DrawingUtility.drawShell(g, x, y, i);
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
		if(bought){
			if(count==2){
				i++;
				count = 0;
			}else count++;
			if(i == 7) i = 1;
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
		if(InputUtility.isMouseLeftDownTrigger()){
			
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
