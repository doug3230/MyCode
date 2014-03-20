package listeners;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseFollower implements MouseMotionListener {

	//Data Members
	//------------
	private Component component;
	
	//Constructor
	//-----------
	public MouseFollower() {setComponent(null);}
	public MouseFollower(Component component) {setComponent(component);}
	
	//Methods
	//-------
	@Override
	public void mouseDragged(MouseEvent e) {mouseMoved(e);}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (component == null)
			throw new RuntimeException("Error: no component set to MouseFollower");
		Point mouseLocation = e.getLocationOnScreen();
		component.setLocation(mouseLocation.x - component.getWidth(), mouseLocation.y);
	}
	
	public Component getComponent() {return component;}
	public void setComponent(Component Component) {this.component = Component;}
}
