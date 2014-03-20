package testing;

import board.SolitaireWindow;

public class SolitaireWindowTesting {
	/**Testing for the SolitaireWindow class*/
	public static void main(String[] args) {
		SolitaireWindow window = new SolitaireWindow("Solitaire", "decks/standard",400,400);
		window.setVisible(true);
		window.newSolitaireGame();
	}
}
