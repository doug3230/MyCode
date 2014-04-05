package space_invaders;

import java.awt.event.*;
import java.util.*;

public class ShipKeyboardListener extends KeyAdapter {

	//Constants
	//---------
	private static final int DEF_SHIP_SPEED_X = 10;
	private static final int DEF_SHIP_SPEED_Y = 0;
	private static final int FIRE_BUTTON = KeyEvent.VK_F;
	
	//Data Members
	//------------
	private ShipSprite ship;
	private int ship_left_vel = -DEF_SHIP_SPEED_X;
	private int ship_right_vel = DEF_SHIP_SPEED_X;
	private int ship_up_vel = -DEF_SHIP_SPEED_Y;
	private int ship_down_vel = DEF_SHIP_SPEED_Y;
	
	//Constructor
	//-----------
	public ShipKeyboardListener(ShipSprite ship) {this.ship = ship;}
	
	//Methods
	//-------
	@Override
	public void keyPressed(KeyEvent e) {
		if (!ship.isActive()) return;
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_LEFT)
			ship.setVelX(ship_left_vel);
		else if (keycode == KeyEvent.VK_RIGHT)
			ship.setVelX(ship_right_vel);
		else if (keycode == KeyEvent.VK_UP)
			ship.setVelY(ship_up_vel);
		else if (keycode == KeyEvent.VK_DOWN)
			ship.setVelY(ship_down_vel);
		else if (keycode == FIRE_BUTTON)
			ship.fire();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_LEFT)
			ship.setVelX(0);
		else if (keycode == KeyEvent.VK_RIGHT)
			ship.setVelX(0);
		else if (keycode == KeyEvent.VK_UP)
			ship.setVelY(0);
		else if (keycode == KeyEvent.VK_DOWN)
			ship.setVelY(0);
		else if (keycode == FIRE_BUTTON)
			ship.ceaseFire();
	}
	
	public void setShip(ShipSprite ship) {this.ship = ship;}
	public ShipSprite getShip() {return ship;}
	
	public void setShipSpeedX(int speed_x) {
		if (speed_x < 0) 
			speed_x = -speed_x;
		ship_left_vel = -speed_x;
		ship_right_vel = speed_x;
	}
	
	public void setShipSpeedY(int speed_y) {
		if (speed_y < 0) 
			speed_y = -speed_y;
		ship_up_vel = -speed_y;
		ship_down_vel = speed_y;
	}
	
	public void setShipSpeed(int speed_x,int speed_y) {
		setShipSpeedX(speed_x); setShipSpeedY(speed_y);
	}
	
	public void setShipSpeed(int speed) {setShipSpeed(speed,speed);}
	
	public void setShipLeftSpeed(int speed) {
		if (speed > 0) speed = -speed;
		ship_left_vel = speed;
	}
	
	public void setShipRightSpeed(int speed) {
		if (speed < 0) speed = -speed;
		ship_right_vel = speed;
	}
	
	public void setShipUpSpeed(int speed) {
		if (speed > 0) speed = -speed;
		ship_up_vel = speed;
	}
	
	public void setShipDownSpeed(int speed) {
		if (speed < 0) speed = -speed;
		ship_down_vel = speed;
	}
	
	public int getShipLeftVel() {return ship_left_vel;}
	public int getShipRightVel() {return ship_right_vel;}
	public int getShipUpVel() {return ship_up_vel;}
	public int getShipDownVel() {return ship_down_vel;}
}