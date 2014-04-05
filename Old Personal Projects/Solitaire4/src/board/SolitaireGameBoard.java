package board;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

import listeners.*;
import piles.*;
import cards.CardSprite;

public class SolitaireGameBoard extends CardGameBoard {

	//Constants
	//---------
	public static final int NUMBER_OF_ACE_PILES = 4;
	public static final int NUMBER_OF_KLONDIKE_PILES = 7;
	
	//Data Members
	//------------
	private SolitaireLayoutManager layoutManager;
	private DeckPile deckPile;
	private DrawPile drawPile;
	private AcePile[] acePiles;
	private KlondikePile[] klondikePiles;
	private DeckListener deckListener;
	private SolitairePileListener pileListener;
	
	//Constructor
	//-----------
	public SolitaireGameBoard(String pathToFolder, int width, int height) {
		super(pathToFolder);
		setSize(width, height);
		layoutManager = new SolitaireLayoutManager(width,height);
		resizeComponents();
		repositionComponents();
	}
	
	//Methods
	//-------
	@Override
	public void initPiles() {
		deleteExistingPiles();
		initDeckPile();
		initDrawPile();
		initAcePiles();
		initKlondikePiles();
	}
	
	@Override
	public void initListeners() {
		deckListener = new DeckListener(deckPile, drawPile);
		pileListener = new SolitairePileListener(this);
	}
	
	@Override
	public void newGame() {
		setUpBoard();
		fillDeckWithCards();
		deckPile.shuffle();
		dealCardsFromDeckToKlondikePiles();
		hideCardsMeantToBeHidden();
		addListeners();
		startTheGame();
	}
	
	@Override
	public void updateDisplay(int width, int height) {
		setSize(width, height);
		resizeComponents();
		repositionComponents();
		for (Pile pile : piles) pile.updateDisplay();
	}
	
	@Override
	public void resizeComponents() {
		super.resizeComponents();
		layoutManager.setSize(getWidth(), getHeight());
		resizePiles();
		resizeVerticalKPileGap();
	}
	
	@Override
	public void repositionComponents() {
		repositionDeckPile();
		repositionDrawPile();
		repositionAcePiles();
		repositionKlondikePiles();
	}
	
	//Private Methods
	//---------------
	private void initDeckPile() {deckPile = new DeckPile(this, LABEL_LAYER); 
								 piles.add(deckPile);}
	private void initDrawPile() {drawPile = new DrawPile(this, LABEL_LAYER); 
								 piles.add(drawPile);}
	private void initAcePiles() {
		if (acePiles == null)
			acePiles = new AcePile[NUMBER_OF_ACE_PILES];
		for (int i = 0; i < NUMBER_OF_ACE_PILES; i++) {
			acePiles[i] = new AcePile(this, LABEL_LAYER);
			piles.add(acePiles[i]);
		}
	}
	private void initKlondikePiles() {
		if (klondikePiles == null)
			klondikePiles = new KlondikePile[NUMBER_OF_KLONDIKE_PILES];
		for (int i = 0; i < NUMBER_OF_KLONDIKE_PILES; i++) {
			klondikePiles[i] = new KlondikePile(this, LABEL_LAYER);
			piles.add(klondikePiles[i]);
		}
	}
	private void resizePiles() {
		int cardWidth = layoutManager.getCardWidth();
		int cardHeight = layoutManager.getCardHeight();
		for (Pile pile : piles) 
			pile.setSize(cardWidth, cardHeight);
	}
	private void resizeVerticalKPileGap() {
		int verGap = layoutManager.getVerGap();
		for (KlondikePile kPile : klondikePiles)
			kPile.setVerGap(verGap);
	}
	private void repositionDeckPile() {deckPile.setLocation(layoutManager.getDeckX(), 
														layoutManager.getDeckY());}
	private void repositionDrawPile() {drawPile.setLocation(layoutManager.getDrawX(), 
															layoutManager.getDeckY());}
	private void repositionAcePiles() {
		for (int i = 0; i < NUMBER_OF_ACE_PILES; i++)
			acePiles[i].setLocation(layoutManager.getAceX(i), 
						   		    layoutManager.getAceY(i));
	}
	private void repositionKlondikePiles() {
		for (int i = 0; i < NUMBER_OF_KLONDIKE_PILES; i++)
			klondikePiles[i].setLocation(layoutManager.getKlondikeX(i), 
										 layoutManager.getKlondikeY(i));
	} 
	private void setUpBoard() {for (Pile pile : piles) 
									{pile.clear(); pile.dontEnforceRules();}}
	
	private void fillDeckWithCards() {for (CardSprite cardSprite : cardSprites) 
											deckPile.push(cardSprite);}
	private void dealCardsFromDeckToKlondikePiles() {
		for (int i = 0; i < NUMBER_OF_KLONDIKE_PILES; i++)
			for (int j = 0; j <= i; j++)
				klondikePiles[i].push(deckPile.draw());
	}
	private void hideCardsMeantToBeHidden() {for (KlondikePile kPile : klondikePiles) 
												kPile.hideAllButFront();}
	private void addListeners() {
		for (Pile pile : piles)
			pile.addMouseListener(pileListener);
		deckPile.removeMouseListener(pileListener);
		deckPile.addMouseListener(deckListener);
	}
	
	private void startTheGame() {for (Pile pile : piles)
									pile.enforceRules(); }
}