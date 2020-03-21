package Cribbage;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.WindowConstants;


/**
 * @author Tim Reed 12/2019
 * Creates fundamentals of a GUI for the calculators to use.  
 *
 */
public class BaseGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private String mode;
	private DiscardGUI discardGUI;
	static Cribbage cribbage;
	private GridBagLayout gridBagLayout;

	/**
	 * Constructor
	 * @param crib = The Cribbage reference
	 */
    
	public BaseGUI (Cribbage crib) {
		this (crib, "Manual");
	}
	
	/**
	 * Constructor
	 * @param crib = The Cribbage reference.
	 * @param mo = String for the mode of operation. "Random" or "Manual"
	 */

	public BaseGUI(Cribbage crib, String mo) {
		super ("Cribbage");
		cribbage = crib;
		mode =  mo;
		initialize();
		if (mo.equals("Random")) {
			discardGUI = new RandomMode(cribbage, this);
		}
		else {
			discardGUI = new ManualMode (cribbage, this);
		}

	}
	
	public void initialize () {
		
		
		setVisible (true);
		setBounds(100, 100, 514, 361);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {99, 30, 47, 0, 68, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
	}
	
	/**
	 * Changes the mode of operation to either Manual or Random
	 * @param newMode - the new mode of operation.  String is either "Manual" or "Random".  Does not do anything if current mode is selected.
	 */
	public void changeMode (String newMode) {
		if (!newMode.equals(mode)) {
			
			getContentPane().removeAll();
			
			switch (newMode) {
			case "Manual":
				mode = "Manual";
				discardGUI = new ManualMode (cribbage, this);
				break;
			case "Random":
				mode = "Random";
				discardGUI = new RandomMode (cribbage, this);
				break;
			default:
				System.err.println("Switch Statement");
			}
			
			revalidate ();
			repaint ();
		}
	}
	
	public String getMode () {return mode;}
	
	
}
