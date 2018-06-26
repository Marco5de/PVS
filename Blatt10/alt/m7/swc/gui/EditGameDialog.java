package swc.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import swc.ctrl.CtrlGroup;
import swc.data.Game;

/**
 * @author Florian Rapp
 */
public class EditGameDialog extends JDialog {
	private static final long serialVersionUID = 13453535L;
	private Game game;
	private boolean successful;
	
	public EditGameDialog(Frame owner, Game game) {
		super(owner);
		this.game = game;
		initComponents();
	}

	private void initComponents() {
		labelFlagH = new JLabel();
		labelTeamH = new JLabel();
		textFieldGoalH = new JTextField();
		label5 = new JLabel();
		labelFlagG = new JLabel();
		labelTeamG = new JLabel();
		textFieldGoalG = new JTextField();
		label6 = new JLabel();
		buttonSave = new JButton();
		buttonCancel = new JButton();

		//======== this ========
		
		setTitle("Edit Game");
		setModal(true);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		
		//---- labelFlagH ----
		labelFlagH.setIcon(CtrlGroup.getFlagIcon(game.getTeamH().getName()));
		contentPane.add(labelFlagH, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(10, 10, 5, 5), 0, 0));

		//---- labelTeamH ----
		labelTeamH.setText(game.getTeamH().getName());
		contentPane.add(labelTeamH, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(10, 0, 5, 5), 0, 0));
		
		//---- textFieldGoalH ----
		textFieldGoalH.setText("" + game.getGoalsH());
		contentPane.add(textFieldGoalH, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(10, 0, 5, 5), 0, 0));

		//---- label5 ----
		label5.setText("Goals");
		contentPane.add(label5, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(10, 0, 5, 10), 0, 0));
		
		//---- labelFlagG ----
		labelFlagG.setIcon(CtrlGroup.getFlagIcon(game.getTeamG().getName()));
		contentPane.add(labelFlagG, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 10, 5, 5), 0, 0));

		//---- labelTeamG ----
		labelTeamG.setText(game.getTeamG().getName());
		contentPane.add(labelTeamG, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		
		//---- textFieldGoalG ----
		textFieldGoalG.setText("" + game.getGoalsG());
		contentPane.add(textFieldGoalG, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- label6 ----
		label6.setText("Goals");
		contentPane.add(label6, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 10), 0, 0));

		//---- buttonSave ----
		buttonSave.setText("Apply changes");
		contentPane.add(buttonSave, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 5), 0, 0));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSaveActionPerformed();
			}
		});	
		//---- buttonCancel ----
		buttonCancel.setText("Cancel");
		contentPane.add(buttonCancel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 5), 0, 0));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonCancelActionPerformed(e);
			}
		});	
		
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
		textFieldGoalG.addKeyListener(kl);
		textFieldGoalH.addKeyListener(kl);
		
		pack();
		setLocationRelativeTo(getOwner());
		this.setResizable(false);
	}

	private void buttonSaveActionPerformed() {
		int goalsH, goalsG;
		try{
			goalsH = Integer.valueOf(textFieldGoalH.getText());
			goalsG = Integer.valueOf(textFieldGoalG.getText());
		}catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Goals are not valid integer!", "Goals amount", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldGoalH.getText().equals("") || textFieldGoalG.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Please insert amount of goals!");
			return;
		}
		if(game.getIntId() > 48 && goalsH == goalsG){
			JOptionPane.showMessageDialog(this, "This game can't finish draw!");
			return;
		}
		game.setGoalsH(goalsH);
		game.setGoalsG(goalsG);
		game.setPlayed(true);
		this.successful = true;
		this.dispose();
	}

	private void buttonCancelActionPerformed(ActionEvent e) {
		this.game = null;
		this.dispose();	
	}

	public boolean wasSuccessful() {
		return this.successful;
	}

	private JLabel labelFlagH;
	private JLabel labelTeamH;
	private JTextField textFieldGoalH;
	private JLabel label5;
	private JLabel labelFlagG;
	private JLabel labelTeamG;
	private JTextField textFieldGoalG;
	private JLabel label6;
	private JButton buttonSave;
	private JButton buttonCancel;
}
