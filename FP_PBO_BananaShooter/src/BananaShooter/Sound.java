package BananaShooter;

import java.io.File;
import javax.sound.sampled.*;

public class Sound {
	
	void play(String music) {
		try {
			File musicPath = new File(music);
			
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
							
			}else {
				System.out.println("Can't Find Audio!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
