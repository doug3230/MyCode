package space_invaders;

import java.awt.*;

public class ShipBeamSprite extends MovableGameSprite2D {

	//Constants
	//---------
	private static final Color DEF_BEAM_COLOUR = Color.YELLOW;
	private static final Color DEF_BEAM_BORDER_COLOUR = Color.ORANGE;
	
	//Data Members
	//------------
	private Component parent = null;
	private Color beamColour, beamBorderColour;
	
	//Constructor
	//-----------
	public ShipBeamSprite(int width, int height, Component parent, Color beamColour, Color beamBorderColour) {
		setWidth(width); setHeight(height); this.parent = parent;
		this.beamColour = beamColour; this.beamBorderColour = beamBorderColour;
	}
	
	public ShipBeamSprite(int width, int height, Component parent) {
		this(width,height,parent,DEF_BEAM_COLOUR,DEF_BEAM_BORDER_COLOUR);
	}
	
	//Methods
	//-------
	@Override
	public void paint(Graphics g) {
		if (!isVisible()) return;
		g.setColor(beamColour);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(beamBorderColour);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		if (!isActive()) return;
		int future_x = getX() + getVelX();
		int future_y = getY() + getVelY();
		
		int lower_width = 0;
		int upper_width = parent.getWidth();
		int lower_height = 0;
		int upper_height = parent.getHeight();
		if (parent instanceof Container) {
			Insets insets = ((Container) parent).getInsets();
			lower_width += insets.left;
			upper_width -= insets.right;
			lower_height += insets.top;
			upper_height -= insets.bottom;
		}
		
		if (future_x < lower_width || future_x + getWidth() > upper_width || 
				future_y < lower_height || future_y + getHeight() > upper_height)
			suspend();
		else
			updatePosition();
	}
	
	public boolean intersects(GameSprite2D sprite) {
		boolean x_intersection = (getX() <= sprite.getX() + sprite.getWidth()) &&
				(sprite.getX() <= getX() + getWidth());
		boolean y_intersection = (getY() <= sprite.getY() + sprite.getHeight()) &&
				(sprite.getY() <= getY() + getHeight());
		return isActive() && x_intersection && y_intersection;
	}
	public Color getBeamColor() {return beamColour;}
	public Color getBeamBorderColor() {return beamBorderColour;}
	public void setBeamColor(Color c) {beamColour = c;}
	public void setBeamBorderColor(Color c) {beamBorderColour = c;}
	public Component getParent() {return parent;}
	public void setParent(Component p) {parent = p;}
}