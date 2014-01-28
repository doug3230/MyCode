package card;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconFactory {

	// Constants
	// ---------
	public static final String IMAGE_PATH = "images/";
	public static final String CARD_BACK = "CardBack.png";
	public static final String EMPTY_DECK = "EmptyDeck.png";
	public static final String BLANK = "Blank.png";

	// Constructor
	// -----------
	protected IconFactory() {
	}

	// Static Methods
	// --------------
	public static ImageIcon createCardIcon(String deckFolder, Card card) {
		String fileName = card.getRank() + "_" + card.getSuit() + ".png";
		return new ImageIcon(fullPath(deckFolder, fileName));
	}

	public static ImageIcon createCardBackIcon(String deckFolder) {
		return new ImageIcon(fullPath(deckFolder, CARD_BACK));
	}

	public static ImageIcon createBlankIcon(String deckFolder) {
		return new ImageIcon(fullPath(deckFolder, BLANK));
	}

	public static ImageIcon createEmptyDeckIcon(String deckFolder) {
		return new ImageIcon(fullPath(deckFolder, EMPTY_DECK));
	}

	public static ImageIcon createResizedIcon(ImageIcon icon, int width,
			int height) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public static String fullPath(String deckFolder, String fileName) {
		if (!deckFolder.endsWith("/"))
			deckFolder += "/";
		return (IMAGE_PATH + deckFolder + fileName);
	}
}