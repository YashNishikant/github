
public class biggestnumberarrayApp {
	public static void main(String[] args) {

		int[] myArr = new int[] { 12, 7, 4, 5, 56, 23, 5, 23, 89, 1, 25, 3, 6 };

		int largest = myArr[0];

		for (int i = 0; i < myArr.length; i++) {

			if (myArr[i] > largest) {
				largest = myArr[i];
			}
		}
		System.out.println(largest);
	}
}
