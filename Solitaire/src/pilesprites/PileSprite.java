package pilesprites;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import piles.Pile;
import cards.CardIconFactory;
import cards.CardSprite;

@SuppressWarnings("serial")
public class PileSprite extends JLabel {
	
	//Data Members
	//------------
	private Pile<CardSprite> pile;
	private JLayeredPane layerPane;
	private int layerIndex;
	private CardIconFactory factory;
	
	//Constructors
	//------------
	public PileSprite(Pile<CardSprite> pile, JLayeredPane layerPane, CardIconFactory factory, int layerIndex) {
		this.pile = pile;
		this.layerPane = layerPane;
		this.factory = factory;
		this.layerIndex = layerIndex;
		layerPane.add(this, new Integer(layerIndex));
	}
	
	public PileSprite(Pile<CardSprite> pile, JLayeredPane layerPane, CardIconFactory factory) {
		this(pile, layerPane, factory, 1);
	}
	
	//Methods
	//-------
	public JLayeredPane getLayerPane() {
		return layerPane;
	}
	
	public Pile<CardSprite> getPile() {
		return pile;
	}
	
	public void setPile(Pile<CardSprite> pile) {
		this.pile = pile;
		updateDisplay();
	}
	
	public CardIconFactory getCardIconFactory() {
		return factory;
	}
	
	public int getLayerIndex() {
		return layerIndex;
	}
	
	public void setLayerIndex(int layerIndex) {
		this.layerIndex = layerIndex;
		layerPane.setLayer(this, layerIndex);
	}
	
	public void updateDisplay() {}
}