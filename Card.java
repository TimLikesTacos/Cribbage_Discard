

/**
 * This is the Card class.  It represents a single card in a standard playing deck.
 * @author Tim Reed 12/2019
 *
 */

public class Card implements Comparable <Card>{
	
	protected Suit suit;	// suit of the card. Heart, Diamond, Spade, Club.
	protected String name;	// The actual character on the card, A, 2, 3,...., 10, J, Q, K.
	protected int value;	// Value of the cards.  Aces are 1, facecards are 10.
	
	/**
	 * Constructor
	 * @param suit2 = suit of the card
	 * @param n = name of the card
	 * @param v = value of the card points-wise.
	 */

	Card (Suit suit2, String n, int v) {
		suit = suit2;
		name = n;
		value = v;
	}

	/**
	 * Construtor
	 * @param c = Card to create.
	 */
	Card (Card c){
		suit = c.suit;
		name = c.name;
		value = c.value;
	}
	
	/**
	 * Default constructor.  Creates Ace of Hearts card with -1 value if no parameters are given
	 */
	Card () { this(Suit.HEART, "A", -1);}

	/**
	 * Outputs the card Suit and Face value.  Used for debugging.
	 */
	public void print () {
		System.out.format("Card Suit: %s, Card Face: %s%n", suit, name);
	}
	
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
	enum Suit  {
		// values are unicode symbols.  Hearts and diamonds are white, others are black
		HEART (0x2661),
		DIAMOND (0x2662),
		SPADE (0x2660),
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
	 * @return the value of the card. 1-10.  Facecards are 10, aces are 1.
	 */
	public int getValue () {
		return value;
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
		return converter (name);
	}

	// Internal method to support other methods.
	private int converter (String n) {
		switch (n) {
		case "A":
			return 1;
		case "J":
			return 11;
		case "Q":
			return 12;
		case "K":
			return 13;
		default:
			return Integer.parseInt(n);
		}
	}

	/**
	 * Compares this to another Card.
	 * @param Card to compare this to.
	 * @return -1 if this is less than parameter, 0 if equal, 1 if greater than.
	 */
	@Override
	public int compareTo (Card b) {

		if (b == null || this == null)
			return 0;
		int lhs = converter (getName());
		int rhs = converter (b.getName());
		return (lhs < rhs) ?  -1 : (lhs == rhs) ? 0 : 1;
	}
	
}