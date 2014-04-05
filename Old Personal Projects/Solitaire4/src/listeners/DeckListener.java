package listeners;

import java.awt.event.MouseAdapter;

import piles.DeckPile;
import piles.DrawPile;

public class DeckListener extends MouseAdapter {
	//Data Members
	//------------
	private DeckPile deckPile;
	private DrawPile drawPile;
	
	//Constructor
	//-----------
	public DeckListener() {}
	
	public DeckListener(DeckPile deckPile, DrawPile drawPile) {
		this.deckPile = deckPile;
		this.drawPile = drawPile;
	}
	
	//Methods
	//-------
	public DeckPile getDeckPile() {return deckPile;}
	public DrawPile getDrawPile() {return drawPile;}
	public void setDeckPile(DeckPile deckPile) {this.deckPile = deckPile;}
	public void setDrawPile(DrawPile drawPile) {this.drawPile = drawPile;}
	
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if (deckPile == null || drawPile == null)
			throw new RuntimeException("Error: one or both of DeckListener's DeckPile and DrawPile has not been set");
		deckPile.dontEnforceRules();
		drawPile.dontEnforceRules();
		
		if (!deckPile.isEmpty()) drawPile.push(deckPile.draw());
		else while (!drawPile.isEmpty()) deckPile.push(drawPile.draw());
		
		deckPile.enforceRules();
		drawPile.enforceRules();
		deckPile.updateDisplay();
		drawPile.updateDisplay();
	}
}