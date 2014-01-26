package card;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardSprite extends JLabel {

	// Data Members
	// ------------
	private Card card;
	private String deckFolder;

	// Constructor
	// -----------
	public CardSprite(Card card, String deckFolder, int width, int height) {
		super();
		this.card = card;
		this.deckFolder = deckFolder;
		setIcon(CardIconFactory.createCardIcon(deckFolder, card));
		setSize(width, height);
	}

	// Methods
	// -------
	public Card getCard() {
		return card;
	}

	public String getDeckFolder() {
		return deckFolder;
	}

	public void setCard(Card card) {
		setIcon(CardIconFactory.createCardIcon(deckFolder, card));
		setSize(getWidth(), getHeight());
	}

	public void setDeckFolder(String deckFolder) {
		this.deckFolder = deckFolder;
		setIcon(CardIconFactory.createCardIcon(deckFolder, card));
		setSize(getWidth(), getHeight());
	}

	public void setIcon(Icon icon) {
		if (!(icon instanceof ImageIcon))
			throw new RuntimeException(
					"CardSprite is only meant to have ImageIcons as Icons.");
		super.setIcon(icon);
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		ImageIcon oldIcon = (ImageIcon) this.getIcon();
		this.setIcon(CardIconFactory.createResizedIcon(oldIcon, width, height));
	}

	public void setWidth(int width) {
		setSize(width, getHeight());
	}

	public void setHeight(int height) {
		setSize(getWidth(), height);
	}
}