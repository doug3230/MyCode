package testing;

import javax.swing.JLayeredPane;

import piles.DeckPile;

public class DeckPileTesting {

	/**Testing for the DeckPile class*/
	public static void main(String[] args) {
		final javax.swing.JFrame window = new javax.swing.JFrame("test window");
		final javax.swing.JPanel panel = new javax.swing.JPanel();
		final JLayeredPane layerPane = new JLayeredPane();
		window.setSize(400,400);
		panel.setLayout(null);
		
		final DeckPile deckLabel = new DeckPile(layerPane,1);
		deckLabel.setSize(60,80);
		deckLabel.setLocation(40,40);
		deckLabel.addToLayerPane();
		
		final java.util.ArrayList<cards.Card> deckOfCards = cards.Card.allCards();
		
		deckLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.isAltDown())
					deckLabel.shuffle();
				else if (!e.isShiftDown()) {
					if (!deckOfCards.isEmpty()) {
						cards.CardSprite sprite = new cards.CardSprite(deckOfCards.remove(0), "decks/standard");
						sprite.setSize(deckLabel.getSize());
						deckLabel.push(sprite);
						sprite.show();
						deckLabel.updateDisplay();
					}
				} else while (!deckLabel.isEmpty()) deckLabel.draw();
			}
		});
		
		panel.add(deckLabel);
		panel.setSize(400,400);
		panel.setBackground(java.awt.Color.BLUE);
		layerPane.add(panel, new Integer(0));
		
		window.setContentPane(layerPane);
		window.setVisible(true);
	}
}