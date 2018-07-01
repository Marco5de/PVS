package erp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import erp.database.Auftragsposition;
import erp.database.LegoDB;

/**
 * popup window showing the Auftragsposition objects "attached" to a given
 * Autrag in a table view
 * 
 */
@SuppressWarnings("serial")
public class AuftragsPosAnsichtForm extends JDialog {
	/**
	 * the main element of the dialog, an uneditable JTable, this is achieved by
	 * overriding the isCellEditable() method of the TableModel class
	 */
	private JTable table;

	/**
	 * dialog constructor, uses one JScrollPane that is set to CENTER, the
	 * scrollpane contains one JTable
	 * 
	 * @param auftragsnummer
	 *            the auftragsnummer of the Auftrag for which the individual
	 *            Auftragspositionen should be displayed
	 * @param legoDB
	 *            the database instance
	 */
	public AuftragsPosAnsichtForm(int auftragsnummer, LegoDB legoDB) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Auftragspositionen f\u00FCr Auftrag #" + auftragsnummer);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				// create a new tablemodel, based on the default table model
				// the only change is the ovverriding of the isCellEditable()
				// method to disallow cell editing
				DefaultTableModel tableModel = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				String[] tableHeaders = { "Auftragsposition", "Teile ID", "Farbe", "Anzahl" };
				// get all Auftragsposition for the current Auftrag from the
				// database, then use a for loop to convert the information into
				// a 2 dimensional string array to use as part of our table
				// model
				List<Auftragsposition> auftraegspositionen = legoDB.readAuftragspositionen(auftragsnummer);
				String[][] tableContents = new String[auftraegspositionen.size()][4];
				for (int i = 0; i < auftraegspositionen.size(); i++) {
					Auftragsposition currentAuftragsposition = auftraegspositionen.get(i);
					tableContents[i][0] = currentAuftragsposition.getAuftragspositionsnummer() + "";
					tableContents[i][1] = currentAuftragsposition.getTeileID();
					tableContents[i][2] = getColorNameFromCode(currentAuftragsposition.getFarbe());
					tableContents[i][3] = currentAuftragsposition.getAnzahlBestellt() + "";
				}
				tableModel.setDataVector(tableContents, tableHeaders);
				table = new JTable(tableModel);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AuftragsPosAnsichtForm.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
