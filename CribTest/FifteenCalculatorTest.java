package CribTest;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import Calculators.FifteenCalculator;
import Cribbage.Card;

public class FifteenCalculatorTest {

	static ArrayList <ArrayList<Card>> hands = new ArrayList <ArrayList<Card>> ();
	@BeforeClass
	public static void buildHands () {
		hands = new ArrayList<ArrayList<Card>> ();
		hands.add(new HandBuilder (new Integer [] {}).getHand()); // empty set
		hands.add(new HandBuilder (new Integer [] {1, 2, 3, 4}).getHand());
		hands.add(new HandBuilder (new Integer [] {1, 2, 3, 4, 5, 6}).getHand());
		hands.add(new HandBuilder (new Integer [] {1, 1}).getHand());
		hands.add(new HandBuilder (new Integer [] {1, 2, 2, 1}).getHand());
		hands.add(new HandBuilder (new Integer [] {3, 3, 3, 1, 5}).getHand());
		hands.add(new HandBuilder (new Integer [] {4, 4, 4, 4, 6, 7}).getHand());
		hands.add(new HandBuilder (new Integer [] {1, 2, 3, 4, 5, 6}).getHand());
		hands.add(new HandBuilder (new Integer [] {1, 2, 3, 4, 6, 5}).getHand());
		hands.add(new HandBuilder (new Integer [] {10, 9, 8, 7, 6}).getHand());
		hands.add(new HandBuilder (new String [] {"K", "K", "Q", "J"}).getHand());
		hands.add(new HandBuilder (new String [] {"5", "K", "Q", "Q"}).getHand());
		hands.add(new HandBuilder (new String [] {"K", "5", "2", "3"}).getHand());
		hands.add(new HandBuilder (new String [] {"Q", "K", "A", "2"}).getHand());
	}
	@Test
	public void test() {
		int [] expected = new int [] {0, 0, 8, 0, 0, 2, 12, 8, 8, 4, 0, 6, 4, 0};
		for (int i = 0; i < hands.size(); ++i) {
			FifteenCalculator calc = new FifteenCalculator (hands.get(i));
			assertThat ("" + i + ": ", calc.getPoints(), is (expected [i]));
		}
	}

}
