
public class DoNow {

	public static void main(String[] args) {

		int x = 0;

		for (int i = 5; i < 10; i++) {
			for (int j = x; j < 10; j++) {
				System.out.print(j);
			}
			System.out.println(":" + x);
			x += 2;
		}

		// DICE
		int a = (int) (Math.random() * 6) + 1;
		int b = (int) (Math.random() * 6) + 1;

		System.out.println("\nDICE 1: " + a);
		System.out.println("DICE 2: " + b);

		System.out.println("SUM: " + (a + b));
		System.out.println("PRODUCT: " + (a * b));

		System.out.println("DIFFERENCE: " + Math.abs((a - b)));

		// DICE GAME
		
		System.out.println("\n\n");
		
		// PLAYER1
		int c = (int) (Math.random() * 6) + 1;
		int d = (int) (Math.random() * 6) + 1;
		// PLAYER2
		int c2 = (int) (Math.random() * 6) + 1;
		int d2 = (int) (Math.random() * 6) + 1;

		int player1points = 0;
		int player2points = 0;
		
		if ((c > c2) && (d > d2)) {
			player1points += 3;
		}

		if ((c < c2) && (d < d2)) {
			player2points += 3;
		}

	}
}
