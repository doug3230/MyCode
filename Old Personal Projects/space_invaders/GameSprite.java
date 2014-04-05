package space_invaders;

import java.awt.Graphics;

public abstract class GameSprite {
	
	//Data Members
	//------------
	private boolean visible = true;
	private boolean active = true;
	
	//Methods
	//-------
	public void suspend() {visible = false; active = false;}
	public void restore() {visible = true; active = true;}
	public boolean isVisible() {return visible;}
	public boolean isActive() {return active;}
	public void setVisible(boolean b) {visible = b;}
	public void setActive(boolean b) {active = b;}
	
	//Abstract Methods
	//----------------
	public abstract void paint(Graphics g);
	public abstract void update();
}
