package utility;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.oracle.webservices.internal.api.databinding.Databinding.Builder;

import logic.PlayerStatus;
import render.GameScreen;

public class DrawingUtility {
	public static final Font standardFont = new Font("Comic Sans MS", Font.BOLD, 30);
	public static final Font nomalFont = new Font("Comic Sans MS", Font.BOLD, 15);
	public static final Font smallFont = new Font("Tahoma", Font.PLAIN, 10);
	
	protected static BufferedImage getImage(String directory){
		ClassLoader loader = DrawingUtility.class.getClassLoader();
		try{
			return ImageIO.read(loader.getResource(directory));
		}catch(IOException e){
			return null;
		}
	}

	public static BufferedImage duckPic = getImage("res/img/duck-all-new3.png");
	public static BufferedImage superDuckPic = getImage("res/img/superDuck-all-new3.png");
	public static BufferedImage aggresDuckPic = getImage("res/img/aggresDuck-all-jig.png");
	public static BufferedImage frozenDuckPic = getImage("res/img/frozenDuck-all.png");
	public static BufferedImage star = getImage("res/img/star-all.png");
	public static BufferedImage dragon = getImage("res/img/toothless-all-new.png");
	public static BufferedImage superDragon = getImage("res/img/toothlessSuper-all-new.png");
	public static BufferedImage smallDragon = getImage("res/img/toothlessSmall-all-new.png");
	public static BufferedImage coverDragon = getImage("res/img/toothless-all-cover.png");
	public static BufferedImage coverSmallDragon = getImage("res/img/toothlessSmall-all-cover.png");
	public static BufferedImage egg = getImage("res/img/egg.png");
	public static BufferedImage superEgg = getImage("res/img/superEgg.png");
	public static BufferedImage frozenEgg = getImage("res/img/frozenEgg.png");
	public static BufferedImage shell = getImage("res/img/shell-all-new.png");
	public static BufferedImage home = getImage("res/img/home-but.png");
	public static BufferedImage next = getImage("res/img/next-but.png");
	public static BufferedImage play = getImage("res/img/play-but.png");
	public static BufferedImage sound = getImage("res/img/sound-but-all.png");
	public static BufferedImage replay = getImage("res/img/replay-but.png");
	public static BufferedImage bin = getImage("res/img/bin-but.png");
	public static BufferedImage back = getImage("res/img/back-but.png");
	public static BufferedImage backCover = getImage("res/img/back-but-cover.png");
	public static BufferedImage winLine = getImage("res/img/winLine.png");
	public static BufferedImage gameName = getImage("res/img/GameName.png");
	public static BufferedImage introGame = getImage("res/img/introGame.png");
	public static BufferedImage introGameBut = getImage("res/img/introGame-but.png");
	public static BufferedImage[] bg = new BufferedImage[7];
	
	public static BufferedImage[] startBG = new BufferedImage[4];
	public static BufferedImage startInfoGame = getImage("res/img/infoGame.png");
	public static BufferedImage startInfoBut = getImage("res/img/info-but.png");
	public static BufferedImage startDragon = getImage("res/img/start-dragon-all.png");
	public static BufferedImage startButton = getImage("res/img/start-but-all.png");
	public static BufferedImage startButtonCover = getImage("res/img/start-but-all-cover.png");
	public static BufferedImage startDuck = getImage("res/img/start-duck-all.png");
	public static BufferedImage startName = getImage("res/img/start-name-all.png");
	
	
	public static void createStartBg(){
		startBG[0] = getImage("res/img/start-bg.png");
		startBG[1] = getImage("res/img/start-bg2.png");
		startBG[2] = getImage("res/img/start-bg3.png");
		startBG[3] = getImage("res/img/start-bg.png");
	}
	
