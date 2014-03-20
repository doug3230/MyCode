package testing;

import cards.Card;

public class CardTesting {
	/**Testing for the Card class*/
	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		int rank; String suit;
		String doTest;
		
		doTest = "y";
		while (doTest.equals("y")) {
			try {
				System.out.print("Enter rank: ");
				rank = Integer.parseInt(scanner.next());
				System.out.print("Enter suit: ");
				suit = scanner.next();
				
				System.out.println(new Card(rank,suit));
			} catch (NumberFormatException e) {
				System.out.println("Rank must be an integer");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.print("Test again? (y/n): ");
			doTest = scanner.next();
		}
		System.out.println("Done!");
	}
}