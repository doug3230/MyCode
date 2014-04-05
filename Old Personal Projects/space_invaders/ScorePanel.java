package space_invaders;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	
	//Constructor
	//-----------
	private static final int MAX_LIVES = 3;
	
	//Data Members
	//------------
	private int lives = 0;
	private int score = 0;
	private JLabel livesLabel = new JLabel();
	private JLabel scoreLabel = new JLabel();
	private JLabel pausedLabel = new JLabel();
	private JLabel gameoverLabel = new JLabel();
	
	//Constructor
	//-----------
	public ScorePanel() {
		super();
		setLayout(new GridLayout(1,4));
		setBackground(Color.LIGHT_GRAY);
		add(livesLabel);
		add(scoreLabel);
		add(gameoverLabel);
		add(pausedLabel);
		pausedLabel.setForeground(Color.WHITE);
		gameoverLabel.setForeground(Color.RED);
		gameIsOver();
		initializeValues();
		updateLabels();
	}
	
	//Methods
	//-------
	public void initializeValues() {
		score = 0;
		lives = MAX_LIVES;
		gameoverLabel.setText("");
		pausedLabel.setText("");
	}
	
	public void updateLabels() {
		livesLabel.setText("Lives: " + lives);
		scoreLabel.setText("Score: " + score);
	}
	
	public void addToScore(int points) {score += points;}
	public void addLife() {if (lives < MAX_LIVES) lives += 1;}
	public void loseLife() {if (lives > 0) lives -= 1;}
	public boolean isOutOfLives() {return (lives == 0);}
	public void gameIsPaused() {
		pausedLabel.setText("Game paused");
	}
	public void gameIsUnpaused() {
		pausedLabel.setText("");
	}
	public void gameIsOver() {
		gameoverLabel.setText("Game over");
	}
	public void aliensLanded() {
		gameoverLabel.setText("Aliens Landed");
	}
}