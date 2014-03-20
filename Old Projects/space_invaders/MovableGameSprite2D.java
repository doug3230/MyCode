package space_invaders;

public abstract class MovableGameSprite2D extends GameSprite2D implements
		Moveable {
	
	//Data Members
	//------------
	private int x_velocity = 0;
	private int y_velocity = 0;

	//Methods
	//-------
	@Override
	public void setPosition(int x, int y) {setX(x); setY(y);}

	@Override
	public void setVelocity(int x, int y) {x_velocity = x; y_velocity = y;}

	@Override
	public void updatePosition() {setPosition(getX() + x_velocity, getY() + y_velocity);}
	
	public int getVelX() {return x_velocity;}
	public int getVelY() {return y_velocity;}
	public void setVelX(int x) {x_velocity = x;}
	public void setVelY(int y) {y_velocity = y;}
	public void addVelX(int x) {x_velocity += x;}
	public void addVelY(int y) {y_velocity += y;}
}