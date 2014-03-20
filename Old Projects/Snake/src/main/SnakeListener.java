package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeListener implements KeyListener {
	// Data Members
	// ------------
	private Direction direction;
	private static final Direction DEFAULT = Direction.LEFT;

	// Constructor
	// -----------
	public SnakeListener() {
		direction = DEFAULT;
	}
	
	public SnakeListener(Direction d) {
		direction = d;
	}

	// Methods
	// -------
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction d) {
		direction = d;
	}

	public void keyPressed(KeyEvent event) {
		int typedChar = event.getKeyCode();

		switch (typedChar) {
		case KeyEvent.VK_UP:
			direction = Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			direction = Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			direction = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			direction = Direction.RIGHT;
			break;
		}
	}

	public void keyReleased(KeyEvent event) {
	}
	
	public void keyTyped(KeyEvent event) {	
	}
}