package CribTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.hamcrest.Matchers.*;

import Cribbage.Card;
import Cribbage.Deck;

/*
 * 		Unicode values:
 * 		HEART (0x2661),
		DIAMOND (0x2662),
		SPADE (0x2660),
		CLUB (0x2663);
 */
public class DeckTest {

	Deck test;
	static Card AOfSpade;
	static Card FiveOfClub;
	static Card TenOfHeart;
	static Card KingOfDiamond;
	String [] face = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	
	
	@BeforeClass
	public static void start () {
		AOfSpade = new Card (Card.Suit.SPADE, "A");
		FiveOfClub = new Card (Card.Suit.CLUB, "5");
		TenOfHeart = new Card (Card.Suit.HEART, "10");
		KingOfDiamond = new Card (Card.Suit.DIAMOND, "K");
	}
	@Before
	public void init () {
		test = new Deck ();
	}
	
	@Test
	public void defaultConstructorTest() {
		
		// test contains proper normal deck parameters
		assertThat (test, notNullValue()); // object created
		assertThat (test.size(), is(0)); // has no cards in it
		assertThat (test.contains(FiveOfClub), is(false)); // does not contain cards
		assertThat (test.contains(AOfSpade), is (false));
		Iterator <Card> iter = test.iterator();
		assertThat (iter.hasNext(), is(false)); // zero cards so nothing to iterate over
		assertThat (test.toString(), notNullValue()); // returns something other than null
		assertThat (test.toString(), not(containsString(AOfSpade.toString()))); //string does not contain a card
		assertThat (test.toString(), not(containsString(FiveOfClub.toString()))); 

	}
	
	@Test
	public void buildNormalTest () {
		test.buildNormal();
		assertThat (test.size(), is (52));
		// tests that two decks created are equal.  Also checks hashcode function for the Card class.
		assertThat (test.contains(AOfSpade), is(true)); // Contains a card in the normal deck
		assertThat (test.contains(FiveOfClub), is(true)); // contains a card in the normal deck
		assertThat (test.contains(TenOfHeart), is(true));
		assertThat (test.contains(KingOfDiamond), is (true));
		Deck test2 = new Deck ();
		assertThat (test2, not(sameInstance(test))); // different objects
		test2.buildNormal();
		assertThat (test2.size(), equalTo(test.size())); // same length
		assertThat (test2, equalTo(test)); // two normal built decks should be equal.
		
		Card peek = test.peek ();
		Iterator <Card> iter1 = test.iterator();
		Set<Integer> hashCodes = new HashSet<Integer> (); // used to check uniqueness of hashcodes
		Set<Card> usedCards = new HashSet<Card>(52); // ensure cards are not duplicated
		/* The following map is used to count the number of times a unique facevalue has been found going through the deck.
		 * There should be 13 entries, each with a count of 4 */
		Map<String,Integer> count = new HashMap<String,Integer> (13); 
		for (int i = 0; i < test.size(); ++i) {
			Card card1 = iter1.next();
			assertThat (card1, notNullValue());
			if (i ==0) {
				assertThat (card1, equalTo(peek));
			}
			assertThat ("Hashcode for card: " + card1.toString(),hashCodes.add(card1.hashCode()), is(true));
			assertThat ("Card: " + card1.toString(), usedCards.add(card1), is(true));
			count.put(card1.getName(), count.getOrDefault(card1.getName(), 0) + 1);
			
		}
		assertThat (iter1.hasNext(), is(false)); // iterator should be complete
		assertThat (count.size(), is (13)); // should have 13 unique facevalues with a count of 4;
		assertThat (usedCards.contains(AOfSpade), is(true));
		assertThat (usedCards.contains(FiveOfClub), is(true));
		assertThat (usedCards.contains(KingOfDiamond),is(true));
		assertThat (usedCards.contains(TenOfHeart), is(true));
		Set <String> keys = count.keySet();
		List<String> cardFaces = Arrays.asList(face);
		for (String key : keys) {
			assertThat (count.get(key), is (4));
			assertThat (cardFaces.contains(key), is(true));
	
		}
		
		String cardString = test.toString();
		assertThat (cardString, stringContainsInOrder(cardFaces));
			
		//System.err.println (test.toString());
		//assertThat (test.getCards(), equalTo (test2.getCards()));
	}
	
