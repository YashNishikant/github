package objects;

public class card {

	private int value;
	private String suit;
	private String color;

	public card(int v, String s) {

		value = v + 1;
		suit = s;

		if (suit.equals("Hearts") || suit.equalsIgnoreCase("Diamonds")) {
			color = "Red";
		} else {
			color = "Black";
		}
	}

	public String toString1() {
		return value + " of " + suit;
	}

	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}

	public String getColor() {
		return color;
	}

	public String toString() {
		switch (value) {
		case 1:
			return "Ace of " + suit;
		case 11:
			return "Jack of " + suit;
		case 12:
			return "Queen of " + suit;
		case 13:
			return "King of " + suit;
		}
		return value + " of " + suit;
	}
}
