package swc.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import swc.data.Team;

public class EditGroupDialog extends JDialog {
	private static final long serialVersionUID = 336345671L;
	private JList list;
	private Team team;
	private String teamname;
	private boolean successful;
	
	public EditGroupDialog(Frame owner, JList list){
		super(owner);
		this.list = list;
		this.teamname = (String)list.getSelectedValue();
		initComponents();
	}

	public EditGroupDialog(Frame owner, Team selected) {
		super(owner);
		this.teamname = selected.getName();
		this.team = selected;
		initComponents();
	}

	private void initComponents() {
		
		contentPanel = new JPanel();
		textFieldName = new JTextField();
		labelName = new JLabel();
		buttonCancel = new JButton();
		buttonSave = new JButton();
		
		setTitle("Edit Group");
		setModal(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		// ======== contentPanel ========
		{
			contentPanel.setLayout(new GridBagLayout());
			((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[] {
					0, 0, 0, 0 };
			((GridBagLayout) contentPanel.getLayout()).rowHeights = new int[] {
					0, 0, 0 };
			((GridBagLayout) contentPanel.getLayout()).columnWeights = new double[] {
					0.0, 1.0, 0.0, 1.0E-4 };
			((GridBagLayout) contentPanel.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

			// ---- labelTitle ----
			labelName.setText("Titel:");
			contentPanel.add(labelName, new GridBagConstraints(0, 0, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(10, 10, 5, 5), 0, 0));

			// ---- textFieldName ----
			textFieldName.setColumns(25);
			textFieldName.setText(teamname);
			contentPanel.add(textFieldName, new GridBagConstraints(1, 0,
					2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(10, 0, 5, 10), 0, 0));
			
			// ---- buttonCancel ----
			buttonCancel.setText("Cancel");
			buttonCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonCancelActionPerformed(e);
				}
			});
			contentPanel.add(buttonCancel, new GridBagConstraints(2, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

			// ---- buttonSave ----
			buttonSave.setText("Apply changes");
			buttonSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSaveActionPerformed();
				}
			});
			contentPanel.add(buttonSave, new GridBagConstraints(1, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
		}
		
		KeyListener kl = new KeyListener() {		
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == 10)
					buttonSaveActionPerformed();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		};
		textFieldName.addKeyListener(kl);
		
		contentPane.add(contentPanel);
		pack();
		setLocationRelativeTo(getOwner());
		this.setResizable(false);
	}
	
	private void buttonSaveActionPerformed() {
		if(textFieldName.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Please insert team name!");
			return;
		}
		if(list != null){
			DefaultListModel dlm = (DefaultListModel) list.getModel();
			dlm.set(list.getSelectedIndex(), textFieldName.getText());
		}
		else if(team != null){
			team.setName(textFieldName.getText());
		}
		this.successful = true;
		this.list = null;
		this.team = null;
		this.dispose();
	}

	private void buttonCancelActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private JPanel contentPanel;
	private JTextField textFieldName;
	private JLabel labelName;
	private JButton buttonSave;
	private JButton buttonCancel;

	public boolean wasSuccessful() {
		return this.successful;
	}
}
