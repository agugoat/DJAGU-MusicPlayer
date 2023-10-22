package mP3AudioTester;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class musicGui {
	Sounds musicController = new Sounds();
	private JProgressBar progressBar;
	JSlider slider = new JSlider();

	private Timer timer;

	private String uploadFile() {
		JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Wav Files", "wav"));
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();

		} else {
			return null;
		}
	}

	// intializes the progress bar
	private void initializeProgressBar() {
		if (timer != null) {
			timer.stop();
			// if a timer is currently running, it should be stopped.
		}
		progressBar = new JProgressBar();
		long songDurationLong = musicController.getTotalDuration();
		int songDuration = (int) (songDurationLong / 1000); // Convert to milliseconds
		progressBar.setMaximum(songDuration); // sets the maxiumn to the song duration
		progressBar.setValue(0); // sets the value to the beginning of the song

		int updateInterval = 1000; //
		timer = new Timer(updateInterval, new ActionListener() {
			// times to the beginning to the end of the song
			public void actionPerformed(ActionEvent evt) {
				long currentPositionLong = musicController.getCurrentPosition();
				int currentPosition = (int) (currentPositionLong / 1000); // Convert to milliseconds
				progressBar.setValue(currentPosition); // sets the progress bars value to the current postion of the
														// song

			}

		});
	}

	public musicGui() {
		JPanel panel = new JPanel();

		// Create JFrame instance
		JFrame frame = new JFrame("DJ AGU Music Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);

		// Create buttons and add action listeners
		JButton playButton = new JButton("Play");
		playButton.addActionListener(e -> {
			musicController.startMusic();
			timer.start();
		});
		// Pause button connects to pauseMusic Method
		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(e -> musicController.pauseMusic());
		// Resume button connects to resumeMusic Method
		JButton resumeButton = new JButton("Resume");
		resumeButton.addActionListener(e -> musicController.resumeMusic());
		// choose/ upload music button lets you choose the wav file to play
		// also triggers the progress bar of the current bar
		JButton chooseButton = new JButton("Upload Music");
		chooseButton.addActionListener(e -> {
			String selectedFilePath = uploadFile();
			musicController.setMusicPath(selectedFilePath);
			musicController.initializeMusic();
			initializeProgressBar();
			panel.add(progressBar, BorderLayout.CENTER);

		});
		// setting the layout and buttons
		panel.setLayout(new FlowLayout());
		panel.add(playButton);
		panel.add(pauseButton);
		panel.add(resumeButton);
		panel.add(chooseButton);
		;

		// File chooser to set what music you want play

		// Add panel to frame
		frame.add(panel);

		// Display the frame
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new musicGui();
			// displays the gui
		});
	}
}
