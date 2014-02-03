package cards;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CardSprite extends JLabel {

	// Data Members
	// ------------
	private Card card;
	private CardIconFactory factory;
	private ImageIcon cardFront;
	private ImageIcon cardBack;
	private boolean isShown;

	// Constructor
	// -----------
	public CardSprite(Card card, CardIconFactory factory, boolean isShown) {
		super();
		this.card = card;
		this.factory = factory;
		this.isShown = isShown;
		
		this.cardFront = factory.createCardIcon(card);
		this.cardBack = factory.createCardBackIcon();
		updateShownIcon();
	}
	
	public CardSprite(Card card, CardIconFactory factory) {
		this(card, factory, true);
	}

	// Methods
	// -------
	public Card getCard() {
		return card;
	}
	
	public CardIconFactory getCardIconFactory() {
		return factory;
	}

	public boolean isShown() {
		return isShown;
	}

	public void setCard(Card card) {
		this.card = card;
		updateFrontIcon();
	}
	
	public void setCardIconFactory(CardIconFactory factory) {
		this.factory = factory;
		updateFrontIcon();
		updateBackIcon();
		updateShownIcon();
	}
	
	public void setIsShown(boolean isShown) {
		this.isShown = isShown;
		updateShownIcon();
	}
	
	public void show() {
		this.isShown = true;
		this.setIcon(cardFront);
	}

	public void hide() {
		this.isShown = false;
		this.setIcon(cardBack);
	}

	public void flip() {
		if (isShown)
			hide();
		else
			show();
	}

	public void updateIconSize() {
		this.cardFront = factory.createResizedIcon(cardFront, getWidth(), getHeight());
		this.cardBack = factory.createResizedIcon(cardBack, getWidth(), getHeight());
		updateShownIcon();
	}
	
	public void updateDisplay() {
		this.cardFront = factory.createCardIcon(card);
		this.cardBack = factory.createCardBackIcon();
		updateIconSize();
	}

	// Private Methods
	// ---------------
	private void updateFrontIcon() {
		int width = cardFront.getIconWidth();
		int height = cardFront.getIconHeight();
		ImageIcon newFront = factory.createCardIcon(card);
		this.cardFront = factory.createResizedIcon(newFront, width, height);
	}

	private void updateBackIcon() {
		int width = cardBack.getIconWidth();
		int height = cardBack.getIconHeight();
		ImageIcon newBack = factory.createCardBackIcon();
		this.cardBack = factory.createResizedIcon(newBack, width, height);
	}
	
	private void updateShownIcon() {
		if (isShown)
			setIcon(cardFront);
		else
			setIcon(cardBack);
	}
}