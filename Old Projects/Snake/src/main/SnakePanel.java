package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SnakePanel extends JPanel implements Runnable {
	// Constants
	// ------------
	public static final double WINNING_BLOCK_PERCENT = 0.4;
	public static final String ENTER = System.getProperty("line.separator");

	// Data Members
	// ------------
	private SnakeWindow snakeWindow;
	private SnakeOptions snakeOptions;
	private Snake snake;
	private SnakeBlock food;
	private SnakeListener snakeListener = new SnakeListener();
	private boolean gameInProgress = false;
	private boolean paused = false;
	private Random generator = new Random();
	private StartingLocation[] startingPoints = {
			new StartingLocation(Direction.RIGHT),
			new StartingLocation(Direction.DOWN),
			new StartingLocation(Direction.UP),
			new StartingLocation(Direction.LEFT) };

	// Constructor
	// -----------
	public SnakePanel(SnakeWindow window, SnakeOptions options) {
		super();
		snakeWindow = window;
		snakeOptions = options;
		//Border border = BorderFactory.createLineBorder(BORDER_COLOUR,
		//		BORDER_LENGTH);
		//setBorder(border);
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(snakeListener);
	}

	// Methods
	// ------
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		int borderLength = snakeOptions.getBorderSize();
		g2D.setColor(snakeOptions.getBorderColour());
		g2D.fillRect(0, 0, this.getWidth(), borderLength);
		g2D.fillRect(this.getWidth() - borderLength, 0, borderLength, this.getHeight());
		g2D.fillRect(0, 0, borderLength, this.getHeight());
		g2D.fillRect(0, this.getHeight() - borderLength, this.getWidth(), borderLength);
		
		if (snake != null) {
			g2D.setColor(snakeOptions.getSnakeColour());
			Rectangle[] snakeBlocks = snake.getBlocks();
			for (Rectangle block : snakeBlocks)
				g2D.fill(block);
			
			g2D.setColor(snakeOptions.getFoodColour());
			g2D.fill(food);
		}
	}
	
	public boolean isGameInProgress() {
		return gameInProgress;
	}
	
	public void endGame() {
		gameInProgress = false;
	}
	
	public void run() {
		playGame();
	}
	
	public void pauseGame() {
		if (snake != null)
			paused = true;
	}
	
	public void resumeGame() {
		if (snake != null)
			paused = false;
	}
	
	public boolean isPaused() {
		return paused;
	}

	// Private Methods
	// ---------------
	private void playGame() {
		updateStartingLocations();
		SnakeBlock.setDefaultLength(snakeOptions.getSnakeSize());
		gameInProgress = true;
		StartingLocation start = chooseRandomStartingLocation();
		snake = new Snake(new SnakeBlock(start.x, start.y));
		snake.setDirection(start.direction);
		snakeListener.setDirection(start.direction);

		int maxBlocks = computeMaxBlocks();
		generateFood();
		while (snake.getSize() < maxBlocks && !gameOver(snake) && gameInProgress) {
			waitASec(snakeOptions.getSnakeSpeed());
			while (paused) {}
			snake.updateLocation();
			snake.setDirection(snakeListener.getDirection());
			if (food.contains(snake.getLeftNostril())
					|| food.contains(snake.getRightNostril())) {
				snake.eatBlock(food);
				generateFood();
			}
			this.repaint();
		}
		if (gameInProgress) {
			String message;
			if (snake.getSize() == maxBlocks)
				message = "Congratulations! The snake is now full and you have won!";
			else
				message = "Game Over!";
			message += ENTER + "Foods eaten: " + (snake.getSize() - 1);
			JOptionPane.showMessageDialog(this, message);
			gameInProgress = false;
		}
		else {
			snake = null;
			repaint();
			snakeWindow.resume();	
		}
	}
	
	private boolean gameOver(Snake snake) {
		Point leftNostril = snake.getLeftNostril();
		Point rightNostril = snake.getRightNostril();
		boolean hitBoundary = inBoundary(leftNostril.x, leftNostril.y)
				&& inBoundary(rightNostril.x, rightNostril.y);
		boolean hitSelf = snake.tailContains(leftNostril.x, leftNostril.y)
				&& snake.tailContains(rightNostril.x, rightNostril.y);
		return (hitBoundary || hitSelf);
	}

	private boolean inBoundary(int x, int y) {
		int borderLength = snakeOptions.getBorderSize();
		boolean top = y <= borderLength;
		boolean bottom = y >= this.getHeight() - borderLength;
		boolean left = x <= borderLength;
		boolean right = x >= this.getWidth() - borderLength;
		return (top || bottom || left || right);
	}

	private void updateStartingLocations() {
		int width = this.getWidth();
		int height = this.getHeight();
		startingPoints[0].setLocation(width / 4, height / 4);
		startingPoints[1].setLocation(3 * width / 4, height / 4);
		startingPoints[2].setLocation(width / 4, 3 * height / 4);
		startingPoints[3].setLocation(3 * width / 4, 3 * height / 4);
	}

	private StartingLocation chooseRandomStartingLocation() {
		int choice = generator.nextInt(startingPoints.length);
		return startingPoints[choice];
	}

	private int computeMaxBlocks() {
		int panelSize = (this.getWidth() - 2 * snakeOptions.getBorderSize())
				* (this.getHeight() - 2 * snakeOptions.getBorderSize());
		int maxBlocks = panelSize
				/ (SnakeBlock.getDefaultLength() * SnakeBlock
						.getDefaultLength());
		maxBlocks = (int) (maxBlocks * WINNING_BLOCK_PERCENT);
		return maxBlocks;
	}
	
	private void generateFood() {
		food = new SnakeBlock();
		int borderLength = snakeOptions.getBorderSize();
		int width = this.getWidth() - 2 * borderLength;
		int height = this.getHeight() - 2 * borderLength;
		int x = generator.nextInt(width) + borderLength;
		int y = generator.nextInt(height) + borderLength;

		food.setLocation(x, y);
		while (snake.contains(food)) {
			x = generator.nextInt(width) + borderLength;
			y = generator.nextInt(height) + borderLength;
			food.setLocation(x, y);
		}
	}

	private void waitASec(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Inner Class
	// -----------
	private class StartingLocation extends Point {
		public Direction direction;

		public StartingLocation(Direction d) {
			super();
			direction = d;
		}
	}
}