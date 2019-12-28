

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;


/**
 * @author Tim Reed
 * Establishes the fundamentals for a discard GUI.  This is a superclass and either RandomMode or ManualMode should be called to calculate discards.
 *
 */
public class DiscardGUI {
	
	
	private JCheckBox discard1; // Check boxes in these will indicate what is the preferred discard.
	private JCheckBox discard2;
	private JCheckBox discard3;
	private JCheckBox discard4;
	private JCheckBox discard5;
	private JCheckBox discard6;
	private JCheckBox ownCribBox; // Checked if it is the player's own crib.

	private JTextField score;
	private JLabel remainingPointsLabel;
	private JLabel toCribPointsLabel;
	private JTextField remainingScore; // points remaining in hand after discard
	private JTextField toCribScore; // points in the cards given to the crib
	private JCheckBox flush; // checked if playing with flush 
	
	// Menu options to change the mode of operation
	private JMenuBar menuBar;
	private JMenu mnMode;
	private JMenuItem mntmRandom;
	private JMenuItem mntmManualEntry;
	
	protected String mode; // String which contains the current mode of operation (Random, Manual)
	protected Cribbage cribbage; // Reference to the cribbage object
	protected BaseGUI app; // The main app that started the GUI.
	
	/**
	 * Constructor
	 * @param crib = the Cribbage object
	 * @param from = the BaseGUI that created this.
	 */
	public DiscardGUI (Cribbage crib, BaseGUI from) {
		cribbage = crib;
		app = from;
		initialize ();
	}
	
	
	public void repaint ()
	{
		// Get the discards from the discard calculator.  Note that the last three elements on this list are scores.
		List <Integer>discards = cribbage.getBestDiscard (); 
	    toCribScore.setText("" + discards.remove(discards.size () - 1));
		remainingScore.setText("" + discards.remove(discards.size () - 1));
		score.setText("" + discards.remove(discards.size () - 1));

		// Remaining elements on discards list are the cards to be discarded
		ownCribBox.setSelected(cribbage.getCrib());	
		flush.setSelected(cribbage.getFlush());
		
		// set discard boxes
		discard1.setSelected(inList (discards, 0));
		discard2.setSelected(inList (discards, 1));
		discard3.setSelected(inList (discards, 2));
		discard4.setSelected(inList (discards, 3));
		discard5.setSelected(inList (discards, 4));
		discard6.setSelected(inList (discards, 5));
	}
	
