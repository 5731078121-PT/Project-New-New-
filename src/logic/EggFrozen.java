package logic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import utility.DrawingUtility;

public class EggFrozen extends Egg {

	public EggFrozen(Duck shooter) {
		super(shooter);
		// TODO Auto-generated constructor stub
		this.power = 15;
	}
	
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(tran);
		
		if(!destroyed){
			DrawingUtility.drawFrozenEgg(g, x, y+70/2);
		}
	}

}
