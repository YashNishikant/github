import java.util.Scanner;

class Car {

	String Color;
	private int Speed;
	String Seats;
	private int TSpeedH = 120;
	private int TSpeedN = 150;
	
	void Accelerate() {
		Speed = Speed + 10;
	}

	void Decelerate() {
		Speed = Speed - 10;
		if (Speed < 0) {
			Speed = 0;
		}
	}

	void TopSpeedH() {	
		if (Speed > TSpeedH) {
			Speed = TSpeedH;
		System.out.println("Top Speed Reached");
		}
	}

	void TopSpeedN() {
		if (Speed > TSpeedN) {
			Speed = TSpeedN;
		System.out.println("Top Speed Reached");
		}
	}
	
	int GetTopSpeedN() {
		return TSpeedN;
	}
	
	int GetTopSpeedH() {	
		return TSpeedH;	
	}
	
	int GetSpeed() {
		return Speed;
	}

	int Brake() {
		Speed = 0;
		return Speed;
	}
}

public class App {
	private static Scanner input;

	public static void main(String[] args) throws InterruptedException {

		input = new Scanner(System.in);
		System.out.println("Which car would you like to purchase? (Honda or Nissan)");

		String value = input.nextLine();

		Car Honda = new Car();
		Honda.Color = "Color: Blue";
		Honda.Seats = "7 seats";
		Honda.GetTopSpeedH();
		
		Car Nissan = new Car();
		Nissan.Color = "Color: Gray";
		Nissan.Seats = "5 seats";
		Nissan.GetTopSpeedN();
		
		if (value.equals("Honda")) {
			System.out.println("INFORMATION ON HONDA");
			System.out.println(Honda.Color);
			System.out.println(Honda.Seats);
			System.out.println("Top Speed: " + Honda.GetTopSpeedH());
			Thread.sleep(500);
			System.out.println("Would you like to test drive this car?");
		}

		if (value.equals("Nissan")) {
			System.out.println("INFORMATION ON NISSAN");
			System.out.println(Nissan.Color);
			System.out.println(Nissan.Seats);
			System.out.println("Top Speed: " + Nissan.GetTopSpeedN());
			Thread.sleep(500);
			System.out.println("Would you like to test drive this car?");

		}

		String value2 = input.nextLine();

		if (value2.equals("Yes")) {
			System.out.println("Ok, lets go! (Type 'END' to stop the test drive)");
			System.out.println("_____________________");
		}

		if (value2.equals("No")) {
			System.out.println("Have a nice day, bye!");
			return;
		}

		while (true) {

			String value3 = input.nextLine();

			if (value3.equals("Accelerate")) {
				if (value.equals("Nissan")) {
					Nissan.Accelerate();
					Nissan.TopSpeedN();
					System.out.println(Nissan.GetSpeed() + " MPH");
				}

				if (value.equals("Honda")) {
					Honda.Accelerate();
					Honda.TopSpeedH();
					System.out.println(Honda.GetSpeed() + " MPH");
				}
			}

			if (value3.equals("Decelerate")) {
				if (value.equals("Honda")) {
					Honda.Decelerate();
					System.out.println(Honda.GetSpeed() + " MPH");
				}

				if (value.equals("Nissan")) {
					Nissan.Decelerate();
					System.out.println(Nissan.GetSpeed() + " MPH");
				}

			}

			if (value3.equals("Brake")) {
				if (value.equals("Nissan")) {
					System.out.println(Nissan.Brake() + " MPH Car Stopped");
				}
				if (value.equals("Honda")) {
					System.out.println(Honda.Brake() + " MPH Car Stopped");
				}
			}

			if (value3.equals("Speed")) {
				if (value.equals("Honda")) {
					System.out.println("Current Speed: " + Honda.GetSpeed() + " MPH");
				}
				if (value.equals("Nissan")) {
					System.out.println("Current Speed: " + Nissan.GetSpeed() + " MPH");
				}
			}

			if (value3.equals("END")) {
				System.out.println("Thank you for test driving this car! Bye!");
				return;
			}
		}
	}
}
