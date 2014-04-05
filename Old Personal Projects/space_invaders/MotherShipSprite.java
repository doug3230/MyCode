package space_invaders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class MotherShipSprite extends AlienSprite {

	public MotherShipSprite(int width, int height, Component parent) {
		super(width, height, parent);
	}

	@Override
	public void paint(Graphics g) {
		if (isExploding()) {
			g.setColor(Color.YELLOW);
			g.fillOval(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.ORANGE);
			g.drawOval(getX(), getY(), getWidth(), getHeight());
		} else {
			g.setColor(Color.RED);
			g.fillOval(getX() + getWidth() / 4, getY(), getWidth() / 2, getHeight());
			g.setColor(Color.BLACK);
			g.fillRect(getX(), getY() + getHeight() / 2, getWidth(), getHeight() / 2);
			g.setColor(Color.MAGENTA);
			g.drawRect(getX(), getY() + getHeight() / 2, getWidth(), getHeight() / 2);
		}
	}
}
