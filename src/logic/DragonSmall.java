package logic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import utility.DrawingUtility;

public class DragonSmall extends Dragon {

	public DragonSmall(int x) {
		super(x);
		// TODO Auto-generated constructor stub
		this.speed = 3;
		this.hpMax = 20;
		this.hp = (int) this.hpMax;
		this.i = RandomUtility.random(0, 7);
				
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) hp/hpMax);
		g.setComposite(tran);

		g.drawString(Integer.toString(column), x, y);
		g.drawString(Integer.toString(hp), x, y+10);
		
		DrawingUtility.drawSmallDragon(g, x, y, i);
		if(isFrozen){
			tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) ((float) hp/hpMax + 0.1));
			g.setComposite(tran);
			
			DrawingUtility.drawCoverSmallDragon(g, x, y, i);
			if(frozenCount == frozenTime){
				frozenCount = 0;
				isFrozen = false;
			}else frozenCount++;
		}
		
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
		if(count==1){
			i++;
			count = 0;
		}else count++;
		if(i == 8) i = 0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !dead;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
