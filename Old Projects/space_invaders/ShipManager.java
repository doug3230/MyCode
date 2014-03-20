package space_invaders;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;

public class ShipManager extends GameSprite {
	
	//Constants
	//---------
	private static final int DEF_BEAM_WIDTH = 6;
	private static final int DEF_BEAM_HEIGHT = 10;
	private static final int DEF_BEAM_VEL_X = 0;
	private static final int DEF_BEAM_VEL_Y = -10;
	
	//Data Members
	//------------
	private ShipSprite ship;
	private Container parentComponent;
	private ScorePanel scorePanel;
	private ArrayList<ShipBeamSprite> beams = new ArrayList<ShipBeamSprite>();
	private ArrayList<ShipBeamSprite> beamsToRemove = new ArrayList<ShipBeamSprite>();
	private AliensManager aliensManager;
	private int beam_width = DEF_BEAM_WIDTH;
	private int beam_height = DEF_BEAM_HEIGHT;
	private int beam_vel_x = DEF_BEAM_VEL_X;
	private int beam_vel_y = DEF_BEAM_VEL_Y;
	
	//Constructor
	//-----------
	public ShipManager(ShipSprite ship, Container parentComponent, ScorePanel scorePanel) {
		this.ship = ship; this.parentComponent = parentComponent;
		this.scorePanel = scorePanel;
		ship.setParent(parentComponent);
		ship.suspend();
		suspend();
	}
	
	//Methods
	//-------
	public ShipSprite getShip() {return ship;}
	public void setShip(ShipSprite s) {ship = s; ship.setParent(parentComponent);}
	public ArrayList<ShipBeamSprite> getBeams() {return beams;}
	
	public int getBeamVelX() {return beam_vel_x;}
	public int getBeamVelY() {return beam_vel_y;}
	public void setBeamVelX(int v) {beam_vel_x = v;}
	public void setBeamVelY(int v) {beam_vel_y = v;}
	public int getBeamWidth() {return beam_width;}
	public int getBeamHeight() {return beam_height;}
	public void setBeamWidth(int w) {beam_width = w;}
	public void setBeamHeight(int h) {beam_height = h;}
	public Container getParentComponent() {return parentComponent;}
	public void setParentComponent(Container p) {
		parentComponent = p; ship.setParent(p);
		for (ShipBeamSprite sprite : beams)
			sprite.setParent(p);
	}
	public void setAliensManager(AliensManager aliensManager) {this.aliensManager = aliensManager;}
	
	@Override
	public void paint(Graphics g) {
		if (ship.isVisible()) ship.paint(g);
		for (ShipBeamSprite beam : beams)
			beam.paint(g);
	}

	@Override
	public void update() {
		if (!isActive()) return;
		ship.update();
		if (ship.isFiring()) {
			ShipBeamSprite beam = new ShipBeamSprite(beam_width,beam_height,parentComponent);
			Point location = ship.getFiringLocationOf(beam);
			beam.setPosition(location.x, location.y);
			beam.setVelocity(beam_vel_x, beam_vel_y);
			beams.add(beam);
			ship.coolDown();
		}
		for (ShipBeamSprite beam : beams) {
			beam.update();
			if (!beam.isActive())
				beamsToRemove.add(beam);
		}
		for (ShipBeamSprite beam : beamsToRemove)
			beams.remove(beam);
		beamsToRemove.clear();
		
		if (!ship.isExploding()) {
			for (ShipBeamSprite beam : aliensManager.getBeams())
				if (beam.intersects(ship))
					{ship.explode(); beam.suspend();}
		}
		if (!ship.isVisible()) {
			ship.restore();
			scorePanel.loseLife();
			if (scorePanel.isOutOfLives()) {
				scorePanel.gameIsOver();
				aliensManager.destroyAllAliens();
				aliensManager.removeAlienBeams();
			}
		}
	}
	
	public void removeShipBeams() {beams.clear();}
	
	public void placeShip() {
		Insets insets = parentComponent.getInsets();
		int x_coord = (parentComponent.getWidth() - ship.getWidth()) / 2;
		int y_coord = (parentComponent.getHeight() - ship.getHeight() - insets.bottom);
		ship.setPosition(x_coord, y_coord);
		ship.restore();
	}
}