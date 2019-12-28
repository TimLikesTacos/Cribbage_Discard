
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;


/**
 * @author Tim Reed 12/2019
 * This class creates a GUI for manual entry of cards to see the recommended discards into the crib.
 */
public class ManualMode extends DiscardGUI {
	
	private JComboBox <String> card1Box; //Drop down box to select the card.
	private JComboBox <String> card2Box;
	private JComboBox <String> card3Box;
	private JComboBox <String> card4Box;
	private JComboBox <String> card5Box;
	private JComboBox <String> card6Box;

	/**
	 * Constructor
	 * @param crib = The Cribbage object
	 * @param from = The BaseGUI that created this discardGUI
	 * @wbp.parser.entryPoint
	 */
	public ManualMode (Cribbage crib, BaseGUI from) {
		super (crib, from);
		mode = "Manual";
	}
	
	protected void initialize () {
		super.initialize ();
		app.setTitle ("Discard Calculator - Manual Entry");
		
		// This will be used to create the menu to select cards.
		Deck cards = new Deck ();
		String [] cardString = new String [cards.size()];
		for (int i = 0; i < cards.size(); ++i) {
			cardString [i] = cards.at(i).toString();
			
		}
		
		card1Box = new JComboBox<>(cardString);
		card1Box.setSelectedIndex(0);
		cribbage.setHand(0, cards.at(card1Box.getSelectedIndex()));
		GridBagConstraints gbc_card1Box = new GridBagConstraints();
		gbc_card1Box.insets = new Insets(0, 0, 5, 5);
		gbc_card1Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_card1Box.gridx = 4;
		gbc_card1Box.gridy = 1;
		app.getContentPane().add(card1Box, gbc_card1Box);
	
		card1Box.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
			cribbage.setHand(0, cards.at(card1Box.getSelectedIndex()));
			repaint ();
			}
		});
		
		card2Box = new JComboBox<>(cardString);
		card2Box.setSelectedIndex(1);
		cribbage.setHand(1, cards.at(card2Box.getSelectedIndex()));
		GridBagConstraints gbc_card2Box = new GridBagConstraints();
		gbc_card2Box.insets = new Insets(0, 0, 5, 5);
		gbc_card2Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_card2Box.gridx = 4;
		gbc_card2Box.gridy = 2;
		app.getContentPane().add(card2Box, gbc_card2Box);
		
		card2Box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cribbage.setHand(1, cards.at(card2Box.getSelectedIndex()));
				repaint ();
				
			}
		});
		
		card3Box = new JComboBox<>(cardString);
		card3Box.setSelectedIndex(2);
		cribbage.setHand(2, cards.at(card3Box.getSelectedIndex()));
		GridBagConstraints gbc_card3Box = new GridBagConstraints();
		gbc_card3Box.insets = new Insets(0, 0, 5, 5);
		gbc_card3Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_card3Box.gridx = 4;
		gbc_card3Box.gridy = 3;
		app.getContentPane().add(card3Box, gbc_card3Box);
		
		card3Box.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
			cribbage.setHand(2, cards.at(card3Box.getSelectedIndex()));
			repaint ();
			}
		});
		
		card4Box = new JComboBox<>(cardString);
		card4Box.setSelectedIndex(3);
		cribbage.setHand(3, cards.at(card4Box.getSelectedIndex()));
		GridBagConstraints gbc_card4Box = new GridBagConstraints();
		gbc_card4Box.insets = new Insets(0, 0, 5, 5);
		gbc_card4Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_card4Box.gridx = 4;
		gbc_card4Box.gridy = 4;
		app.getContentPane().add(card4Box, gbc_card4Box);
		
		card4Box.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
			cribbage.setHand(3, cards.at(card4Box.getSelectedIndex()));
			repaint ();
			}
		});
		
		card5Box = new JComboBox<>(cardString);
		card5Box.setSelectedIndex(4);
		cribbage.setHand(4, cards.at(card5Box.getSelectedIndex()));
		GridBagConstraints gbc_card5Box = new GridBagConstraints();
		gbc_card5Box.insets = new Insets(0, 0, 5, 5);
		gbc_card5Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_card5Box.gridx = 4;
		gbc_card5Box.gridy = 5;
		app.getContentPane().add(card5Box, gbc_card5Box);
		
		card5Box.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
			cribbage.setHand(4, cards.at(card5Box.getSelectedIndex()));
			repaint ();
			}
		});
		
		card6Box = new JComboBox<>(cardString);
		card6Box.setSelectedIndex(5);
		cribbage.setHand(5, cards.at(card6Box.getSelectedIndex()));
		GridBagConstraints gbc_card6Box = new GridBagConstraints();
		gbc_card6Box.insets = new Insets(0, 0, 5, 5);
		gbc_card6Box.fill = GridBagConstraints.HORIZONTAL;
		gbc_card6Box.gridx = 4;
		gbc_card6Box.gridy = 6;
		app.getContentPane().add(card6Box, gbc_card6Box);
		
		card6Box.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
			cribbage.setHand(5, cards.at(card6Box.getSelectedIndex()));
			repaint ();
			}
		});
		
		card1Box.setBackground(Color.WHITE);
		card2Box.setBackground(Color.WHITE);
		card3Box.setBackground(Color.WHITE);
		card4Box.setBackground(Color.WHITE);
		card5Box.setBackground(Color.WHITE);
		card6Box.setBackground(Color.WHITE);
	}
	
}
