package cards;

import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * An extension of the JLabel class that displays the front
 * or back image of a playing card. Calling the show() method
 * sets CardSprite's Icon to its front ImageIcon, calling the hide() method 
 * sets it to the back ImageIcon, and flip() calls show() if CardSprite's current Icon
 * is the back ImageIcon or hide() if it is the front ImageIcon.</br>
 * </br> 
 * Calling the resizeIcons() method resizes the images in CardSprite's 
 * ImageIcons to match CardSprite's width and height.</br>
 * </br>
 * Note: for performance reasons, when the resize icon methods are called,
 * CardSprite's ImageIcons are reinitialized to new values (so any third parties
 * with their Icon set to CardSprite's Icon should be updated.)
 * @author Richard Douglas
 */
public class CardSprite extends JLabel {

	//Data Members
	//------------
	private Card card;
	private String pathToFolder;
	private ImageIcon cardFront;
	private ImageIcon cardBack;
	private boolean isShown;
	
	//Constructor
	//-----------
	/**
	 * Initializes CardSprite
	 * @param card the Card whose front image is displayed
	 * @param pathToFolder where card image files are kept
	 * @param isShown true to show CardSprite's front, false to show the back image
	 */
	public CardSprite(Card card, String pathToFolder, boolean isShown) {
		super();
		this.card = card;
		this.pathToFolder = pathToFolder;
		this.cardFront = new CardIcon(card, pathToFolder);
		this.cardBack = CardIcon.newCardBackIcon(pathToFolder);
		if (isShown) show(); else hide();
	}
	/**
	 * Initializes CardSprite with isShown set to true
	 * @param card the Card whose front image is displayed
	 * @param pathToFolder where card image files are kept
	 */
	public CardSprite(Card card, String pathToFolder) {this(card,pathToFolder,true);}
	
	//Methods
	//-------
	/**true if CardSprite's front image is displayed, false otherwise*/
	public boolean isShown() {return this.isShown;}
	/**shows CardSprite's front image*/
	public void show() {this.isShown = true; this.setIcon(cardFront);}
	/**shows CardSprite's back image*/
	public void hide() {this.isShown = false; this.setIcon(cardBack);}
	/**show()s CardSprite if hidden, hide()s CardSprite if shown*/
	public void flip() {if (isShown) hide(); else show();}
	/**gets CardSprite's Card*/
	public Card getCard() {return card;}
	/**sets CardSprite's Card, CardSprite's front image is updated*/
	public void setCard(Card card) {
		this.card = card;
		cardFront = new CardIcon(card,pathToFolder);
		resizeIcon(cardFront, Image.SCALE_SMOOTH, getWidth(), getHeight());
		if (isShown) show();
	}
	/**gets the location of CardSprite's image files*/
	public String getPathToFolder() {return pathToFolder;}
	/**sets the location of CardSprite's image files, 
	 * CardSprite's front and back images are updated*/
	public void setPathToFolder(String pathToFolder) {
		this.pathToFolder = pathToFolder;
		cardFront = new CardIcon(card,pathToFolder);
		cardBack = CardIcon.newCardBackIcon(pathToFolder);
	}
	/**gets the front ImageIcon*/
	public ImageIcon getFrontIcon() {return cardFront;}
	/**gets the back ImageIcon*/
	public ImageIcon getBackIcon() {return cardBack;}
	/**Resizes the front ImageIcon to match the size of CardSprite.</br>
	 * Note: for performance reasons, the front ImageIcon is reinitialized.
	 * @param imageSpeed the speed at which to resize (e.g. Image.SCALE_SMOOTH, Image.SCALE_DEFAULT...)*/
	public void resizeFrontIcon(int imageSpeed) {
		cardFront = new CardIcon(card, pathToFolder); 
		resizeIcon(cardFront, imageSpeed, getWidth(), getHeight());
		if (isShown) show(); else hide();
	}
	/**Resizes the front ImageIcon smoothly*/
	public void resizeFrontIcon() {resizeFrontIcon(Image.SCALE_SMOOTH);}
	/**Resizes the back ImageIcon to match the size of CardSprite.</br>
	 * Note: for performance reasons, the back ImageIcon is reinitialized.
	 * @param imageSpeed the speed at which to resize (e.g. Image.SCALE_SMOOTH, Image.SCALE_DEFAULT...)*/
	public void resizeBackIcon(int imageSpeed) {
		cardBack = CardIcon.newCardBackIcon(pathToFolder); 
		resizeIcon(cardBack, imageSpeed, getWidth(), getHeight());
		if (isShown) show(); else hide();
	}
	/**Resizes the back ImageIcon smoothly*/
	public void resizeBackIcon() {resizeBackIcon(Image.SCALE_SMOOTH);}
	/**Resizes the front and back ImageIcons to match the size of CardSprite.</br>
	 * Note: for performance reasons, the ImageIcons are reinitialized.
	 * @param imageSpeed the speed at which to resize (e.g. Image.SCALE_SMOOTH, Image.SCALE_DEFAULT...)*/
	public void resizeIcons(int imageSpeed) {resizeFrontIcon(imageSpeed); resizeBackIcon(imageSpeed);}
	/**Resizes the front and back ImageIcons smoothly*/
	public void resizeIcons() {resizeIcons(Image.SCALE_SMOOTH);}
	
	//Private Methods
	//---------------
	private static void resizeIcon(ImageIcon icon, int imageSpeed, int width, int height) {
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, imageSpeed);
		icon.setImage(image);
	}
}