package pilesprites;

import javax.swing.JLayeredPane;

import piles.Pile;
import cards.CardIconFactory;
import cards.CardSprite;

public class DeckSprite extends PileSprite {

	public DeckSprite(Pile<CardSprite> pile, JLayeredPane layerPane,
			CardIconFactory factory) {
		super(pile, layerPane, factory);
	}
	
	public DeckSprite(Pile<CardSprite> pile, JLayeredPane layerPane,
			CardIconFactory factory, int labelIndex) {
		super(pile, layerPane, factory, labelIndex);
	}

	public void updateDisplay() {
		Pile<CardSprite> pile = getPile();
		CardIconFactory factory = getCardIconFactory();
		
		if (pile.isEmpty()) {
			
		}
	}
}
