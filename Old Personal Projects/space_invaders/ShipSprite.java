package space_invaders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class ShipSprite extends MovableGameSprite2D {

	//Data Members
	//------------
	private Component parent = null;
	private boolean firing = false;
	private boolean coolingDown = false;
	private boolean isExploding = false;
	private CooldownManager manager = new CooldownManager();
	private ExplosionManager explosionManager = new ExplosionManager();
	
	//Constructor
	//-----------
	public ShipSprite(int width, int height, Component parent) {
		setWidth(width); setHeight(height); this.parent = parent;
	}
	
	//Methods
	//-------
	@Override
	public void update() {
		if (!isActive()) return;
		int vel_x = getVelX();
		int vel_y = getVelY();
		
		if (!canMoveX(vel_x)) 
			vel_x = 0;
		if (!canMoveY(vel_y))
			vel_y = 0;
		setVelocity(vel_x,vel_y);
		updatePosition();
	}
	
	@Override
	public void restore() {isExploding = false; super.restore();}
	
	public boolean canMoveX(int x_shift) {
		int lower_width = 0;
		int upper_width = parent.getWidth();
		if (parent instanceof Container) {
			Insets insets = ((Container) parent).getInsets();
			lower_width += insets.left;
			upper_width -= insets.right;
		}
		return (getX() + x_shift >= lower_width) && 
				(getX() + this.getWidth() + x_shift <= upper_width);
	}
	
	public boolean canMoveY(int y_shift) {
		int lower_height = 0;
		int upper_height = parent.getHeight();
		if (parent instanceof Container) {
			Insets insets = ((Container) parent).getInsets();
			lower_height += insets.top;
			upper_height -= insets.bottom;
		}
		return (getY() + y_shift >= lower_height) && 
				(getY() + this.getHeight() + y_shift <= upper_height);
	}
	
	public boolean canMove(int x_shift, int y_shift) {
		return canMoveX(x_shift) && canMoveY(y_shift);
	}
	
	public Component getParent() {return parent;}
	public void setParent(Component c) {parent = c;}
	public void fire() {if (!isCoolingDown()) firing = true;}
	public void ceaseFire() {firing = false;}
	public boolean isFiring() {return firing;}
	public int getCooldownDelay() {return manager.cooldownTimer.getDelay();}
	public void setCooldownDelay(int d) {manager.cooldownTimer.setDelay(d);}
	public boolean isCoolingDown() {return coolingDown;}
	public void explode() {isExploding = true; explosionManager.explodeTimer.start();}
	public boolean isExploding() {return isExploding;}
	public void coolDown() {
		coolingDown = true; firing = false;
		manager.cooldownTimer.start();
	}
	public void doneCoolingDown() {coolingDown = false;}
	
	//Abstract Methods
	//----------------
	public abstract Point getFiringLocationOf(GameSprite2D ammo);
	
	//Inner Classes
	//-------------
	private class CooldownManager implements ActionListener {

		//Constants
		//---------
		private static final int DEF_COOLDOWN_RATE = 400;
		
		//Data Members
		//------------
		private Timer cooldownTimer = new Timer(DEF_COOLDOWN_RATE,this);
		
		//Methods
		//-------
		@Override
		public void actionPerformed(ActionEvent e) {doneCoolingDown(); cooldownTimer.stop();}
	}
	
	private class ExplosionManager implements ActionListener {
		
		//Constants
		//---------
		private static final int DEF_EXPLODE_RATE = 300;
		
		//Data Members
		//------------
		private Timer explodeTimer = new Timer(DEF_EXPLODE_RATE,this);
		
		//Methods
		//-------
		@Override
		public void actionPerformed(ActionEvent e) {
			if (isExploding)
				{isExploding = false; suspend(); explodeTimer.stop();}
		}									
	}
}