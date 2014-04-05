package piles;

import javax.swing.JLayeredPane;

import cards.Card;
import cards.CardSprite;

public class AcePile extends DrawPile {
	
	//Constructor
	//-----------
	/**Initializes AcePile*/
	public AcePile(JLayeredPane layerPane, int labelLayer) {super(layerPane, labelLayer);}

	//Methods
	//-------
	/**
	 * If AcePile is empty, returns whether cardSprite has an ace Card</br>
	 * Otherwise, returns whether cardSprite is the suit successor of the 
	 * Card at the top of AcePile
	 */
	@Override
	public boolean canPushLegally(CardSprite cardSprite) {
		Card card = cardSprite.getCard();
		if (isEmpty())
			return (card.getRank() == Card.ACE_RANK);
		else
			return card.isSuitSuccessorOf(peek().getCard());
	}
}