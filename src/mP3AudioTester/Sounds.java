package mP3AudioTester;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	private Clip clip;
	public String musicPath;
	private long clipTimePosition;

	public void initializeMusic() {
		try {
			File musicFile = new File(musicPath);
			// Path file to Music
			if (musicFile.exists()) {
				// checks if the file exists
				AudioInputStream input = AudioSystem.getAudioInputStream(musicFile);
				clip = (AudioSystem.getClip());
				clip.open(input);
			} else {
				System.out.print("No music Bozo");
			}
		} catch (Exception e) {
			System.out.print("No music Bozo");
		}
		if (clip != null && clip.isRunning()) {
			clipTimePosition = clip.getMicrosecondPosition();
			clip.stop();
		}
	}

	public void startMusic() {
		while (clip != null && !clip.isRunning()) {

			clip.setMicrosecondPosition(clipTimePosition);
			clip.start();
			// showMessage("Press ya bung to stop playing");
		}
	}

	public void pauseMusic() {
		if (clip != null && clip.isRunning()) {
			clipTimePosition = clip.getMicrosecondPosition();
			clip.stop();
		}
	}

	// This method Resumes the wav file that is being played
	public void resumeMusic() {
		while (clip != null && !clip.isRunning()) {
			clip.setMicrosecondPosition(clipTimePosition);
			// plays from the postion that it has been paused from
			clip.start();
			// plays the clip
		}
	}

	// this method stops the music
	public void stopMusic() {
		if (clip != null && clip.isRunning()) {
			clip.stop();
			// stops the music without having the pause ref point
		}
	}

	public void setMusicPath(String wavPath) {
		this.musicPath = wavPath;

	}

	public String getMusicPath() {
		return musicPath;
	}

	public Clip getterClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public long getCurrentPosition() {
		return clip.getMicrosecondPosition();
	}

	public long getTotalDuration() {
		return clip.getMicrosecondLength();
	}

}