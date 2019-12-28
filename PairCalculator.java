import java.util.List;

/**
 * 
 */

/**
 * @author Tim Reed 12/2019
 * This calculates the points for the amount of pairs in the hand.
 */
public class PairCalculator extends Calculator {

	private final int POINTS_FOR_PAIR = 2;
	private final int HIGHEST_CARD_INDEX = 13; // value that represents a king.  This will be used to determine pairs
	
	/**
	 * Constructor.  Note that points are automatically calculated when instantiated.  Use Calculator's getPoints() to get the amount of points.
	 * @param hand = the set of cards that is going to be used to calculate the number of fifteens
	 */
	public PairCalculator (List <Card> hand) {
		super (hand);
		calculate ();
	}
	
	public void calculate () {
		

		
		int [] ofAKind = new int [HIGHEST_CARD_INDEX+1];
		
		// simply goes through the hand, adding one to the index of the card.  Letters are turned into numbers. i.e. Q == 12.
		for (Card c : theHand) {
			++ofAKind[c.getNameValue()];
		}
		// goes throught the ofAKind array to find pairs
		for (int i = 0; i < ofAKind.length; ++i) {
			if (ofAKind [i] < 2)
				continue;
			// 2 of a kind = 1 pair
			// 3 of a kind = 3 pair
			// 4 of a kind = 6 pair
			// This is calculated by adding n-1 for the number of pairs, i.e. 4 of a kind = (2 - 1) + (3 - 1) + (4 - 1)
			int pair = 1;
			// loop that calculates the amount of pairs
			for (int j = 2; j <= ofAKind[i] - 1; ++j) {
				pair += j;
			}
			points += pair * POINTS_FOR_PAIR;
			
		}
	
	
	}
}
