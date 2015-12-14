package utility;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import logic.GameLogic;
import main.Main;
import render.RenderableHolder;

public class AudioUtility {
	
	protected static AudioClip getSound(String directory){
		ClassLoader loader = AudioUtility.class.getClassLoader();
		try{
			return Applet.newAudioClip(loader.getResource(directory).toURI().toURL());
		} catch(Exception e){
			return null;
		}
	}
	
	public static AudioClip universalSound = getSound("res/sound/placedDuckSound.wav");
	public static AudioClip starSound = getSound("res/sound/star.wav");
	public static AudioClip dragonBiteSound = getSound("res/sound/dragonBiteSound.wav");
	public static AudioClip layEggSound = getSound("res/sound/layEggSound.wav");
	public static AudioClip duckBiteSound = getSound("res/sound/duckJigSound.wav");
	//public static AudioClip toNextSceneSound = getSound("res/sound/1Sound.wav");
}
