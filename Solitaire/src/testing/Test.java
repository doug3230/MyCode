package testing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

import card.Card;
import card.CardSprite;

public class Test {

	public static void main(String[] args) {
		JFrame window = new JFrame("Test Window");
		
		Card ace = new Card(1,"spades");
		CardSprite aceSprite = new CardSprite(ace, "standard_deck", true);
		
		aceSprite.setSize(100,100);
		aceSprite.updateIconSize();
		
		window.setSize(300,400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = window.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(aceSprite, BorderLayout.CENTER);
		window.setVisible(true);
		
		Card two = aceSprite.getCard();
		two.setRank(2);
		two.setSuit("hearts");
		aceSprite.updateDisplay();
	}
}