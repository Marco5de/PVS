package erp.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp.database.Auftragsposition;
import erp.database.LegoDB;

/**
 * popup wizard for gathering information from the user in order to create a new
 * Auftragsposition for an Auftrag
 * 
 */
@SuppressWarnings("serial")
public class AuftragsPosAnlegenForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTeileID;
	private JTextField txtAnzahl;
	private JButton hinzufuegenButton;
	private JComboBox<String> comboBoxFarbe;
	private static Boolean create = false;

	/**
	 * dialog constructor, checks all input for correctness in the main
	 * actionlistener, shows joptionpane popups for incorrect input the constructor
	 * is never used directly except for by the createAuftragsposition() method
	 * 
	 * @param legoDB
	 */
	private AuftragsPosAnlegenForm(final LegoDB legoDB) {
		setModal(true);
		setTitle("Auftragsposition hinzuf\u00FCgen");
		setBounds(100, 100, 450, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
		{
			JLabel lblNeueAuftragspos = new JLabel("Neue Auftragsposition:");
			contentPanel.add(lblNeueAuftragspos);
		}
		{
			JLabel lblEmpty = new JLabel("");
			contentPanel.add(lblEmpty);
		}
		{
			JLabel lblTeileID = new JLabel("Teile ID:");
			contentPanel.add(lblTeileID);
		}
		{
			txtTeileID = new JTextField();
			txtTeileID.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(txtTeileID);
			txtTeileID.setColumns(10);
		}
		{
			JLabel lblFarbe = new JLabel("Farbe");
			contentPanel.add(lblFarbe);
		}
		{
			comboBoxFarbe = new JComboBox<String>();
			comboBoxFarbe.setModel(new DefaultComboBoxModel<String>(new String[] { "Neutral", "Rot", "Blau" }));
			contentPanel.add(comboBoxFarbe);
		}
		{
			JLabel lblAnzahl = new JLabel("Anzahl:");
			contentPanel.add(lblAnzahl);
		}
		{
			txtAnzahl = new JTextField();
			txtAnzahl.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(txtAnzahl);
			txtAnzahl.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 5, 0, 0));
			{
				JLabel lblEmpty_1 = new JLabel("");
				buttonPane.add(lblEmpty_1);
			}
			{
				JLabel lblEmpty_3 = new JLabel("");
				buttonPane.add(lblEmpty_3);
			}
			{
				JLabel lblEmpty_2 = new JLabel("");
				buttonPane.add(lblEmpty_2);
			}
			{
				hinzufuegenButton = new JButton("Hinzuf\u00FCgen");
				hinzufuegenButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (txtAnzahl.getText().isEmpty() || txtTeileID.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Bitte alle Felder vervollstaendigen",
									"Fehlende Eingaben", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							if (Integer.parseInt(txtAnzahl.getText()) < 1) {
								JOptionPane.showMessageDialog(null, "Die Bestellanzahl ist ungueltig",
										"Ungueltige Bestellanzahl", JOptionPane.ERROR_MESSAGE);
								return;
							}
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Die Bestellanzahl ist ungueltig",
									"Ungueltige Bestellanzahl", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (!legoDB.checkValidTeil(txtTeileID.getText(), comboBoxFarbe.getSelectedIndex())) {
							JOptionPane.showMessageDialog(null, "Eingegebenes Teil existiert nicht in dieser Farbe",
									"Ungueltiges Teil", JOptionPane.ERROR_MESSAGE);
							return;
						}
						create = true;
						AuftragsPosAnlegenForm.this.dispose();
					}
				});
				buttonPane.add(hinzufuegenButton);
				getRootPane().setDefaultButton(hinzufuegenButton);
			}
			{
				JButton abbruchButton = new JButton("Abbruch");
				abbruchButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						create = false;
						AuftragsPosAnlegenForm.this.dispose();
					}
				});
				buttonPane.add(abbruchButton);
			}
		}
	}

	/**
	 * the only accessible part of the class, this method shows the wizard, creates
	 * an Auftragsposition from the gathered information and returns it
	 * 
	 * @param legoDB
	 *            the database instance
	 * 
	 * @return a new Auftragsposition
	 */
	static Auftragsposition createAuftragsposition(LegoDB legoDB) {
		Auftragsposition returnAuftragsposition = new Auftragsposition();
		AuftragsPosAnlegenForm temp = new AuftragsPosAnlegenForm(legoDB);
		temp.setVisible(true);
		if (create) {
			returnAuftragsposition.setAnzahlBestellt(Integer.parseInt(temp.txtAnzahl.getText()));
			returnAuftragsposition.setTeileID(temp.txtTeileID.getText());
			returnAuftragsposition.setFarbe(temp.comboBoxFarbe.getSelectedIndex());
			return returnAuftragsposition;
		} else {
			return null;
		}

	}

}
