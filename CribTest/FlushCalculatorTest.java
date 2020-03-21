package CribTest;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.junit.Test;

import Calculators.*;
import Cribbage.Card;
import Cribbage.Deck;

public class FlushCalculatorTest {

	
	Deck normalDeck = new Deck ();
	ArrayList <ArrayList<Card>> hands;
	
//	hands = new ArrayList<ArrayList<Card>> ();
//	hands.add(handBuilder (new Integer [] {})); // empty set
//	hands.add(handBuilder (new Integer [] {1, 2, 3, 4}));
//	hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 5, 6}));
//	hands.add(handBuilder (new Integer [] {1, 1}));
//	hands.add(handBuilder (new Integer [] {1, 2, 2, 1}));
//	hands.add(handBuilder (new Integer [] {3, 3, 3, 1, 5}));
//	hands.add(handBuilder (new Integer [] {4, 4, 4, 4, 6, 7}));
//	hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 5, 6}));
//	hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 6, 5}));
//	hands.add(handBuilder (new Integer [] {10, 9, 8, 7, 6}));
//	hands.add(handBuilder (new String [] {"K", "K", "Q", "J"}));
//	hands.add(handBuilder (new String [] {"A", "K", "Q", "Q"}));
//	hands.add(handBuilder (new String [] {"K", "A", "2", "3"}));
//	hands.add(handBuilder (new String [] {"Q", "K", "A", "2"}));

	
	@Test
	public void flushCalculator() {
		hands = new TestHands().getHands();
		assertNotNull (hands);
		for (int i = 0; i < hands.size(); ++i) {
			ArrayList<Card> hand = hands.get(i);
			FlushCalculator flushCalc = new FlushCalculator (hand);
			assertThat (flushCalc, notNullValue());
			int expected = 0;
			if (hand.size ()>= 4) {
				expected = hand.size();
			}
			assertThat (flushCalc.getPoints(), is(expected));
		}
		FlushCalculator flushCalc = new FlushCalculator (hand);
		/* 
		 * The max number of points for a flush is 13, since it is not expected to play cribbage without
		 * more than 13 points.  Since 4 cards are needed for a flush, it is not currently possible with
		 * known rules for cribbage that eight or more cards are used.  Therefore it is not expected to 
		 * need check for multiple flushes in a hand.
		 */
		assertEquals (13, flushCalc.getPoints());
		
		hand.clear();
		// The following will test a hand entirely of one suit.
		for (int i = 0; i <= 52; ++i) { 
			if (hand.size() % 13 == 0) {
				hand.clear ();
			}
			flushCalc = new FlushCalculator (hand);
			
			if (i % 13 < 4) // no points for less than 4 cards
				assertEquals (0, flushCalc.getPoints());
			else
				assertEquals ("Flush",i % 13, flushCalc.getPoints());
			
			hand.add(normalDeck.at((i * 4) % 52));
			
		}
		
		hand.clear();
		// The following will test with a hand with mixed suits.
		for (int i = 0; i <= 52; ++i) {
			if (hand.size() % 13 == 0) {
				hand.clear ();
			}
			flushCalc = new FlushCalculator (hand);
			
			if (hand.size() < 4)
				assertEquals (0, flushCalc.getPoints());
			else
				assertEquals ((i % 13) / 4 + 1, flushCalc.getPoints());
		}
	} // end flushTest
	
	

}
