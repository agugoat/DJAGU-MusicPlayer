package djController;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private String mp3Path;

	void musicPlayer(String mp3Path) {

		try {
			File musicPath = new File(mp3Path);

			if (musicPath.exists()) {
				AudioInputStream input = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(input);
				clip.start();
			} else {
				System.out.print("No music Bozo");
			}
		} catch (Exception e) {
		}

	}
}
