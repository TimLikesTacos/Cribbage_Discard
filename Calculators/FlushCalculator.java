package Calculators;
import java.util.List;

import Cribbage.Card;

/**
 * 
 */

/**
 * @author Tim Reed 12/2019
 * This calculates the points from a flush.
 *
 */
public class FlushCalculator extends Calculator {
	
	private final int POINTS_FOR_FLUSH = 1;
	private final int NEEDED_NUMBER_FOR_FLUSH = 4;
	private int cardsInFlush=0;
	
	/**
	 * Constructor.  Note that points are automatically calculated when instantiated.  Use Calculator's getPoints() to get the amount of points.
	 * @param hand = the set of cards that is going to be used to calculate the number of fifteens
	 */
	public FlushCalculator (List <Card> hand) {
		super(hand);
		calculate ();
	}
	
	public void calculate () {
		/* Calculate points from flush.  Standard is 1 point per card, min 4 cards. */

		for (int i = 0; i <= numCards - NEEDED_NUMBER_FOR_FLUSH; ++i) {
			Card.Suit theSuit = theHand.get(i).getSuit();
			for (int j = i; j < numCards; ++j) {
				if (theSuit.equals(theHand.get(j).getSuit())) {
					++cardsInFlush;
				}
			}
			// if didn't get the needed numbers, set the number to zero.
			if (cardsInFlush < NEEDED_NUMBER_FOR_FLUSH) {
				cardsInFlush = 0;
			}
			// if got the neededNumber, there will not be another flush, so break the loop
			else
				break;
		}
		//System.err.println("Flush: " + pointsForFlush * cardsInFlush);
		points = POINTS_FOR_FLUSH * cardsInFlush;
	}
}
