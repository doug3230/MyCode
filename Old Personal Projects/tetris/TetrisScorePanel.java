package tetris;

import java.awt.*;
import javax.swing.*;

public class TetrisScorePanel extends JPanel {

	//Constants
	//---------
	private static final String ENTER = System.getProperty("line.separator");
	private static final String CONTROLS =
			"N : new game" + ENTER +
			"P : pause/unpause" + ENTER +
			"SPACE : drop block" + ENTER +
			"LEFT : move left" + ENTER +
			"RIGHT : move right" + ENTER +
			"D : move down" + ENTER +
			"UP : rotate right" + ENTER +
			"DOWN : rotate left";
	
	//Data Members
	//------------
	private TetrisGamePanel gamePanel;
	private Tetromino nextBlock;
	private NextTetrominoPanel nextLabel = new NextTetrominoPanel("Next");
	private JTextField levelLabel = new TetrisLabel("Level");
	private JTextField scoreLabel = new TetrisLabel("Score");
	private JTextField linesLabel = new TetrisLabel("Lines");
	private JTextArea rulesLabel = new RulesLabel("Controls");
	
	//Constructor
	//-----------
	public TetrisScorePanel(TetrisGamePanel panel) {
		super();
		gamePanel = panel;
		setLayout(new GridLayout(3,1));
		add(nextLabel);
		add(new SummaryPanel());
		add(rulesLabel);
		setScore(0);
		setLevel(1);
		setLines(0);
		generateNewBlock();
	}
	
	//Methods
	//-------
	public Tetromino getNextBlock() {return nextBlock;}
	public void setNextBlock(Tetromino newBlock) {
		nextBlock = newBlock;
		nextLabel.updateNextTetromino();
	}
	public void setLevel(int newLevel) {levelLabel.setText(newLevel + "");}
	public void setLines(int newLines) {linesLabel.setText(newLines + "");}
	public void setScore(int newScore) {scoreLabel.setText(newScore + "");}
	public void generateNewBlock() {setNextBlock(Tetromino.generateRandomTetromino());}
	
	//Inner Classes
	//-------------
	private class TetrisLabel extends JTextField {
		private TetrisLabel(String whatToLabel) {
			super();
			setBorder(BorderFactory.createTitledBorder(whatToLabel));
			setHorizontalAlignment(SwingConstants.RIGHT);
			setBackground(Color.WHITE);
			setEditable(false);
			setColumns(8);
		}
	}
	
	private class NextTetrominoPanel extends JPanel {
		private static final int X = 2;
		private static final int Y = 2;
		private static final int ROWS = 6;
		private static final int COLS = 5;
		private JPanel[][] blocks;
		
		private NextTetrominoPanel(String whatToLabel) {
			super();
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createTitledBorder(whatToLabel));
			setLayout(new GridLayout(ROWS,COLS));
			blocks = new JPanel[ROWS][COLS];
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++) {
					blocks[i][j] = new JPanel();
					blocks[i][j].setBackground(Color.WHITE);
					this.add(blocks[i][j]);
				}
		}
		
		private void updateNextTetromino() {
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
					blocks[i][j].setBackground(Color.BLACK);
			
			Color blockColour = nextBlock.getColor();
			Point[] blockCoords = nextBlock.getCoords();
			for (Point pt : blockCoords)
				blocks[Y - pt.y][X + pt.x].setBackground(blockColour);
		}
	}
	
	private class SummaryPanel extends JPanel {
		private SummaryPanel() {
			setLayout(new GridLayout(3,1));
			add(levelLabel);
			add(scoreLabel);
			add(linesLabel);
		}
	}
	
	private class RulesLabel extends JTextArea {
		private RulesLabel(String whatToLabel) {
			super();
			setBorder(BorderFactory.createTitledBorder(whatToLabel));
			setText(CONTROLS);
		}
	}
}