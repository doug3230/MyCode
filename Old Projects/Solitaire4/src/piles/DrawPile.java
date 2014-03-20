package piles;

import javax.swing.JLayeredPane;

import cards.CardSprite;

public class DrawPile extends Pile {

	//Constructor
	//-----------
	/**Initializes DrawPile*/
	public DrawPile(JLayeredPane layerPane, int labelLayer) {super(layerPane, labelLayer);}
	
	//Methods
	//-------
	/**show()s cardSprite and then adds it to the top of DrawPile*/
	@Override
	public void push(CardSprite cardSprite) {cardSprite.show(); super.push(cardSprite);}
	/**
	 * Returns false as when a game is being played, no CardSprites are
	 * allowed to be pushed to DrawPile
	 */
	@Override
	public boolean canPushLegally(CardSprite cardSprite) {return false;}
}