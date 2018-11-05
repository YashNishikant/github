//car
class Car {

	String Color;
	protected int Speed;
	String Seats;
	private int TSpeed;
	private int rateOfAcc;

	public Car(int topSpeed, int rateAcceleration) {
		TSpeed = topSpeed;
		rateOfAcc = rateAcceleration;
	}

	void Accelerate() {
		Speed = Speed + rateOfAcc;
		if (Speed > TSpeed) {
			Speed = TSpeed;
			System.out.println("Top Speed Reached");
		}
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

	int GetTopSpeed() {
		return TSpeed;
	}

	int Brake() {
		Speed = 0;
		return Speed;
	}
}
