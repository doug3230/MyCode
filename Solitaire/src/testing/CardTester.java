package testing;

import static org.junit.Assert.*;
import static card.Card.*;

import org.junit.Test;

import card.Card;

public class CardTester {

	@Test
	public void testCardAttributes() {
		Card aceOfSpades = new Card(1, "spades");
		assertEquals(1, aceOfSpades.getRank());
		assertEquals("Spades", aceOfSpades.getSuit());
		assertNotEquals("spades", aceOfSpades.getSuit());

		try {
			Card badRank = new Card(9001, "Spades");
			fail("invalid Card 9001 of Spades accepted as valid");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		try {
			Card badSuit = new Card(2, "Polar Bears");
			fail("invalid Card 2 of Polar Bears accepted as valid");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	@Test
	public void testCardStringForm() {
		Card ace = new Card(ACE, "spades");
		Card jack = new Card(JACK, "hearts");
		Card queen = new Card(QUEEN, "diamonds");
		Card king = new Card(KING, "clubs");
		Card number = new Card(5, "clubs");
		
		assertEquals("Ace of Spades", ace.toString());
		assertEquals("Jack of Hearts", jack.toString());
		assertEquals("Queen of Diamonds", queen.toString());
		assertEquals("King of Clubs", king.toString());
		assertEquals("5 of Clubs", number.toString());
	}
}