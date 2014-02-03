package card;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class is used mainly for creating ImageIcons for CardSprites.
 * It can be used to create ImageIcons for:
 * <ul>
 * <li>The front of a card (createCardIcon())
 * <li>The back of a card (createCardBackIcon())
 * <li>A blank card (createBlankIcon())
 * <li>An empty deck (createEmptyDeckIcon())
 * </ul>
 * </br>
 * For writing other applications that use CardSprites,
 * this class can be subclassed in which case one may be interested
 * in overriding the static methods. 
 * 
 * </br>
 * The class works under the assumption that all the deck folders are located
 * in a common directory (pathToFolders()), and that card images are named in
 * a consistent way across all folders.
 * </br>
 * Also of note is that the static "fileName()" methods should include the file extension
 * (".png", ".jpeg", etc...) 
 * @author Richard Douglas
 */
public class CardIconFactory {
	
	//Constructor
	//-----------
	/**Initializes CardIconFactory. CardIconFactory is ready to create ImageIcons upon construction.*/
	public CardIconFactory() {
		
	}

	//Public Methods
	//--------------
	/**Creates an ImageIcon for a card in a card game.*/
	public ImageIcon createCardIcon(String deckFolder, Card card) {
		return new ImageIcon(fullPath(deckFolder, cardFileName(card)));
	}

	/**Creates an ImageIcon for the back of a card in a card game.*/
	public ImageIcon createCardBackIcon(String deckFolder) {
		return new ImageIcon(fullPath(deckFolder, cardBackFileName()));
	}

	/**Creates an ImageIcon for a blank card in a card game.*/
	public ImageIcon createBlankIcon(String deckFolder) {
		return new ImageIcon(fullPath(deckFolder, blankFileName()));
	}

	/**Creates an ImageIcon for an empty deck in a card game.*/
	public ImageIcon createEmptyDeckIcon(String deckFolder) {
		return new ImageIcon(fullPath(deckFolder, emptyDeckFileName()));
	}

	/**Creates a copy of the passed in ImageIcon resized to a new width and height.*/
	public ImageIcon createResizedIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	//Template Methods
	//----------------
	/**The path to all folders used in creating ImageIcons.*/
	public static String pathToFolders() {
		return "images/";
	}

	/**The name of the image file for the "back of a card" image.*/
	public static String cardBackFileName() {
		return "CardBack.png";
	}

	/**The name of the image file for an "empty deck" image.*/
	public static String emptyDeckFileName() {
		return "EmptyDeck.png";
	}

	/**The name of the image file for a "blank card" image.*/
	public static String blankFileName() {
		return "Blank.png";
	}

	/**The name of the image file for the front of a given card.*/
	public static String cardFileName(Card card) {
		return card.getRank() + "_" + card.getSuit() + ".png";
	}

	//Helper Methods
	//--------------
	private static String fullPath(String deckFolder, String fileName) {
		if (!deckFolder.endsWith("/"))
			deckFolder += "/";
		return (pathToFolders() + deckFolder + fileName);
	}
}