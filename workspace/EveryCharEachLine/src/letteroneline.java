
import java.util.Scanner;

public class letteroneline {
	private static Scanner input;

	public static void main(String[] args) {

		char yashchar = 10;

		System.out.print("Yash Char:" + yashchar);
		System.out.println("End Yash Char.");

		input = new Scanner(System.in);

		System.out.println("Enter a string:");

		String text = input.nextLine();

		for (int i = 0; i < text.length(); i++) {
			char mychar = text.charAt(i);
			System.out.println(mychar);
		}
	}
}
