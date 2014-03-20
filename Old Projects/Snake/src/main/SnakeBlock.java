package main;

import java.awt.Rectangle;

public class SnakeBlock extends Rectangle {
	//Data Members
	//------------
	private int blockLength;
	private static int defaultLength = 0;
	
	//Constructors
	//------------
	public SnakeBlock() {
		super(defaultLength,defaultLength);
		blockLength = defaultLength;
	}
	
	public SnakeBlock(int length) {
		super(length,length);
		blockLength = length;
	}
	
	public SnakeBlock(int x, int y) {
		super(x,y,defaultLength,defaultLength);
		blockLength = defaultLength;
	}
	
	public SnakeBlock(int x, int y, int length) {
		super(x,y,length,length);
		blockLength = length;
	}
	
	//Methods
	//-------
	public static void setDefaultLength(int length) {
		defaultLength = length;
	}
	public static int getDefaultLength() {
		return defaultLength;
	}
	
	public int getLength() {
		return blockLength;
	}
}
