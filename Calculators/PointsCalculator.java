package Calculators;
import java.util.List;

import Cribbage.Card;



/**
 * @author Tim Reed 12/2019
 * This class is main points calculator for any hand.  Input to the constructor is the hand in which you want points to be calculated
 * It calls other calculators to calculate specific parameters.
 */
public class PointsCalculator extends Calculator {

	private final boolean flush; // True if flush is being used and should be calculated in the points
	
	/**
	 * Constructor.  
	 * @param incoming = List for the hand that is to be calculated, whether it is the hand prior to discard, the hand after discard, the hand after incorporating the cut card
	 * or the crib.
	 */
	public PointsCalculator (List <Card> incoming){
		this (incoming, false); // default flush is false
	}
	
	/**
	 * Constructor.  Note that the points is automatically calculated when instantiated. Use calculator's getPoints () to get the amount of points.
	 * @param incoming = List for the hand that is to be calculated, whether it is the hand prior to discard, the hand after discard, the hand after incorporating the cut card
	 * or the crib.
	 * @param flu = true if flush is to used in points calculation, false otherwise.
	 */
	public PointsCalculator (List <Card> incoming, boolean flu) {
		super (incoming);
		flush = flu;
		calculate ();
	}
	
	/**
	 * Calculates the points.
	 */
	public void calculate () {
		
		points += new FifteenCalculator (theHand).getPoints (); // Fifteens
		if (flush) {
			points += new FlushCalculator (theHand).getPoints (); // Flush
		}
		points += new PairCalculator (theHand).getPoints (); // Pairs
		points += new RunCalculator (theHand).getPoints(); // Runs
	}


	
	


	
	
	


	
	
	
}
