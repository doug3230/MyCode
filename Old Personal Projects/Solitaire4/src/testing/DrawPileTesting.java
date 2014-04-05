/*package testing;

import javax.swing.JLayeredPane;

import piles.AcePile;
import piles.DeckPile;
import piles.DrawPile;
import cards.CardSprite;

public class DrawPileTesting {
	*//**Testing for the DrawPile class*//*
	public static void main(String[] args) {
		final javax.swing.JFrame window = new javax.swing.JFrame("test window");
		final javax.swing.JPanel panel = new javax.swing.JPanel();
		final JLayeredPane layerPane = new JLayeredPane();
		window.setSize(400,400);
		panel.setSize(400,400);
		
		final DeckPile deckLabel = new DeckPile(layerPane, 1);
		deckLabel.setSize(60,80);
		deckLabel.setLocation(40,40);
		
		final DrawPile DrawPile = new DrawPile(layerPane, 1);
		DrawPile.setSize(60,80);
		DrawPile.setLocation(120,40);
		
		final AcePile aceLabel = new AcePile(layerPane, 1);
		aceLabel.setSize(60,80);
		aceLabel.setLocation(200,40);
		
		final javax.swing.JButton button = new javax.swing.JButton("Shuffle");
		button.setSize(button.getPreferredSize());
		button.setLocation(40, 130);
		
		deckLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (!deckLabel.isEmpty())
					DrawPile.push(deckLabel.draw());
				else while (!DrawPile.isEmpty()) deckLabel.push(DrawPile.draw());
				
				deckLabel.updateDisplay();
				DrawPile.updateDisplay();
			}
		});
		
		DrawPile.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (!DrawPile.isEmpty()) {
					CardSprite sprite = DrawPile.peek();
					if (aceLabel.canPush(sprite)) {
						aceLabel.push(DrawPile.draw());
						DrawPile.updateDisplay();
						aceLabel.updateDisplay();
					}
				}
			}
		});
		
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				deckLabel.shuffle();
			}
		});
		
		cards.CardSpriteManager manager = new cards.CardSpriteManager("decks/standard");
		for (cards.Card card : cards.Card.allCards())
			manager.addSprite(card);
		
		for (cards.CardSprite sprite : manager.getSprites())
			deckLabel.push(sprite);
		
		deckLabel.addToLayerPane();
		deckLabel.updateDisplay();
		DrawPile.addToLayerPane();
		DrawPile.updateDisplay();
		aceLabel.addToLayerPane();
		aceLabel.updateDisplay();
		panel.setBackground(java.awt.Color.GREEN);
		layerPane.add(button, new Integer(1));
		layerPane.add(panel, new Integer(0));
		window.setContentPane(layerPane);
		window.setVisible(true);
	}
}*/