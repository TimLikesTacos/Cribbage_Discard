package Cribbage;
import java.util.ArrayList;
import java.util.List;

import Calculators.DiscardCalculator;


/**
 * This class is the base class to establish the play of a game of cribbage.
 * 
 * @author Tim Reed 12/2019
 * 
 */

public abstract class Cribbage {

	protected List <Card> hand; // used to contain cards in the hand
	protected List <Card> crib; // used to contain cards in the crib
	protected int pointsInCrib; // keeps track of points in the Crib
	protected int pointsInHand; // keeps track of points in the hand
	protected Boolean ownCrib; // true if the crib belongs to the player
	protected Deck cards; // the deck of cards
	
	private final int PLAYERS; // Number of players
	protected int cardsDelt; // number of cards delt to players.  This is determined by the number of players in the game.
	protected int cardsDis; // the number of cards each player discards into the crib. Depends on amount of players
	protected int deltCardstoCrib; // the number of cards delt directly to the crib.  Depends on amount of players.
	
	boolean flush; // true if playing with allowing points for flushes
	boolean nobs; // true if playing with nobs in hand (Jack of cut card suit is 1 point).
	
	DiscardCalculator discard; // used to calculate the best option to discard into the crib at beginning of hand.
	
	/**
	 * Default Constructor.  Creates a two player game
	 */
	public Cribbage () {this (2); } 
	
	/**
	 * Constructor
	 * @param players = amount of players in the game.  Default is 2.
	 * Establishes the values for how many cards to deal based on the number of players. Sets default values for the ownCrib, flush, and nobs.
	 */
	public Cribbage (int players) {
		// play if for 2 to 4 players.  Anything outside this range will default to 2
		if (players > 4 || players < 2)
			players = 2;
		PLAYERS = players;
		cards = new Deck ();
		cards.buildNormal();
		cards.shuffle (); // shuffles the deck
		hand = new ArrayList <Card> ();
		crib = new ArrayList <Card> ();
		pointsInCrib = 0;
		pointsInHand = 0;
		
		// This switch statement establishes the number of cards delt, 
		// the number each player discards, and if an additional card is
		// delt into the crib.  These are in the rules for cribbage.
		
		switch (PLAYERS) {
		case 2: 
			cardsDelt = 6;
			cardsDis = 2;
			deltCardstoCrib = 0;
			break;
		case 3:
			cardsDelt = 5;
			cardsDis = 1;
			deltCardstoCrib = 1;
			break;
		case 4:
			cardsDelt = 5;
			cardsDis = 1;
			deltCardstoCrib = 1;
			break;
		default: // should never be used. constructor sets to 2 if anything other than 2, 3, or 4.
			cardsDelt = 6;
			cardsDis = 2;
			deltCardstoCrib = 0;
			break;
		}
		
		ownCrib = true; // sets default to owncrib.
		flush = false; // set default.  Optional scoring rule
		nobs = false; // set default.  Optional scoring rule
	}
	
	/**
	 * Changes the ownCrib variable to the opposite of what it currently is.  If it is the players own crib, calling this method will make it not the players crib
	 */
	public void swapCrib ()
	{
		ownCrib = !ownCrib;
	}
	
	/**
	 * @return boolean value.  True if it is the crib belongs to the player.  False otherwise.
	 */
	public boolean getCrib ()
	{
		return ownCrib;
	}
	
	/**
	 * Gets the Card from the hand.
	 * @param index in the hand List
	 * @return Card in the specified index
	 */
	public Card getCard (int index)
	{
		return hand.get(index);
	}
	
	public void swapFlush () {
		flush = !flush;
	}
	/**
	 * Gets the status of the flush flag.
	 * @return boolean value.  True if flush is being used, false otherwise.
	 */
	public boolean getFlush () {
		return flush;
	}
	
	/**
	 * Gets status of the nobs flag.
	 * @return boolean, True if nobs is being used, false otherwise.
	 */
	
	public boolean getNobs () {
		return nobs;
	}
	
	/**
	 * Sets the hand list to a Card at a certain index
	 * @param pos = position in the List that will be set
	 * @param c = Card to set
	 */
	public void setHand (int pos, Card c) {
		hand.set(pos, c);
	}
	
	/**
	 * Gets the best discard options at the beginning of the round
	 * @return List of Integers.  The integers correspond to the card in the hand that should be discarded.
	 */
	public List <Integer>getBestDiscard () {
	
		discard = new DiscardCalculator (hand, flush, ownCrib, cardsDis);
		ArrayList <Integer> cardsToDiscard = discard.getDiscards ();
		
		return cardsToDiscard;
		
	}
	
	/**
	 * Abstract method to be implemented by subclasses.  Dealing and rules different for the amount of players.
	 */
	public abstract void redeal ();
	
	/**
	 * Abstract method to be implemented by subclasses.  Dealing and rules different for the amount of players.
	 */
	public abstract void deal ();
	
	/**
	 * Outputs the cards in hand array.  Used for debugging
	 */
	protected void print()
	{
		for (int i = 0; i < hand.size(); ++i)
		{
			System.out.println (hand.get(i));
		}
	}
	
}
