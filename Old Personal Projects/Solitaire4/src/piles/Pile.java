package piles;
import cards.CardSprite;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

public class Pile extends JLabel {
	
	//Constants
	//---------
	public static final Border DEFAULT_SELECTION_BORDER = 
			BorderFactory.createLineBorder(Color.YELLOW, 2);
	
	//Data Members
	//------------
	protected ArrayList<CardSprite> cardSprites;
	protected JLayeredPane layerPane;
	private int labelLayer;
	private boolean isEnforcingRules;
	private Border displayBorder;
	
	//Constructor
	//-----------
	/**Initializes Pile*/
	public Pile(JLayeredPane layerPane, int labelLayer, boolean isEnforcingRules, Border displayBorder) {
		super();
		cardSprites = new ArrayList<CardSprite>();
		this.layerPane = layerPane;
		this.isEnforcingRules = isEnforcingRules;
		this.displayBorder = displayBorder;
		setLabelLayer(labelLayer);
		initBorder();
	}
	public Pile(JLayeredPane layerPane, int labelLayer, boolean isEnforcingRules) {
		this(layerPane, labelLayer, isEnforcingRules, DEFAULT_SELECTION_BORDER);
	}
	/**Initializes Pile with isEnforcingRules set to false*/
	public Pile(JLayeredPane layerPane, int labelLayer) {this(layerPane, labelLayer,false);}
	
	//Methods
	//-------
	/**
	 * Removes and returns the top CardSprite in Pile</br>
	 * Throws a RuntimeException if Pile is empty
	 */
	public CardSprite draw() {return cardSprites.remove(0);}
	/**Adds cardSprite to the top of Pile</br>
	 * Throws a RuntimeException if !canPush(cardSprite)*/
	public void push(CardSprite cardSprite) {
		if (!canPush(cardSprite)) 
			throw new RuntimeException("Can't push " + cardSprite.getCard() + " to pile");
		cardSprites.add(0, cardSprite);
	}
	/**
	 * Meant to update the display of Pile to match its current game state.</br>
	 * For now, if Pile is empty, sets its Icon to null, otherwise sets its Icon
	 * to the Icon of whatever CardSprite is at the top of Pile
	 */
	public void updateDisplay() {
		if (isEmpty())
			setIcon(null);
		else {
			peek().setSize(this.getSize());
			peek().resizeIcons();
			setIcon(peek().getIcon());
		}
	}
	/**Removes all CardSprites from Pile*/
	public void clear() {cardSprites.clear();}
	/**Returns the ith CardSprite (starting from the top of Pile (which is position 0))</br>
	 * Throws a RuntimeException if the ith CardSprite does not exist*/
	public CardSprite peek(int i) {return cardSprites.get(i);}
	/**Returns the 0th CardSprite (the one at the top of Pile)
	 * Throws a RuntimeException if Pile is empty*/
	public CardSprite peek() {return peek(0);}
	/**Returns whether or not there are no CardSprites in Pile*/
	public boolean isEmpty() {return cardSprites.isEmpty();}
	/**Returns whether or not pushed CardSprites must follow the
	 * rules of whatever game is being played*/
	public boolean isEnforcingRules() {return isEnforcingRules;}
	/**Tells Pile that pushed CardSprites must follow the rules
	 * of whatever game is being played*/
	public void enforceRules() {isEnforcingRules = true;}
	/**Tells Pile that pushed CardSprites do not need to follow
	 * the rules of whatever game is being played*/
	public void dontEnforceRules() {isEnforcingRules = false;}
	/**The number of CardSprites in Pile*/
	public int numberOfCardSprites() {return cardSprites.size();}
	/**Gets Pile's layer in which it is added to layerPane*/
	public int getLabelLayer() {return labelLayer;}
	/**Changes Pile's layer in layerPane*/
	public void setLabelLayer(int labelLayer) {
		this.labelLayer = labelLayer;
		layerPane.setLayer(this, new Integer(labelLayer));
	}
	/**Adds Pile to layerPane*/
	public void addToLayerPane() {layerPane.add(this, new Integer(labelLayer));}
	/**If Pile isEnforcingRules returns canPushLegally(cardSprite)</br>
	 * otherwise returns true*/
	public boolean canPush(CardSprite cardSprite) {
		if (isEnforcingRules())
			return canPushLegally(cardSprite);
		else
			return true;
	}
	/**
	 * Meant to return whether cardSprite is allowed to be pushed to Pile in
	 * compliance with the rules of whatever game is being played. </br>
	 * For now, since no game is specified, returns true
	 */
	public boolean canPushLegally(CardSprite cardSprite) {return true;}
	/**Initializes the Border set to Pile for when it is displayed in a GUI*/
	public void initBorder() {
		Border lineBorder = BorderFactory.createLineBorder(Color.WHITE);
		setBorder(lineBorder);
	}
	
	public Border getDisplayBorder() {return displayBorder;}
	
	public void setDisplayBorder(Border displayBorder) {unborderDisplayedSprites();
														  this.displayBorder = displayBorder;}
	
	public void borderDisplayedSprites() {
		if (isEmpty())
			return;
		else
			setBorder(displayBorder);
	}
	
	public void unborderDisplayedSprites() {initBorder();}
	
	public void transferCardSprites(Pile target) {
		if (isEmpty())
			return;
		else if (target.canPush(peek()))
			target.push(draw());
	}
}