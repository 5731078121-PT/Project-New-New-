package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utility.DrawingUtility;

public class PlayingArea implements IRenderable{
	private BufferedImage bgImage = null;
	private BufferedImage icon = null;
	private int currentX = 0;
	private int imageWidth;
	private int[][] table;
	private final int boxWidth = 75;
	
	public PlayingArea() {
		// TODO Auto-generated constructor stub
//		bgImage = DrawingUtility.bg;
//		if(bgImage != null){
//			imageWidth = bgImage.getWidth();
//		}else{
//			imageWidth = 0;
//		}
		table = new int[7][5];
	}
	
	public void updateBackground(){
		currentX++;
		if(currentX >= imageWidth){
			currentX = 0;
		}
	}
	
	public boolean canBePlaced(int x, int y){
		if(x >= 175 && x <= GameScreen.WIDTH - 50){
			if(y >= 125 && y <= GameScreen.HEIGHT - 50){
				for(int i = 0; i<7; i++){
					for(int j = 0; j<5; j++){
						if(table[i][j]==0){
							if(175 + j*boxWidth <= x && 175 + (j+1)*boxWidth >= x){
								if(125 + i*boxWidth <= y && 125 + (i+1)*boxWidth >= y) return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public int placedX(int x){
		x -= 175;
		
		for(int i = 0; i<5; i++){
			if(i*boxWidth <= x && (i+1)*boxWidth >= x){
				return 175+ i*boxWidth;
			}
		}
		return 150;
		
	}

	public int placedY(int y){
		y -= 125;
		
		for(int i = 0; i<7; i++){
			if(i*boxWidth <= y && (i+1)*boxWidth >= y){
				return 125+ i*boxWidth;
			}
		}
		return 100;
		
	}
	
	public void placed(int i,int j){
		table[i][j] = 1;
	}
	
	public void dead(int i, int j){
		table[i][j] = 0;
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
		g.setComposite(tran);
		g.setColor(Color.black);
		g.fillRoundRect(45, 125, 85, 75/2-1, 20, 20);
		g.fillRoundRect(50, 125+75/2+1, 75, 75-2, 20, 20);
		g.fillRoundRect(50, 200+75/2+1, 75, 75-2, 20, 20);
		g.fillRoundRect(50, 275+75/2+1, 75, 75-2, 20, 20);
		g.fillRoundRect(50, 275+75*3/2+1, 75, 75-2, 20, 20);
		g.fillRoundRect(50, 275+75*5/2+1, 75, 75-2, 20, 20);
		
		g.setColor(Color.white);
		for(int i = 0; i<7; i++){
			for(int j = 0; j<5; j++){
				if(table[i][j] == 0){
					g.fillRoundRect(175 + j*boxWidth -1, 125 + i*boxWidth -1, boxWidth-2, boxWidth-2, 20, 20);					
				}
			}
		}

		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
