package board;

public class SolitaireLayoutManager {

	//Constants
	//---------
	public static final int MAX_PILES_IN_ROW = SolitaireGameBoard.NUMBER_OF_KLONDIKE_PILES;
	public static final int MAX_PILES_IN_COL = 2;
	public static final int MAX_VERGAPS_IN_KPILE = 19;
	
	public static final int CARDWIDTH_OVER_HORDIST = 2;
	public static final int CARDHEIGHT_OVER_VERGAP = 4;
	
	public static final int DECKX_OVER_HORDIST = 1;
	public static final int DECKY_OVER_VERGAP = 2;
	public static final int VERDIST_OVER_VERGAP = 2;
	
	//Data Members
	//------------
	private int width;
	private int height;
	private int cardWidth;
	private int cardHeight;
	private int deckX;
	private int deckY;
	private int horDist;
	private int verDist;
	private int verGap;
	
	//Constructor
	//-----------
	public SolitaireLayoutManager(int width, int height) {setSize(width, height);}
	
	//Methods
	//-------
	public int getDeckX() {return deckX;}
	public int getDeckY() {return deckY;}
	public int getDrawX() {return getDeckX() + getCardWidth() + getHorDist();}
	public int getDrawY() {return getDeckY();}
	public int getAceX(int indexFromLeft) {return getDrawX() + (2 + indexFromLeft)*(getCardWidth() + getHorDist());}
	public int getAceY(int indexFromLeft) {return getDeckY();}
	public int getKlondikeX(int indexFromLeft) {return getDeckX() + indexFromLeft*(getCardWidth() + getHorDist());}
	public int getKlondikeY(int indexFromLeft) {return getDeckY() + getCardHeight() + getVerDist();}
	public int getHorDist() {return horDist;}
	public int getVerDist() {return verDist;}
	public int getVerGap() {return verGap;}
	public int getCardWidth() {return cardWidth;}
	public int getCardHeight() {return cardHeight;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public void setWidth(int width) {this.width = width; computeHorizontalValues();}
	public void setHeight(int height) {this.height = height; computeVerticalValues();}
	public void setSize(int width, int height) {this.width = width; this.height = height; computeValues();}
	
	//Private Methods
	//---------------
	private void computeValues() {computeHorizontalValues(); computeVerticalValues();}
	
	private void computeHorizontalValues() {
		int horDistsInWidth = (DECKX_OVER_HORDIST) +
							  (MAX_PILES_IN_ROW) + 
							  (MAX_PILES_IN_ROW * CARDWIDTH_OVER_HORDIST);
		horDist = width / horDistsInWidth;
		
		deckX = horDist * DECKX_OVER_HORDIST;
		cardWidth = horDist * CARDWIDTH_OVER_HORDIST;
	}
	
	private void computeVerticalValues() {
		int verGapsInHeight = (MAX_PILES_IN_COL*CARDHEIGHT_OVER_VERGAP) + 
							  (MAX_VERGAPS_IN_KPILE) +
							  (DECKY_OVER_VERGAP) +
							  (MAX_PILES_IN_COL*VERDIST_OVER_VERGAP);
		verGap = height / verGapsInHeight;
		
		cardHeight = verGap * CARDHEIGHT_OVER_VERGAP;
		deckY = verGap * DECKY_OVER_VERGAP;
		verDist = verGap * VERDIST_OVER_VERGAP;
	}
}