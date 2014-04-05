package space_invaders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class AliensManager extends GameSprite {

	//Constants
	//---------
	private static final int DEF_ALIEN_SPEED_X = 3;
	private static final int DEF_ALIEN_SPEED_Y = 3;
	private static final double SHOULD_ALIEN_FIRE = 0.95; //fire if Math.random() is >
	
	private static final int DEF_BEAM_WIDTH = 4;
	private static final int DEF_BEAM_HEIGHT = 8;
	private static final int DEF_BEAM_VEL_X = 0;
	private static final int DEF_BEAM_VEL_Y = 10;
	
	//Data Members
	//------------
	private AlienSprite[][] aliens;
	private Component parentComponent;
	private ArrayList<ShipBeamSprite> beams = new ArrayList<ShipBeamSprite>();
	private ArrayList<ShipBeamSprite> beamsToRemove = new ArrayList<ShipBeamSprite>();
	private ShipManager shipManager;
	private int beam_width = DEF_BEAM_WIDTH;
	private int beam_height = DEF_BEAM_HEIGHT;
	private int beam_vel_x = DEF_BEAM_VEL_X;
	private int beam_vel_y = DEF_BEAM_VEL_Y;
	
	private int start_x;
	private int start_y;
	private int current_y;
	private int bottom_line_y = 380;
	private int hor_gap;
	private int ver_gap;
	private int alien_width;
	private int alien_height;
	
	private int last_vel_x = 0;
	private int alien_vel_x = 0; 
	private int alien_vel_y = 0;
	private int aliens_left = 0;
	private ScorePanel scorePanel;
	private MotherShipManager motherManager;
	
	//Constructor
	//-----------
	public AliensManager(AlienSprite[][] aliens, Component parentComponent, int start_x,
			int start_y, int hor_gap, int ver_gap, int alien_width, int alien_height,
			ScorePanel scorePanel, MotherShipManager motherManager) {
		this.aliens = aliens; this.parentComponent = parentComponent;
		if (aliens.length > 0)
			aliens_left = aliens.length * aliens[0].length;
		else
			aliens_left = 0;
		this.start_x = start_x; this.start_y = start_y; this.current_y = start_y;
		this.hor_gap = hor_gap; this.ver_gap = ver_gap;
		this.alien_width = alien_width; this.alien_height = alien_height;
		this.scorePanel = scorePanel; this.motherManager = motherManager;
		initializeAliens();
		suspend();
	}
	
	//Methods
	//-------
	public AlienSprite[][] getAliens() {return aliens;}
	public void setAliens(AlienSprite[][] aliens) {this.aliens = aliens; updateAliensParent();}
	public ArrayList<ShipBeamSprite> getBeams() {return beams;}
	
	public int getBeamVelX() {return beam_vel_x;}
	public int getBeamVelY() {return beam_vel_y;}
	public void setBeamVelX(int v) {beam_vel_x = v;}
	public void setBeamVelY(int v) {beam_vel_y = v;}
	public int getBeamWidth() {return beam_width;}
	public int getBeamHeight() {return beam_height;}
	public void setBeamWidth(int w) {beam_width = w;}
	public void setBeamHeight(int h) {beam_height = h;}
	public Component getParentComponent() {return parentComponent;}
	public void setParentComponent(Component p) {
		parentComponent = p; updateAliensParent();
		for (ShipBeamSprite sprite : beams)
			sprite.setParent(p);
	}
	public void setShipManager(ShipManager shipManager) {this.shipManager = shipManager;}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(0, bottom_line_y, parentComponent.getWidth(), bottom_line_y);
		for (AlienSprite[] row : aliens)
			for (AlienSprite alien : row)
				alien.paint(g);
		for (ShipBeamSprite beam : beams)
			beam.paint(g);
	}

	@Override
	public void update() {
		if (!isActive()) return;
		if (alien_vel_y != 0) {
			current_y += alien_vel_y;
			setAlienVel(-last_vel_x,0);
		}
		if (!aliensCanMoveX())
			setAlienVel(0,DEF_ALIEN_SPEED_Y);
		updateAliensPosition();
		fireAlienBeams();

		for (ShipBeamSprite beam : beams) {
			beam.update();
			if (!beam.isActive())
				beamsToRemove.add(beam);
		}
		for (ShipBeamSprite beam : beamsToRemove)
			beams.remove(beam);
		beamsToRemove.clear();
		
		for (ShipBeamSprite beam : shipManager.getBeams())
			checkIfBeamHitsAnAlien(beam);
		
		if (aliens_left == 0) {
			placeAliens(current_y);
			setAlienVel(DEF_ALIEN_SPEED_X,0);
			scorePanel.addLife();
		}
		checkIfAliensLanded();
	}
	
	public void destroyAllAliens() {
		for (AlienSprite[] row : aliens)
			for (AlienSprite alien : row)
				alien.suspend();
	}
	
	public void removeAlienBeams() {beams.clear();}
	
	public void placeAliens() {
		placeAliens(start_y);
		current_y = start_y;
		setAlienVel(DEF_ALIEN_SPEED_X,0);
	}
	
	//Private Methods
	//---------------
	private void updateAliensParent() {
		for (AlienSprite[] row : aliens)
			for (AlienSprite alien : row)
				alien.setParent(parentComponent);
	}
	
	private void setAlienVel(int vel_x,int vel_y) {
		last_vel_x = alien_vel_x;
		alien_vel_x = vel_x;
		alien_vel_y = vel_y;
		for (AlienSprite[] row : aliens)
			for (AlienSprite alien : row)
				alien.setVelocity(vel_x, vel_y);
	}
	
	private boolean aliensCanMoveX() {
		boolean canMove = true;
		int i = 0; int j;
		while (canMove && i < aliens.length) {
			j = 0;
			while (canMove && j < aliens[i].length) {	
				canMove = aliens[i][j].canMoveX(alien_vel_x);
				j++;
			} 
			i++;
		}
		return canMove;
	}
	
	private void updateAliensPosition() {
		for (AlienSprite[] row : aliens)
			for (AlienSprite alien : row)
				alien.update();
	}
	
	private void fireAlienBeams() {
		AlienSprite[] aliensToFire = new AlienSprite[aliens[0].length];
		for (int j = 0; j < aliens[0].length; j++)
			aliensToFire[j] = findBottomAlienInColumn(j);
		
		chooseAliens(aliensToFire);
		for (AlienSprite alien : aliensToFire) {
			if (alien == null) continue;
			if (decideToFire())
				alien.fire();
			if (alien.isFiring()) {
				ShipBeamSprite beam = new ShipBeamSprite(beam_width,beam_height,parentComponent);
				Point location = alien.getFiringLocationOf(beam);
				beam.setBeamColor(Color.RED);
				beam.setBeamBorderColor(Color.MAGENTA);
				beam.setPosition(location.x, location.y);
				beam.setVelocity(beam_vel_x, beam_vel_y);
				beams.add(beam);
				if (alien.isCoolingDown())
					alien.coolDown();
			}
		}
	}
	
	private AlienSprite findBottomAlienInColumn(int j) {
		int i = aliens.length - 1;
		AlienSprite alien = null;
		while (alien == null && i >= 0) {
			if (aliens[i][j].isActive())
				alien = aliens[i][j];
			i--;
			}
		return alien;
	}
	
	private boolean decideToFire() {
		return (Math.random() > SHOULD_ALIEN_FIRE);
	}
	
	private void chooseAliens(AlienSprite[] aliensToFire) {
		for (int i = 0; i < aliensToFire.length; i++) {
			int eliminate = (int) (Math.random() * aliensToFire.length);
			aliensToFire[eliminate] = null;
		}
	}
	
	private void checkIfBeamHitsAnAlien(ShipBeamSprite beam) {
		boolean notHit = true;
		int i = 0; int j = 0;
		while (i < aliens.length && notHit) {
			j = 0;
			while (j < aliens[i].length && notHit) {
				if (aliens[i][j].isVisible() && !aliens[i][j].isExploding() && 
						beam.intersects(aliens[i][j]))
					notHit = false;
				else
					j++;
			}
			if (notHit) i++;
		}
		
		if (!notHit) {
			beam.suspend();
			aliens[i][j].explode();
			int points = (aliens.length - i) * 20;
			scorePanel.addToScore(points);
			aliens_left -= 1;
			if (!motherManager.isShipOut() && Math.random() < 0.1)
				motherManager.launchShip();
		}
	}
	
	private void initializeAliens() {
		for (int i = 0; i < aliens.length; i++)
			for (int j = 0; j < aliens[i].length; j++) {
				aliens[i][j] = new AlienSprite(alien_width,alien_height,parentComponent);
				aliens[i][j].setCooldownDelay(2000);
				aliens[i][j].suspend();
			}
		this.current_y = start_y;
	}
	
	private void placeAliens(int y_coord) {
		int x = start_x;
		int y = y_coord;
		
		for (int i = 0; i < aliens.length; i++) {
			x = start_x;
			for (int j = 0; j < aliens[i].length; j++) {
				aliens[i][j].setPosition(x,y);
				aliens[i][j].restore();
				x += hor_gap + alien_width;
			}
			y += ver_gap + alien_height;
		}
			
		if (aliens.length > 0)
			aliens_left = aliens.length * aliens[0].length;
		else
			aliens_left = 0;
	}
	
	private void checkIfAliensLanded() {
		for (AlienSprite[] row : aliens)
			for (AlienSprite alien : row)
				if (alien.isActive() && alien.getY() + alien.getHeight() >= bottom_line_y) {
					scorePanel.aliensLanded();
					removeAlienBeams();
					destroyAllAliens();
				}
	}
}