	@Test
	public void copyConstructorTest () {
		test.buildNormal();
		Deck copy = new Deck (test);
		assertNotSame (test, copy);
		assertEquals (test, copy);
		test.shuffle();
		assertNotEquals (test, copy);
		assertThat (test.contains(AOfSpade), is(true));
		assertThat (test.contains(FiveOfClub), is(true));
		assertThat (test.contains(KingOfDiamond),is(true));
		assertThat (test.contains(TenOfHeart), is(true));
		
	}
	
	@Test
	public void shuffleTest () {
		
		
		test.buildNormal ();
		test.shuffle ();
		Deck plain = new Deck (); // normal deck to compare to.
		plain.buildNormal();
		
		assertThat (test, not(equalTo(plain)));
		assertThat (test.size(), is(52));
		Iterator <Card> iter = test.iterator();
		Iterator <Card> plainIter = plain.iterator();
		Set<Card> usedCards = new HashSet <Card>(52);
		Map<String, Integer> count = new HashMap <String, Integer> (13);
		int matchedCardCount = 0;
		for (int i = 0; i < test.size(); ++i) {
			Card card = iter.next();
			assertThat (card, notNullValue()); // Return an object
			assertThat (plain.contains(card), is(true)); // Ensure that card is in a normal deck
			assertThat (test.toString(),usedCards.add(card), is(true)); // endure that card is not being duplicated and is unique.
			count.put(card.getName(),count.getOrDefault(card.getName(), 0) + 1);
			if (card.equals(plainIter.next())) {
				++matchedCardCount; // Counts the number of times a shuffled deck and normal deck match up.
			}
		}
		assertThat (iter.hasNext(), is(false));
		assertThat (usedCards.contains(AOfSpade), is(true));
		assertThat (usedCards.contains(FiveOfClub), is(true));
		assertThat (usedCards.contains(KingOfDiamond),is(true));
		assertThat (usedCards.contains(TenOfHeart), is(true));
		assertThat (count.size(), is (13));
		assertThat ("Matches: " + matchedCardCount, matchedCardCount, lessThan(8)); // Allows _some_ matches to occur.
		Set<String> keys = count.keySet();
		for (String k : keys) {
			assertThat (count.get(k), is (4)); // Four cards per face value
		}
		assertThat (test.toString(), not(equalTo(plain.toString())));
	}
		
	@Test
	public void addToTopTest () {
		test.buildNormal();
		Card peek = test.peek(); // Get normal first card of the deck
		Deck empty = new Deck ();
		test.addToTop(AOfSpade);
		test.addToTop(FiveOfClub);
		empty.addToTop(AOfSpade);
		empty.addToTop(FiveOfClub);
		assertThat (test.size(), equalTo(54));
		assertThat (empty.size(), equalTo (2));
		assertThat (empty.contains(FiveOfClub), is(true));
		assertThat (empty.contains(AOfSpade), is(true));
		// Five of club is on top.
		assertThat (empty.toString(), stringContainsInOrder(Arrays.asList(FiveOfClub.toString(), AOfSpade.toString())));
		assertThat (test.toString(), stringContainsInOrder(Arrays.asList(FiveOfClub.toString(), AOfSpade.toString(), AOfSpade.toString(), FiveOfClub.toString())));
		//assertThat (test.hashCode(), not(equalTo(hash)));
		Iterator <Card> testIter = test.iterator();
		Iterator <Card> emptyIter = empty.iterator();
		assertThat (testIter.next(), equalTo(FiveOfClub));
		assertThat (emptyIter.next(), equalTo(FiveOfClub));
		assertThat (testIter.next(), equalTo(AOfSpade));
		assertThat (emptyIter.next(), equalTo(AOfSpade));
		assertThat (testIter.next(), equalTo(peek));
		assertThat (emptyIter.hasNext(), is (false));	
	}
	
	
	@Test
	public void removeFromTopTest () {
		test.buildNormal();
		Card peek = test.peek();
		Card removed = test.removeFromTop();
		assertThat (removed, equalTo(peek));
		assertThat (test.size(), is (51));
		Card loopRemoved;
		test.sort();
		for (int i = test.size(); i > 0; --i) {
			loopRemoved = test.removeFromTop();
			assertThat ("i: " + i + "\n" + loopRemoved + ", " + removed + "\n" + test.toString(),
					loopRemoved.compareTo(removed), is(1));
		}
		assertThat (test.size(), is(0));
		try {
			assertThat (test.removeFromTop(), nullValue());
		}
		catch (Exception e) {
			fail (); // should identify the cards are empty and return null, not exception.
		}
		test.buildNormal();
		peek = test.peek();
		test.addToTop(FiveOfClub);
		removed = test.removeFromTop();
		assertThat (removed, is(FiveOfClub));
		assertThat (test.removeFromTop(), is(peek));
	}
	
