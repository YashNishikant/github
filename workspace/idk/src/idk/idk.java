package idk;

import java.util.Scanner;

public class idk {

public static void main(String[] args) {
	
	System.out.println("Hello! Welcome!");
	
	Scanner scan = new Scanner(System.in);
	
	System.out.println("Would you like a drink? (y or n)");
	String drinkANS = scan.nextLine();
	
	if (drinkANS.equalsIgnoreCase("Y")) {
		
		System.out.println("(s)oda or (l)emonade?");
		String drinkANS2 = scan.nextLine();
	
		if(drinkANS2.equalsIgnoreCase("s")) {
			String orderDrink = "Soda";
			
		}
		
		if(drinkANS2.equalsIgnoreCase("l")) {
			String orderDrink = "Lemonade";
			
		}
		
	}

	
	System.out.println("Would you like food? (y or n)");
	String foodANS = scan.nextLine();
	
	if (foodANS.equalsIgnoreCase("Y")) {
		
		System.out.println("(h)otdog or (f)ries?");
		String foodANS2 = scan.nextLine();
	
		if(foodANS2.equalsIgnoreCase("h")) {
			String orderFood= "Hotdog";
			
		}
		
		if(foodANS2.equalsIgnoreCase("f")) {
			String orderFood = "Fries";
			
		}
		
	}
	
	if (foodANS.equalsIgnoreCase("N")) {
		
		
		
	}

}	
	
}
