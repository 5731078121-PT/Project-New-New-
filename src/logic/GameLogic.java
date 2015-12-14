package logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import input.InputUtility;
import javafx.print.PageLayout;
import render.GameScreen;
import render.IRenderable;
import render.PlayingArea;
import render.RenderableHolder;
import utility.AudioUtility;
import utility.DrawingUtility;

public class GameLogic {

	private int releaseDragonDelay;
	private int releaseDragonDelayCounter;
	private int starfallDelay;
	private int starfallDelayCounter;
	public static PlayerStatus playerStatus;
	protected  Duck duck;
	protected DuckSuper duckSuper;
	protected DuckAggressive duckAggressive;
	public static PlayingArea playingArea;
	protected Shell shell;
	protected Star star;
	protected Dragon dragon;
	protected DragonSuper superDragon;
	public static boolean newDuck;
	public static boolean newSuperDuck;
	public static boolean newShell;
	public static boolean newAggressiveDuck;
	public static boolean newFrozenDuck;
	public static int[][] dragonInState;
	private final int STAGE_MAX = 10;
	private int smallDragonTime = 2  , smallDragonCount;
	private boolean releaseSmallDragon;
	private int currentStage ;
	

	public GameLogic() {
		// TODO Auto-generated constructor stub
	
		playingArea = new PlayingArea();
		RenderableHolder.getInstance().add(playingArea);
		RenderableHolder.getInstance().add(playerStatus);
		createDuck();
		createSuperDuck();
		createAggressiveDuck();
		createFrozenDuck();
		createShell();
		currentStage = GameLogic.playerStatus.getState();
		if(currentStage > 11) currentStage = 11; 
		
		dragonInState = new int[STAGE_MAX+1][10];
//		gen DRAGON = 0;
//		gen SUPER DRAGON = 1
		for(int i = 1; i<7; i++){
			for(int j = 9; i + j >= 10; j--){
				dragonInState[i][j] = 1;
			}
		}
		for(int i=7; i<STAGE_MAX; i++){
			for(int j = 1; j<10; j++){
				dragonInState[i][j] = 1;
			}
		}
//		gen SMALL DRAGON = 2
		for(int i = 3; i<STAGE_MAX; i++){
			dragonInState[i][9] = 2;
		}
		for(int i = 7; i<STAGE_MAX; i++){
			dragonInState[i][8] = 2;
		}
		dragonInState[9][7] = 3;
		
//		for STAGE > 10
		for(int i = 0; i<10; i++){
			dragonInState[10][i] = RandomUtility.random(1, 2);
		}
		
		
		releaseDragonDelay = RandomUtility.random(100, 120);
		starfallDelay = RandomUtility.random(30, 50);

		
	}
	
	public void logicUpdate(){
//		 if Game is PAUSE or END >> not update logic
		if(GameLogic.playerStatus.isPause() || GameLogic.playerStatus.isEnd) return;
		
		RenderableHolder.sort();
		for(IRenderable a : RenderableHolder.getRenderableList()){
			
			a.update();
			
		}
		if(newDuck){
			if(!DrawingUtility.isMute) AudioUtility.universalSound.play();
			this.createDuck();
		}
		if(newSuperDuck){
			if(!DrawingUtility.isMute) AudioUtility.universalSound.play();
			this.createSuperDuck();
		}
		if(newShell){
			if(!DrawingUtility.isMute) AudioUtility.universalSound.play();
			this.createShell();
		}
		if(newAggressiveDuck){
			if(!DrawingUtility.isMute) AudioUtility.universalSound.play();
			this.createAggressiveDuck();
		}
		if(newFrozenDuck){
			if(!DrawingUtility.isMute) AudioUtility.universalSound.play();
			this.createFrozenDuck();
		}
		
//		STAR FALL
		if(starfallDelay == starfallDelayCounter){
			starfallDelayCounter = 0;
			starfallDelay = RandomUtility.random(150, 200);
			star = new Star(RandomUtility.random(175, GameScreen.WIDTH), 0);
			RenderableHolder.getInstance().add(star);
		}else starfallDelayCounter++;
		
//		DRAGON OUT
		if(GameLogic.playerStatus.canReleaseDragon()){
			if(releaseDragonDelay == releaseDragonDelayCounter){
				releaseDragonDelayCounter = 0;
				releaseDragonDelay = RandomUtility.random(200, 250);
				int ran = RandomUtility.random(0, 9);
				System.out.println(ran);
//				int ran = 9;
				int i = RandomUtility.random(1, 5);
//				int i = 1;
				if(dragonInState[currentStage -1][ran] == 0){
					RenderableHolder.getInstance().add(new Dragon(175+i*75));					
				}else if(dragonInState[currentStage -1][ran] == 1){
					RenderableHolder.getInstance().add(new DragonSuper(175+i*75));					
				}else if(dragonInState[currentStage -1][ran] == 2){
					releaseSmallDragon = true;
					RenderableHolder.getInstance().add(new DragonSmall(175+1*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+2*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+3*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+4*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+5*75));
					
				}
				
			
			}
			releaseDragonDelayCounter++;
			
			if(releaseSmallDragon){
				if(releaseDragonDelayCounter%50 == 0){
					RenderableHolder.getInstance().add(new DragonSmall(175+1*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+2*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+3*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+4*75));
					RenderableHolder.getInstance().add(new DragonSmall(175+5*75));
					if(smallDragonCount == smallDragonTime){
						smallDragonCount = 0;
						releaseSmallDragon = false;
					}else{
						smallDragonCount++;
						System.out.println("           " +smallDragonCount);
					}
				}
				
			}
			
		}
		
//		SU KAN
		fighting();
		
		
	}
	
