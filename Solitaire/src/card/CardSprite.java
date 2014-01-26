package card;

import java.awt.Image;

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
		this.card = card;
	}

	public void setDeckFolder(String deckFolder) {
		this.deckFolder = deckFolder;
	}

	public void resizeIcon() {
		ImageIcon icon = (ImageIcon) getIcon();
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(getWidth(), getHeight(),
				Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(resizedImage));
	}
}