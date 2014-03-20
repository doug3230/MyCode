package testing;

import cards.Card;
import cards.CardIcon;

public class CardIconTesting {
	/**Testing for the CardIcon class*/
	public static void main(String[] args) {
		javax.swing.JFrame window = new javax.swing.JFrame("Tester");
		window.setSize(400,400);
		
		javax.swing.JPanel panel = new javax.swing.JPanel();
		panel.setSize(400,400);
		panel.setLayout(null);
		panel.setBackground(java.awt.Color.BLUE);
		
		javax.swing.JLabel label = new javax.swing.JLabel();
		label.setSize(80,60);
		
		Card card = new Card(1,"hearts");
		CardIcon icon = new CardIcon(card,"decks/standard");
		java.awt.Image img = icon.getImage();
		img = img.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		icon.setImage(img);
		label.setIcon(icon);
		
		panel.add(label);
		window.setContentPane(panel);
		window.setVisible(true);
	}
}