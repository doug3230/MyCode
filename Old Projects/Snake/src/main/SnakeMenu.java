package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SnakeMenu extends JMenuBar {
	//Constants
	//---------
	public static final String MENU_HEADING = "Menu";
	public static final String[] MENU_OPTIONS = {"New Game", "Pause/Unpause Game", "Options", "Help", "Exit"};
	
	//Data Members
	//------------
	private SnakeWindow window;
	private MenuListener listener = new MenuListener();
	
	//Constructor
	//-----------
	public SnakeMenu(SnakeWindow w) {
		super();
		window = w;
		JMenu heading = new JMenu(MENU_HEADING);
		JMenuItem option;
		for (String choice : MENU_OPTIONS) {
			option = new JMenuItem(choice);
			option.addActionListener(listener);
			heading.add(option);
		}
		this.add(heading);
	}
	
	//Inner Class
	//-----------
	private class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			if (command.equals("New Game"))
				window.newGame();
			else if (command.equals("Pause/Unpause Game"))
				window.pauseOrUnpauseGame();
			else if (command.equals("Options"))
				window.showSnakeOptions();
			else if (command.equals("Help"))
				window.showSnakeHelp();
			else
				System.exit(0);
		}		
	}
}