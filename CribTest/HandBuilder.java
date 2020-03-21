package CribTest;
import java.util.ArrayList;


import Cribbage.Card;


public class HandBuilder {

	public ArrayList<Card> hand;
	/*
	 * For use where suits do not matter
	 */

	public HandBuilder (Card.Suit suit, Object [] arr)
	{
		hand = new ArrayList<Card> ();
		for (int i = 0; i < arr.length; ++i) {
			hand.add(new Card (Card.Suit.HEART, arr[i].toString()));
		}
	}
	
	public HandBuilder(Object [] arr) {
		this (Card.Suit.HEART, arr);
	}
	
	public ArrayList <Card>getHand () {
		return hand;
	}

}
