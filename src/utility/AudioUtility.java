package utility;

import java.applet.Applet;
import java.applet.AudioClip;


public class AudioUtility {
	
	protected static AudioClip getSound(String directory){
		ClassLoader loader = AudioUtility.class.getClassLoader();
		try{
			return Applet.newAudioClip(loader.getResource(directory).toURI().toURL());
		} catch(Exception e){
			return null;
		}
	}
	
	public static AudioClip universalClickSound = getSound("res/sound/placedDuckSound.wav");
	public static AudioClip starSound = getSound("res/sound/star.wav");
	public static AudioClip dragonBiteSound = getSound("res/sound/dragonBiteSound.wav");
	public static AudioClip layEggSound = getSound("res/sound/layEggSound.wav");
	public static AudioClip duckBiteSound = getSound("res/sound/duckJigSound.wav");
	//public static AudioClip toNextSceneSound = getSound("res/sound/1Sound.wav");
	public static AudioClip bgm = getSound("res/sound/background-music1.wav");
	public static boolean isMute = false;
}
