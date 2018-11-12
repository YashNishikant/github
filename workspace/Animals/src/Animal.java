
public class Animal {

	void Food() {
		System.out.println("Food: Other Animals");
	}

	public static void main(String[] args) {

	System.out.println("ANIMAL INFORMATION");	
	System.out.println("____________");
	
	//Lion
	System.out.println("Lion:");
	System.out.println();
	Lion Lion1 = new Lion();
	
	Lion1.Transportation();
	Lion1.Sound();
	Lion1.Live();
	System.out.println("____________");
	
	//Eagle
	System.out.println("Eagle:");
	System.out.println();
	Eagle Eagle1 = new Eagle();
	
	Eagle1.Transportation();
	Eagle1.Sound();
	Eagle1.Live();
	System.out.println("____________");
	
	//Shark
	System.out.println("Shark:");
	System.out.println();
	Shark Shark1 = new Shark();
	
	Shark1.Transportation();
	Shark1.Sound();
	Shark1.Live();
	System.out.println("____________");
	
	//Cobra
	System.out.println("Cobra:");
	System.out.println();
	Cobra Cobra1 = new Cobra();
	
	Cobra1.Transportation();
	Cobra1.Sound();
	Cobra1.Live();
	System.out.println("____________");
	
	//Deggy
	System.out.println("Deggy:");
	System.out.println();
	Deggy Deggy1 = new Deggy();
		
	Deggy1.Transportation();
	Deggy1.Sound();
	Deggy1.Live();
	}
}