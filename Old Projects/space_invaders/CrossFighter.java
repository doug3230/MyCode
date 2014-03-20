package space_invaders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class CrossFighter extends ShipSprite {

	public CrossFighter(int width, int height, Component parent) {
		super(width, height, parent);
	}
	
	//Methods
	//-------
	@Override
	public void paint(Graphics g) {
		if (!isVisible()) return;
		if (isExploding()) {
			g.setColor(Color.RED);
			g.fillOval(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.MAGENTA);
			g.drawOval(getX(), getY(), getWidth(), getHeight());
		} else {
			drawCross(g);
			drawWings(g);
			drawBody(g);
		}
	}
	
	public Point getFiringLocationOf(GameSprite2D ammo) {
		int x_coord = getX() + (getWidth() - ammo.getWidth()) / 2;
		int y_coord = getY() - ammo.getHeight();
		return new Point(x_coord,y_coord);
	}
	
	public void optimiseWidthAndHeight() {
		int width = getWidth(); int height = getHeight();
		width -= width % 18; height -= height % 18;
		setWidth(width); setHeight(height);
	}
	
	//Private Methods
	//---------------
	private void drawBody(Graphics g) {
			int leftCorner_x = getX() + getWidth() / 3;
			int leftCorner_y = getY() + getHeight() / 3;
			int body_width = getWidth() / 3;
			int body_height = body_width * 2;
			
			//draw body of ship
			g.setColor(Color.BLACK);
			g.fillRect(leftCorner_x, leftCorner_y, body_width, body_height);
			
			int windowCorner_x = leftCorner_x + (body_width / 4);
			int windowCorner_y = leftCorner_y + (body_height / 6);
			int window_width = body_width / 2;
			int window_height = body_width / 3;
			
			//draw window
			g.setColor(Color.BLUE);
			g.fillRect(windowCorner_x, windowCorner_y, window_width, window_height);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(windowCorner_x, windowCorner_y, window_width, window_height);
		}
		
	private void drawWings(Graphics g) {
			drawLeftWing(g);
			drawRightWing(g);
		}
		
	private void drawCross(Graphics g) {
			int horizontalRect_x = getX() + getWidth() / 3;
			int horizontalRect_y = getY() + getWidth() / 9;
			int horizontalRect_width = getWidth() / 3;
			int horizontalRect_height = getWidth() / 9;
			
			int verticalRect_x = getX() + 4 * getWidth() / 9;
			int verticalRect_y = getY();
			int verticalRect_width = getWidth() / 9;
			int verticalRect_height = getWidth() / 3;
			
			//fill horizontal rectangle
			g.setColor(Color.YELLOW);
			g.fillRect(horizontalRect_x, horizontalRect_y, 
					horizontalRect_width, horizontalRect_height);
			//fill vertical rectangle
			g.fillRect(verticalRect_x, verticalRect_y, 
					verticalRect_width, verticalRect_height);
		}
		
	private void drawLeftWing(Graphics g) {
			int[] triangleX = {getX(),getX()+getWidth()/3,getX()+getWidth()/3};
			int[] triangleY = {getY()+2*getWidth()/3,getY()+getWidth()/3,getY()+2*getWidth()/3};
			Polygon triangle = new Polygon(triangleX,triangleY,3);
			
			int wingEdge_x = getX();
			int wingEdge_y = getY() + 2 * getWidth() / 3;
			int wingEdge_width = getWidth() / 6;
			int wingEdge_height = getWidth() / 3;
			
			//draw triangle
			g.setColor(Color.DARK_GRAY);
			g.fillPolygon(triangle);
			
			//draw wing chunk
			g.setColor(Color.WHITE);
			g.fillRect(wingEdge_x, wingEdge_y, 
					wingEdge_width, wingEdge_height);
			
			//draw wing outline
			g.setColor(Color.BLACK);
			g.drawPolygon(triangle);
		}
		
	private void drawRightWing(Graphics g) {
			int[] triangleX = {getX()+2*getWidth()/3,getX()+2*getWidth()/3,getX()+getWidth()};
			int[] triangleY = {getY()+getWidth()/3,getY()+2*getWidth()/3,getY()+2*getWidth()/3};
			Polygon triangle = new Polygon(triangleX,triangleY,3);
			
			int wingEdge_x = getX() + 5 * getWidth() / 6 ;
			int wingEdge_y = getY() + 2 * getWidth() / 3;
			int wingEdge_width = getWidth() / 6;
			int wingEdge_height = getWidth() / 3;
			
			//draw triangle
			g.setColor(Color.DARK_GRAY);
			g.fillPolygon(triangle);
			
			//draw wing chunk
			g.setColor(Color.WHITE);
			g.fillRect(wingEdge_x, wingEdge_y, 
					wingEdge_width, wingEdge_height);
			
			//draw wing outline
			g.setColor(Color.BLACK);
			g.drawPolygon(triangle);
		}
}