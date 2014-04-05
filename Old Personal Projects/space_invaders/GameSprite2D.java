package space_invaders;

import java.awt.Graphics;

public abstract class GameSprite2D extends GameSprite {
	
	//Data Members
	//------------
	private int x_coord = 0;
	private int y_coord = 0;
	private int width = 0;
	private int height = 0;
	
	//Methods
	//-------
	public int getX() {return x_coord;}
	public int getY() {return y_coord;}
	public void setX(int x) {x_coord = x;}
	public void setY(int y) {y_coord = y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public void setWidth(int w) {width = w;}
	public void setHeight(int h) {height = h;}
}