	// Internal method used to see if the card is in the list to be discarded.
	private boolean inList (List <Integer> list, int num) {
		for (int i = 0; i < list.size(); ++i) {
			if (num == (int)list.get(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {

		ownCribBox = new JCheckBox("Your crib");
		ownCribBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cribbage.swapCrib();
				repaint ();
				
			}
		});
		discard1 = new JCheckBox("");
		GridBagConstraints gbc_dicard1 = new GridBagConstraints();
		gbc_dicard1.insets = new Insets(0, 0, 5, 5);
		gbc_dicard1.gridx = 3;
		gbc_dicard1.gridy = 1;
		app.getContentPane().add(discard1, gbc_dicard1);

		discard2 = new JCheckBox("");
		GridBagConstraints gbc_discard2 = new GridBagConstraints();
		gbc_discard2.insets = new Insets(0, 0, 5, 5);
		gbc_discard2.gridx = 3;
		gbc_discard2.gridy = 2;
		app.getContentPane().add(discard2, gbc_discard2);
		
		discard3 = new JCheckBox("");
		GridBagConstraints gbc_discard3 = new GridBagConstraints();
		gbc_discard3.insets = new Insets(0, 0, 5, 5);
		gbc_discard3.gridx = 3;
		gbc_discard3.gridy = 3;
		app.getContentPane().add(discard3, gbc_discard3);

		
		discard4 = new JCheckBox("");
		GridBagConstraints gbc_discard4 = new GridBagConstraints();
		gbc_discard4.insets = new Insets(0, 0, 5, 5);
		gbc_discard4.gridx = 3;
		gbc_discard4.gridy = 4;
		app.getContentPane().add(discard4, gbc_discard4);

		discard5 = new JCheckBox("");
		GridBagConstraints gbc_discard5 = new GridBagConstraints();
		gbc_discard5.insets = new Insets(0, 0, 5, 5);
		gbc_discard5.gridx = 3;
		gbc_discard5.gridy = 5;
		app.getContentPane().add(discard5, gbc_discard5);
		


		JLabel lblCard = new JLabel("Card #1");
		GridBagConstraints gbc_lblCard = new GridBagConstraints();
		gbc_lblCard.anchor = GridBagConstraints.WEST;
		gbc_lblCard.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard.gridx = 0;
		gbc_lblCard.gridy = 1;
		app.getContentPane().add(lblCard, gbc_lblCard);
		
		JLabel lblCard_1 = new JLabel("Card #2");
		GridBagConstraints gbc_lblCard_1 = new GridBagConstraints();
		gbc_lblCard_1.anchor = GridBagConstraints.WEST;
		gbc_lblCard_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_1.gridx = 0;
		gbc_lblCard_1.gridy = 2;
		app.getContentPane().add(lblCard_1, gbc_lblCard_1);
	
		JLabel lblCard_5 = new JLabel("Card #3");
		GridBagConstraints gbc_lblCard_5 = new GridBagConstraints();
		gbc_lblCard_5.anchor = GridBagConstraints.WEST;
		gbc_lblCard_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_5.gridx = 0;
		gbc_lblCard_5.gridy = 3;
		app.getContentPane().add(lblCard_5, gbc_lblCard_5);
		
		JLabel lblCard_4 = new JLabel("Card #4");
		GridBagConstraints gbc_lblCard_4 = new GridBagConstraints();
		gbc_lblCard_4.anchor = GridBagConstraints.WEST;
		gbc_lblCard_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_4.gridx = 0;
		gbc_lblCard_4.gridy = 4;
		app.getContentPane().add(lblCard_4, gbc_lblCard_4);
		
		JLabel lblCard_3 = new JLabel("Card #5");
		GridBagConstraints gbc_lblCard_3 = new GridBagConstraints();
		gbc_lblCard_3.anchor = GridBagConstraints.WEST;
		gbc_lblCard_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_3.gridx = 0;
		gbc_lblCard_3.gridy = 5;
		app.getContentPane().add(lblCard_3, gbc_lblCard_3);
		
		JLabel lblCard_2 = new JLabel("Card #6");
		GridBagConstraints gbc_lblCard_2 = new GridBagConstraints();
		gbc_lblCard_2.anchor = GridBagConstraints.WEST;
		gbc_lblCard_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_2.gridx = 0;
		gbc_lblCard_2.gridy = 6;
		app.getContentPane().add(lblCard_2, gbc_lblCard_2);
		
		discard6 = new JCheckBox("");
		GridBagConstraints gbc_discard6 = new GridBagConstraints();
		gbc_discard6.insets = new Insets(0, 0, 5, 5);
		gbc_discard6.gridx = 3;
		gbc_discard6.gridy = 6;
		app.getContentPane().add(discard6, gbc_discard6);


		GridBagConstraints gbc_ownCribBox = new GridBagConstraints();
		gbc_ownCribBox.anchor = GridBagConstraints.WEST;
		gbc_ownCribBox.insets = new Insets(0, 0, 5, 5);
		gbc_ownCribBox.gridx = 0;
		gbc_ownCribBox.gridy = 7;
		app.getContentPane().add(ownCribBox, gbc_ownCribBox);
		
		flush = new JCheckBox("Flush");
		GridBagConstraints gbc_flush = new GridBagConstraints();
		gbc_flush.anchor = GridBagConstraints.WEST;
		gbc_flush.insets = new Insets(0, 0, 5, 5);
		gbc_flush.gridx = 2;
		gbc_flush.gridy = 7;
		app.getContentPane().add(flush, gbc_flush);
		flush.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				cribbage.swapFlush();
				repaint ();
			}
		});
		
		
		JLabel lblCurrentPoints = new JLabel("Current Points:");
		GridBagConstraints gbc_lblCurrentPoints = new GridBagConstraints();
		gbc_lblCurrentPoints.anchor = GridBagConstraints.WEST;
		gbc_lblCurrentPoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentPoints.gridx = 0;
		gbc_lblCurrentPoints.gridy = 8;
		app.getContentPane().add(lblCurrentPoints, gbc_lblCurrentPoints);
		
		score = new JTextField();
		GridBagConstraints gbc_score = new GridBagConstraints();
		gbc_score.fill = GridBagConstraints.HORIZONTAL;
		gbc_score.anchor = GridBagConstraints.WEST;
		gbc_score.insets = new Insets(0, 0, 5, 5);
		gbc_score.gridx = 2;
		gbc_score.gridy = 8;
		app.getContentPane().add(score, gbc_score);
		score.setColumns(4);
		
		remainingPointsLabel = new JLabel("Remaining Points: ");
		GridBagConstraints gbc_remainingPointsLabel = new GridBagConstraints();
		gbc_remainingPointsLabel.anchor = GridBagConstraints.WEST;
		gbc_remainingPointsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_remainingPointsLabel.gridx = 0;
		gbc_remainingPointsLabel.gridy = 9;
		app.getContentPane().add(remainingPointsLabel, gbc_remainingPointsLabel);
		
		remainingScore = new JTextField();
		GridBagConstraints gbc_remainingScore = new GridBagConstraints();
		gbc_remainingScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_remainingScore.anchor = GridBagConstraints.WEST;
		gbc_remainingScore.insets = new Insets(0, 0, 5, 5);
		gbc_remainingScore.gridx = 2;
		gbc_remainingScore.gridy = 9;
		app.getContentPane().add(remainingScore, gbc_remainingScore);
		remainingScore.setColumns(4);
		
		toCribPointsLabel = new JLabel("Points to the crib:");
		GridBagConstraints gbc_toCribPointsLabel = new GridBagConstraints();
		gbc_toCribPointsLabel.anchor = GridBagConstraints.WEST;
		gbc_toCribPointsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_toCribPointsLabel.gridx = 0;
		gbc_toCribPointsLabel.gridy = 10;
		app.getContentPane().add(toCribPointsLabel, gbc_toCribPointsLabel);
		
		toCribScore = new JTextField();
		GridBagConstraints gbc_toCribScore = new GridBagConstraints();
		gbc_toCribScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_toCribScore.anchor = GridBagConstraints.SOUTHWEST;
		gbc_toCribScore.insets = new Insets(0, 0, 5, 5);
		gbc_toCribScore.gridx = 2;
		gbc_toCribScore.gridy = 10;
		app.getContentPane().add(toCribScore, gbc_toCribScore);
		toCribScore.setColumns(4);
	
		menuBar = new JMenuBar();
		app.setJMenuBar(menuBar);
		
		mnMode = new JMenu("Mode");
		menuBar.add(mnMode);
		
		mntmManualEntry = new JMenuItem("Manual Entry");
		mnMode.add(mntmManualEntry);
		mntmManualEntry.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
					app.changeMode ("Manual");
			}
		});
		
		mntmRandom = new JMenuItem("Random");
		mnMode.add(mntmRandom);
		mntmRandom.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				app.changeMode("Random");
			}
		});
	
		
	}
}
