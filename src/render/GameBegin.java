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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	private int mouseClickX  = -1, mouseClickY = -1;
	private boolean isIntroGame = false;
	
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
					if(isIntroGame) {
						if(620 <= e.getY() && e.getY() <= 645){
							if(50 <= e.getX() && e.getX() <= 75){
								isIntroGame = false;
							}
						}
						return;
					}
					if(475 <= e.getY() && e.getY() <= 575){
//						click PLAY but
						if(375/2+125 <= e.getX() && e.getX() <= 375/2+225){
							if(index != -1 && !name[index].equals("no name")){
								GameLogic.playerStatus = new PlayerStatus(name[index], state[index], star[index]);
								GameSaveUtility.recordData();
								Main.runGame();
								
							}
						}
//						click BIN but
						else if(375/2 <= e.getX() && e.getX() <= 375/2+100){
							if(index != -1){
								System.out.println("binbin");
								GameSaveUtility.removePlayer(index);
								String str = GameSaveUtility.updateData();
								data = str.split("\n");
								mouseClickX = -1;
								mouseClickY = -1;
								updateData();							
							}
						}
					}
										
					if(375/2 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 375/2+225){
						if(125 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60){
							mouseClickX = e.getX();
							mouseClickY = e.getY();
							index = 0;
							noName(index);
						}else if(125+1*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*2){
							mouseClickX = e.getX();
							mouseClickY = e.getY();
							index = 1;
							noName(index);
						}else if(125+2*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*3){
							mouseClickX = e.getX();
							mouseClickY = e.getY();
							index = 2;
							noName(index);
						}else if(125+3*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*4){
							mouseClickX = e.getX();
							mouseClickY = e.getY();
							index = 3;
							noName(index);
						}else if(125+4*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*5){
							mouseClickX = e.getX();
							mouseClickY = e.getY();
							index = 4;
							noName(index);
						}
					}
					
					else{
						mouseClickX = -1;
						mouseClickY = -1;
					}
					
					if(e.getY() >= 650 && e.getY() <= 650+125/3){
						if(e.getX() >= 10 && e.getX() <= 10+125/3){
							Main.titleScene();
						}
					}
					
					if(e.getY() >= 650 && e.getY() <= 650+125/3){
						if(e.getX() >= 590-125/3 && e.getX() <= 590){
							isIntroGame = true;
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
	
	private void noName(int index){
		if(!name[index].equals("no name")) return ;
		String name = JOptionPane.showInputDialog(new JFrame(), "Enter your name", "Welcome", JOptionPane.QUESTION_MESSAGE);
		name = name.toUpperCase();
		GameSaveUtility.addPlayer(name);
		String str = GameSaveUtility.updateData();
		data = str.split("\n");
		updateData();
		return ;
		

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
		
		g2.clearRect(0, 0, 600, 700);
		g2.setColor(Color.WHITE);
		
		
//		background
/*		AlphaComposite noTran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2.setComposite(noTran);
		g2.setColor(new Color(255/2, 0, 255/2));
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);*/
		DrawingUtility.drawGameBegin(g2);
		
		
		System.out.println(index);
		
//		action when PLAY'S NAME is clicked
		g2.setColor(Color.white);
		if(mouseClickX != -1 && mouseClickY != -1){
			if(375/2 <= mouseClickX && mouseClickX <= 375/2 + 225){
				if(125 <= mouseClickY && mouseClickY <= 125+60){
					g2.fillRoundRect(375/2 - 5, 120 + 0*65, 235, 70, 35, 35);
				}else if(125+1*65 <= mouseClickY && mouseClickY <= 125+60*2){
					g2.fillRoundRect(375/2 - 5, 120 + 1*65, 235, 70, 35, 35);
				}else if(125+2*65 <= mouseClickY && mouseClickY <= 125+60*3){
					g2.fillRoundRect(375/2 - 5, 120 + 2*65, 235, 70, 35, 35);
				}else if(125+3*65 <= mouseClickY && mouseClickY <= 125+60*4){
					g2.fillRoundRect(375/2 - 5, 120 + 3*65, 235, 70, 35, 35);
				}else if(125+4*65 <= mouseClickY && mouseClickY <= 125+60*5){
					g2.fillRoundRect(375/2 - 5, 120 + 4*65, 235, 70, 35, 35);
				}
			}
		}
		
		/*//button PLAY and BIN
		g2.setColor(Color.DARK_GRAY);
		g2.fillRoundRect(200, 450, 95, 60, 30, 30);
		g2.fillRoundRect(305, 450, 95, 60, 30, 30);*/
		g2.setFont(DrawingUtility.standardFont);
		
//		button PLAYER'S NAME
		for(int i = 0; i<name.length; i++){
			g2.setColor(Color.DARK_GRAY);
			g2.fillRoundRect(375/2, 125 + i*65, 225, 60, 30, 30);
			g2.setColor(Color.white);
			Rectangle2D rec = g.getFontMetrics().getStringBounds(name[i], g);
			g2.drawString(name[i], (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),170 + i* 65);
			
		}
		
		g2.setFont(DrawingUtility.smallFont);
		
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
		g2.setComposite(tran);
//		action when mouse over
		g2.setColor(Color.WHITE);
		if(475 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 575 && !isIntroGame){
			if(375/2 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 375/2+100){
				g2.fillRoundRect(375/2, 475, 100, 100, 40, 40);
//				System.out.println("in");
			}
			else if(375/2+125 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 375/2+225){
				g2.fillRoundRect(375/2 +125, 475, 100, 100, 40, 40);
			}
		}
		//BACK BUT
		else if(InputUtility.getMouseY() >= 650 && InputUtility.getMouseY() <= 650+125/3 && !isIntroGame){
			if(InputUtility.getMouseX() >= 10 && InputUtility.getMouseX() <= 10+125/3){
				g2.drawImage(DrawingUtility.backCover, DrawingUtility.resizeBg, 10, 650);
			}
		}
		
		//INTROGAME BUT
		else if(InputUtility.getMouseY() >= 650 && InputUtility.getMouseY() <= 650+125/3 && !isIntroGame){
			if(InputUtility.getMouseX() >= 590-125/3 && InputUtility.getMouseX() <= 590){
				g2.fillOval(690, 650, 125/3, 125/3);
			}
		}
		
		
		else if(375/2 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 375/2+225 && !isIntroGame){
			if(125 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60){
				g2.fillRoundRect(375/2, 125 + 0*65, 225, 60, 30, 30);
			}else if(125+1*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*2){
				g2.fillRoundRect(375/2, 125 + 1*65, 225, 60, 30, 30);
			}else if(125+2*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*3){
				g2.fillRoundRect(375/2, 125 + 2*65, 225, 60, 30, 30);
			}else if(125+3*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*4){
				g2.fillRoundRect(375/2, 125 + 3*65, 225, 60, 30, 30);
			}else if(125+4*65 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 125+60*5){
				g2.fillRoundRect(375/2, 125 + 4*65, 225, 60, 30, 30);
			}
		}
		
		if(isIntroGame){
			DrawingUtility.drawIntroGame(g2);
			if(620 <= InputUtility.getMouseY() && InputUtility.getMouseY() <= 645){
				if(50 <= InputUtility.getMouseX() && InputUtility.getMouseX() <= 75){
					tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
					g2.setComposite(tran);
					g2.drawImage(DrawingUtility.backCover, DrawingUtility.resizeHalf, 50, 620);
				}
			}
		}
		
		
	}
}
