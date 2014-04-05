package tetris;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Tetromino {

	//Constants
	//---------
	public static final int BLOCKS = 4;
	
	public enum BlockType { NO_SHAPE, Z_SHAPE, S_SHAPE, LINE_SHAPE, 
        T_SHAPE, SQUARE_SHAPE, L_SHAPE, REVERSE_L_SHAPE};
    
    private static final int[][][] BLOCKTYPE_COORDS = {
    	{ { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
        { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
        { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
        { { 0, -2 },  { 0, -1 },   { 0, 0 },   { 0, 1 } },
        { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, -1} },
        { { 0, 0 },   { 1, 0 },   { 0, -1 },   { 1, -1 } },
        { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
        { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };
    
    private static Color[] BLOCKTYPE_COLOURS = {
    	Color.WHITE, Color.BLUE, Color.RED, Color.YELLOW,
    	Color.ORANGE, Color.GREEN, Color.CYAN, Color.MAGENTA
    };
	
	//Data Members
	//------------
	private Color blockColour;
	private Point[] blockCoords;
	private BlockType blockType;
	private static Random randomNumberGenerator = new Random();
	
	//Constructors
	//------------
	public Tetromino(BlockType type) {
		blockCoords = new Point[BLOCKS];
		blockType = type;
		
		int typeIndex = blockType.ordinal();
		for (int i = 0; i < BLOCKS; i++) {
			blockCoords[i] = new Point();
			blockCoords[i].x = BLOCKTYPE_COORDS[typeIndex][i][0];
			blockCoords[i].y = BLOCKTYPE_COORDS[typeIndex][i][1];
		}
		blockColour = BLOCKTYPE_COLOURS[typeIndex];
	}
	
	public Tetromino() {
		this(BlockType.NO_SHAPE);
	}
	
	//Methods
	//-------
	public static Tetromino generateRandomTetromino() {
		int length = BlockType.values().length;
		int typeChoice = 1 + randomNumberGenerator.nextInt(length - 1);
		return new Tetromino((BlockType.values())[typeChoice]);
	}
	
	public BlockType getBlockType() {return blockType;}
	
	public Color getColor() {return blockColour;}
	
	public Point[] getCoords() {
		Point[] copyOfCoords = new Point[BLOCKS];
		for (int i = 0; i < BLOCKS; i++)
			copyOfCoords[i] = new Point(blockCoords[i]);
		return copyOfCoords;
	}
	
	public int getX(int blockIndex) {
		if (blockIndex < 0 || blockIndex >= BLOCKS )
			throw new IndexOutOfBoundsException("block index out of bounds");
		return blockCoords[blockIndex].x;
	}
	
	public int getY(int blockIndex) {
		if (blockIndex < 0 || blockIndex >= BLOCKS )
			throw new IndexOutOfBoundsException("block index out of bounds");
		return blockCoords[blockIndex].y;
	}
	
	public Tetromino rotateLeft() {
		Tetromino rotation = new Tetromino();
		rotation.blockColour = this.blockColour;
		rotation.blockType = this.blockType;
		
		for (int i = 0; i < BLOCKS; i++) {
			rotation.blockCoords[i].x = -this.getY(i); 
			rotation.blockCoords[i].y = this.getX(i);
		}
			
		return rotation;
	}
	
	public Tetromino rotateRight() {
		Tetromino rotation = new Tetromino();
		rotation.blockColour = this.blockColour;
		rotation.blockType = this.blockType;
		
		for (int i = 0; i < BLOCKS; i++) {
			rotation.blockCoords[i].x = this.getY(i); 
			rotation.blockCoords[i].y = -this.getX(i);
		}
			
		return rotation;
	}
	
	public String toString() {
		String strForm = blockColour + " - " + blockType + " - ";
		strForm += "("+ blockCoords[0].x +"," + blockCoords[0].y + ")"  ;
		for (int i = 1; i < BLOCKS; i++)
			strForm += " , " + "("+ blockCoords[i].x +"," + blockCoords[i].y + ")";
		return strForm;
	}
}