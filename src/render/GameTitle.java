package render;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import utility.AudioUtility;
import utility.DrawingUtility;
import utility.InputUtility;
import logic.GameLogic;
import main.Main;

public class GameTitle extends JPanel{
	
	private int index;
	private int indexBG;
	private int count;
	
	public GameTitle() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));

		index = 0;
		indexBG = 0;
		count = 0;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				// start but
				if(e.getX() >= 125 && e.getX() <= 360){
					if(e.getY() >= 565 && e.getY() <= 640) {
						if(!DrawingUtility.isMute) AudioUtility.universalClickSound.play();
						Main.isStart = true;
						Main.startGame();
					}
				}
				
				//info icon
				if(e.getX() >= 10 && e.getX() <= 10+125/3){
					if(e.getY() >= 650 && e.getY() <= 650+125/3) {
						if(!DrawingUtility.isMute) AudioUtility.universalClickSound.play();
						
					}
				}
				
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				InputUtility.setMouseX(arg0.getX());
				InputUtility.setMouseY(arg0.getY());
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {};
		});

		
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.clearRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		if(index == 24) {
			index = 0;
			indexBG++;

			if(indexBG >= 3) indexBG = 0;
		}
		
		DrawingUtility.drawGameTitle(g2,indexBG, index, count);
		
		
//		ACTION WHEN MOUSE OVER
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
		g2.setComposite(tran);

//		info icon
		if(InputUtility.getMouseX() >= 10 && InputUtility.getMouseX() <= 10+125/3){
			if(InputUtility.getMouseY() >= 650 && InputUtility.getMouseY() <= 650+125/3) {
				g2.setColor(Color.WHITE);
				g2.fillOval(10, 650, 125/3, 125/3);
			
		}
	}	
		
		
//		start but
		if(InputUtility.getMouseX() >= 125 && InputUtility.getMouseX() <= 360){
			if(InputUtility.getMouseY() >= 565 && InputUtility.getMouseY() <= 640) {
			
				BufferedImage startButtonCoverUse = DrawingUtility.startButtonCover.getSubimage((count%4)*600, 0, 600, 700);
				g2.drawImage(startButtonCoverUse, null, 0, 0);

			}
		}


		if(count == 24) count = 0;
		if(count%2 == 0) index++;
		count++;
	}
}
