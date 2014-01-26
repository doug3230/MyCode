package card;

/**
 * This class models a playing card used in games like Solitaire and Black Jack.
 * </br> It has two attributes:
 * <ul>
 * <li><b>rank</b> (the numerical value of the card)</li>
 * <li><b>suit</b> (which family the card belongs to)</li>
 * </ul>
 * Thus the two of clubs has rank 2, and suit "Clubs". Aces have rank 1. Jacks
 * Queens, and Kings have ranks 11, 12, and 13 respectively. </br>
 * 
 * @author Richard Douglas
 */

public class Card {

	// Constants
	// ---------
	/** The lowest allowed rank. */
	public static final int MIN_RANK = 1;
	/** The highest allowed rank. */
	public static final int MAX_RANK = 13;
	/** Clubs suit. */
	public static final String CLUBS = "Clubs";
	/** Diamonds suit. */
	public static final String DIAMONDS = "Diamonds";
	/** Hearts suit. */
	public static final String HEARTS = "Hearts";
	/** Spades suit. */
	public static final String SPADES = "Spades";
	/** An array of all suits. */
	public static final String[] SUITS = { CLUBS, DIAMONDS, HEARTS, SPADES };

	// Data Members
	// ------------
	private int rank;
	private String suit;

	// Constructors
	// ------------
	/**
	 * Initializes Card with the specified rank and suit.
	 * 
	 * @param rank
	 *            must be >= MIN_RANK and <= MAX_RANK.
	 * @param suit
	 *            must be in SUITS.
	 */
	public Card(int rank, String suit) {
		setRank(rank);
		setSuit(suit);
	}

	// Methods
	// -------
	/** rank accessor. */
	public int getRank() {
		return rank;
	}

	/** suit accessor. */
	public String getSuit() {
		return suit;
	}

	/**
	 * rank mutator.
	 * 
	 * @param rank
	 *            must be >= MIN_RANK and <= MAX_RANK.
	 */
	public void setRank(int rank) {
		if (rank < MIN_RANK || MAX_RANK < rank)
			throw new RuntimeException("Card rank must be between " + MIN_RANK
					+ " and " + MAX_RANK + " (inclusive)");
		this.rank = rank;
	}

	/**
	 * suit mutator.
	 * 
	 * @param suit
	 *            must be in SUITS.
	 */
	public void setSuit(String suit) {
		int i = 0;
		while (i < SUITS.length && !suit.equalsIgnoreCase(SUITS[i]))
			i += 1;
		if (i == SUITS.length)
			throw new RuntimeException("Card suit must be '" + CLUBS + "', '"
					+ DIAMONDS + "', " + HEARTS + "', or '" + SPADES + "'.");
		this.suit = SUITS[i];
	}

	/** Is Card's suit red? */
	public boolean isRed() {
		return (suit.equals(DIAMONDS) || suit.equals(HEARTS));
	}

	/** Is Card's suit black? */
	public boolean isBlack() {
		return (suit.equals(CLUBS) || suit.equals(SPADES));
	}

	/** Is Card of the clubs suit? */
	public boolean isClub() {
		return (suit.equals(CLUBS));
	}

	/** Is Card of the diamonds suit? */
	public boolean isDiamond() {
		return (suit.equals(DIAMONDS));
	}

	/** Is Card of the hearts suit? */
	public boolean isHeart() {
		return (suit.equals(HEARTS));
	}

	/** Is Card of the spades suit? */
	public boolean isSpades() {
		return (suit.equals(SPADES));
	}

	/** Does this Card come right before the other Card in its suit? */
	public boolean precedes(Card other) {
		return (this.hasSameSuitAs(other) && this.rankPrecedes(other));
	}

	/** Does this Card come right after the other Card in its suit? */
	public boolean succeeds(Card other) {
		return (this.hasSameSuitAs(other) && this.rankSucceeds(other));
	}

	/** Does this Card come right before the other Card (ignoring suit)? */
	public boolean rankPrecedes(Card other) {
		return (other.rank - this.rank == 1);
	}

	/** Does this Card come right after the other Card (ignoring suit)? */
	public boolean rankSucceeds(Card other) {
		return (this.rank - other.rank == 1);
	}

	/** Is this Card's rank less than the other Card's rank? */
	public boolean hasRankLessThan(Card other) {
		return (this.rank < other.rank);
	}

	/** Is this Card's rank the same as the other Card's rank? */
	public boolean hasSameRankAs(Card other) {
		return (this.rank == other.rank);
	}

	/** Is this Card's rank greater than the other Card's rank? */
	public boolean hasRankGreaterThan(Card other) {
		return (this.rank > other.rank);
	}

	/** Is this Card's suit the same as the other Card's suit? */
	public boolean hasSameSuitAs(Card other) {
		return (this.suit.equals(other.suit));
	}

	/** Does this Card have the same rank and suit as the other Card? */
	public boolean equals(Card other) {
		return (this.hasSameSuitAs(other) && this.hasSameRankAs(other));
	}
}