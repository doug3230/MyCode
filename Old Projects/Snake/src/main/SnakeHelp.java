package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SnakeHelp extends JFrame implements ActionListener {
	// Constants
	// ---------
	public static final int WINDOW_WIDTH = 540;
	public static final int WINDOW_HEIGHT = 320;
	public static final String ENTER = System.getProperty("line.separator");
	public static final String HELP_TEXT = "Instructions"
			+ ENTER
			+ "--------------"
			+ ENTER
			+ "You are a snake. You like to eat stuff. Lots of stuff. Even stuff that isn't good for you!" 
			+ ENTER
			+ "Control yourself and avoid trying to 'eat the wall' so to speak and also please "
			+ ENTER
			+ "don't try eating your own body again!"
			+ ENTER
			+ ENTER
			+ "Change the direction of the snake with the arrow keys. Try to move into the coloured blocks"
			+ ENTER 
			+ "in order to make the snake bigger. The game ends when the snake hits a wall or its own body."
			+ ENTER
			+ ENTER
			+ "It is also possible to win the game but that requires a looooooot of eating!"
			+ ENTER
			+ ENTER
			+ "--------------"
			+ ENTER
			+ "Game coded by Richard Douglas"
			+ ENTER
			+ ENTER
			+ "If you have any questions, contact him at doug3230@mylaurier.ca"
			+ ENTER;
			
	
	//Data Members
	//------------
	private SnakeWindow snakeWindow;
	
	//Constructor
	//-----------
	public SnakeHelp(SnakeWindow window) {
		super();
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		snakeWindow = window;
		
		JTextArea textField = new JTextArea();
		textField.setText(HELP_TEXT);
		textField.setEditable(false);
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(okButton);
		
		getContentPane().add(textField,BorderLayout.CENTER);
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);
	}
	
	//Methods
	//-------
	public void actionPerformed(ActionEvent arg0) {
		snakeWindow.setVisible(true);
		this.setVisible(false);
	}
}