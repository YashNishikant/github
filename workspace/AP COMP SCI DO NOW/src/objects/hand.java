package objects;

import java.util.ArrayList;

public class hand {

	private int maxCards;
	private ArrayList<card> cards;

	public hand(int m) {

		cards = new ArrayList<card>(maxCards);
		maxCards = m;
		getCards();
		getMaxCards(maxCards);
	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < cards.size(); i++)
			temp += cards.get(i).toString() + "\t";
		return temp;
	}

	public ArrayList<card> getCards() {
		return cards;
	}

	public int getMaxCards(int max) {
		return max;
	}

	public void addCard(card c) {
		cards.add(c);
	}
}
