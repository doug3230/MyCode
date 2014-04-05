package tetris;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TetrisGamePanel extends JPanel implements ActionListener {
	
	//Data Members
	//------------
	private TetrisScorePanel scorePanel = new TetrisScorePanel(this);
	private TetrisBoardPanel blockPanel = new TetrisBoardPanel(this);
	private Timer timer;
	private int score = 0;
	private int level = 1;
	private int lines = 0;
	private Thread gameThread;
	
	//Constructor
	//-----------
	public TetrisGamePanel() {
		super();
		setLayout(new BorderLayout());
		add(blockPanel,BorderLayout.CENTER);
		add(scorePanel,BorderLayout.EAST);
	}
	
	//Methods
	//-------
	public TetrisScorePanel getScorePanel() {return scorePanel;}
	public TetrisBoardPanel getBlockPanel() {return blockPanel;}

	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	//Private Methods
	//---------------
	private void updateScorePanel() {
		scorePanel.setScore(score);
		scorePanel.setLevel(level);
		scorePanel.setLines(lines);
	}
}