	public static void createBg(){
		bg[0] = getImage("res/img/background-1.png");
		bg[1] = getImage("res/img/background-2.png");
		bg[2] = getImage("res/img/background-3.png");
		bg[3] = getImage("res/img/background-4.png");
		bg[4] = getImage("res/img/background-5.png");
		bg[5] = getImage("res/img/background-6.png");
		bg[6] = getImage("res/img/background-7.png");
		
	}
	
	
//	public static int duckCount = 0;
//	public static final AffineTransformOp resizeDuck = new AffineTransformOp(AffineTransform.getScaleInstance(0.75/2, 0.75/2), AffineTransformOp.TYPE_BICUBIC);
//	public static final AffineTransformOp resizeDragon = new AffineTransformOp(AffineTransform.getScaleInstance(0.75/5, 0.75/5), AffineTransformOp.TYPE_BICUBIC);
//	public static final AffineTransformOp resizeStar = new AffineTransformOp(AffineTransform.getScaleInstance(0.3, 0.3), AffineTransformOp.TYPE_BICUBIC);
//	public static final AffineTransformOp resizeEgg = new AffineTransformOp(AffineTransform.getScaleInstance(0.25, 0.25), AffineTransformOp.TYPE_BICUBIC);
//	public static final AffineTransformOp resizeShell = new AffineTransformOp(AffineTransform.getScaleInstance(0.75/2, 0.75/2), AffineTransformOp.TYPE_BICUBIC);
//	public static final AffineTransformOp resizeBg = new AffineTransformOp(AffineTransform.getScaleInstance(1/1.2, 1/1.2), AffineTransformOp.TYPE_BICUBIC);
//	public static final AffineTransformOp resizeHalf = new AffineTransformOp(AffineTransform.getScaleInstance(0.5,0.5), AffineTransformOp.TYPE_BICUBIC);
//	public static final AffineTransformOp resizeTwoFive = new AffineTransformOp(AffineTransform.getScaleInstance(0.4,0.4), AffineTransformOp.TYPE_BICUBIC);
	
	public static final AffineTransformOp resizeDuck = null;
	public static final AffineTransformOp resizeDragon = null;
	public static final AffineTransformOp resizeStar = null;
	public static final AffineTransformOp resizeEgg = null;
	public static final AffineTransformOp resizeShell = null;
	public static final AffineTransformOp resizeBg = null;
	public static final AffineTransformOp resizeHalf = null;
	public static final AffineTransformOp resizeTwoFive = new AffineTransformOp(AffineTransform.getScaleInstance(0.4,0.4), AffineTransformOp.TYPE_BICUBIC);
	public static final AffineTransformOp resizeBut = new AffineTransformOp(AffineTransform.getScaleInstance(1/1.2, 1/1.2), AffineTransformOp.TYPE_BICUBIC);
	
	public static void drawBg(Graphics2D g, int i){
		g.drawImage(bg[i], resizeBg, 0, 0);
	}
	
	public static void drawDuck(Graphics2D g, int x, int y, int duckCount){
		BufferedImage duckUse = duckPic.getSubimage(duckCount*75, 0, 75, 75);
		g.drawImage(duckUse, resizeDuck, x, y);
		
	}
	
	public static void drawSuperDuck(Graphics2D g, int x, int y, int duckCount){
		BufferedImage duckUse = superDuckPic.getSubimage(duckCount*75, 0, 75, 75);
		g.drawImage(duckUse, resizeDuck, x, y);
	}
	
	public static void drawAggresDuck(Graphics2D g, int x, int y, int i){
		BufferedImage duckUse = aggresDuckPic.getSubimage(i*75, 0, 75, 75);
		g.drawImage(duckUse, resizeDuck, x, y);
	}
	
	public static void drawFrozenDuck(Graphics2D g, int x, int y, int i){
		BufferedImage duckUse = frozenDuckPic.getSubimage(i*75, 0, 75, 75);
		g.drawImage(duckUse, resizeDuck, x, y);
	}

	public static void drawDragon(Graphics2D g, int x, int y, int i){
		/* fill code */
		int xx = i*75;
		BufferedImage dragonUse = dragon.getSubimage(xx, 0, 75, 105);
		g.drawImage(dragonUse, resizeDragon, x, y);
	}
	
	public static void drawSuperDragon(Graphics2D g, int x, int y, int i){
		BufferedImage dragonUse = superDragon.getSubimage(i*75, 0, 75, 105);
		g.drawImage(dragonUse, resizeDragon, x, y);
		
	}
	
