package tetris;

public class Tester {
	public static void main(String[] args) {
		Tetromino myBlock = Tetromino.generateRandomTetromino();
		System.out.println(myBlock);
		System.out.println(myBlock.rotateRight());
		System.out.println(myBlock.rotateLeft());
	}
}
