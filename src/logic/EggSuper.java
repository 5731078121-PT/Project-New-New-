package logic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import utility.DrawingUtility;

public class EggSuper extends Egg {
	
	public EggSuper(Duck shooter) {
		// TODO Auto-generated constructor stub
		super(shooter);
		this.power = 25;
	}
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(tran);
		
		if(!destroyed){
			DrawingUtility.drawSuperEgg(g, x, y+70/2);
		}
	}
}