	public static void drawSmallDragon(Graphics2D g, int x, int y, int i){
		BufferedImage dragonUse = smallDragon.getSubimage(i*75, 0, 75, 105);
		g.drawImage(dragonUse, resizeDragon, x, y);
	}

	public static void drawCoverDragon(Graphics2D g, int x, int y, int i){
		BufferedImage dragonUse = coverDragon.getSubimage(i*75, 0, 75, 105);
		g.drawImage(dragonUse, resizeDragon, x, y);
	}
	
	public static void drawCoverSmallDragon(Graphics2D g, int x, int y, int i){
		BufferedImage dragonUse = coverSmallDragon.getSubimage(i*75, 0, 75, 105);
		g.drawImage(dragonUse, resizeDragon, x, y);
	}
	
	public static void drawEgg(Graphics2D g,int x, int y){
		/* fill code */
		g.drawImage(egg, resizeEgg, x-25/2, y-5 );
	}
	
	public static void drawSuperEgg(Graphics2D g, int x, int y){	
		g.drawImage(superEgg, resizeEgg, x-25/2, y-5);
	}
	
	public static void drawFrozenEgg(Graphics2D g, int x, int y){
		g.drawImage(frozenEgg, resizeEgg, x-25/2, y-5);
	}
	
	public static void drawShell(Graphics2D g, int x, int y, int i){
		BufferedImage shellUse = shell.getSubimage(i*75, 0, 75, 75);
		g.drawImage(shellUse, resizeShell, x, y);
	}
		
	public static void drawStar(Graphics2D g, int x, int y, int i){
		BufferedImage starUse = star.getSubimage(i*30, 0, 30, 30);
		g.drawImage(starUse, resizeStar, x, y);
	}
	
	public static void drawGameName(Graphics2D g, int x, int y){
//		g.drawImage(gameName, resizeTwoFive, x, y);
		g.drawImage(gameName, null, x, y);
	}
	
	public static void drawPauseScreen(Graphics2D g){
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
		g.setComposite(tran);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		AlphaComposite noTran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g.setComposite(noTran);
		
		g.setFont(standardFont);
		Rectangle2D rec = g.getFontMetrics().getStringBounds("PAUSE", g);
		g.drawString("PAUSE", (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),475/2 - 25);
		g.setFont(smallFont);
		
		g.drawImage(play, resizeHalf, 125+375/2, 475/2);
		
		if(AudioUtility.isMute) g.drawImage(sound.getSubimage(100, 0, 100, 100), resizeHalf, 125+375/2, 475/2+125);
		else g.drawImage(sound.getSubimage(0, 0, 100, 100), resizeHalf, 125+375/2, 475/2+125);
		
		g.drawImage(home, resizeHalf, 375/2, 475/2+125);
		g.drawImage(replay, resizeHalf, 375/2, 475/2);
		
	}
	
	public static void drawLoseScreen(Graphics2D g, boolean timesUpLose){
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
		g.setComposite(tran);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		AlphaComposite noTran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(noTran);
		
		g.setColor(Color.white);
		g.setFont(standardFont);

		Rectangle2D rec = g.getFontMetrics().getStringBounds("YOU LOSE!", g);
		g.drawString("YOU LOSE!", (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),475/2+25);
		
		if(timesUpLose){
			rec = g.getFontMetrics().getStringBounds("TIME'S UP", g);
			g.drawString("TIME'S UP", (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),475/2-25);
		}
		else{
			rec = g.getFontMetrics().getStringBounds("DRAGON REACHED GOAL", g);
			g.drawString("DRAGON REACHED GOAL", (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),475/2-25);
		}
		
		g.setFont(smallFont);
	
		g.drawImage(home, resizeHalf, 375/2, 475/2+50);
		g.drawImage(replay, resizeHalf, 375/2 + 125, 475/2 + 50);
	}
	
