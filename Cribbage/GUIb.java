package Cribbage;
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;


public class GUIb {

	private JFrame frame;
	private JTextField card1;
	private JTextField card2;
	private JTextField card3;
	private JTextField card4;
	private JTextField card5;
	private JTextField card6;
	private JCheckBox discard1;
	private JCheckBox discard2;
	private JCheckBox discard3;
	private JCheckBox discard4;
	private JCheckBox discard5;
	private JCheckBox discard6;
	private JCheckBox ownCribBox;
	private static Cribbage cribbage;
	private JTextField score;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
		cribbage = new TwoPlayerCrib ();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUIb window = new GUIb();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIb() {
		
		System.err.println("GUIb");
		initialize();
	}
	
	public void paint ()
	{
		List <Integer>discards = cribbage.getBestDiscard ();
		card1.setText(cribbage.getCard(0).toString());
		card2.setText(cribbage.getCard(1).toString());
		card3.setText(cribbage.getCard(2).toString());
		card4.setText(cribbage.getCard(3).toString());
		card5.setText(cribbage.getCard(4).toString());
		card6.setText(cribbage.getCard(5).toString());
		ownCribBox.setSelected(cribbage.getCrib());
		//score.setText(""+cribbage.getPointsInArray());	
		
		// set discard boxes
		discard1.setSelected(inList (discards, 0));
		discard2.setSelected(inList (discards, 1));
		discard3.setSelected(inList (discards, 2));
		discard4.setSelected(inList (discards, 3));
		discard5.setSelected(inList (discards, 4));
		discard6.setSelected(inList (discards, 5));
		

		
	}
	private boolean inList (List <Integer> list, int num) {
		for (int i = 0; i < list.size() - 2; ++i) {
			if (num == (int)list.get(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 361);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {128, 30, 0, 30, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		
		ownCribBox = new JCheckBox("Your crib");
		ownCribBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cribbage.swapCrib();
				paint ();
				
			}
		});
		card1 = new JTextField();
		GridBagConstraints gbc_card1 = new GridBagConstraints();
		gbc_card1.anchor = GridBagConstraints.WEST;
		gbc_card1.insets = new Insets(0, 0, 5, 5);
		gbc_card1.gridx = 2;
		gbc_card1.gridy = 1;
		frame.getContentPane().add(card1, gbc_card1);
		card1.setColumns(10);
		
		card2 = new JTextField();
		GridBagConstraints gbc_card2 = new GridBagConstraints();
		gbc_card2.anchor = GridBagConstraints.WEST;
		gbc_card2.insets = new Insets(0, 0, 5, 5);
		gbc_card2.gridx = 2;
		gbc_card2.gridy = 2;
		frame.getContentPane().add(card2, gbc_card2);
		card2.setColumns(10);
		
		card3 = new JTextField();
		card3.setColumns(10);
		GridBagConstraints gbc_card3 = new GridBagConstraints();
		gbc_card3.anchor = GridBagConstraints.WEST;
		gbc_card3.insets = new Insets(0, 0, 5, 5);
		gbc_card3.gridx = 2;
		gbc_card3.gridy = 3;
		frame.getContentPane().add(card3, gbc_card3);
		
		card4 = new JTextField();
		card4.setColumns(10);
		GridBagConstraints gbc_card4 = new GridBagConstraints();
		gbc_card4.anchor = GridBagConstraints.WEST;
		gbc_card4.insets = new Insets(0, 0, 5, 5);
		gbc_card4.gridx = 2;
		gbc_card4.gridy = 4;
		frame.getContentPane().add(card4, gbc_card4);
		
		card5 = new JTextField();
		card5.setColumns(10);
		GridBagConstraints gbc_card5 = new GridBagConstraints();
		gbc_card5.anchor = GridBagConstraints.WEST;
		gbc_card5.insets = new Insets(0, 0, 5, 5);
		gbc_card5.gridx = 2;
		gbc_card5.gridy = 5;
		frame.getContentPane().add(card5, gbc_card5);
		
		card6 = new JTextField();
		card6.setColumns(10);
		GridBagConstraints gbc_card6 = new GridBagConstraints();
		gbc_card6.anchor = GridBagConstraints.WEST;
		gbc_card6.insets = new Insets(0, 0, 5, 5);
		gbc_card6.gridx = 2;
		gbc_card6.gridy = 6;
		frame.getContentPane().add(card6, gbc_card6);

		JLabel lblCard = new JLabel("Card #1");
		GridBagConstraints gbc_lblCard = new GridBagConstraints();
		gbc_lblCard.anchor = GridBagConstraints.WEST;
		gbc_lblCard.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard.gridx = 0;
		gbc_lblCard.gridy = 1;
		frame.getContentPane().add(lblCard, gbc_lblCard);

		discard1 = new JCheckBox("");
		GridBagConstraints gbc_dicard1 = new GridBagConstraints();
		gbc_dicard1.insets = new Insets(0, 0, 5, 0);
		gbc_dicard1.gridx = 4;
		gbc_dicard1.gridy = 1;
		frame.getContentPane().add(discard1, gbc_dicard1);
		
		JLabel lblCard_1 = new JLabel("Card #2");
		GridBagConstraints gbc_lblCard_1 = new GridBagConstraints();
		gbc_lblCard_1.anchor = GridBagConstraints.WEST;
		gbc_lblCard_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_1.gridx = 0;
		gbc_lblCard_1.gridy = 2;
		frame.getContentPane().add(lblCard_1, gbc_lblCard_1);
		
		
		discard2 = new JCheckBox("");
		GridBagConstraints gbc_discard2 = new GridBagConstraints();
		gbc_discard2.insets = new Insets(0, 0, 5, 0);
		gbc_discard2.gridx = 4;
		gbc_discard2.gridy = 2;
		frame.getContentPane().add(discard2, gbc_discard2);
	
		JLabel lblCard_5 = new JLabel("Card #3");
		GridBagConstraints gbc_lblCard_5 = new GridBagConstraints();
		gbc_lblCard_5.anchor = GridBagConstraints.WEST;
		gbc_lblCard_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_5.gridx = 0;
		gbc_lblCard_5.gridy = 3;
		frame.getContentPane().add(lblCard_5, gbc_lblCard_5);
		
		discard3 = new JCheckBox("");
		GridBagConstraints gbc_discard3 = new GridBagConstraints();
		gbc_discard3.insets = new Insets(0, 0, 5, 0);
		gbc_discard3.gridx = 4;
		gbc_discard3.gridy = 3;
		frame.getContentPane().add(discard3, gbc_discard3);
		
		JLabel lblCard_4 = new JLabel("Card #4");
		GridBagConstraints gbc_lblCard_4 = new GridBagConstraints();
		gbc_lblCard_4.anchor = GridBagConstraints.WEST;
		gbc_lblCard_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_4.gridx = 0;
		gbc_lblCard_4.gridy = 4;
		frame.getContentPane().add(lblCard_4, gbc_lblCard_4);
		
		discard4 = new JCheckBox("");
		GridBagConstraints gbc_discard4 = new GridBagConstraints();
		gbc_discard4.insets = new Insets(0, 0, 5, 0);
		gbc_discard4.gridx = 4;
		gbc_discard4.gridy = 4;
		frame.getContentPane().add(discard4, gbc_discard4);
		
		JLabel lblCard_3 = new JLabel("Card #5");
		GridBagConstraints gbc_lblCard_3 = new GridBagConstraints();
		gbc_lblCard_3.anchor = GridBagConstraints.WEST;
		gbc_lblCard_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_3.gridx = 0;
		gbc_lblCard_3.gridy = 5;
		frame.getContentPane().add(lblCard_3, gbc_lblCard_3);
		
		discard5 = new JCheckBox("");
		GridBagConstraints gbc_discard5 = new GridBagConstraints();
		gbc_discard5.insets = new Insets(0, 0, 5, 0);
		gbc_discard5.gridx = 4;
		gbc_discard5.gridy = 5;
		frame.getContentPane().add(discard5, gbc_discard5);
		
		JLabel lblCard_2 = new JLabel("Card #6");
		GridBagConstraints gbc_lblCard_2 = new GridBagConstraints();
		gbc_lblCard_2.anchor = GridBagConstraints.WEST;
		gbc_lblCard_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCard_2.gridx = 0;
		gbc_lblCard_2.gridy = 6;
		frame.getContentPane().add(lblCard_2, gbc_lblCard_2);
		
		discard6 = new JCheckBox("");
		GridBagConstraints gbc_discard6 = new GridBagConstraints();
		gbc_discard6.insets = new Insets(0, 0, 5, 0);
		gbc_discard6.gridx = 4;
		gbc_discard6.gridy = 6;
		frame.getContentPane().add(discard6, gbc_discard6);

		GridBagConstraints gbc_ownCribBox = new GridBagConstraints();
		gbc_ownCribBox.anchor = GridBagConstraints.WEST;
		gbc_ownCribBox.insets = new Insets(0, 0, 5, 5);
		gbc_ownCribBox.gridx = 0;
		gbc_ownCribBox.gridy = 7;
		frame.getContentPane().add(ownCribBox, gbc_ownCribBox);
		
		
		JLabel lblCurrentPoints = new JLabel("Current Points:");
		GridBagConstraints gbc_lblCurrentPoints = new GridBagConstraints();
		gbc_lblCurrentPoints.anchor = GridBagConstraints.WEST;
		gbc_lblCurrentPoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentPoints.gridx = 0;
		gbc_lblCurrentPoints.gridy = 8;
		frame.getContentPane().add(lblCurrentPoints, gbc_lblCurrentPoints);
		
		JButton btnRedeal = new JButton("Re-deal");
		btnRedeal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cribbage.redeal();
				paint ();
			}
		});
		GridBagConstraints gbc_btnRedeal = new GridBagConstraints();
		gbc_btnRedeal.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedeal.gridx = 2;
		gbc_btnRedeal.gridy = 7;
		frame.getContentPane().add(btnRedeal, gbc_btnRedeal);
		
		score = new JTextField();
		GridBagConstraints gbc_score = new GridBagConstraints();
		gbc_score.anchor = GridBagConstraints.WEST;
		gbc_score.insets = new Insets(0, 0, 5, 5);
		gbc_score.gridx = 2;
		gbc_score.gridy = 8;
		frame.getContentPane().add(score, gbc_score);
		score.setColumns(10);
		paint ();
		
	}
}
