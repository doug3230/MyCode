package card;

import static org.junit.Assert.*;

import org.junit.Test;

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
		}

		try {
			Card badSuit = new Card(2, "Polar Bears");
			fail("invalid Card 2 of Polar Bears accepted as valid");
		} catch (Exception e) {
		}
	}
}