	public void fighting(){
		for(int i = 0; i<RenderableHolder.getRenderableList().size(); i++){

			for(int j = 0; j<RenderableHolder.getRenderableList().size(); j++){

				if(RenderableHolder.getRenderableList().get(i) instanceof Dragon){
					Dragon dragon = (Dragon) RenderableHolder.getRenderableList().get(i);
					if(dragon.isVisible()){
						
						if(RenderableHolder.getRenderableList().get(j) instanceof Egg){
							Egg egg = (Egg) RenderableHolder.getRenderableList().get(j);
							
							if(!egg.destroyed &&egg.column == dragon.column ){
								if(egg.y-15 <= dragon.y && egg.y+15 >= dragon.y){
									if(egg instanceof EggFrozen){
										dragon.isFrozen = true;
									}
									
									egg.attackDragon(dragon);
									break;
								}
							}
						}
						if(RenderableHolder.getRenderableList().get(j) instanceof Duck){
							Duck duck = (Duck) RenderableHolder.getRenderableList().get(j);
							
							if(!duck.dead && duck.column == dragon.column){
								duck.haveDragon = true;
								if(duck.y<=dragon.y && duck.y+50>= dragon.y){
									dragon.attackDuck(duck);
									
								}
								if(dragon.dead){
									duck.haveDragon = false;
									System.out.println(" Yes Dead");
								}
							}
							if(dragon.dead){
								duck.haveDragon = false;
								System.out.println(" Yes Dead");
							}
						}
						if(RenderableHolder.getRenderableList().get(j) instanceof Shell){
							Shell shell = (Shell) RenderableHolder.getRenderableList().get(j);
							
							if(!shell.dead && shell.column == dragon.column){
								if(shell.y<=dragon.y && shell.y+50>=dragon.y){

									dragon.attackShell(shell);
								}
							}
						}
						if(RenderableHolder.getRenderableList().get(j) instanceof DuckAggressive){
							DuckAggressive aggressDuck = (DuckAggressive) RenderableHolder.getRenderableList().get(j);
							
							if(!aggressDuck.dead && aggressDuck.column == dragon.column){
								if(aggressDuck.y <= dragon.y && aggressDuck.y+75 >= dragon.y){
									aggressDuck.setJig(true);
									System.out.println("jigjig");
									
									aggressDuck.attackDragon(dragon);
								}else{
									aggressDuck.setJig(false);
									
								}
							}
						}
					}
					
				}
			}
		}
	}
	
	public void createDuck(){
//		duck = new Duck(50,125);
//		RenderableHolder.getInstance().add(duck);
		RenderableHolder.getInstance().add(new Duck(50, 125+75/2));
		newDuck = false;
	}
	public void createSuperDuck(){
		RenderableHolder.getInstance().add(new DuckSuper(50, 200+75/2));
		newSuperDuck = false;
	}
	public void createShell(){
		RenderableHolder.getInstance().add(new Shell(50, 275+150+75/2));
		newShell = false;
	}
	public void createAggressiveDuck(){
		RenderableHolder.getInstance().add(new DuckAggressive(50, 275+75/2));
		newAggressiveDuck = false;
	}
	public void createFrozenDuck(){
		RenderableHolder.getInstance().add(new DuckFrozen(50, 275+75+75/2));
		newFrozenDuck = false;
	}
	
	
}
