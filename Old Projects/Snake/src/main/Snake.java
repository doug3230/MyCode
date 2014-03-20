package main;

import java.awt.Point;

import utilities.LinkedList;

public class Snake {
	//Data Members
	//------------
	private LinkedList<SnakeBlock> snakeList = new LinkedList<SnakeBlock>();
	private Direction direction;
	
	//Constructors
	//------------
	public Snake() {};
	
	public Snake(SnakeBlock block) {
		snakeList.insertToFront(block);
	}
	
	//Methods
	//-------
	public boolean contains(SnakeBlock block) {
		int x = block.getLocation().x;
		int y = block.getLocation().y;
		int length = block.getLength();
		SnakeBlock[] snake = getBlocks();
		
		boolean isContained = false;
		int i = 0;
		while (i < snake.length && !isContained)
			isContained = blockContainsPoint(snake[i++],x,y,length);
		return isContained;
	}
	
	public boolean tailContains(int x, int y) {
		SnakeBlock[] snake = getBlocks();
		boolean isContained = false;
		int i = 1;
		while (i < snake.length && !isContained)
			isContained = snake[i++].contains(x,y);
		return isContained;
	}
	
	public void eatBlock(SnakeBlock block) {
		snakeList.insertToFront(block);
	}
	
	public SnakeBlock[] getBlocks() {
		Object[] array = snakeList.toArray();
		SnakeBlock[] snake = new SnakeBlock[array.length];
		for (int i = 0; i < array.length; i++)
			snake[i] = (SnakeBlock) array[i];
		return snake;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public SnakeBlock getHead() {
		SnakeBlock head;
		if (snakeList.isEmpty())
			head = null;
		else
			head = snakeList.elemAt(0);
		return head;
	}
	
	public Point getLeftNostril() {
		SnakeBlock head = getHead();
		int x = head.getLocation().x;
		int y = head.getLocation().y;
		
		switch(direction) {
		case DOWN:
			y = y + head.getLength();
			x = x + head.getLength();
			break;
		case LEFT:
			y = y + head.getLength();
			break;
		case RIGHT:
			x = x + head.getLength();
			break;
		default:
			//case UP: all is good
			break;
		}
		return new Point(x,y);
	}
	
	public Point getRightNostril() {
		SnakeBlock head = getHead();
		int x = head.getLocation().x;
		int y = head.getLocation().y;
		
		switch(direction) {
		case UP:
			x = x + head.getLength();
			break;
		case DOWN:
			y = y + head.getLength();
			break;
		case RIGHT:
			x = x + head.getLength();
			y = y + head.getLength();
			break;
		default:
			//case LEFT: all is good
			break;
		}
		return new Point(x,y);
	}
	
	public int getSize() {
		return snakeList.getSize();
	}
	
	public boolean hasBody() {
		return (snakeList.getSize() > 1);
	}
	
	public boolean setDirection(Direction newDirection) {
		boolean goodDirection = !badDirection(newDirection);
		if (goodDirection)
			direction = newDirection;
		return goodDirection;
	}
	
	public void updateLocation() {
		switch(direction) {
		case UP:
			move(0,-1);
			break;
		case DOWN:
			move(0,1);
			break;
		case LEFT:
			move(-1,0);
			break;
		default:
			//RIGHT
			move(1,0);
			break;
		}
	}
	
	//Private Methods
	//---------------
	private void move(int x, int y) {
		SnakeBlock[] body = getBlocks();
		for (int i = body.length - 1; i > 0; i--)
			body[i].setLocation(body[i - 1].getLocation());
		
		SnakeBlock head = getHead();
		int newX = head.getLocation().x + x * head.getLength();
		int newY = head.getLocation().y + y * head.getLength();
		head.setLocation(newX,newY);
	}
	
	private boolean badDirection(Direction newDirection) {
		boolean result;
		
		if (!hasBody())
			result = false;
		else {
			switch(direction) {
			case UP:
				result = newDirection == Direction.DOWN;
				break;
			case DOWN:
				result = newDirection == Direction.UP;
				break;
			case LEFT:
				result = newDirection == Direction.RIGHT;
				break;
			default:
				//RIGHT
				result = newDirection == Direction.LEFT;
				break;
			}
		}
		return result;
	}
	
	private static boolean blockContainsPoint(SnakeBlock block, int x, int y, int length) {
		boolean topLeft = block.contains(x,y);
		boolean topRight = block.contains(x + length,y);
		boolean bottomLeft = block.contains(x,y + length);
		boolean bottomRight = block.contains(x + length, y + length);
		return (topLeft || topRight || bottomLeft || bottomRight);
	}
}