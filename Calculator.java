import java.util.List;
import java.util.ArrayList;


/**
 * @author Tim Reed 12/2019
 * Base class for point calculators in cribbage.
 *
 */
public class Calculator implements CribbageCalc{

	protected int points;			// Stores points to be calculated
	protected List <Card> theHand;	// The hand of cards used for calculationsd
	protected int numCards;			// Number of cards in the hand.
	
	/**
	 * Constructor.  Creates a copy of the hand passed into as parameter for use in calculations.
	 * @param hand = Hand of cards to be used in calculations.
	 */
	Calculator (List <Card>hand){
		theHand = new ArrayList <Card> (hand);
		numCards = theHand.size();
		points = 0;
	}
	
	/**
	 * @return points that have been calculated.
	 */
	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void calculate() {
	}

}
