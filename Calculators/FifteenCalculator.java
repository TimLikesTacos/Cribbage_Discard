package Calculators;
import java.util.ArrayList;
import java.util.List;

import Cribbage.Card;


/**
 * @author Tim Reed 12/2019
 * This class calculates the points for fifteens in the cribbage hand.
 */
public class FifteenCalculator extends Calculator {

	private final int FIFTEEN = 15;
	private final int POINTSFORFIFTEEN = 2;
	private int numberOfFifteens;
	
	/* Below is a list of Integers that represent cards used in the calculation for 15.  
	 * There can be any amount from zero to all the cards in the hand in this list.
	 */
	private List <Integer> involvedCards; 
	
	/**
	 * Constructor.  Note that points are automatically calculated when instantiated.  Use Calculator's getPoints() to get the amount of points.
	 * @param hand = the set of cards that is going to be used to calculate the number of fifteens
	 */
	public FifteenCalculator (List <Card> hand) {
	
		super (hand);
		involvedCards = new ArrayList <Integer> (hand.size());
		calculate ();
	}
	
	@Override
	public void calculate() {
		
		
		if (theHand.size() ==0)
			return; // prevents accessing array out of bounds.  no need to calculate points
		// ensure invovledCards is empty
		if (!involvedCards.isEmpty()) {
			involvedCards.clear();
		}

		int sum = 0;		

		// Backtrack to calculate 15s.
		involvedCards.add(0);
		while (!involvedCards.isEmpty()) {

			sum = total ();

			if (sum == FIFTEEN) {
				++numberOfFifteens;
				nextIteration();
			}
			else if (sum > FIFTEEN) {
				nextIteration();
			}
			else {
				// add next cards to the count
				int last = involvedCards.get(involvedCards.size() -1);
				if (last == numCards-1) {
					nextIteration ();
				}
				else {
					involvedCards.add(last + 1);
				}
			}
		}
		points =  numberOfFifteens * POINTSFORFIFTEEN;
	}

	private int total () {
		int sum = 0;
		for (int i = 0; i < involvedCards.size(); ++i)
		{
			sum = sum + getNumericValue(theHand.get(involvedCards.get(i)));
		}
		return sum;
	}
	// internal method to increment or trim the last item in involvedCards
	private void nextIteration () {

		int last = involvedCards.size()-1;

		// Increment value in last position by one.
		involvedCards.set(last, involvedCards.get(last) + 1);
		while (!involvedCards.isEmpty() && involvedCards.get(last) == numCards) {
			// if last cards is equal to the max  number of cards, remove the last card and incrememnt the previous
			involvedCards.remove(last);
			if (involvedCards.isEmpty()) {
				continue;
			}
			last = involvedCards.size() -1; // get the new size, which is one smaller than previously.
			involvedCards.set(last, involvedCards.get(last) + 1); //Increment last card.  Repeat the while loop if needed.
		}

	}
	
	//internal method to get the numeric value for each card
	private int getNumericValue (Card c) {
		int value;
		try {
			 value = Integer.parseInt(c.getName());
		}
		catch (Exception e){ //parse will throw exception if not an integer character, i.e. facecards
			if (c.getName().equals("A"))
				value = 1; // aces are worth one
			else
				value = 10; //J, Q, K are 10.
		}
		return value;
	}
	
}
