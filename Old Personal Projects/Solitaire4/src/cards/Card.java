package cards;

import java.util.ArrayList;

/**
 * <b>Card</b> is a class for representing a playing card used in such games
 * as Solitaire, Freecell and Blackjack. <b>Card</b> has two attributes:
 * its <i>rank</i> (int) and its <i>suit</i> (String). <b>Card</b> also has methods
 * for checking whether it has a red/black <i>suit</i>, whether a given <b>Card</b> equals
 * <i>this</i> <b>Card</b>, as well as methods for whether <i>this</i> <b>Card</b> is
 * the predecessor/successor of another given <b>Card</b>. </br>
 * </br>
 * For convenience, a static method <b>Card</b>.allCards() is also provided.
 * @author Richard Douglas
 */
public class Card {

	//Constants
	//---------
	/**The smallest possible <i>rank</i>*/
	public static final int MIN_RANK = 1;
	/**The largest possible <i>rank</i>*/
	public static final int MAX_RANK = 13;
	/**The <i>rank</i> associated with a "King" <b>Card</b>*/
	public static final int KING_RANK = 13;
	/**The <i>rank</i> associated with a "Queen" <b>Card</b>*/
	public static final int QUEEN_RANK = 12;
	/**The <i>rank</i> associated with a "Jack" <b>Card</b>*/
	public static final int JACK_RANK = 11;
	/**The <i>rank</i> associated with an "Ace" <b>Card</b>*/
	public static final int ACE_RANK = 1;
	/**The "Hearts" <i>suit</i>*/
	public static final String HEARTS_SUIT = "Hearts";
	/**The "Diamonds" <i>suit</i>*/
	public static final String DIAMONDS_SUIT = "Diamonds";
	/**The "Clubs" <i>suit</i>*/
	public static final String CLUBS_SUIT = "Clubs";
	/**The "Spades" <i>suit</i>*/
	public static final String SPADES_SUIT = "Spades";
	/**The "Red" colour() String*/
	public static final String RED_COLOUR = "Red";
	/**The "Black" colour() String*/
	public static final String BLACK_COLOUR = "Black";
	/**The values that can be used as <i>suits</i>*/
	public static final String[] VALID_SUITS = {HEARTS_SUIT,DIAMONDS_SUIT,
												CLUBS_SUIT,SPADES_SUIT};
	
	//Data Members
	//------------
	private int rank;
	private String suit;
	
	//Constructors
	//------------
	/**Initializes <i>this</i> <b>Card</b> with <i>rank</i> and <i>suit</i> values set*/
	public Card(int rank, String suit) {setRank(rank); setSuit(suit);}
	/**Initializes <i>this</i> <b>Card</b> with <i>suit</i> and <i>rank</i> values set*/
	public Card(String suit, int rank) {setSuit(suit); setRank(rank);}
	
