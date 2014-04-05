package piles;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.border.Border;

import cards.Card;
import cards.CardSprite;

public class KlondikePile extends Pile {	
	
	//Data Members
	//------------
	private JLayeredPane layerPane;
	private int numberHidden;
	private int verGap;
	
	//Constructor
	//-----------
	public KlondikePile(JLayeredPane layerPane, int labelLayer, int verGap, boolean isEnforcingRules) {
		super(layerPane, labelLayer, isEnforcingRules);
		this.layerPane = layerPane;
		setNumberOfSpritesHidden(0);
		setVerGap(verGap);
	}
	
	public KlondikePile(JLayeredPane layerPane, int labelLayer, int verGap) {this(layerPane, labelLayer, verGap, false);}
	
	public KlondikePile(JLayeredPane layerPane, int labelLayer) {this(layerPane,labelLayer,0);}
	
	//Methods
	//-------
	@Override
	public CardSprite draw() {
		CardSprite drawn = super.draw();
		layerPane.remove(drawn);
		return drawn;
	}
	
	@Override
	public void updateDisplay() {
		updateShown();
		updateHidden();
		updateLocations();
	}
	
	@Override
	public void push(CardSprite sprite) {
		super.push(sprite);
		layerPane.add(sprite, JLayeredPane.DEFAULT_LAYER);
	}
	
	@Override
	public boolean canPushLegally(CardSprite sprite) {
		Card card = sprite.getCard();
		if (isEmpty())
			return (card.getRank() == Card.KING_RANK);
		else {
			Card currentFront = peek().getCard();
			return (!(card.isSameColour(currentFront)) && 
					card.isPredecessorOf(currentFront));
		}
	}
	
	@Override
	public void transferCardSprites(Pile target) {
		boolean wasEnforcingRules = isEnforcingRules();
		dontEnforceRules();
		transferCardSpritesAux(target);
		if (wasEnforcingRules) enforceRules();
	}
	
	@Override
	public void borderDisplayedSprites() {
		for (CardSprite cardSprite : cardSpritesAtFront())
			cardSprite.setBorder(getDisplayBorder());
	}
	
	@Override
	public void unborderDisplayedSprites() {
		for (CardSprite cardSprite : cardSpritesAtFront())
			cardSprite.setBorder(null);
	}
	
	public ArrayList<CardSprite> cardSpritesAtFront() {
		ArrayList<CardSprite> spritesAtFront = new ArrayList<CardSprite>();
		for (int i = 0; i < numberOfCardSprites() - numberHidden; i++)
			spritesAtFront.add(0,peek(i));
		return spritesAtFront;
	}
	
	@Override
	public boolean contains(int x, int y) {
		int trueHeight = getHeight();
		if (!isEmpty()) trueHeight += (verGap * (numberOfCardSprites() - 1));
		
		boolean containsX = (x <= getWidth() && x >= 0);
		boolean containsY = (y <= trueHeight && y >= 0);
		return (containsX && containsY);
	}
	
	public int getVerGap() {return verGap;}
	
	public void setVerGap(int verGap) {this.verGap = verGap;}
	
	public int getNumberOfSpritesHidden() {return numberHidden;}
	
	public void setNumberOfSpritesHidden(int hidden) {
		if (hidden > numberOfCardSprites())
			throw new RuntimeException("Not enough CardSprites to hide " + hidden);
		this.numberHidden = hidden;
		updateDisplay();
	}
	
	public void hideAll() {setNumberOfSpritesHidden(numberOfCardSprites());}
	
	public void showAll() {setNumberOfSpritesHidden(0);}
	
	public void hideAllButFront() {
		int spritesToHide = numberOfCardSprites() - 1;
		if (spritesToHide > 0)
			setNumberOfSpritesHidden(spritesToHide);
	}
	
	public void showFront() {
		if (getNumberOfSpritesHidden() == 0)
			return;
		else if (numberOfCardSprites() == 1)
			setNumberOfSpritesHidden(0);
		else
			hideAllButFront();
	}
	
	public boolean isCompletelyHidden() {return (numberOfCardSprites() == numberHidden);}
	
	public int layerOf(int i) {return numberOfCardSprites() - i + getLabelLayer();}
	
	//Private Methods
	//---------------
	private void updateLocations() {
		int y = getY();
		int sprites = numberOfCardSprites();
		
		for (int i = sprites - 1; i >= 0; i--) {
			peek(i).setLocation(getX(),y);
			y += verGap;
		}
	}
	
	private void updateHidden() {
		CardSprite sprite;
		int sprites = numberOfCardSprites();
		for (int i = 0; i < numberHidden; i++) {
			sprite = peek(sprites - i - 1);
			sprite.hide();
			layerPane.setLayer(sprite, new Integer(layerOf(sprites - i - 1)));
			sprite.setSize(getSize());
			sprite.resizeBackIcon();
		}
	}
	
	private void updateShown() {
		CardSprite sprite;
		int sprites = numberOfCardSprites();
		for (int i = numberHidden; i < sprites; i++) {
			sprite = peek(sprites - i - 1);
			sprite.show();
			layerPane.setLayer(sprite, new Integer(layerOf(sprites - i - 1)));
			sprite.setSize(getSize());
			sprite.resizeFrontIcon();
		}
	}
	
	private void transferCardSpritesAux(Pile target) {
		if (cardSpritesAtFront().isEmpty())
			return;
		else {
			CardSprite front = draw();
			transferCardSpritesAux(target);
			if (target.canPush(front))
				target.push(front);
			else
				push(front);
		}
	}
}