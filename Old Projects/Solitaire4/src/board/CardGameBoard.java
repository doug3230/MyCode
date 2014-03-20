package board;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import piles.Pile;

import cards.Card;
import cards.CardSprite;

public class CardGameBoard extends JLayeredPane {
	
	//Constants
	//---------
	public static final int BACKGROUND_LAYER = 0;
	public static final int LABEL_LAYER = 1;
	
	//Data Members
	//------------
	protected ArrayList<CardSprite> cardSprites = new ArrayList<CardSprite>();
	protected ArrayList<Pile> piles = new ArrayList<Pile>();
	protected JPanel backgroundPanel;
	
	//Constructor
	//-----------
	public CardGameBoard(String pathToFolder) {
		super();
		initBackgroundPanel();
		initCardSprites(pathToFolder);
		initPiles();
		initListeners();
		addPilesToBoard();
	}
	
	//Methods
	//-------
	public void newGame() {}
	
	public void resizeComponents() {backgroundPanel.setSize(getSize());}
	
	public void repositionComponents() {}
	
	public JPanel getBackgroundPanel() {return backgroundPanel;}
	
	public void setBackgroundPanel(JPanel panel) {
		if (backgroundPanel != null) remove(backgroundPanel);
		backgroundPanel = panel; 
		add(backgroundPanel, new Integer(BACKGROUND_LAYER));
	}
	
	public void initCardSprites(String pathToFolder) {
		for (Card card : Card.allCards())
			cardSprites.add(new CardSprite(card, pathToFolder));
	}
	
	public void initBackgroundPanel() {
		backgroundPanel = new JPanel();
		backgroundPanel.setBackground(Color.BLACK);
		add(backgroundPanel, new Integer(BACKGROUND_LAYER));
	}
	
	public void initPiles() {deleteExistingPiles();}
	
	public void initListeners() {}
	
	public void addPilesToBoard() {for (Pile pile : piles) pile.addToLayerPane();}
	
	public void removePilesFromBoard() {for (Pile pile : piles) remove(pile);}
	
	public void deleteExistingPiles() {removePilesFromBoard(); piles.clear();}
	
	public void setPathToFolder(String pathToFolder) {
		for (CardSprite cardSprite : cardSprites)
			cardSprite.setPathToFolder(pathToFolder);
	}
	
	public void updateDisplay(int width, int height) {}
}