package test;

//A Java Program to illustrate Caesar Cipher Technique
public class CharExercises {
	// Encrypts text using a shift of s

	// Driver code
	public static void main(String[] args) {
		System.out.println("Uppercase String ENcryption");
		String plainText = "ASLYFOXJUMPEDOUTOFABOX"; // Message to be encrypted
		int s = 4; // Number of places to shift message in encryption
		System.out.println("Plain Text  : " + plainText);
		System.out.println("Shift : " + s);
		String cipherText = encrypt(plainText, s); // Encrypt the message with the given shift
		System.out.println("Cipher Text: " + cipherText); // Print the encrypted cipher Text?

		/*
		 * NOTE YOU CAN USE THE SAME METHOD TO DECRYPT CIPHER TEXT, TRY TO DEFINE THE
		 * decryptShift BELOW TO DO SO
		 */
		int decryptShift = -4; // <------------- CHANGE THIS

		System.out
				.println("DeCipher: " + encrypt(cipherText, decryptShift) + " (change decryptShift to make this work)");

		// YOUR TASK: DO THE SAME FOR LOWERCASE
		System.out.println("\nUppercase String ENcryption");
		plainText = "iwillnoteattheminahouseiwillnoteatthemwithamouse"; // Message to be encrypted
		s = 7; // Number of places to shift message in encryption
		System.out.println("Plain Text  : " + plainText);
		System.out.println("Shift : " + s);
		cipherText = encrypt(plainText, s); // Encrypt the message with the given shift
		System.out.println("Cipher Text: " + cipherText); // Print the encrypted cipher Text?
		decryptShift = -7;

		System.out
				.println("DeCipher: " + encrypt(cipherText, decryptShift) + " (change decryptShift to make this work)");

	}

	public static String encrypt(String text, int s) {
		String result = "";
		char c;
		int charInt;

		for (int i = 0; i < text.length(); i++) {
			c = text.charAt(i);
			charInt = (int) c;
			if (Character.isUpperCase(c)) {
				/*
				 * NOTE THE CODE BELOW COULD HAVE BEEN DONE IN LESS LINES BUT STRETCHED INTO 5
				 * LINES TO SHOW ALL STEPS
				 */
				charInt = (charInt + s); // add a shift of s

				if (s < 0) {
					charInt = (65 - charInt) % 26;
				} else {

					charInt = (charInt - 65) % 26; // get the number of chars beyond 65, if beyond end of alphabet (26
													// chars) restart from 0

				}

				charInt += 65; // move this many characters beyond A (represented by 65)
				char ch = (char) charInt; // cast the int back to a char
				result += ch; // append the char to the result String
			} else {
				charInt = (charInt + s);
				charInt = (charInt - 97) % 26;
				charInt += 97;
				char ch = (char) charInt;
				result += ch;

			}
		}
		return result;
	}

}