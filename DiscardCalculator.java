import java.util.ArrayList;
import java.util.List;


public class DiscardCalculator extends Calculator {

	private final int discards;
	private final boolean owncrib;
	private int remainScore;
	private int cribScore;
	
	private ArrayList<Integer> discardList;
	private ArrayList<Integer> remainingList;
	
	public DiscardCalculator (List <Card> in) {
		this (in, false, false, 2);
	}
	public DiscardCalculator (List <Card> in, boolean flu, boolean crib, int disc) {
		
		super (in);
		owncrib = crib;
		discards = disc;
		discardList = new ArrayList <Integer> (discards);
		remainingList = new ArrayList <Integer> (numCards - discards);
		points = new PointsCalculator (theHand, flu).getPoints();
		/** initialize the lists.
		 * For a two player game, disc_arr and currentBest will be {0, 1}, and hand will be {2, 3, 4, 5}
		 * This is the starting position for calculating the best hand
		 */
		for (int i = 0; i < numCards; ++i) {
			
			if (i < discards) {
				discardList.add(i);
				
			}
			if (i >= discards) {
				remainingList.add(i);
				
			}
		} // end list initialization	
	} // end constructor

	private int convertAndCalculate (List <Integer>cardList) {
		List <Card> toConvert = new ArrayList <Card> (cardList.size());
		for (int i = 0; i < cardList.size(); ++i) {
			toConvert.add(theHand.get((int) cardList.get(i)));
		}
		return new PointsCalculator (toConvert).getPoints();
	}
	
	// Internal function to adjust an array for backtracking calculations. Returns true if increment is possible, returns false if no more possibilities of 
	// incrementing ( the last permutation).
	private boolean step () {
		
		int i = discardList.size() - 1;
		discardList.set(i, discardList.get(i) + 1);
		
		
		if (discardList.get(i) == numCards) {
			if (i <= 0) {
				return false;
			}
			while (i > 0 && discardList.get(i) == numCards) {
				discardList.set(i - 1,  discardList.get(i-1) + 1);
				discardList.set(i, discardList.get(i - 1) + 1);
				--i;
			}			
		}
		
		// If unable to stay < numCards, return false;
		if (discardList.get(discardList.size() - 1) >= numCards) {
			return false;
		}
		else {
			//Rebuild hand array after discards changed.
			int in_discCount = 0;
			for (i = 0; i < numCards; ++i) {
				boolean in_disc = false;
				for (int j = 0; j < discardList.size (); ++j){
					if (discardList.get(j) == i) {
						++in_discCount;
						in_disc = true;
					}
				}
				if (!in_disc) {
					remainingList.set(i - in_discCount, i);
				}
			}
			return true;
		}
	}
	
	// returns a list of the discards.  The last three spots are for the points before, points after, and points to the crib.
	public ArrayList<Integer> getDiscards () {
		
		// used to track the best hand so far when calculating discards
		ArrayList <Integer> currentBestDisc = new ArrayList <Integer> (discardList);
		int currentBestScore = 0;

		boolean more = true;
		
		while (more) {
			
			int t_scoreRemaining = convertAndCalculate (remainingList);
			int t_scoreToCrib = convertAndCalculate (discardList);
			int currentScore = (owncrib) ? t_scoreRemaining + t_scoreToCrib : t_scoreRemaining - t_scoreToCrib;
			
			if (currentScore > currentBestScore) {
				currentBestScore = currentScore;
				remainScore = t_scoreRemaining;
				cribScore = t_scoreToCrib;
				currentBestDisc.clear();
				// copy discardList to currentBestDisc
				for (Integer i : discardList) {
					currentBestDisc.add(i);
				}
			
			}
			more = step ();
		} // end while loop
		
		currentBestDisc.add(points);
		currentBestDisc.add(remainScore);
		currentBestDisc.add(cribScore);
	
		return currentBestDisc;
	}
}
