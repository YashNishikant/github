import java.util.Scanner;

public class assignment {

	public static void main(String[] args) {
		
		int[] array = new int[15];

		System.out.println("\n________________________________________________________\n(1)");

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 21) + 10;
			System.out.print(array[i] + " ");
		}

		System.out.println("\n\n");
		int j = array.length - 1;
		int temp = 0;
		for (int i = 0; i < array.length / 2; i++) {

			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			j--;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("\n________________________________________________________\n(2)");

		int[][] array2 = new int[5][3];
		int number = 1;
		int x = 2;
		int y = 0;

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 3; k++) {

				if (k == 0) {
					array2[i][k] = number;
				} else {
					array2[i][k] = (number+=1);
				}

				System.out.print(array2[i][k] + " ");
			}
			System.out.println();
			number++;
			y++;
		}
		
	}
}
