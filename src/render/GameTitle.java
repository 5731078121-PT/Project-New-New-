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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.GameLogic;
import main.Main;

public class GameTitle extends JPanel{
	public GameTitle() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		JButton start = new JButton("START!");
		this.setBackground(Color.black);
		this.add(start, BorderLayout.SOUTH);
		
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Main.isStart = true;
				Main.runGame();
			}
		});
		this.setVisible(true);
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
//		g2.setComposite(tran);
//		g2.setColor(Color.black);
//		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
//	
//	}
}
