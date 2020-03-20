package Cribbage;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is a subclass of Cribbage.  It implements specific rules for two player games.
 * @author Tim Reed 12/2019
 *
 */
public class TwoPlayerCrib extends Cribbage{

	private List <Card> opp; // Adds an additional List for the opponent's hand.
	
	/**
	 * Deals the cards to opponent and player based off two player rules
	 */
	@Override
	public void deal () {
	// if own crib, deal opponent first.
		int offset = 1;
		if (ownCrib)
		{
			offset = 0;
		}
		for (int i = offset; i < (cardsDelt * 2) + offset + deltCardstoCrib; ++i)
		{
			if (i % 2 == 1) // deal to opponent
			{
				opp.add(new Card (cards.removeFromTop()));
			}
			else // deal to hand
			{
				hand.add(new Card (cards.removeFromTop()));
			}
		}
	}
	
	/**
	 * Redeals the cards.  Creates a new deck of cards, shuffles them, and calls deal() method.
	 */
	@Override
	public void redeal () {
		opp.clear();
		hand.clear ();
		cards = new Deck ();
		cards.buildNormal();
		cards.shuffle();
		deal ();
	}
	
	/**
	 * Constructor.  Establishes the same items as the Cribbage class, and deals the cards according to the rules of the TwoPlayerCrib class.
	 */
	public TwoPlayerCrib (){ 
		
		super (2);
		opp = new ArrayList <Card> ();
		deal ();
	}
	
}
