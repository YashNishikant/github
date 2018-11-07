
public class CarGarage {

	private int i = 0;
	
	void Enter(Car car) {
		i++;
		System.out.println("Car entered garage");
	}

	void Exit(Car car) {
		i--;
		System.out.println("Car exited garage");
	}

	int CountCar() {
		return i;
	}
	
	public static void main(String[] args) {

		HondaOdyssey hondaOdyssey = new HondaOdyssey(false, true, "Blue");
		NissanRogue nissanCar = new NissanRogue(true, false, "silver");
		
		CarGarage garage = new CarGarage();

		
		garage.Enter(hondaOdyssey);
		garage.Enter(nissanCar);
		garage.Exit(nissanCar);
	
		System.out.println(garage.CountCar());
	}
}
