package space_invaders;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class AlienSprite extends ShipSprite {
	
	//Data Members
	//------------
	private boolean leftGunCoolingDown = false;
	private boolean rightGunCoolingDown = false;
	
	//Constructor
	//-----------
	public AlienSprite(int width, int height, Component parent) {
		super(width, height, parent);
	}

	//Methods
	//-------
	@Override
	public void paint(Graphics g) {
		if (!isVisible()) return;
		if (isExploding()) {
			g.setColor(Color.YELLOW);
			g.fillOval(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.ORANGE);
			g.drawOval(getX(), getY(), getWidth(), getHeight());
		} else {
			g.setColor(Color.GREEN);
			g.fillOval(getX() + getWidth() / 4, getY(), getWidth() / 2, getHeight());
			g.setColor(Color.BLUE);
			g.fillRect(getX(), getY() + getHeight() / 2, getWidth(), getHeight() / 2);
			g.setColor(Color.CYAN);
			g.drawRect(getX(), getY() + getHeight() / 2, getWidth(), getHeight() / 2);
		}
	}
	
	@Override
	public Point getFiringLocationOf(GameSprite2D ammo) {
		if (isCoolingDown() || (!leftGunCoolingDown && !rightGunCoolingDown)) {
			if (chooseToFireLeft()) {
				leftGunCoolingDown = true;
				return leftFiringLocation(ammo);
			} else {
				rightGunCoolingDown = true;
				return rightFiringLocation(ammo);
			}
		} else if (leftGunCoolingDown) {
			rightGunCoolingDown = true;
			return rightFiringLocation(ammo);
		}
		else {
			//right gun is cooling down
			leftGunCoolingDown = true;
			return leftFiringLocation(ammo);
		} 
	}
	
	@Override
	public boolean isCoolingDown() {return leftGunCoolingDown && rightGunCoolingDown;}
	@Override
	public void doneCoolingDown() {leftGunCoolingDown = false; rightGunCoolingDown = false;}
	
	//Private Methods
	//---------------
	private boolean chooseToFireLeft() {
		if (Math.random() < 0.5) return true;
		else return false;
	}
	
	private Point leftFiringLocation(GameSprite2D ammo) {
		int ammo_x = getX() + getWidth()/4 - ammo.getWidth()/2;
		int ammo_y = getY() + getHeight();
		return new Point(ammo_x,ammo_y);
	}
	
	private Point rightFiringLocation(GameSprite2D ammo) {
		int ammo_x = getX() + 3*getWidth()/4 - ammo.getWidth()/2;
		int ammo_y = getY() + getHeight();
		return new Point(ammo_x,ammo_y);
	}
}