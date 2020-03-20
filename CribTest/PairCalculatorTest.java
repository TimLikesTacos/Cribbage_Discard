package CribTest;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import Calculators.*;
import Cribbage.Card;

public class PairCalculatorTest {

	ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
	@Before
	public void init () {
		hands = (new TestHands ()).getHands();
	}


	
	@Test
	public void PairTest() {

		for (int i = 0; i < hands.size(); ++i) {
			ArrayList<Card> hand = hands.get(i); // get the test hand
			PairCalculator calc = new PairCalculator (hand);
			
			int expected = 0;
			
			switch (i) {
			case 0: expected = 0;
			break;
			case 1: expected = 0;
			break;
			case 2: expected = 0;
			break;
			case 3: expected = 2;
			break;
			case 4: expected = 4;
			break;
			case 5: expected = 6;
			break;
			case 6: expected = 12;
			break;
			case 7: expected = 0;
			break;
			case 8: expected = 0;
			break;
			case 9: expected = 0;
			break;
			case 10: expected = 2;
			break;
			case 11: expected = 2;
			break;
			case 12: expected = 0;
			break;
			case 13: expected = 0;
			break;
			
			default: expected = 0;
			}
			assertThat (calc.getPoints(), is(expected));
			
		}
	} // end pairTest

	/*
	 * 	0hands.add(handBuilder (new Integer [] {})); // empty set
		1hands.add(handBuilder (new Integer [] {1, 2, 3, 4}));
		2hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 5, 6}));
		3hands.add(handBuilder (new Integer [] {1, 1}));
		4hands.add(handBuilder (new Integer [] {1, 2, 2, 1}));
		5hands.add(handBuilder (new Integer [] {3, 3, 3, 1, 5}));
		6hands.add(handBuilder (new Integer [] {4, 4, 4, 4, 6, 7}));
		7hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 5, 6}));
		8hands.add(handBuilder (new Integer [] {1, 2, 3, 4, 6, 5}));
		9hands.add(handBuilder (new Integer [] {10, 9, 8, 7, 6}));
		10hands.add(handBuilder (new String [] {"K", "K", "Q", "J"}));
		11hands.add(handBuilder (new String [] {"A", "K", "Q", "Q"}));
		12hands.add(handBuilder (new String [] {"K", "A", "2", "3"}));
		13hands.add(handBuilder (new String [] {"Q", "K", "A", "2"}));
		
	 */
}
