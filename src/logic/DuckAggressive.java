package logic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import input.InputUtility;
import render.RenderableHolder;
import utility.DrawingUtility;

public class DuckAggressive extends Duck{
	private int power = 1;
	private boolean isJig;

	public DuckAggressive(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.hpMax = 200;
		this.hp = (int) this.hpMax;
		this.eggDelay = 0;
		this.eggDelayCounter = this.eggDelay;
		this.isJig = true;
		this.defaultX = 50;
		this.defaultY = 275 + 75/2;
		this.price = 6;
	}
	
	public void update(){
		if(!dead){
			
			if(!canBuy) getDuck();
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
						GameLogic.playerStatus.setMoney(GameLogic.playerStatus.getMoney()-this.price);
						this.z = 0;
						GameLogic.newAggressiveDuck = true;
					}else{
						this.x = defaultX;
						this.y = defaultY;
						this.canBuy = false;
						this.z = 0;
					}
				}
			}
			
			
//			if(bought&&haveDragon){
//				if(eggDelay == eggDelayCounter){
//					eggDelayCounter = 0;
//					RenderableHolder.getInstance().add(new Egg(this));
//				
//				}else eggDelayCounter++;
//				if(hp == 0 ){
//					GameLogic.playingArea.dead((y-125)/75, (x-175)/75);
//					dead = true;
//				}
//				
//				
//			}
			
		}	
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) hp/hpMax);
		g.setComposite(tran);
		
		DrawingUtility.drawAggresDuck(g, x, y, i);
		g.drawString(Integer.toString(column), x, y);
		g.drawString(Integer.toString(hp), x, y+10);
		
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
		if(bought){
			if(isJig){
				System.out.println("draw Jig");
				if(i < 10) i = 10;
				if(count == 2){
					i++;
					count = 0;
				}else count++;
				if(i == 14) i = 10;
			}
			else{
				if(i>9) i = 9;
				if(count==2){
					i++;
					count = 0;
				}else count++;
				if(i == 9) i = 1;				
			}
		}
		
				
	}
	
	public void attackDragon(Dragon dragon){
		dragon.decreaseHP(this.power);
		System.out.println("in");
	}
	
	public boolean isJig() {
		return isJig;
	}
	
	public void setJig(boolean isJig) {
		this.isJig = isJig;
	}
	
}
