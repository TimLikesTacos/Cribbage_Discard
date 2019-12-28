
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;


/**
 * @author Tim Reed 12/2019
 * RandomMode is the mode of operation of the Discard calculator that supplies random cards to the player.
 * Contains the GUI attributes for this.
 *
 */
public class RandomMode extends DiscardGUI {

	protected JTextField card1; // These textfields will display the card.
	protected JTextField card2;
	protected JTextField card3;
	protected JTextField card4;
	protected JTextField card5;
	protected JTextField card6;

	/**
	 * Constructor
	 * @param crib = The current cribbage object.
	 * @param from = The BaseGUI that created this discard GUI.
	 */
	public RandomMode (Cribbage crib, BaseGUI from) {
		super (crib, from);
		app.setTitle("Discard Calculator - Random Hand");
		mode = "Random";
		initialize ();
	}
	
	/**
	 * Initializes the GUI.  Also creates a Redeal button that redeals the cards and updates the GUI.
	 */
	protected void initialize () {
		super.initialize ();
		
		JButton btnRedeal = new JButton("Re-deal");
		btnRedeal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cribbage.redeal();
				repaint ();
			}
		});
		

//		for (int i = 0; i < cards.size(); ++i) {
//			
//		}
		GridBagConstraints gbc_btnRedeal = new GridBagConstraints();
		gbc_btnRedeal.anchor = GridBagConstraints.WEST;
		gbc_btnRedeal.insets = new Insets(0, 0, 0, 5);
		gbc_btnRedeal.gridx = 0;
		gbc_btnRedeal.gridy = 11;
		app.getContentPane().add(btnRedeal, gbc_btnRedeal);
		
		card1 = new JTextField();
		card1.setColumns(4);
		GridBagConstraints gbc_card1 = new GridBagConstraints();
		gbc_card1.fill = GridBagConstraints.HORIZONTAL;
		gbc_card1.anchor = GridBagConstraints.WEST;
		gbc_card1.insets = new Insets(0, 0, 5, 5);
		gbc_card1.gridx = 2;
		gbc_card1.gridy = 1;
		app.getContentPane().add(card1, gbc_card1);
		
		card2 = new JTextField();
		GridBagConstraints gbc_card2 = new GridBagConstraints();
		gbc_card2.fill = GridBagConstraints.HORIZONTAL;
		gbc_card2.anchor = GridBagConstraints.WEST;
		gbc_card2.insets = new Insets(0, 0, 5, 5);
		gbc_card2.gridx = 2;
		gbc_card2.gridy = 2;
		app.getContentPane().add(card2, gbc_card2);
		card2.setColumns(4);
		

		card3 = new JTextField();
		card3.setColumns(4);
		GridBagConstraints gbc_card3 = new GridBagConstraints();
		gbc_card3.fill = GridBagConstraints.HORIZONTAL;
		gbc_card3.anchor = GridBagConstraints.WEST;
		gbc_card3.insets = new Insets(0, 0, 5, 5);
		gbc_card3.gridx = 2;
		gbc_card3.gridy = 3;
		app.getContentPane().add(card3, gbc_card3);
		
		card4 = new JTextField ();
		card4.setColumns(4);
		GridBagConstraints gbc_card4 = new GridBagConstraints();
		gbc_card4.fill = GridBagConstraints.HORIZONTAL;
		gbc_card4.anchor = GridBagConstraints.WEST;
		gbc_card4.insets = new Insets(0, 0, 5, 5);
		gbc_card4.gridx = 2;
		gbc_card4.gridy = 4;
		app.getContentPane().add(card4, gbc_card4);
		
		card5 = new JTextField();
		card5.setColumns(4);
		GridBagConstraints gbc_card5 = new GridBagConstraints();
		gbc_card5.fill = GridBagConstraints.HORIZONTAL;
		gbc_card5.anchor = GridBagConstraints.WEST;
		gbc_card5.insets = new Insets(0, 0, 5, 5);
		gbc_card5.gridx = 2;
		gbc_card5.gridy = 5;
		app.getContentPane().add(card5, gbc_card5);
		
		card6 = new JTextField();
		card6.setColumns(4);
		GridBagConstraints gbc_card6 = new GridBagConstraints();
		gbc_card6.fill = GridBagConstraints.HORIZONTAL;
		gbc_card6.anchor = GridBagConstraints.WEST;
		gbc_card6.insets = new Insets(0, 0, 5, 5);
		gbc_card6.gridx = 2;
		gbc_card6.gridy = 6;
		app.getContentPane().add(card6, gbc_card6);
		app.repaint ();
		repaint ();
		
	}

	public void repaint () {
		super.repaint ();
		card1.setText(cribbage.getCard(0).toString()); // Updates the textfield with the card
		card2.setText(cribbage.getCard(1).toString());
		card3.setText(cribbage.getCard(2).toString());
		card4.setText(cribbage.getCard(3).toString());
		card5.setText(cribbage.getCard(4).toString());
		card6.setText(cribbage.getCard(5).toString());
	}
	
}
