package testing;

import cards.Card;
import cards.CardSprite;

public class CardSpriteTesting {
	/**Testing for the CardSprite class*/
	public static void main(String[] args) {
		javax.swing.JFrame window = new javax.swing.JFrame();
		window.setSize(400,300);
		window.setResizable(false);
		javax.swing.JPanel panel = new javax.swing.JPanel();
		panel.setSize(400,300);
		
		Card card = new Card(5,"hearts");
		final CardSprite sprite = new CardSprite(card,"decks/standard");
		
		javax.swing.JButton button = new javax.swing.JButton("Click me!");
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (sprite.isShown()) 
					sprite.hide(); 
				else 
					sprite.show();
			}
		});
		
		panel.setLayout(new java.awt.BorderLayout());
		panel.add(sprite, java.awt.BorderLayout.CENTER);
		panel.add(button, java.awt.BorderLayout.SOUTH);
		window.setContentPane(panel);
		
		window.setVisible(true);
		sprite.resizeIcons();
	}
}