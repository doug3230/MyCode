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
		
		this.cardFront = SolitaireIconFactory.createCardIcon(deckFolder, card);
		this.cardBack = SolitaireIconFactory.createCardBackIcon(deckFolder);
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
		this.cardFront = SolitaireIconFactory.createResizedIcon(cardFront, getWidth(), getHeight());
		this.cardBack = SolitaireIconFactory.createResizedIcon(cardBack, getWidth(), getHeight());
		updateShownIcon();
	}
	
	public void updateDisplay() {
		this.cardFront = SolitaireIconFactory.createCardIcon(deckFolder, card);
		this.cardBack = SolitaireIconFactory.createCardBackIcon(deckFolder);
		updateIconSize();
	}

	// Private Methods
	// ---------------
	private void updateFrontIcon() {
		int width = cardFront.getIconWidth();
		int height = cardFront.getIconHeight();
		ImageIcon newFront = SolitaireIconFactory.createCardIcon(deckFolder, card);
		this.cardFront = SolitaireIconFactory.createResizedIcon(newFront, width, height);
	}

	private void updateBackIcon() {
		int width = cardBack.getIconWidth();
		int height = cardBack.getIconHeight();
		ImageIcon newBack = SolitaireIconFactory.createCardBackIcon(deckFolder);
		this.cardBack = SolitaireIconFactory.createResizedIcon(newBack, width, height);
	}
	
	private void updateShownIcon() {
		if (isShown)
			setIcon(cardFront);
		else
			setIcon(cardBack);
	}
}