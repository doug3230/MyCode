package card;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CardSprite extends JLabel {

	// Data Members
	// ------------
	private Card card;
	private String deckFolder;
	private CardIconFactory factory;
	private ImageIcon cardFront;
	private ImageIcon cardBack;
	private boolean isShown;

	// Constructor
	// -----------
	public CardSprite(Card card, String deckFolder, CardIconFactory factory, boolean isShown) {
		super();
		this.card = card;
		this.deckFolder = deckFolder;
		this.factory = factory;
		this.isShown = isShown;
		
		this.cardFront = factory.createCardIcon(deckFolder, card);
		this.cardBack = factory.createCardBackIcon(deckFolder);
		updateShownIcon();
	}
	
	public CardSprite(Card card, String deckFolder, CardIconFactory factory) {
		this(card, deckFolder, factory, true);
	}

	// Methods
	// -------
	public Card getCard() {
		return card;
	}

	public String getDeckFolder() {
		return deckFolder;
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
		updateShownIcon();
	}

	public void setDeckFolder(String deckFolder) {
		this.deckFolder = deckFolder;
		updateFrontIcon();
		updateBackIcon();
		updateShownIcon();
	}

	public void setCardIconFactory(CardIconFactory factory) {
		this.factory = factory;
		updateDisplay();
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
		this.cardFront = factory.createCardIcon(deckFolder, card);
		this.cardBack = factory.createCardBackIcon(deckFolder);
		updateIconSize();
	}

	// Private Methods
	// ---------------
	private void updateFrontIcon() {
		int width = cardFront.getIconWidth();
		int height = cardFront.getIconHeight();
		ImageIcon newFront = factory.createCardIcon(deckFolder, card);
		this.cardFront = factory.createResizedIcon(newFront, width, height);
	}

	private void updateBackIcon() {
		int width = cardBack.getIconWidth();
		int height = cardBack.getIconHeight();
		ImageIcon newBack = factory.createCardBackIcon(deckFolder);
		this.cardBack = factory.createResizedIcon(newBack, width, height);
	}
	
	private void updateShownIcon() {
		if (isShown)
			setIcon(cardFront);
		else
			setIcon(cardBack);
	}
}