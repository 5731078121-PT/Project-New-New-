package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import input.InputUtility;
import logic.GameLogic;
import logic.PlayerStatus;
import main.Main;
import utility.DrawingUtility;
import utility.GameSaveUtility;

public class GameBegin extends JComponent {
	private String[] data;
	private String[] name;
	private int[] state;	
	private int[] star;
	private int index;
	
	public GameBegin() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		System.out.println("in");
		
		String str = GameSaveUtility.displayPlayer();
		data = new String[5];
		name = new String[5];
		state = new int[5];
		star = new int[5];
		data = str.split("\n");
		updateData();
		index = -1;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(e.getButton() == 1){
					if(450 <= e.getY() && e.getY() <= 510){
						if(200 <= e.getX() && e.getX() <= 295){
							GameLogic.playerStatus = new PlayerStatus(name[3], state[3], star[3]);
							Main.runGame();
						}
						else if(305 <= e.getX() && e.getX() <= 400){
							if(index != -1){
								GameSaveUtility.removePlayer(index);
							}
						}
					}
					
					if(!InputUtility.isMouseLeftDown()){
						InputUtility.setMouseLeftDownTrigger(true);
					}else{
						InputUtility.setMouseLeftDownTrigger(false);
					}
					InputUtility.setMouseLeftDown(true);
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				if(e.getButton() == 1){
					InputUtility.setMouseLeftDownTrigger(false);
					InputUtility.setMouseLeftDown(false);
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
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void updateData(){
		for(int i = 0; i<5; i++){
			name[i] = data[i].substring(0, data[i].indexOf(":"));
			if(name[i].equals("")) name[i] = "no name";
			state[i] = Integer.parseInt(data[i].substring(data[i].indexOf(":")+1, data[i].indexOf(";")));
			data[i] = data[i].trim();
			star[i] = Integer.parseInt(data[i].substring(data[i].indexOf(";")+1, data[i].length()));
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AlphaComposite noTran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2.setComposite(noTran);
		g2.setColor(new Color(255/2, 0, 255/2));
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		g2.setColor(Color.DARK_GRAY);
		g2.fillRoundRect(200, 450, 95, 60, 30, 30);
		g2.fillRoundRect(305, 450, 95, 60, 30, 30);
		g2.setFont(DrawingUtility.standardFont);
		
		for(int i = 0; i<name.length; i++){
			g2.setColor(Color.DARK_GRAY);
			g2.fillRoundRect(200, 125 + i*65, 200, 60, 30, 30);
			g2.setColor(Color.white);
			Rectangle2D rec = g.getFontMetrics().getStringBounds(name[i], g);
			g2.drawString(name[i], (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),170 + i* 65);
			
		}
		
		g2.setFont(DrawingUtility.smallFont);
		
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
		g2.setComposite(tran);
		
		g2.setColor(Color.pink);
		if(450 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 510){
			if(200 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 295){
				g2.fillRoundRect(200, 450, 95, 60, 30, 30);
				System.out.println("in");
			}
			else if(305 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 400){
				g2.fillRoundRect(305, 450, 95, 60, 30, 30);
			}
		}
		else if(200 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 400){
			if(125 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60){
				g2.fillRoundRect(200, 125 + 0*65, 200, 60, 30, 30);
			}else if(125+1*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*2){
				g2.fillRoundRect(200, 125 + 1*65, 200, 60, 30, 30);
			}else if(125+2*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*3){
				g2.fillRoundRect(200, 125 + 2*65, 200, 60, 30, 30);
			}else if(125+3*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*4){
				g2.fillRoundRect(200, 125 + 3*65, 200, 60, 30, 30);
			}else if(125+4*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*5){
				g2.fillRoundRect(200, 125 + 4*65, 200, 60, 30, 30);
			}
		}
		
	}
}
