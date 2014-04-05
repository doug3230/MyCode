package space_invaders;

import java.awt.Container;
import java.awt.Graphics;

public class MotherShipManager extends GameSprite {

	//Constants
	//---------
	private static final int DEF_MOTHERSHIP_SPEED_X = 6;
	private static final int DEF_MOTHERSHIP_SPEED_Y = 0;
	private static final int MOTHERSHIP_Y = 15;
	
	//Data Members
	//------------
	private MotherShipSprite mothership;
	private Container parentComponent;
	private ShipManager shipManager;
	private ScorePanel scorePanel;
	private boolean comeFromRight = true;
	
	//Constructor
	//-----------
	public MotherShipManager(MotherShipSprite mothership, Container parentComponent, ScorePanel scorePanel,
			ShipManager shipManager) {
		this.mothership = mothership; this.parentComponent = parentComponent;
		this.shipManager = shipManager; this.scorePanel = scorePanel;
		mothership.setParent(parentComponent);
		mothership.suspend();
	}
	
	@Override
	public void paint(Graphics g) {
		if (mothership.isVisible())
			mothership.paint(g);
	}

	@Override
	public void update() {
		if (!isActive() || !mothership.isActive()) return;
		if (mothership.canMoveX(mothership.getVelX()))
			mothership.updatePosition();
		else
			mothership.suspend();
		
		for (ShipBeamSprite beam : shipManager.getBeams())
			if (beam.intersects(mothership) && !mothership.isExploding() 
					&& mothership.isVisible()) {
				mothership.explode();
				scorePanel.addToScore(1000);
			}
	}

	public void launchShip() {
		int x, vel_x;
		if (comeFromRight) {
			x = parentComponent.getWidth() - mothership.getWidth() - parentComponent.getInsets().right;
			vel_x = -DEF_MOTHERSHIP_SPEED_X;
		} else {
			x = parentComponent.getInsets().left;
			vel_x = DEF_MOTHERSHIP_SPEED_X;
		}
		mothership.setPosition(x, MOTHERSHIP_Y);
		mothership.setVelocity(vel_x, DEF_MOTHERSHIP_SPEED_Y);
		comeFromRight = !comeFromRight;
		mothership.restore();
	}
	
	public boolean isShipOut() {return mothership.isActive();}
	
	public void removeShip() {mothership.suspend();}
}