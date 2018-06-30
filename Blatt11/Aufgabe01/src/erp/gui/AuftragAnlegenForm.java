package erp.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import erp.database.Auftragsposition;
import erp.database.Kunde;
import erp.database.LegoDB;

/**
 * popup wizard for getting information about a new Auftrag from the user and
 * creating it
 * 
 */
@SuppressWarnings("serial")
public class AuftragAnlegenForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtKundenauftragsnummer;
	private JTextField txtKundenauftragsdatum;
	private JTable table;
	private DefaultTableModel tableModel;
	private ArrayList<Auftragsposition> auftragspositionen = new ArrayList<Auftragsposition>();
	private JComboBox<String> comboBoxKundennummer;

	/**
	 * dialog constructor, the dialog uses the
	 * AuftragsposWizard.createAuftragsposition() method to show another popup when
	 * the user clicks the "Auftragsposition hinzufuegen" button, the second popup
	 * gathers information on the individual Auftragsposition, this one one the
	 * whole Auftrag
	 * 
	 * @param legoDB
	 *            the database instance
	 */
	public AuftragAnlegenForm(final LegoDB legoDB) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Auftrag erstellen");
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(contentPanel, BorderLayout.CENTER);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
			{
				JLabel lblNeuerAuftrag = new JLabel("Neuer Auftrag:");
				contentPanel.add(lblNeuerAuftrag);
			}
			{
				JLabel lblEmpty = new JLabel("");
				contentPanel.add(lblEmpty);
			}
			{
				JLabel lblKundennummer = new JLabel("Kundennummer:");
				contentPanel.add(lblKundennummer);
			}
			{
				// get all kunden from database and create combobox from a
				// vector of strings with the kdnr and kdname concatenated
				Vector<String> comboBoxItems = new Vector<String>();
				for (Kunde i : legoDB.readKunden()) {
					comboBoxItems.add(i.getKdNr() + ": " + i.getKdName());
				}
				comboBoxKundennummer = new JComboBox<String>(comboBoxItems);
				contentPanel.add(comboBoxKundennummer);
			}
			{
				JLabel lblKundenauftragsnummer = new JLabel("Kunden-Auftragsnummer:");
				contentPanel.add(lblKundenauftragsnummer);
			}
			{
				txtKundenauftragsnummer = new JTextField();
				txtKundenauftragsnummer.setHorizontalAlignment(SwingConstants.RIGHT);
				contentPanel.add(txtKundenauftragsnummer);
				txtKundenauftragsnummer.setColumns(10);
			}
			{
				JLabel lblKundenauftragsdatum = new JLabel("Kunden-Auftragsdatum");
				contentPanel.add(lblKundenauftragsdatum);
			}
			{
				txtKundenauftragsdatum = new JTextField();
				txtKundenauftragsdatum.setHorizontalAlignment(SwingConstants.RIGHT);
				contentPanel.add(txtKundenauftragsdatum);
				txtKundenauftragsdatum.setColumns(10);
			}
			{
				JPanel buttonPane = new JPanel();
				panel.add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new GridLayout(0, 5, 0, 0));
				{
					JButton btnAuftragsposHinzufgen = new JButton("<html>Auftragspos.<br>\r\nhinzuf\u00FCgen</html>");
					btnAuftragsposHinzufgen.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Auftragsposition temp = AuftragsPosAnlegenForm.createAuftragsposition(legoDB);
							if (temp != null) {
								temp.setAuftragsnummer(legoDB.readNextAuftragsnummer());
								temp.setAuftragspositionsnummer(tableModel.getRowCount() + 1);
								auftragspositionen.add(temp);
								String[] tempArray = new String[] { temp.getTeileID(),
										getColorNameFromCode(temp.getFarbe()), temp.getAnzahlBestellt() + "" };
								tableModel.addRow(tempArray);
							}
						}
					});
					buttonPane.add(btnAuftragsposHinzufgen);
				}
				{
					JLabel lblEmpty_1 = new JLabel("");
					buttonPane.add(lblEmpty_1);
				}
				{
					JButton erstellenButton = new JButton("Erstellen");
					erstellenButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							try {
								String[] teileID = new String[auftragspositionen.size()];
								int[] farbe = new int[auftragspositionen.size()];
								int[] amount = new int[auftragspositionen.size()];

								for (int i = 0; i < auftragspositionen.size(); i++) {
									teileID[i] = auftragspositionen.get(i).getTeileID();
									farbe[i] = auftragspositionen.get(i).getFarbe();
									amount[i] = auftragspositionen.get(i).getAnzahlBestellt();
								}
								// As the combobox contains a String in
								// <kdNR>:<kdName> format, we have to split it
								// at the ":"
								// then we can take the first half of the split
								// and get the int value of that
								int kundennummer = Integer
										.parseInt(comboBoxKundennummer.getSelectedItem().toString().split(":")[0]);
								String kundenauftragsnummer = txtKundenauftragsnummer.getText();
								Date kundenauftragsdatum = DateFormat.getDateInstance()
										.parse(txtKundenauftragsdatum.getText());
								legoDB.createAuftrag(kundennummer, kundenauftragsnummer, kundenauftragsdatum, teileID,
										farbe, amount);
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(null, "Das Datum ist nicht interpretierbar.",
										"Ung�ltiges Datum", JOptionPane.ERROR_MESSAGE);
								return;
							}
							AuftragAnlegenForm.this.dispose();
						}
					});
					buttonPane.add(erstellenButton);
					getRootPane().setDefaultButton(erstellenButton);
				}
				{
					JButton abbruchButton = new JButton("Abbruch");
					abbruchButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							AuftragAnlegenForm.this.dispose();
						}
					});
					buttonPane.add(abbruchButton);
				}
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane);
			{
				tableModel = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				String[] tableHeaders = { "Teile ID", "Farbe", "Anzahl" };
				tableModel.setColumnIdentifiers(tableHeaders);
				table = new JTable(tableModel);
				scrollPane.setViewportView(table);
			}
		}
	}

	/**
	 * helper method, resolves colorcodes to color names
	 * 
	 * @param color
	 *            the colorcode to resolve
	 * @return string with the "name" of the color
	 */
	private String getColorNameFromCode(int color) {
		if (color == 1) {
			return "Rot";
		}
		if (color == 2) {
			return "Blau";
		}
		return "Neutral";
	}
}