	//Methods
	//-------
	/**gets <i>this</i> <b>Card</b>'s <i>rank</i> value*/
	public int getRank() {return rank;}
	/**gets <i>this</i> <b>Card</b>'s <i>suit</i>*/
	public String getSuit() {return suit;}
	/**
	 * sets <i>this</i> <b>Card</b>'s <i>rank</i> value if it is
	 * >= MIN_RANK and <= MAX_RANK</br>
	 * throws a RuntimeException otherwise
	 */
	public void setRank(int rank) {
		if (MIN_RANK <= rank && rank <= MAX_RANK)
			this.rank = rank;
		else
			throw new RuntimeException("Card rank " + rank + " is not a valid rank");
		return;
	}
	/**
	 * sets <i>this</i> <b>Card</b>'s <i>suit</i> to a new value (if the new value is in VALID_SUITS)</br>
	 * throws a RuntimeException if the <i>suit</i> value is invalid
	 */
	public void setSuit(String suit) {
		String upperCaseSuit = suit.toUpperCase();
		for (String validSuit : VALID_SUITS) {
			if (upperCaseSuit.equals(validSuit.toUpperCase())) {
				this.suit = validSuit;
				return;
			}
		}
		throw new RuntimeException("Card suit " + suit + " is not a valid suit");
	}
	/**
	 * returns a String representation of <i>this</i> <b>Card</b>'s <i>rank</i></br>
	 * 1 -> "Ace", 2 -> "2", ... , 13 -> "King"
	 */
	public String rankString() {
		switch (rank) {
		case KING_RANK:
			return "King";
		case QUEEN_RANK:
			return "Queen";
		case JACK_RANK:
			return "Jack";
		case ACE_RANK:
			return "Ace";
		default:
			return rank + "";
		}
	}
	/**returns whether or not <i>this</i> <b>Card</b> and <i>other</i> <b>Card</b> have the same <i>suit</i>.*/
	public boolean hasSameSuitAs(Card other) {return other.suit.equals(this.suit);}
	/**returns whether or not <i>this</i> <b>Card</b> and <i>other</i> <b>Card</b> have the same <i>rank</i>*/
	public boolean hasSameRankAs(Card other) {return (this.rank == other.rank);}
	/**returns whether or not <i>this</i> <b>Card</b>'s <i>rank</i> equals <i>other</i> <b>Card</b>'s <i>rank</i> + 1*/
	public boolean isSuccessorOf(Card other) {return (this.rank == other.rank + 1);}
	/**returns whether or not <i>this</i> <b>Card</b> is <i>other</i> <b>Card</b>'s successor and both <b>Cards</b> have the same <i>suit</i>*/
	public boolean isSuitSuccessorOf(Card other) {return (hasSameSuitAs(other) && isSuccessorOf(other));}
	/**returns whether or not <i>this</i> <b>Card</b>'s <i>rank</i> equals <i>other</i> <b>Card</b>'s <i>rank</i> - 1*/
	public boolean isPredecessorOf(Card other) {return other.isSuccessorOf(this);}
	/**returns whether or not <i>this</i> <b>Card</b> is <i>other</i> <b>Card</b>'s predecessor and both <b>Cards</b> have the same <i>suit</i>*/
	public boolean isSuitPredecessorOf(Card other) {return (hasSameSuitAs(other) && isPredecessorOf(other));}
	/**returns whether or not <i>this</i> <b>Card</b> has a red <i>suit</i>*/
	public boolean isRed() {return (suit.equals(HEARTS_SUIT) || suit.equals(DIAMONDS_SUIT));}
	/**returns whether or not <i>this</i> <b>Card</b> has a black <i>suit</i>*/
	public boolean isBlack() {return (suit.equals(CLUBS_SUIT) || suit.equals(SPADES_SUIT));}
	/**<b>Card</b>'s colour (Red or Black)*/
	public String colour() {if (isRed()) return RED_COLOUR; else return BLACK_COLOUR;}
	/**returns whether <i>this</i> <b>Card</b> and <i>other</i> <b>Card</b> are both red or both black*/
	public boolean isSameColour(Card other) {return this.colour().equals(other.colour());}
	/**returns an ArrayList of all possible <b>Cards</b> that can be made*/
	public static ArrayList<Card> allCards() {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (String suit : VALID_SUITS)
			for (int rank = MIN_RANK; rank <= MAX_RANK; rank++)
				cards.add(new Card(rank,suit));
		return cards;
	}
	/**returns a String representation of <i>this</i> <b>Card</b>*/
	@Override
	public String toString() {return rankString() + " of " + suit;}
	/**returns whether <i>other</i> is a <b>Card</b> and equals <i>this</i> <b>Card</b>*/
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Card))
			return false;
		else 
			return this.equals((Card) other);
	}
	/**returns whether <i>this</i> <b>Card</b> and <i>other</i> <b>Card</b> have the same <i>suit</i> and <i>rank</i>*/
	public boolean equals(Card other) {
		boolean sameRank = this.rank == other.rank;
		boolean sameSuit = this.suit.equals(other.suit);
		return sameRank && sameSuit;
	}
}