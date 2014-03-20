package tetris;

import java.awt.BorderLayout;

import javax.swing.JApplet;

public class TetrisApplet extends JApplet {
	
	//Data Members
	//------------
	private TetrisGamePanel gamePanel = new TetrisGamePanel();
	
	//Constructor
	//-----------
	public TetrisApplet() {
		super();
	}
	
	//Methods
	//-------
	public void init() {
		this.setLayout(new BorderLayout());
		this.add(gamePanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
