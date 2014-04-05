package listeners;

import java.awt.Component;
import java.awt.event.MouseEvent;

import piles.Pile;

public class PileMouseFollower extends MouseFollower {
	public PileMouseFollower() {super();}
	public PileMouseFollower(Component component) {super(component);}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		Pile pile = getComponent();
		pile.updateDisplay();
	}
	
	public void setComponent(Component component) {
		if (!(component instanceof Pile) && component != null)
			throw new RuntimeException("Component of PileMouseFollower must be a Pile");
		super.setComponent(component);
	}
	public Pile getComponent() {return (Pile) super.getComponent();}
}
