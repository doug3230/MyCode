package listeners;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import board.CardGameBoard;
import piles.KlondikePile;
import piles.Pile;
import cards.CardSprite;

public class SolitairePileListener extends MouseAdapter {
	
	//Data Members
	//------------
	private CardGameBoard gameBoard;
	private Pile selected;
	private Border selectionBorder;
	
	//Constructors
	//------------
	public SolitairePileListener(CardGameBoard gameBoard) {this.gameBoard = gameBoard;}
	
	//Methods
	//-------
	public void mouseClicked(java.awt.event.MouseEvent e) {
		Pile target = (Pile) e.getSource();
		
		if (selected == null && target.isEmpty())
			return;
		else if (selected == null) {
			selected = target;
			selected.borderDisplayedSprites();
			selected.updateDisplay();
		}
		else {
			selected.unborderDisplayedSprites();
			selected.transferCardSprites(target);
			if (selected instanceof KlondikePile) {
				KlondikePile klondikePile = (KlondikePile) selected;
				if (klondikePile.isCompletelyHidden()) 
					klondikePile.showFront();
			}
			selected.updateDisplay();
			selected = null;
		}
		target.updateDisplay();
		gameBoard.repaint();
	}
}