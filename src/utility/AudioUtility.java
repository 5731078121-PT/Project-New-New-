package utility;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;

import render.RenderableHolder;

public class AudioUtility {
	public static AudioClip starSound;
	
	static{
		try{
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			starSound = Applet.newAudioClip(loader.getResource("res/sound/star.wav").toURI().toURL());
			
		}catch(Exception e){
			starSound = null;
			
		}
	}
	
}
