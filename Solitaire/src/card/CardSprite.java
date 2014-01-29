package card;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CardSprite extends JLabel {

	// Data Members
	// ------------
	private Card card;
	private String deckFolder;
	private ImageIcon cardFront;
	private ImageIcon cardBack;
	private boolean isShown;

	// Constructor
	// -----------
	public CardSprite(Card card, String deckFolder, boolean isShown) {
		super();
		this.card = card;
		this.deckFolder = deckFolder;
		this.isShown = isShown;
		
		this.cardFront = IconFactory.createCardIcon(deckFolder, card);
		this.cardBack = IconFactory.createCardBackIcon(deckFolder);
		updateShownIcon();
	}

	// Methods
	// -------
	public Card getCard() {
		return card;
	}

	public String getDeckFolder() {
		return deckFolder;
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
		this.cardFront = IconFactory.createResizedIcon(cardFront, getWidth(), getHeight());
		this.cardBack = IconFactory.createResizedIcon(cardBack, getWidth(), getHeight());
		updateShownIcon();
	}
	
	public void updateDisplay() {
		this.cardFront = IconFactory.createCardIcon(deckFolder, card);
		this.cardBack = IconFactory.createCardBackIcon(deckFolder);
		updateIconSize();
	}

	// Private Methods
	// ---------------
	private void updateFrontIcon() {
		int width = cardFront.getIconWidth();
		int height = cardFront.getIconHeight();
		ImageIcon newFront = IconFactory.createCardIcon(deckFolder, card);
		this.cardFront = IconFactory.createResizedIcon(newFront, width, height);
	}

	private void updateBackIcon() {
		int width = cardBack.getIconWidth();
		int height = cardBack.getIconHeight();
		ImageIcon newBack = IconFactory.createCardBackIcon(deckFolder);
		this.cardBack = IconFactory.createResizedIcon(newBack, width, height);
	}
	
	private void updateShownIcon() {
		if (isShown)
			setIcon(cardFront);
		else
			setIcon(cardBack);
	}
}