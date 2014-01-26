package card;

/**
 * This class models a playing card used in games like Solitaire and Black Jack.
 * </br>
 * It has two attributes:
 * <ul>
 * <li><b>rank</b> (the numerical value of the card)</li>
 * <li><b>suit</b> (which family the card belongs to)</li>
 * </ul>
 * Thus the two of clubs has rank 2, and suit "clubs".
 * Aces have rank 1. Jacks Queens, and Kings have ranks 
 * 11, 12, and 13 respectively.
 * </br>
 * 
 * @author Richard Douglas
 */

public class Card {
	
	//Constants
	//---------
	public static final int MIN_RANK = 1;
	public static final int MAX_RANK = 13;
	public static final String CLUBS = "Clubs";
	public static final String DIAMONDS = "Diamonds";
	public static final String HEARTS = "Hearts";
	public static final String SPADES = "Spades";
	public static final String[] SUITS = {CLUBS, DIAMONDS, HEARTS, SPADES};
	
	//Data Members
	//------------
	private int rank;
	private String suit;
}
