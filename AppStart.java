import java.awt.EventQueue;


/**
 * @author Tim Reed 
 * 12/2019 - This program creates a GUI to represent a typical two player cribbage hand.  Six cards are delt, either randomly from a deck of cards
 * or manually selected.  It will automatically calculate the amount of points in the hand of 6, and figure the best 2 cards to discard into the crib
 * based off of maximizing the points.  It was intended that most of the classes can be used without changes to support both a cribbage game and also
 * different rules for different amounts of players or optional point rules.
 *
 */
public class AppStart {

	private static Cribbage cribbage;
	
	public static void main (String[] args) {
		
		// At this stage, we will only be using the TwoPlayerCrib rules.
		cribbage = new TwoPlayerCrib ();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new BaseGUI(cribbage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
}
