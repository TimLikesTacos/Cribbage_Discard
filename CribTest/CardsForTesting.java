package CribTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Cribbage.Card;

public class CardsForTesting {
	Card AOfSpade;
	Card FiveOfClub;
	Card TenOfHeart;
	Card KingOfDiamond;
	Card FourOfClub;
	Card FiveOfSpade;
	
	public CardsForTesting() {
		AOfSpade = new Card (Card.Suit.SPADE, "A");
		FiveOfClub = new Card (Card.Suit.CLUB, "5");
		TenOfHeart = new Card (Card.Suit.HEART, "10");
		KingOfDiamond = new Card (Card.Suit.DIAMOND, "K");
		FourOfClub = new Card (Card.Suit.CLUB, "4");
		FiveOfSpade = new Card (Card.Suit.SPADE, "5");
	}
	
	public Card [] toArray () {
		 return new Card [] {AOfSpade, FiveOfClub, TenOfHeart, KingOfDiamond, FourOfClub, FiveOfSpade};
		
	}
	
	public List <Card> toList () {
		List <Card> list = new ArrayList <Card> (6);
		list = Arrays.asList(toArray());
		return list;
	}
}
