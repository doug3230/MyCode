package piles;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLayeredPane;

import cards.CardSprite;

public class DeckPile extends Pile {
	
	//Constructor
	//-----------
	public DeckPile(JLayeredPane layerPane, int labelLayer) {super(layerPane, labelLayer);}

	//Methods
	//-------
	@Override
	public void push(CardSprite card) {card.hide(); super.push(card);}
	
	@Override
	public boolean canPushLegally(CardSprite sprite) {return false;}
	
	public void shuffle() {
		Random random = new Random();
		
		int choice; CardSprite sprite;
		for (int cardsLeft = cardSprites.size(); cardsLeft > 0; cardsLeft--) {
			choice = random.nextInt(cardsLeft);
			sprite = cardSprites.remove(choice);
			cardSprites.add(cardSprites.size(), sprite);
		}
	}
}