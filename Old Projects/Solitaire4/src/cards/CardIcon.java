package cards;

import javax.swing.ImageIcon;

/**
 * <b>CardIcon</b> is a class whose job is to act as a CardSprite's ImageIcon.
 * Given a Card object and a String indicating the location of all card image files,
 * <b>CardIcon</b> initializes itself with the Card's image displayed. </br>
 * </br>
 * <b>CardIcon</b> carries out its duties by making use of its own static methods.
 * These can be overridden if the class is to be reused.</br>
 * </br>
 * The static method <b>CardIcon</b>.newCardBackIcon() initializes and returns an
 * ImageIcon with the image of the back of a card displayed.
 * @author Richard Douglas
 */
public class CardIcon extends ImageIcon {
	
	//Constructor
	//-----------
	/**
	 * Initializes <b>CardIcon</b> 
	 * @param card the Card whose image is to be displayed
	 * @param pathToFolder where card's image file is kept
	 */
	public CardIcon(Card card, String pathToFolder) {super(pathToCard(card,pathToFolder));}
	
	//Methods
	//-------
	/**
	 * The location of card's image file
	 * @param card the Card whose image file we want
	 * @param pathToFolder where card's image file is kept
	 * @return a String of form pathToFolder/cardFileName() + cardFileExtension()
	 */
	public static String pathToCard(Card card, String pathToFolder) {return pathToFolder + "/" + cardFileName(card) + cardFileExtension();}
	/**
	 * returns an ImageIcon with the image of a card back
	 * @param pathToFolder where the back image is located
	 * @return an ImageIcon displaying the image
	 */
	public static ImageIcon newCardBackIcon(String pathToFolder) {return new ImageIcon(pathToCardBack(pathToFolder));}
	/**
	 * The location of a card's back image
	 * @param pathToFolder where the back image is located
	 * @return a String of form pathToFolder/cardBackFileName() + cardFileExtension()
	 */
	public static String pathToCardBack(String pathToFolder) {return pathToFolder + "/" + cardBackFileName() + cardFileExtension();}
	/**returns the name of card's corresponding image file*/
	public static String cardFileName(Card card) {return card.rankString() + "_" + card.getSuit();}
	/**The name of a card's card back image file*/
	public static String cardBackFileName() {return "cardback";}
	/**The extension String (of form ".extension") of the card image files*/
	public static String cardFileExtension() {return ".png";}
}