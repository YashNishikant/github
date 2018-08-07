import java.util.Scanner;

public class xNumb {
	private static Scanner input;

	public static void main(String[] args) {

		input = new Scanner(System.in);

		System.out.println("Enter a value:");
		int value2 = input.nextInt();

		System.out.println("How many multiples do you want?");
		int mult = input.nextInt();

		System.out.print("First " + mult + " multiples of " + value2 + ": ");

		for (int i = 1; i <= mult; i++) {

			if (i < mult) {
				System.out.print(value2 * i + ", ");
			} else {
				System.out.print(value2 * i);
			}
		}

	}
}
