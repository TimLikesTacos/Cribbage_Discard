package Cribbage;


/**
 * This is the Card class.  It represents a single card in a standard playing deck.
 * @author Tim Reed 12/2019
 * Updated:
 * 		-removed member <code>int value</code> from class. This is part of the game, not of the card.
 * 			Constructors and accessors and mutators have been changed to reflect this.
 * 		-Cleaned up converter to get a numerical value of the facecard
 * 		-Removed print method.  toString() completed requirement
 * 		-Changed compareTo () to also compare suits.  This makes it consistant with equals().
 *
 */

public class Card implements Comparable {
	
	protected Suit suit;	// suit of the card. Heart, Diamond, Spade, Club.
	protected String name;	// The actual character on the card, A, 2, 3,...., 10, J, Q, K.
	
	/**
	 * Constructor
	 * @param suit2 = suit of the card
	 * @param n = name of the card
	 */

	public Card (Suit suit2, String n) {
		suit = suit2;
		name = n;
	}

	/**
	 * Construtor
	 * @param c = Card to create.
	 */
	public Card (Card c){
		suit = c.suit;
		name = c.name;
	}
	
	/**
	 * Default constructor.  Creates Ace of Hearts card when no parameters are given.
	 */
	public Card () { this(Suit.HEART, "A");}


	/**
	 * Coverts to string.  Outputs the face value followed by the suit using unicode symbols
	 */
	@Override
	public String toString () {
		return new String ("" + name + " " + suit.convert());
	}


	/**
	 * Enumeration for the Suits.  Contains unicode values to allow printing the symbols of the suits.
	 * @author Tim Reed 12/2019
	 *
	 */
	public enum Suit  {
		// values are unicode symbols.  Hearts and diamonds are white, others are black
		SPADE (0x2660),
		HEART (0x2661),
		DIAMOND (0x2662),
		CLUB (0x2663);

		private int s;

		Suit (int v) {
			this.s = v;
		}

		public int getSuit () {return this.s;}
		// this funciton coverts the suit to a printable unicode character
		public String convert ()
		{
			char [] ch = Character.toChars(s);
			String str = "";
			for (int i = 0; i < ch.length; ++i){
				str = str + ch [i];
			}
			return str;
		}

		public boolean equals (Suit other) {
			if (this == other)
				return true;
			else
				return false;
		}
	}

	/**
	 * 
	 * @return Suit of the card
	 */
	public Suit getSuit () {
		return suit;
	}
	
	/**
	 * @return String of the name of the card.
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * This function allows getting the name of the card in numerical value (i.e. Q is 12).
	 * This can be used for determining runs
	 * @return Value of card 1-13.  Kings are 13, Aces are 1.
	 */

	public int getNameValue () {
		
		switch (name) {
		case "A":
			return 1;
		case "J":
			return 11;
		case "Q":
			return 12;
		case "K":
			return 13;
		default:
			return Integer.parseInt(name);
		}
	}


	/**
	 * Compares this to another Card.
	 * @param Card to compare this to.
	 * @return -1 if this is less than parameter, 0 if equal, 1 if greater than.
	 */
	
	@Override
	public boolean equals (Object b) {
		if (this == b)
			return true;
		if (!(b instanceof Card))
			return false;
		Card card = (Card) b;
		return (card.suit.equals(suit) && card.name.equals(name));	
	}
	
	@Override
	public int hashCode () {
		int result = name.hashCode();
		result = 31 * result + suit.getSuit() - 2659;
		return result;
	}
	/**
	 * Used to sort card.  Uses the value of the card, Aces are 1, Kings are 13.  
	 * Suits are in the following order and are only considered if the face value 
	 * is the same: Heart, Diamond, Spade, Club 
	 */
	
	public int compareTo (Object b) {

		if (b == null || this == null)
			return 0;
		if (!(b instanceof Card))
			return 0;
		Card bCard = (Card) b;
		int lhs = getNameValue ();
		int rhs = bCard.getNameValue();
		if (lhs == rhs) {
			return (this.suit.getSuit() < bCard.suit.getSuit()) ? -1 : 
					(this.suit.getSuit() == bCard.suit.getSuit()) ? 0 : 1;
		}
		return (lhs < rhs) ?  -1 : (lhs == rhs) ? 0 : 1;
	}
	
}