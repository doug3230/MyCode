package space_invaders;

import java.awt.Color;
import java.awt.Graphics;

public class RectSprite extends GameSprite2D {

	//Constants
	//---------
	private static final Color DEF_COLOUR = Color.WHITE;
	
	//Data Members
	//------------
	private Color rectColour = DEF_COLOUR;
	
	//Constructor
	//-----------
	public RectSprite(int x_coord, int y_coord, int width, int height) {
		setX(x_coord); setY(y_coord);
		setWidth(width); setHeight(height);
	}
	
	@Override
	public void paint(Graphics g) {
		if (!isVisible()) return;
		g.setColor(rectColour);
		g.fillRect(getX(),getY(),getWidth(),getHeight());
	}

	@Override
	public void update() {}
}