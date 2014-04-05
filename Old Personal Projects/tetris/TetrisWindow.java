package tetris;

import javax.swing.*;

public class TetrisWindow extends JFrame {

	//Constants
	//---------
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 500;
	
	//Data Members
	//------------
	private TetrisGamePanel gamePanel = new TetrisGamePanel();
	
	//Constructor
	//-----------
	public TetrisWindow() {
		super("Tetris");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(gamePanel);
	}
	
	//Testing
	//-------
	public static void main(String[] args) {
		TetrisWindow window = new TetrisWindow();
		window.setVisible(true);
	}
}