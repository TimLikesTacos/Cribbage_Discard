

import java.util.List;
import java.util.Random;
import java.util.Vector;



/**
 * @author Tim Reed 12/2019
 * This class represents a deck of cards.  It is mainly comprised of (normally) 52 cards of type Card in an ArrayList.
 *
 */
public class Deck {

	public List <Card> cards; // ArrayList that contains the cards in the deck.
	
	final private int CARDS_IN_SUIT = 13; // Normal playing deck of cards.
	final private int NUMBER_SUITS = 4; // Normal playing deck of cards.
	final private int NUM_CARDS = NUMBER_SUITS * CARDS_IN_SUIT; 
	
	/**
	 * @return int the number of Suits used in the deck
	 */
	public int getNumSuits () {return NUMBER_SUITS;}
	
	/**
	 * @return int the number of cards in one suit.
	 */
	public int getCardsInSuit () {return CARDS_IN_SUIT;}
	
	/**
	 * @return int the size or the number of cards in the deck.
	 */
	public int size () {return NUM_CARDS;}
	
	/**
	 * @return List <Card> the cards in the deck.
	 */
	public List <Card> getCards () {return cards;}

	
	
	// Internal method to get the value of a card to build the deck.
	private int determineValue (String c) {
		
		int toReturn;
		try {
			toReturn = Integer.parseInt (c);
		} catch (NumberFormatException e)
		{
			toReturn = 10;
		}
		if (c.equals("A"))
			toReturn = 1;
		return toReturn;
	}
	
	/**
	 * Constructor
	 * Builds a standard playing deck of cards with 4 Suits and 13 cards in each suit.  Aces are low.  The Deck is not shuffled.
	 */
	public Deck () {

		// Numbers / letters found on cards
		String [] face = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	    
		// Get the values for the suits based off of the card enum.
		Card.Suit suits [] = Card.Suit.values ();
	
		// initialize the decks of cards.  13 cards of each suit, one number / letter per suit.
		cards = new Vector <Card> (NUM_CARDS);
		for (int i=0; i < CARDS_IN_SUIT; ++i)
		{
			for (int j = 0; j < NUMBER_SUITS; ++j)
			{
				cards.add ( new Card ( suits [j], face[i], determineValue (face[i])));
			}
		}	
	}
	
	/**
	 * Randomly shuffles the deck.
	 */
	public void shuffle () {
		
		Random rand = new Random ();

		Card temp = new Card ();
		for (int i = 0; i < NUM_CARDS; ++i)
		{
			int n = i + rand.nextInt(NUM_CARDS - i);
			//Swap cards
			temp =  cards.get(n);
			cards.set(n,cards.get(i)) ;
			cards.set(i, temp);			
		}
	}
	
	/**
	 * 
	 * @param index of Deck
	 * @return Card at index in Deck.
	 */
	public Card at (int index) {
		return cards.get(index);
	}
	
	/**
	 * @param index to remove the card
	 * @return Card removed from Deck
	 */
	public Card remove (int index) {
		return cards.remove(index);
	}

	@Override
	public String toString () {
		return new String ("Number of cards in deck: " + cards.size () +"\tFirst card in the deck: " + cards.get(0).toString ());
	}
	
}
