package djController;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class MP3 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declaration of instance variables
	private AdvancedPlayer player; // MP3 player
	private FileInputStream fileInputStream; // Stream to read the MP3 file
	private String mp3Path; // Path of the MP3 file to play
	private boolean isPlaying = false; // Flag to track if the MP3 is currently playing
	private int pausedOnFrame; // Frame on which the MP3 is paused/stopped
	private boolean isPaused = false;
	private int totalFrames = Integer.MAX_VALUE;
	private int currentFrame;

	// Constructor of MP3Player class
	public MP3() {
		// Set properties of the JFrame
		setTitle("DJ AGU");
		setSize(500, 200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Create Start button and add action listener to start playing the MP3
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(e -> startPlaying());

		// Create Stop button and add action listener to stop playing the MP3
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(e -> stopPlaying());

		// Create Upload button and add action listener to upload the MP3 file
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(e -> uploadFile());

		// Resume Button
		JButton btnResume = new JButton("Resume");
		btnResume.addActionListener(e -> resumePlaying());

		// Pause Button - plays from where it left off
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(e -> pausePlaying());
		btnResume.add(btnPause);

		// Create a JPanel with a grid layout to hold the buttons
		JPanel panel = new JPanel(new GridLayout(1, 3));
		// Add buttons to the panel
		panel.add(btnStart);
		panel.add(btnStop);
		panel.add(btnResume);
		panel.add(btnUpload);
		panel.add(btnPause);

		// Add the panel to the JFrame
		add(panel);
	}

	// Method to start playing the MP3 file
	void startPlaying() {
		if (!isPlaying && mp3Path != null) {
			new Thread(() -> {
				try {
					if (player != null) {
						player.close();
						player = null;
					}
					fileInputStream = new FileInputStream(mp3Path);
					fileInputStream.skip(pausedOnFrame * 4175); // Always skip based on pausedOnFrame

					player = new AdvancedPlayer(fileInputStream);
					player.setPlayBackListener(new PlaybackListener() {
						@Override
						public void playbackFinished(PlaybackEvent evt) {
							pausedOnFrame = evt.getFrame();
						}

						@Override
						public void playbackStarted(PlaybackEvent evt) {
							currentFrame = evt.getFrame();
						}
					});

					isPlaying = true;
					player.play(pausedOnFrame, Integer.MAX_VALUE);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					isPlaying = false;
					try {
						if (fileInputStream != null)
							fileInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	// Method to stop playing the MP3 file
	void stopPlaying() {
		// Check if the MP3 is playing
		if (player != null) {
			// Close the player
			player.close();
			player = null;
			isPlaying = false;
			System.out.println("Current Frame: " + currentFrame);
			System.out.println("Paused on Frame: " + pausedOnFrame);
			if (!isPaused) {
				// pausedOnFrame = 0; // Reset to start from beginning next time
			}
		}
	}

	void pausePlaying() {
		if (player != null && isPlaying) {
			pausedOnFrame = currentFrame; // Save the frame where it was paused
			isPaused = true;
			stopPlaying();
			System.out.println("Current Frame: " + currentFrame);
			System.out.println("Paused on Frame: " + pausedOnFrame);
		}
	}

	public void resumePlaying() {
		if (!isPlaying) {
			startPlaying();
		}

	}

	// Method to upload the MP3 file
	private void uploadFile() {
		// Initialize a file chooser
		JFileChooser fileChooser = new JFileChooser();
		// Show the file chooser dialog and get the user's selection
		int returnValue = fileChooser.showOpenDialog(null);
		// Check if the user selected a file
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// Update the file path
			mp3Path = fileChooser.getSelectedFile().getAbsolutePath();
		}
	}

	// Main method to run the application
	public static void main(String[] args) {
		// Ensure the GUI is created and updated on the Event Dispatch Thread (EDT)
		SwingUtilities.invokeLater(() -> {
			// Create and display the MP3 player window
			new MP3().setVisible(true);

		});
	}

	public void setMp3Path(String mp3Path) {
		this.mp3Path = mp3Path;
	}

}
