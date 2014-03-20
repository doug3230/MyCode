package board;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import cards.Card;

public class SolitaireWindow extends JFrame {

	//Constants
	//---------
	public static final int MIN_WIDTH = 400;
	public static final int MIN_HEIGHT = 400;
	
	//Data Members
	//------------
	private CardGameBoard gameBoard;
	private String pathToFolder;
	
	//Constructor
	//-----------
	public SolitaireWindow(String title, String pathToFolder, int width, int height) {
		super();
		this.pathToFolder = pathToFolder;
		setTitle(title);
		setSize(width, height);
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//Methods
	//-------
	public CardGameBoard getGameBoard() {return gameBoard;}
	
	public void setGameBoard(CardGameBoard gameBoard) {
		this.gameBoard = gameBoard;
		gameBoard.resizeComponents();
		gameBoard.repositionComponents();
		setContentPane(gameBoard);
	}
	
	public void newSolitaireGame() {
		setGameBoard(new SolitaireGameBoard(pathToFolder, getWidth(), getHeight()));
		gameBoard.newGame();
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (gameBoard == null)
			return;
		else
			gameBoard.updateDisplay(getWidth(), getHeight());
	}
}