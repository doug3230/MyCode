package piles;

import javax.swing.JLayeredPane;

import cards.CardSprite;

public class MousePile extends KlondikePile {
	public MousePile(JLayeredPane layerPane, int labelLayer, int verGap) {super(layerPane, labelLayer, verGap);}
	
	public MousePile(JLayeredPane layerPane, int labelLayer) {super(layerPane, labelLayer, 0);}
	
	@Override
	public void initBorder() {setBorder(null);}
	
	@Override
	public boolean canPushLegally(CardSprite cardSprite) {return true;}
}
