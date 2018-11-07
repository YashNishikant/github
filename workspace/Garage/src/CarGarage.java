
public class CarGarage {

	private int i = 0;
	
	void Enter(Car car) {
		i++;
		System.out.println("Car entered garage: Make:" + car.GetMake() + " Model:" + car.GetModel());
	}

	void Exit(Car car) {
		i--;
		System.out.println("Car exited garage: Make:" + car.GetMake() + " Model:" + car.GetModel());
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
		garage.Enter(nissanCar);
		garage.Enter(nissanCar);
		garage.Exit(nissanCar);
		Car car = new Car(100,10);
		garage.Enter(car);
	
		System.out.println(garage.CountCar());
	}
}
