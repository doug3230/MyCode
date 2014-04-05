/*package testing;

import javax.swing.JLayeredPane;

import piles.KlondikePile;
import cards.CardSprite;

public class KlondikePileTesting {

	*//**Testing for the KlondikePile class*//*
	public static void main(String[] args) {
		final javax.swing.JFrame window = new javax.swing.JFrame("Layerpane test");
		window.setSize(400,400);
		final JLayeredPane layerPane = new JLayeredPane();
		layerPane.setSize(400,400);
		final javax.swing.JPanel background = new javax.swing.JPanel();
		background.setSize(400,400);
		background.setBackground(java.awt.Color.RED);
		
		final KlondikePile kLabel1 = new KlondikePile(layerPane, 1, 20);
		kLabel1.setSize(60,80);
		kLabel1.setLocation(40,40);
		
		final KlondikePile kLabel2 = new KlondikePile(layerPane, 1, 20);
		kLabel2.setSize(60,80);
		kLabel2.setLocation(120,40);
		
		kLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				for (CardSprite sprite : kLabel1.cardSpritesSelected(e.getX(), e.getY()))
					sprite.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GREEN));
			}
			
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (!kLabel1.isEmpty()) {
					kLabel2.push(kLabel1.draw());
					kLabel1.updateDisplay();
					kLabel2.updateDisplay();
				}
			}
			
			public void mouseReleased(java.awt.event.MouseEvent e) {
				for (CardSprite sprite : kLabel1.cardSpritesAtFront())
					sprite.setBorder(null);
			}
		});
		
		kLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				for (CardSprite sprite : kLabel2.cardSpritesSelected(e.getX(),e.getY()))
					sprite.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GREEN));
			}
			
			public void mouseClicked(java.awt.event.MouseEvent e) {
				while (!kLabel2.isEmpty())
					layerPane.remove(kLabel2.draw());
				kLabel2.updateDisplay();
				window.repaint();
			}
			
			public void mouseReleased(java.awt.event.MouseEvent e) {
				for (CardSprite sprite : kLabel2.cardSpritesAtFront())
					sprite.setBorder(null);
			}
		});
		
		cards.CardSpriteManager manager = new cards.CardSpriteManager("decks/standard");
		for (cards.Card card : cards.Card.allCards())
			manager.addSprite(card);
		
		cards.CardSprite[] sprites = manager.getSprites();
		for (int i = 0; i < 5; i++)
			kLabel1.push(sprites[i]);
		
		kLabel1.hideAllButFront();
		kLabel1.setNumberOfSpritesHidden(kLabel1.getNumberOfSpritesHidden() - 1);
		kLabel1.updateDisplay();
		kLabel2.hideAllButFront();
		kLabel2.updateDisplay();
		layerPane.add(background, new Integer(0));
		kLabel1.addToLayerPane();
		kLabel2.addToLayerPane();
		window.setContentPane(layerPane);
		window.setVisible(true);
	}
}*/