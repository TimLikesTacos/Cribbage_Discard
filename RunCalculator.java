import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Tim Reed 12/2019
 * This class calculates the amount of points in a run.
 *
 */
public class RunCalculator extends Calculator {

	final int NUMBER_NEEDED = 3; //standard rule for cribbage.
	final int POINTS_PER_CARD = 1;
	
	int numberInRun = 1;
	int secondRun = 0;
	int ofAKindInRun = 1;
	int ofAKindSecondRun = 1;
	
	/**
	 * Constructor.  Note that points are automatically calculated when instantiated.  Use Calculator's getPoints() to get the amount of points.
	 * @param hand = the set of cards that is going to be used to calculate the number of fifteens
	 */
	public RunCalculator (List <Card> hand) {
		super(hand);
		calculate ();
	}
	
	/*  This function works on the premises that the only time possible to have more than one
	 * separate run is when you have 6 cards, which occurs before discarding into the crib for 
	 * some player modes.  If there are two separate runs, the max per run is 3. I.e. 2, 3, 4 and
	 * 10, J, Q.  The separateRun variable will keep track of this. If a double run occurs, such as 2, 3, 3, 4, then the numberInRun variable will 
	 * maintain the value.
	 */
	
	public void calculate () {
	
		// make a copy and sort the hand for easy checking of a run.
		List <Card> sorted = new ArrayList<Card> (theHand);
		Collections.sort(sorted);
		int val = sorted.get(0).getNameValue();

		for (int i = 1; i < numCards; ++i) {
			int tester = sorted.get(i).getNameValue ();
			
			// If run in progress, update the numberInRun
			if (tester == val + 1) {
				++numberInRun;
			}
			
			// This else statement is if the two cards face values are equal to each other.
			// This will keep the run going, and will increase the run amount by pairsInRun. i.e, 3, 4, 4, 4, 5, is
			// three runs of length three.  This will result in pairsInRun == 3, and numberInRun = 3.  
			// Total points should be 9.
			else if (tester == val) {
				++ofAKindInRun;
			}
			// If run not in progress and numberNeeded is not met, reset numberInRun
			else if (numberInRun < NUMBER_NEEDED){
				numberInRun = 1;
				ofAKindInRun = 1;
			}

			// numberNeeded has been met. Stores the amount into separate Run and continues.
			else if (secondRun == 0) {
				secondRun = numberInRun;
				numberInRun = 1;
				ofAKindSecondRun = ofAKindInRun;
				ofAKindInRun = 1;
			}
			val = tester;
		}

		// clear the value if the needed values are not met.
		if (numberInRun < NUMBER_NEEDED) {
			numberInRun = 0;
			ofAKindInRun = 1;
		}
		points = (secondRun * ofAKindSecondRun + numberInRun * ofAKindInRun) * POINTS_PER_CARD;
	}

}
