package Cribbage;



import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;



/**
 * @author Tim Reed 12/2019
 * This class represents a deck of cards.  It is mainly comprised of (normally) 52 cards of type Card in an ArrayList.
 * Updated 3/2020:
 * 		-Reorganized and added / removed items to make more object orientated.
 * 		- Added iterator, encapsulated the underlying structure and made it a Deque versus a ArrayList
 * 		-Removed the building of the deck from the constructor.  Made separate methods.
 */
public class Deck implements Cloneable, Iterable<Card>{

	private Deque <Card> cards; // ArrayList that contains the cards in the deck.
	
	/**
	 * Constructor
	 * Builds a standard playing deck of cards with 4 Suits and 13 cards in each suit.  Aces are low.  The Deck is not shuffled.
	 */
	public Deck () {
		
		cards = new LinkedList <Card> ();

	}
	
	/**
	 * Constructor
	 * Build an empty deck of cards with allocated size based on input integer 
	 * @param number of Cards to allocate.  Does not populate the list.
	 */
	public Deck (Collection<Card> in) {
		cards = new LinkedList <Card> (in);
	}
	
	public Deck (Deck copy) {
		cards = new LinkedList <Card> (copy.cards);
	}
	
	
	/**
	 * Randomly shuffles the deck.
	 */
	public void shuffle () {
		
		Random rand = new Random ();
		Card [] cardArray = new Card [cards.size()];
		cardArray = cards.toArray(cardArray);
		Card temp = new Card ();
		for (int i = 0; i < cards.size(); ++i)
		{
			int n = i + rand.nextInt(cards.size() - i);
			//Swap cards
			temp =  cardArray[n];
			cardArray[n] = cardArray[i];
			cardArray[i] =  temp;			
		}
		cards.clear();
		for (Card c : cardArray) {
			cards.add(c);
		}
	}
	
	/**
	 * @return int the size or the number of cards in the deck.
	 */

	public int size () {
		return cards.size();
	}
	
	public Card peek () {
		return cards.peek();
	}
	
	/**
	 * @return Card removed from the top of the deck
	 */
	public Card removeFromTop () {
		if (cards.size() <= 0)
			return null;
		return cards.pop();
	}
	/**
	 * @param Card to be added to the top of the deck
	 * Does not prevent adding already exisiting cards to the deck.
	 */
	public void addToTop (Card toAdd) {
		cards.addFirst(toAdd);
	}
	/**
	 * @return Card removed from bottom of the Deck
	 * throws NoSuchElementException if deck is empty
	 */
	public Card removeFromBottom () {
		if (cards.size() == 0)
			return null;
		return cards.removeLast();
	}
	
	/*
	 * Cut () performs a cut of the deck.  The cards on top to the cut
	 * location are moved, in order, to the bottom of the deck as one block.
	 * @param int location where all cards less than index will be moved to the bottom
	 * of the deck.  A paramter of 0 will not change the deck.  If index given bigger than deck
	 * size, then the deck will not be cut.
	 */
	public void cut (int index) {
		
		if (index >= cards.size())
			index = 0; // make sure cut location is within deck size
		for (int i = 0; i < index; ++i)
		{
			Card temp = cards.pop(); // removes from top of deck.
			cards.addLast(temp); // add it to the bottom of deck.
		}
		
	}
	/**
	 * @param Card to be added
	 * @return returns boolean if added IAW Deque interface
	 */
	public boolean addToBottom (Card toAdd) {
		return cards.add(toAdd);
	}
	@Override
	public Iterator <Card> iterator () {
		return cards.iterator();
	}
	@Override
	public String toString () {
		return new String ("Number of cards in deck: " + cards.size () +
					"\nCards in the deck: " + cards.toString ());
	}
	/**
	 * @param Card to be determined if in Deck
	 * @return boolean true if the card is in the deck, otherwise false.
	 */

	public boolean contains (Card c) {
		return cards.contains(c);
	}
	@Override
	public boolean equals (Object rhs) {
		if (this == rhs)
			return true;
		if (!(rhs instanceof Deck))
			return false;
		Deck rhsD = (Deck)rhs;
		if (!(cards.size() == rhsD.cards.size()))
			return false;
		Iterator <Card> left = this.iterator();
		Iterator <Card> right = rhsD.iterator();
		
		while(left.hasNext()) {
			if (!(left.next().equals(right.next()))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Object clone () {
		Deck cloned = new Deck (this); //Soft copy

		Iterator <Card> iter = cloned.iterator();
		while (iter.hasNext()) { // Hard copy
			Card toClone = iter.next();
			toClone = new Card (toClone);
		}
		return cloned;
	}
	
	public void sort () {
		Card [] array  = cards.toArray(new Card [cards.size()]);
		Arrays.sort(array);
		cards.clear();
		for (Card c : array) {
			cards.add(c);
		}
		
	}
		
	/**
	 * Creates a normal deck of an already instantiated Deck
	 */
	public void buildNormal () {
		// Numbers / letters found on cards
		final int CARDS_IN_SUIT = 13; // Normal playing deck of cards.
		final int NUMBER_SUITS = 4; // Normal playing deck of cards.
		String [] face = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		
		// Get the values for the suits based off of the card enum.
		Card.Suit suits [] = Card.Suit.values ();
		
		// initialize the decks of cards.  13 cards of each suit, one number / letter per suit.
		for (int i=0; i < CARDS_IN_SUIT; ++i)
		{
			for (int j = 0; j < NUMBER_SUITS; ++j)
			{
				cards.add ( new Card ( suits [j], face[i]));
			}
		}	
	}
	
}
