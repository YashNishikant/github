import java.util.Scanner;

//Car
class Car {

	String Color;
	private int Speed;
	String Seats;
	
	void Accelerate() {
		Speed = Speed + 10;
	}

	void Decelerate() {
		Speed = Speed - 10;
		if (Speed < 0) {
			Speed = 0;
		}
	}

	int GetSpeed() {
		return Speed;
	}

	int Brake() {
		Speed = 0;
		return Speed;
	}
}

//Honda
class Honda extends Car{

	String Color;
	private int Speed = 0;
	String Seats;
	private int TSpeedH = 120;

	void TopSpeed() {
		if (Speed > TSpeedH) {
			Speed = TSpeedH;
			System.out.println("Top Speed Reached");
		}
	}

	int GetTopSpeed() {
		return TSpeedH;
	}
}

//Nissan

class Nissan extends Car {

	String Color;
	private int Speed;
	String Seats;
	private int TSpeedN = 150;

	
	void TopSpeed() {
		if (Speed > TSpeedN) {
			Speed = TSpeedN;
			System.out.println("Top Speed Reached");
		}
	}

	int GetTopSpeedN() {
		return TSpeedN;
	}
}

public class App {
	private static Scanner input;

	public static void main(String[] args) throws InterruptedException {

		input = new Scanner(System.in);
		System.out.println("Which car would you like to purchase? (Honda or Nissan)");

		String value = input.nextLine();

		Honda HondaCar = new Honda();
		HondaCar.Color = "Color: Blue";
		HondaCar.Seats = "7 seats";
		HondaCar.GetTopSpeed();

		Nissan NissanCar = new Nissan();
		NissanCar.Color = "Color: Gray";
		NissanCar.Seats = "5 seats";
		NissanCar.GetTopSpeedN();

		if (value.equals("Honda")) {
			System.out.println("INFORMATION ON HONDA");
			System.out.println(HondaCar.Color);
			System.out.println(HondaCar.Seats);
			System.out.println("Top Speed: " + HondaCar.GetTopSpeed());
			Thread.sleep(500);
			System.out.println("Would you like to test drive this car?");
		}

		if (value.equals("Nissan")) {
			System.out.println("INFORMATION ON NISSAN");
			System.out.println(NissanCar.Color);
			System.out.println(NissanCar.Seats);
			System.out.println("Top Speed: " + NissanCar.GetTopSpeedN());
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
					NissanCar.Accelerate();
					NissanCar.TopSpeed();
					System.out.println(NissanCar.GetSpeed() + " MPH");
				}

				if (value.equals("Honda")) {
					HondaCar.Accelerate();
					HondaCar.TopSpeed();
					System.out.println(HondaCar.GetSpeed() + " MPH");
				}
			}

			if (value3.equals("Decelerate")) {
				if (value.equals("Honda")) {
					HondaCar.Decelerate();
					System.out.println(HondaCar.GetSpeed() + " MPH");
				}

				if (value.equals("Nissan")) {
					NissanCar.Decelerate();
					System.out.println(NissanCar.GetSpeed() + " MPH");
				}

			}

			if (value3.equals("Brake")) {
				if (value.equals("Nissan")) {
					System.out.println(NissanCar.Brake() + " MPH Car Stopped");
				}
				if (value.equals("Honda")) {
					System.out.println(HondaCar.Brake() + " MPH Car Stopped");
				}
			}

			if (value3.equals("Speed")) {
				if (value.equals("Honda")) {
					System.out.println("Current Speed: " + HondaCar.GetSpeed() + " MPH");
				}
				if (value.equals("Nissan")) {
					System.out.println("Current Speed: " + NissanCar.GetSpeed() + " MPH");
				}
			}

			if (value3.equals("END")) {
				System.out.println("Thank you for test driving this car! Bye!");
				return;
			}
		}
	}
}
