package CribTest;
import Cribbage.Card;
import java.util.ArrayList;

public class TestHands {

	public ArrayList <ArrayList<Card>> hands;
	
	public ArrayList<Card> handBuilder(Object [] arr) {
		return handBuilder (arr, Card.Suit.HEART);
	}
	public ArrayList<Card> handBuilder(Object [] arr, Card.Suit suit) {
		ArrayList <Card> hand = new ArrayList<Card> ();
		for (int i = 0; i < arr.length; ++i) {
			hand.add(new Card (suit, arr[i].toString()));
		}
		return hand;
	}
	
	public TestHands () {
		hands = new ArrayList<ArrayList<Card>> ();
		hands.add(handBuilder (new Integer [] {})); // empty set
		hands.add(handBuilder (new Integer [] {1, 2, 3, 4}));
		hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 5, 6}));
		hands.add(handBuilder (new Integer [] {1, 1}));
		hands.add(handBuilder (new Integer [] {1, 2, 2, 1}));
		hands.add(handBuilder (new Integer [] {3, 3, 3, 1, 5}));
		hands.add(handBuilder (new Integer [] {4, 4, 4, 4, 6, 7}));
		hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 5, 6}));
		hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 6, 5}));
		hands.add(handBuilder (new Integer [] {10, 9, 8, 7, 6}));
		hands.add(handBuilder (new String [] {"K", "K", "Q", "J"}));
		hands.add(handBuilder (new String [] {"A", "K", "Q", "Q"}));
		hands.add(handBuilder (new String [] {"K", "A", "2", "3"}));
		hands.add(handBuilder (new String [] {"Q", "K", "A", "2"}));
	}
	
	public ArrayList<ArrayList<Card>> getHands() {return hands;}
}
