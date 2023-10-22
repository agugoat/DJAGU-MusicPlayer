package mP3AudioTester;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundP2 {
	private Clip clip;
	public String musicPath;
	private long clipTimePosition;

	public void initializeMusic() {
		try {
			File musicFile = new File(musicPath);
			if (musicFile.exists()) {
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

	public void resumeMusic() {
		while (clip != null && !clip.isRunning()) {
			clip.setMicrosecondPosition(clipTimePosition);
			clip.start();
			// showMessage("Press ya bung to stop playing");
		}
	}

	public void stopMusic() {
		if (clip != null && clip.isRunning()) {
			clip.stop();
		}
	}

	public static void main(String[] args) {
		SoundP2 musicController = new SoundP2();

		musicController.startMusic();
		musicController.pauseMusic();
		musicController.resumeMusic();
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