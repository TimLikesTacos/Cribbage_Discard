package CribTest;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.*;
import org.junit.Ignore;
import org.junit.Test;

import Cribbage.Card;

public class CardTest {

	Card card0;
	Card card1;
	Card card2;
	Card card3;
	Card card4;
	Card card5;
	Card card6;
	
	Card array [];
	
	@Before
	public void init () {
		/*
		 * Card1 - A Spade
		 * 2 - 5 Club
		 * 3 - 10 Heart
		 * 4 - K Diamond
		 * 5 - 4 Club
		 * 6 - 5 Spade
		 */
		array = new CardsForTesting ().toArray();
		card1 = array[0];
		card2 = array[1];
		card3 = array[2];
		card4 = array[3];
		card5 = array[4];
		card6 = array[5];
	}
	// card0 and card1 are the same, just built from different constructors

	@Before
	public void createInitialCard () {
		card0 = new Card ();
	}
	
	@Test
	public void testDefaultConstructor() {
		// Test  constructors.
		assertThat (card0, notNullValue());
		Card anotherCard = new Card ();
		assertThat (anotherCard, not(sameInstance(card0)));
		assertThat (anotherCard, equalTo (card0));
		assertTrue (card0.getSuit().equals (Card.Suit.HEART));
		assertEquals ("A", card0.getName());
		assertThat (anotherCard.hashCode(), equalTo (card0.hashCode()));
		assertThat (card0.compareTo(anotherCard), is(0));
		assertThat (card0.getNameValue(), is(1));
	}
	
	@Test
	public void testTwoParameterConstructor () {
		
		// Card (Suit, String)
		// Check that the two parameter constructor makes a similar card to the defaul
		Card tester = new Card(Card.Suit.HEART, "A");
		assertThat (tester, notNullValue());
		assertThat (tester, not(sameInstance (card0)));
		assertThat (tester.getName(), is("A"));
		assertThat (tester.getSuit(), is (Card.Suit.HEART));
		assertThat (tester.getNameValue(), is (1));
		assertThat (tester, equalTo(card0));
		assertThat (tester.compareTo(card0), is (0));
		assertThat (tester.hashCode(), (equalTo(card0.hashCode())));
		assertThat (tester.toString(), equalTo(card0.toString()));
		
		//Check using difference parameters
		Card fiveClub = new Card (Card.Suit.CLUB, "5");
		assertThat (fiveClub, notNullValue());
		assertThat (fiveClub.getName(), is("5"));
		assertThat (fiveClub.getNameValue(), is (5));
		assertThat (fiveClub.getSuit(), is (Card.Suit.CLUB));
		
		Card threeClub = new Card (Card.Suit.CLUB, "3");
		assertThat (threeClub, notNullValue());
		assertThat (threeClub.getName(), is ("3"));
		assertThat (threeClub.getNameValue(), is(3));
		assertThat (threeClub.getSuit(), is (Card.Suit.CLUB));
		
		assertThat (threeClub, not (equalTo (fiveClub)));
		assertThat (threeClub, not (equalTo (card0)));
		assertThat (threeClub.hashCode(), not (equalTo(fiveClub.hashCode())));
		assertThat (threeClub.hashCode(), not (equalTo(card0.hashCode())));
		
		assertThat (fiveClub, not(equalTo(card0)));
		assertThat (fiveClub.hashCode(), not(equalTo(card0)));
		
		assertThat (threeClub.compareTo(fiveClub), is (-1));
		assertThat (threeClub.compareTo(threeClub), is (0));
		assertThat (threeClub.compareTo(card0), is (1));
		assertThat (threeClub.compareTo(null), is (0));
		assertThat (threeClub.equals(threeClub), is (true));
		assertThat (threeClub.equals(null), is (false));
	}
	
	@Test 
	public void testCopyConstructor () {
		Card first = new Card (Card.Suit.DIAMOND, "Q");
		Card copy = new Card (first);
		assertThat (copy, notNullValue());
		assertThat (copy, not (sameInstance(first)));
		assertThat (copy, not(equalTo(card0)));
		assertThat (copy, equalTo(first));
		assertThat (copy.hashCode(), equalTo(first.hashCode()));
		assertThat (copy.getName(), equalTo(first.getName()));
		assertThat (copy.getNameValue(), equalTo(first.getNameValue()));
		assertThat (copy.getSuit(), equalTo(first.getSuit()));
		assertThat (copy.compareTo(first), is (0));
		assertThat (copy.compareTo(card0), is (1));
		/*
		 * Card1 - A Spade
		 * 2 - 5 Club
		 * 3 - 10 Heart
		 * 4 - K Diamond
		 * 5 - 4 Club
		 * 6 - 5 Spade
		 */
		assertThat (card1.compareTo(card0), is(0));
		assertThat (card2.compareTo(card1), is (1));
		assertThat (card3.compareTo(card2), is (1));
		assertThat (card4.compareTo(card3), is (1));
		assertThat (card5.compareTo(card4), is (-1));
		assertThat (card6.compareTo(card5), is (1));
		assertThat (card6.compareTo(card2), is (0)); // same facevalue, different suit. They are matched
		assertThat (card6, not(equalTo(card2))); // same facevalue, diffferent suit.  They are not equal.
		
	}
	
}
