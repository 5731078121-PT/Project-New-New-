package logic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import render.GameScreen;
import render.IRenderable;
import utility.DrawingUtility;

public class Egg implements IRenderable{
	protected int speed = 6;
	protected int power = 10;
	
	protected int x, y;
	protected boolean destroyed;
	private Duck shooter;
	public int column;
	protected AlphaComposite tran;
	
	public Egg(Duck shooter) {
		// TODO Auto-generated constructor stub
		this.shooter = shooter;
		this.x = shooter.getX()+75/2;
		this.y = shooter.getY()+75/2;
		this.column = shooter.column;
		this.destroyed = false;
	}
	
	public void attackDragon(Dragon d){
		destroyed = true;
		d.decreaseHP(this.power);
	}
	
	public void update(){
		y += speed;
		if(y > GameScreen.HEIGHT){
			
			destroyed = true;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g.setComposite(tran);
		if(!destroyed){
			DrawingUtility.drawEgg(g, x, y+70/2);
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !destroyed;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -1;
	}

}