	@Test
	public void addToBottomTest () {
		test.addToBottom(FiveOfClub);
		assertThat (test.size(), is(1));
		assertThat (test.contains(FiveOfClub), is(true));
		test = new Deck();
		test.buildNormal();
		test.addToBottom(FiveOfClub);
		assertThat (test.size(), is(53));
		Iterator<Card> iter = test.iterator();
		int count = 0;
		int pos = 0;
		int cardPos = 0;
		while (iter.hasNext()) {
			// make sure deck has two Five of Clubs
			if (iter.next().equals(FiveOfClub)) {
				++count;
				cardPos = pos;
			}
			++pos;
		}
		assertThat (count, is(2));
		assertThat (cardPos, is (test.size() - 1));
	}
	
	@Test
	public void removeFromBotton () {
		test.addToBottom(FiveOfClub);
		Card removed = test.removeFromBottom();
		assertThat (removed, is(FiveOfClub));
		assertThat (test.size(), is(0));

		try {
			assertThat (test.removeFromBottom(), nullValue());
		}
		catch (Exception e) {
			fail(e.toString());
		}
		test = new Deck();
		test.buildNormal();
		Card top = test.peek();
		for (int i = test.size(); i >0 ; --i) {
			removed = test.removeFromBottom();
		}
		assertThat (test.toString(), removed, is(top));
	}
	
	@Test
	public void sortTest () {
		test.buildNormal();
		Deck test2 = new Deck();
		test2.buildNormal();
		test2.sort();
		assertThat (test2, is(test));
		assertThat (test2.toString(), is (test.toString()));
		test2.shuffle();
		assertThat (test2, not(test));
		test2.sort();
		assertThat (test2, is (test));
		assertThat (test2.toString(), is (test.toString()));
		test2.addToBottom(TenOfHeart);
		test2.addToTop(TenOfHeart);
		test2.sort();
		Iterator <Card> iter = test2.iterator();
		while (true) {
			if (iter.next().equals(TenOfHeart)) {
				assertThat (iter.next(), is(TenOfHeart));
				assertThat (iter.next(), is (TenOfHeart));
				assertThat (iter.next(), not(TenOfHeart));
				break;
			}
			if (iter.hasNext() == false) {
				fail();
				break;
			}
		}
		
	}
	
	@Test
	public void cutTest () {
		test.buildNormal();
		Deck normal = new Deck ();
		normal.buildNormal();
		assertThat (test, is(normal));
		test.cut(0);
		assertThat (test, is(normal));
		test.cut(test.size() + 4);
		assertThat (test, is(normal));
		test.cut(10);
		assertThat (test, not(normal));
		
		int deckSize = test.size();
		for (int i = 0; i < deckSize; ++i) {
			test = new Deck ();
			test.buildNormal();
			test.cut(i);
			List<Card> topPart = new ArrayList<Card> (i);
			Iterator<Card> testIter = test.iterator();
			Iterator<Card> normIter = normal.iterator();
			for (int j = 0; j < i; ++j) {
				topPart.add((Card) normIter.next()); // move iterator through the normal deck to get to the cut point
			}
			int j = 0;
			while (j++ < deckSize - i)
			{
				/* Check both decks for being equal starting at
				* the cut point to the normal end of the deck. */
				assertThat ("<i,j>: <" + i + ", " + j + ">\n" + normal.toString()+"\n"+test.toString(), testIter.next(), is(normIter.next())); 
			}
			Iterator <Card> topIter = topPart.iterator();
			while (j++ < deckSize) {
				/* Check that the remaining portions in the cut test deck are equal to the original top portion
				 * 
				 */
				assertThat (testIter.next(), is(topIter.next()));
			}
		}
	}
	
	@Test
	public void cloneTest () {
		test.buildNormal();
		Deck cloned = (Deck)test.clone ();
		assertThat (cloned, not(sameInstance (test)));
		assertThat (cloned.peek(), is(test.peek()));
		assertThat (cloned.size(), is(test.size()));
		cloned.removeFromTop();
		assertThat (cloned, not(test));
		cloned = (Deck)test.clone();
		assertThat (cloned, is(test));
		assertThat (cloned, not(sameInstance(test)));
		test.addToTop(AOfSpade);
		assertThat (cloned, not(test));

	}

}
