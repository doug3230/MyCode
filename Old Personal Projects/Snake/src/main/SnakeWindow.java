package main;

import java.awt.Color;

import javax.swing.JFrame;

public class SnakeWindow extends JFrame {

	// Constants
	// ------------
	public static final String TITLE = "Snake";
	
	// Data Members
	// ------------
	private SnakePanel snakePanel;
	private SnakeOptions snakeOptions;
	private SnakeHelp snakeHelp;
	private Thread gameThread;
	private boolean paused;
	
	// Constructor
	// -----------
	public SnakeWindow() {
		super();
		setTitle(TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snakeOptions = new SnakeOptions(this);
		snakeHelp = new SnakeHelp(this);
		snakePanel = new SnakePanel(this,snakeOptions);
		setContentPane(snakePanel);
		setJMenuBar(new SnakeMenu(this));
		updateWindow();
	}
	
	public void newGame() {
		endGame();
		snakePanel.setBackground(snakeOptions.getWindowColour());
		setSize(snakeOptions.getWindowSize(),snakeOptions.getWindowSize());
		gameThread = new Thread(snakePanel);
		gameThread.start();
	}
	
	public void pause() {
		paused = true;
		while (paused) {}
	}
	
	public void resume() {
		paused = false;
	}
	
	public void pauseOrUnpauseGame() {
		if (snakePanel.isPaused())
			snakePanel.resumeGame();
		else
			snakePanel.pauseGame();
	}
	
	public void showSnakeOptions() {
		snakePanel.endGame();
		this.setVisible(false);
		snakeOptions.setVisible(true);
	}
	
	public void showSnakeHelp() {
		snakePanel.endGame();
		this.setVisible(false);
		snakeHelp.setVisible(true);
	}
	
	public void pauseGame() {
		snakePanel.pauseGame();
	}
	
	public void resumeGame() {
		snakePanel.resumeGame();
	}
	
	public void endGame() {
		if (gameThread != null && snakePanel.isGameInProgress()) { 
			snakePanel.endGame();
			snakePanel.resumeGame();
			pause();
		}
	}
	
	public void updateWindow() {
		snakePanel.setBackground(snakeOptions.getWindowColour());
		setSize(snakeOptions.getWindowSize(),snakeOptions.getWindowSize());
		snakePanel.repaint();
	}
}
