package swc.gui;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import swc.data.*;
import swc.ctrl.*;

/**
 * @author Florian Rapp
 */
public class CreateDialog extends JDialog {
	private static final long serialVersionUID = 1155754L;
	private SoccerWC worldCup;
	private Vector<DefaultListModel> models = new Vector<DefaultListModel>();
	private boolean successful;
	
	
	public CreateDialog(Frame owner, SoccerWC worldCup) {
		super(owner);
		this.worldCup = worldCup;
		this.successful = false;
		initComponents();
	}
	
	private void initComponents() {
		panelGroups = new JPanel();
		label10 = new JLabel();
		label11 = new JLabel();
		label12 = new JLabel();
		label13 = new JLabel();
		listA = new JList();
		listB = new JList();
		listC = new JList();
		listD = new JList();
		label14 = new JLabel();
		label15 = new JLabel();
		label16 = new JLabel();
		label17 = new JLabel();
		listE = new JList();
		listF = new JList();
		listG = new JList();
		listH = new JList();
		label9 = new JLabel();
		textFieldName = new JTextField();
		buttonCreateWC = new JButton();
		buttonAbort = new JButton();
		lmA = new DefaultListModel();
		lmB = new DefaultListModel();
		lmC = new DefaultListModel();
		lmD = new DefaultListModel();
		lmE = new DefaultListModel();
		lmF = new DefaultListModel();
		lmG = new DefaultListModel();
		lmH = new DefaultListModel();

		//======== this ========
		Container contentPane = getContentPane();
		this.setModal(true);
		
		//======== preparingLists ========
		
		models.add(lmA);
		models.add(lmB);
		models.add(lmC);
		models.add(lmD);
		models.add(lmE);
		models.add(lmF);
		models.add(lmG);
		models.add(lmH);
		
		// preparing the lists
		
		listA.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listA.setCellRenderer(new ListsRenderer());
		listA.setModel(lmA);
		
		listB.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listB.setCellRenderer(new ListsRenderer());
		listB.setModel(lmB);
		
		listC.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listC.setCellRenderer(new ListsRenderer());
		listC.setModel(lmC);
		
		listD.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listD.setCellRenderer(new ListsRenderer());
		listD.setModel(lmD);
		
		listE.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listE.setCellRenderer(new ListsRenderer());
		listE.setModel(lmE);
		
		listF.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listF.setCellRenderer(new ListsRenderer());
		listF.setModel(lmF);
		
		listG.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listG.setCellRenderer(new ListsRenderer());
		listG.setModel(lmG);
		
		listH.setBorder(javax.swing.BorderFactory.createLineBorder
				(java.awt.Color.lightGray));
		listH.setCellRenderer(new ListsRenderer());
		listH.setModel(lmH);

		//======== panelGroups ========
		{
			panelGroups.setLayout(new GridBagLayout());
			((GridBagLayout)panelGroups.getLayout()).columnWidths = new int[] {150, 150, 150, 150, 0};
			((GridBagLayout)panelGroups.getLayout()).rowHeights = new int[] {10, 100, 10, 100, 10, 0};
			((GridBagLayout)panelGroups.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)panelGroups.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

			//---- label10 ----
			label10.setText("Group A");
			panelGroups.add(label10, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 10, 5, 5), 0, 0));

