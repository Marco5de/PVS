package erp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import erp.database.Auftrag;
import erp.database.LegoDB;

/**
 * main program gui window, contains a menubar with items and a tabbed pane in
 * the middle, shows a table with all Auftrag objects by default
 * 
 */
@SuppressWarnings("serial")
public class AuftragForm extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private LegoDB legoDB;

	/**
	 * window constructor, sets look and feel to the native look of the host system,
	 * and creates the basic gui over which the other view classes can popup over
	 * 
	 * @param legoDB
	 *            the instance of the database class
	 */
	public AuftragForm(final LegoDB legoDB) {
		this.legoDB = legoDB;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setTitle("Lego Trailer ERP");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnDatei.add(mntmBeenden);

		JMenu mnAuftrge = new JMenu("Auftr\u00E4ge");
		menuBar.add(mnAuftrge);

		JMenuItem mntmNeuerAuftrag = new JMenuItem("Neuer Auftrag");
		mntmNeuerAuftrag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AuftragAnlegenForm(legoDB).setVisible(true);
				refreshTable();
			}
		});
		mnAuftrge.add(mntmNeuerAuftrag);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Auftragsliste", null, scrollPane, null);

		// create a new JTable with an altered DefaultTableModel, overriding
		// isCellEditable like this will make the table non editable
		table = new JTable(new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// capture doubleclicks on the table and get the auftragsnummer
				// of the doubleclicked Autrag, then show its details
				if (arg0.getClickCount() == 2) {
					int row = table.getSelectedRow();
					int auftragsnummer = Integer.parseInt((String) table.getValueAt(row, 0));
					new AuftragsPosAnsichtForm(auftragsnummer, legoDB).setVisible(true);
				}

			}
		});
		refreshTable();
		scrollPane.setViewportView(table);
	}

	/**
	 * method to read all Auftrag objects from the database and convert them into a
	 * 2 dimensional array for use as a JTable table view gets called after a new
	 * Auftrag was created and saved to the database to reload the changes and show
	 * the newest data in the table
	 */
	void refreshTable() {
		String[] tableHeaders = { "Auftragsnummer", "Kundennummer", "Erfassungsdatum", "Auftragsstatus" };
		List<Auftrag> auftraege = legoDB.readAuftraege();
		String[][] tableContents = new String[auftraege.size()][4];
		for (int i = 0; i < auftraege.size(); i++) {
			Auftrag currentAuftrag = auftraege.get(i);
			tableContents[i][0] = currentAuftrag.getAuftrNr() + "";
			tableContents[i][1] = currentAuftrag.getKdNr() + "";
			tableContents[i][2] = currentAuftrag.getErfassungsDatum() + "";
			tableContents[i][3] = currentAuftrag.getAuftrStatus() + "";
		}
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setDataVector(tableContents, tableHeaders);
	}
}
