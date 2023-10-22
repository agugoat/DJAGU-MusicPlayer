package mP3AudioTester;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class mp3Tester {
	private Sounds sounds;

	@Test
	public void testInitializeMusic() {
		sounds = new Sounds();
		sounds.setMusicPath("C:\\Users\\pagug\\Downloads\\Chineke Idinma.wav");
		sounds.initializeMusic();
		assertNotNull(sounds.getterClip());
	}

	@Test
	public void testStartAndStopMusic() {
		sounds = new Sounds();
		sounds.setMusicPath("C:\\Users\\pagug\\Downloads\\Chineke Idinma.wav");
		sounds.initializeMusic();
		sounds.startMusic();
		assertTrue(sounds.getterClip().isRunning());

		sounds.stopMusic();
		assertFalse(sounds.getterClip().isRunning());
	}

	@Test
	public void testPauseAndResumeMusic() {
		sounds = new Sounds();
		sounds.setMusicPath("C:\\Users\\pagug\\Downloads\\Chineke Idinma.wav");
		sounds.initializeMusic();
		sounds.startMusic();
		assertTrue(sounds.getterClip().isRunning());

		sounds.pauseMusic();
		assertFalse(sounds.getterClip().isRunning());

		sounds.resumeMusic();
		assertTrue(sounds.getterClip().isRunning());
	}

}
