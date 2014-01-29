package pile;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import card.CardSprite;

@SuppressWarnings("serial")
public class PileSprite extends JLabel {
	
	//Data Members
	//------------
	private Pile<CardSprite> pile;
	private JLayeredPane layerPane;
	private int layerIndex;
	
	//Constructors
	//------------
	public PileSprite(Pile<CardSprite> pile, JLayeredPane layerPane, int layerIndex) {
		this.pile = pile;
		this.layerPane = layerPane;
		this.layerIndex = layerIndex;
		layerPane.add(this, new Integer(layerIndex));
	}
	
	//Methods
	//-------
	public JLayeredPane getLayerPane() {
		return layerPane;
	}
	
	public Pile<CardSprite> getPile() {
		return pile;
	}
	
	public int getLayerIndex() {
		return layerIndex;
	}
	
	public void setLayerIndex(int layerIndex) {
		this.layerIndex = layerIndex;
		layerPane.setLayer(this, layerIndex);
	}
}