	public static void drawWinScreen(Graphics2D g, int stage){
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
		g.setComposite(tran);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		g.setColor(Color.white);
		
		AlphaComposite noTran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(noTran);
		
		g.setFont(standardFont);
		
		Rectangle2D rec = g.getFontMetrics().getStringBounds("STAGE "+stage+" CLEAR", g);
		g.drawString("STAGE "+stage+" CLEAR", (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),475/2 + 25);
		rec = g.getFontMetrics().getStringBounds("YOU WIN!", g);
		g.drawString("YOU WIN!", (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),475/2 - 25);
		g.setFont(smallFont);
		
		g.drawImage(home, resizeHalf, 375/2, 475/2+50);
		g.drawImage(next, resizeHalf, 375/2 + 125, 475/2 + 50);
		
	
	}
	
	public static void drawWinLine(Graphics2D g, int time){
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
		g.setComposite(tran);

		AffineTransformOp resizeWinLine = new AffineTransformOp(AffineTransform.getScaleInstance(0.5,0.5), AffineTransformOp.TYPE_BICUBIC);
		BufferedImage sub = winLine.getSubimage(0, 0, winLine.getWidth(), winLine.getHeight()*2/3);
		
		g.drawImage(sub, resizeWinLine, 175, 125-45/2);
		int r = winLine.getWidth() * time/ PlayerStatus.timeMax;
		if(r < 4) r = 4;

		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(tran);
		sub = winLine.getSubimage(0, 0, r, winLine.getHeight()*2/3);
		g.drawImage(sub, resizeWinLine, 175, 125-45/2);
	}
	
	public static void drawGameTitle(Graphics2D g,int iBG, int i, int ibut){
		
		g.clearRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		createStartBg();
		
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1-i*0.04f);
		g.setComposite(tran);
		g.drawImage(startBG[iBG], null, 0, 0);
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i*0.04f);
		g.setComposite(tran);
		g.drawImage(startBG[iBG+1], null, 0, 0);
		
		
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(tran);
		
		BufferedImage startDragonUse = startDragon.getSubimage((ibut%8)*600, 0, 600, 700);
		g.drawImage(startDragonUse, null, 0, 0);
		
		BufferedImage startNameUse = startName.getSubimage((i%2)*600, 0, 600, 700);
		g.drawImage(startNameUse, null, 0, 0);
		
		BufferedImage startDuckUse = startDuck.getSubimage((i%12)*600, 0, 600, 700);
		g.drawImage(startDuckUse, null, 0, 0);
		
		
		BufferedImage startButtonUse = startButton.getSubimage((ibut%4)*600, 0, 600, 700);
		g.drawImage(startButtonUse, null, 0, 0);
		
		
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(tran);
		
		g.drawImage(startInfoBut, resizeBut, 10, 650);
		if(AudioUtility.isMute) g.drawImage(sound.getSubimage(100, 0, 100, 100), resizeDragon, 560, 660);
		else g.drawImage(sound.getSubimage(0, 0, 100, 100), resizeDragon, 560, 660);
		
	}
	
	public static void drawInfoGame(Graphics2D g){
		
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.92f);
		g.setComposite(tran);
		g.drawImage(startInfoGame, null, 0, 0);

	}
	
	public static void drawGameBegin(Graphics2D g){
		
		g.clearRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
		g.setComposite(tran);
		g.drawImage(startBG[2], null, 0, 0);
		
		g.setColor(Color.white);
		
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g.setComposite(tran);
		
		g.setFont(standardFont);
		Rectangle2D rec = g.getFontMetrics().getStringBounds("CHOOSE PLAYER", g);
		g.drawString("CHOOSE PLAYER", (int) (GameScreen.WIDTH/2 - rec.getWidth()/2),100);
		g.setFont(smallFont);
		
		g.drawImage(bin, resizeHalf, 375/2, 475);
		g.drawImage(play, resizeHalf, 125+375/2, 475);
		
		tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
		g.setComposite(tran);
		
		g.drawImage(back, resizeBg, 10, 650);
		g.drawImage(introGameBut, resizeBut, 590-125/3, 650);
	}
	
	public static void drawIntroGame(Graphics2D g){
		
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.92f);
		g.setComposite(tran);
		g.drawImage(introGame, null, 0, 0);

	}
	
	public static void drawLockDuck(Graphics2D g, int y, int stage){
		AlphaComposite tran = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		g.setComposite(tran);
		g.setColor(Color.black);
		g.fillRoundRect(50, y+1, 75, 75-2, 20, 20);
	}
	
}