			//---- label11 ----
			label11.setText("Group B");
			panelGroups.add(label11, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 0, 5, 5), 0, 0));

			//---- label12 ----
			label12.setText("Group C");
			panelGroups.add(label12, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 0, 5, 5), 0, 0));

			//---- label13 ----
			label13.setText("Group D");
			panelGroups.add(label13, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 0, 5, 10), 0, 0));
			panelGroups.add(listA, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 10, 5, 5), 0, 0));
			panelGroups.add(listB, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
			panelGroups.add(listC, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
			panelGroups.add(listD, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 10), 0, 0));

			//---- label14 ----
			label14.setText("Group E");
			panelGroups.add(label14, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 10, 5, 5), 0, 0));

			//---- label15 ----
			label15.setText("Group F");
			panelGroups.add(label15, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- label16 ----
			label16.setText("Group G");
			panelGroups.add(label16, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- label17 ----
			label17.setText("Group H");
			panelGroups.add(label17, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 10), 0, 0));
			panelGroups.add(listE, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 10, 0, 5), 0, 0));
			panelGroups.add(listF, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));
			panelGroups.add(listG, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));
			panelGroups.add(listH, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 10), 0, 0));
			
			//---- label9 ----
			label9.setText("World Cup Name:");
			label9.setHorizontalAlignment(JLabel.RIGHT);
			panelGroups.add(label9, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(10, 10, 5, 5), 0, 0));
			
			panelGroups.add(textFieldName, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(10, 0, 10, 5), 0, 0));
			textFieldName.setDocument(new FixedSizeDocument(20));
			
			//---- buttonCreateWC ----
			buttonCreateWC.setText("Create World Cup");
			buttonCreateWC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonCreateWCActionPerformed(e);
				}
			});
			panelGroups.add(buttonCreateWC, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(10, 5, 10, 5), 0, 0));

			//---- buttonAbort ----
			buttonAbort.setText("Cancel");
			buttonAbort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonAbortActionPerformed(e);
				}
			});
			panelGroups.add(buttonAbort, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(10, 5, 10, 5), 0, 0));
		}
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panelGroups, BorderLayout.PAGE_START);
		pack();
		setTitle("Create new Soccer World Cup");
		setLocationRelativeTo(getOwner());
	}

	private void buttonCreateWCActionPerformed(ActionEvent e) {
		for (DefaultListModel dlm : models) {
			dlm.clear();
		}
		if(textFieldName.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Please specify World Cup Name", "Create World Cup", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			prepareListModels();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			CtrlGroup.setNewWorldCup(worldCup, models, textFieldName.getText());
		} catch (Exception e1) {
			System.out.println("data structure invalid!");
			e1.printStackTrace();
			return;
		}
		this.successful = true;
		JOptionPane.showMessageDialog(this, "Creating data structure successful!", "Create World Cup", JOptionPane.INFORMATION_MESSAGE);
	}

	private void buttonAbortActionPerformed(ActionEvent e) {
		this.successful = false;
		this.dispose();
	}

	private void prepareListModels() throws IOException {
		Vector<String> teams = CtrlGroup.getDefaultTeams();
		int j = 0;
		for (DefaultListModel dfm : models) {
			for (int i = 0; i < 4; i++) {
				dfm.addElement(teams.get(j + i));
			}
			j += 4;
		}
	}	

	private JPanel panelGroups;
	private JLabel label10;
	private JLabel label11;
	private JLabel label12;
	private JLabel label13;
	private JList listA;
	private JList listB;
	private JList listC;
	private JList listD;
	private JLabel label14;
	private JLabel label15;
	private JLabel label16;
	private JLabel label17;
	private JList listE;
	private JList listF;
	private JList listG;
	private JList listH;
	private JLabel label9;
	private JTextField textFieldName;
	private JButton buttonCreateWC;
	private JButton buttonAbort;
	private DefaultListModel lmA;
	private DefaultListModel lmB;
	private DefaultListModel lmC;
	private DefaultListModel lmD;
	private DefaultListModel lmE;
	private DefaultListModel lmF;
	private DefaultListModel lmG;
	private DefaultListModel lmH;
	
	
	public boolean wasSuccessful(){
		return this.successful;
	}
	
	class ListsRenderer extends JLabel implements ListCellRenderer {
		private static final long serialVersionUID = 15646L;

		public Component getListCellRendererComponent(
		                    JList list,
		                    Object value,
		                    int index,
		                    boolean isSelected,
		                    boolean cellHasFocus) {
			Icon icon = CtrlGroup.getFlagIcon(value.toString());
	    	setIcon(icon);
	    	setText(value.toString());
			return this;
		}
	}
	class FixedSizeDocument extends PlainDocument {
		private static final long serialVersionUID = 1423424L;
		private int _intLimit;
		private boolean _boolUppercase = false;

	  
		public FixedSizeDocument(int intLimit) { 
			super();
			this._intLimit = intLimit;
		}
	  
		public FixedSizeDocument(int intLimit, boolean boolUppercase) {  
			super();
			this._intLimit = intLimit;
			this._boolUppercase = boolUppercase;
		}	  
	    
		public void insertString (int intOffset, String strValue, javax.swing.text.AttributeSet objAttribute) throws BadLocationException {
			if (strValue == null) {
				return;
			}

			if ((getLength() + strValue.length()) <= this._intLimit) {
				if (this._boolUppercase) {
					strValue = strValue.toUpperCase();
				}
				super.insertString(intOffset, strValue, objAttribute);
			}
		}
	}
}