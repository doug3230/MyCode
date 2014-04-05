package tetris;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class TetrisBoardPanel extends JPanel {

	//Constants
	//---------
	private static final Color BACKGROUND_COLOUR = Color.BLACK;
	private static final int INITIAL_DELAY = 400;
	private static final int DEFAULT_ROWS = 20;
	private static final int DEFAULT_COLS = 10;
	
	//Data Members
	//------------
	private TetrisGamePanel gamePanel;
	private int rows;
	private int cols;
	private GridSquare[][] gridSquares;
	private Tetromino currentBlock;
	private int curX;
	private int curY;
	private int delay;
	private boolean isGameInProgress;
	private boolean isBlockFalling;
	private boolean isPaused;
	
	private int score;
	private int level;
	private int lines;
	private TetrisKeyboardListener listener = new TetrisKeyboardListener();
	
	//Constructors
	//------------
	public TetrisBoardPanel(TetrisGamePanel panel) {
		this(panel,DEFAULT_ROWS,DEFAULT_COLS);
	}
	
	public TetrisBoardPanel(TetrisGamePanel panel, int rows, int cols) {
		super();
		gamePanel = panel;
		isGameInProgress = false;
		isBlockFalling = false;
		isPaused = false;
		setDimensions(rows,cols);
		setFocusable(true);
		addKeyListener(listener);
	}

	//Methods
	//-------
	public void pauseOrUnpause() {
		if (isGameInProgress)
			isPaused = !isPaused;
	}
	
	public void setDimensions(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		
		gridSquares = new GridSquare[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				gridSquares[i][j] = new GridSquare();
				this.add(gridSquares[i][j]);
			}
	}
	
	//Private Methods
	//---------------
	private void runGame() {
		isGameInProgress = true;
		isBlockFalling = false;
		isPaused = false;
		delay = INITIAL_DELAY;
		score = 0;
		lines = 0;
		level = 1;
		clearGrid();
		
		while (isGameInProgress) {
			if (!isPaused) {
				if (!isBlockFalling) {
					curX = cols / 2;
					curY = 0;
					currentBlock = gamePanel.getScorePanel().getNextBlock();
					gamePanel.getScorePanel().generateNewBlock();
					if (!isValidMove(currentBlock,0,0)) {
						isGameInProgress = false;
						JOptionPane.showMessageDialog(this, "Game Over");
					}
					else {
						drawBlock();
						isBlockFalling = true;
					}
				} else {
					eraseBlock();
					if (isValidMove(currentBlock,0,1)) {
						curY += 1;
					}
					else {
						isBlockFalling = false;
						score += 200;
						clearLines();
					}
					drawBlock();
				}
				updateScorePanelAndDelay();
			}
			sleep(delay);
		}
		if (listener.queuedGame) {
			new Thread(listener).start();
			listener.queuedGame = false;
		}
	}
	
	private void clearLines() {
		int multiplier = 1;
		for (int r = rows - 1; r >= 0;) {
			if (isLine(r)) {
				clearLine(r);
				lines += 1;
				score += multiplier * 1000;
				multiplier += 1;
			} else {
				r--;
			}
		}
	}
	
	private boolean isLine(int row) {
		boolean isALine = true;
		int c = 0;
		while(isALine && c < cols)
			isALine = !gridSquares[row][c++].isBlank();
		return isALine;
	}
	
	private void clearLine(int row) {
		for (int r = row; r >= 0; r--) {
			for (int c = 0; c < cols; c++) {
				if (r == 0)
					gridSquares[r][c].blankOut();
				else if (gridSquares[r - 1][c].isBlank())
					gridSquares[r][c].blankOut();
				else
					gridSquares[r][c].fillWithColour(gridSquares[r - 1][c].getColour());
			}
		}
	}
	
	private void updateScorePanelAndDelay() {
		TetrisScorePanel scorePanel = gamePanel.getScorePanel();
		level = (score / 10000) + 1;
		scorePanel.setScore(score);
		scorePanel.setLevel(level);
		scorePanel.setLines(lines);
		delay = INITIAL_DELAY - level * 10;
	}
	
	private void drawBlock() {
		Point[] blockCoords = currentBlock.getCoords();
		int blockX, blockY;
		for (Point pt : blockCoords) {
			blockX = curX + pt.x;
			blockY = curY - pt.y;
			
			if (blockY > 0)
				gridSquares[blockY][blockX].fillWithColour(currentBlock.getColor());	
		}
	}
	
	private void eraseBlock() {
		Point[] blockCoords = currentBlock.getCoords();
		for (Point pt : blockCoords)
			if (curY - pt.y >= 0)
				gridSquares[curY - pt.y][curX + pt.x].blankOut();
	}
	
	private void clearGrid() {
		for (GridSquare[] row : gridSquares)
			for (GridSquare gs : row)
				gs.blankOut();
	}
	
	private void sleep(int milliseconds) {
			try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	private boolean isValidMove(Tetromino piece, int x_adj, int y_adj) {
		boolean isValid = true;
		int newX = curX + x_adj;
		int newY = curY + y_adj;
		Point[] pts = piece.getCoords();
		
		int i = 0;
		int new_ptx, new_pty;
		while (isValid && i < pts.length) {
			new_ptx = newX + pts[i].x;
			new_pty = newY - pts[i].y;
			
			if (new_ptx < 0 || new_ptx >= cols)
				isValid = false;
			else if (new_pty >= rows)
				isValid = false;
			else if (new_pty < 0)
				isValid = true;
			else
				isValid = gridSquares[new_pty][new_ptx].isBlank();
			i += 1;
		}
		
		return isValid;
	}
	
	private void dropBlockToBottom() {
		eraseBlock();
		while (isValidMove(currentBlock,0,1)) {
			curY += 1;
		}
		drawBlock();
		isBlockFalling = false;
	}
	
	//Inner Classes
	//-------------
	private class GridSquare extends JPanel {
		private boolean isBlank = true;
		private Color squareColour = BACKGROUND_COLOUR;
		
		private GridSquare() {
			super();
			blankOut();
		}
		
		private void blankOut() {
			isBlank = true;
			squareColour = BACKGROUND_COLOUR;
			setBackground(squareColour);
		}
		
		private void fillWithColour(Color newColour) {
			isBlank = false;
			squareColour = newColour;
			setBackground(squareColour);
		}
		
		private boolean isBlank() {return isBlank;}
		private Color getColour() {return squareColour;}
	}
	
	private class TetrisKeyboardListener extends KeyAdapter implements Runnable {
		private boolean queuedGame = false;
		
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			
			if (keycode == KeyEvent.VK_P) {
				isPaused = !isPaused;
				return;
			}
			
			isPaused = true;
			if (keycode == KeyEvent.VK_N) {
				if (isGameInProgress) {
					isGameInProgress = false;
					queuedGame = true;
				}
				else
					new Thread(this).start();
				
			} else if (!isGameInProgress) 
				return;
			else if (keycode == KeyEvent.VK_LEFT) {
				eraseBlock();
				if (isValidMove(currentBlock,-1,0))
					curX -= 1;
				drawBlock();
			}
			else if (keycode == KeyEvent.VK_RIGHT) {
				eraseBlock();
				if (isValidMove(currentBlock,1,0))
					curX += 1;	
				drawBlock();
			}
			else if (keycode == KeyEvent.VK_UP) {
				eraseBlock();
				if (isValidMove(currentBlock.rotateRight(),0,0))
					currentBlock = currentBlock.rotateRight();
				drawBlock();
			}
			else if (keycode == KeyEvent.VK_DOWN) {
				eraseBlock();
				if (isValidMove(currentBlock.rotateLeft(),0,0))
					currentBlock = currentBlock.rotateLeft();
				drawBlock();
			} else if (keycode == KeyEvent.VK_SPACE) {
				dropBlockToBottom();
				score += 200;
				clearLines();
				updateScorePanelAndDelay();
			} else if (keycode == KeyEvent.VK_D) {
				eraseBlock();
				if (isValidMove(currentBlock,0,1))
					curY += 1;
				drawBlock();
			}
			isPaused = false;
		}

		public void run() {
			runGame();
		}
	}
}