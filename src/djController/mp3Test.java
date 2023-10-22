package djController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class mp3Test {
	private MP3 mp3Player;

	@BeforeEach
	void setUp() {
		mp3Player = new MP3();
		mp3Player.setMp3Path("C:\\Users\\pagug\\Downloads\\Victony- All Power (lyrics video ).mp3");
	}

	@Test
	void pausePlaying_shouldSetIsPausedToTrue() {
		mp3Player.startPlaying();
		mp3Player.resumePlaying();
/// fill with test method
		mp3Player.stopPlaying(); // Stop playing after the test

	}

	@Test
	void resumePlaying_shouldSetIsPausedToFalse() {
		mp3Player.startPlaying();
		mp3Player.resumePlaying();
		mp3Player.resumePlaying();
		// fill with tester method
		mp3Player.stopPlaying(); // Stop playing after the test
	}

}
