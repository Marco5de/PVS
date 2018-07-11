package swc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import swc.ctrl.CtrlGroup;
import swc.ctrl.TipUploaderThread;
import swc.data.Game;
import swc.data.SoccerWC;
import swc.data.Tip;

public class BettingDialog extends JDialog {
	private static final long serialVersionUID = 1324234L;

	private static BorderCellRenderer createRenderer(Color color, Insets insets) {
		BorderCellRenderer renderer = new BorderCellRenderer();
		renderer.setColumnBorder(new LinesBorder(color, insets));
		return renderer;
	}

	private JButton addButton;
	private JComboBox<String> betterComboBox;
	private Vector<String> betters;
	private JButton closeButton;
	private JPanel gamesPanel;
	private Vector<Game> globalGameList;

	private Vector<DefaultTableModel> groupModels;

	private Vector<JTable> groupTables;

	private JScrollPane jScrollPane16;

	private JScrollPane jScrollPaneFinal;

	private JScrollPane jScrollPaneQuarter;

	private JScrollPane jScrollPaneSemi;

	private JScrollPane jScrollPaneThird;

	private JLabel label16;

	private JLabel labelFalse;

	private JLabel labelFinal;

	private JLabel labelLegend;

	private JLabel labelNames;

	private JLabel labelPerfect;

	private JLabel labelQuarter;

	private JLabel labelRight;
	private JLabel labelSemi;
	private JLabel labelStatus;
	private JLabel labelThird;
	private Vector<JScrollPane> scrollpanes;
	private JPanel topBarPanel;
	private JButton uploadTipsButton;
	private SoccerWC worldCup;

	public BettingDialog(Frame owner, SoccerWC worldCup) {
		super(owner);
		this.worldCup = worldCup;
		groupModels = new Vector<DefaultTableModel>();
		groupTables = new Vector<JTable>();
		scrollpanes = new Vector<JScrollPane>();
		globalGameList = new Vector<Game>();
		betters = new Vector<String>();
		initComponents();
		drawMatches();
	}

	private void addButtonActionPerformed() {
		String newEmail = JOptionPane.showInputDialog("Please enter better e-mail: ");
		if (newEmail == null)
			return;
		if (!newEmail.isEmpty()) {
			betters.add(newEmail);
			betterComboBox.addItem(newEmail);
			betterComboBox.setSelectedIndex(betterComboBox.getItemCount() - 1);
			loadBettingSheet(newEmail);
			uploadTipsButton.setEnabled(true);
		}
	}

	private void closeButtonActionPerformed() {
		this.dispose();
	}

	@SuppressWarnings("serial")
	private void drawMatches() {

		// putting the scrollpanes to alist
		scrollpanes.add(jScrollPane16);
		scrollpanes.add(jScrollPaneQuarter);
		scrollpanes.add(jScrollPaneSemi);
		scrollpanes.add(jScrollPaneThird);
		scrollpanes.add(jScrollPaneFinal);

		// prepare model data
		String[] columns = { "Match", "Date", "Time", "Venue", "", "", "Result", "", "", "", "Tip Home", "",
				"Tip Guest", "Tip Result" };

		// filling data
		for (int j = 0; j < 5; j++) {
			Vector<Game> currentGames = null;
			if (j == 0)
				currentGames = worldCup.getFinals().getRoundOf16();
			else if (j == 1)
				currentGames = worldCup.getFinals().getQuarterFinals();
			else if (j == 2)
				currentGames = worldCup.getFinals().getSemiFinals();
			else if (j == 3) {
				currentGames = new Vector<Game>();
				currentGames.add(worldCup.getFinals().getThirdGame());
			} else if (j == 4) {
				currentGames = new Vector<Game>();
				currentGames.add(worldCup.getFinals().getFinalGame());
			}

			globalGameList.addAll(currentGames);

			// JTable currentTable = groupTables.get(j);
			JScrollPane currentScrollPane = scrollpanes.get(j);

			Object[][] data = new Object[currentGames.size()][14];

			for (int i = 0; i < data.length; i++) {
				data[i][0] = "" + currentGames.get(i).getIntId();
				data[i][1] = "" + currentGames.get(i).getDate();
				data[i][2] = "" + currentGames.get(i).getTime();
				data[i][3] = "" + currentGames.get(i).getLocation();
				data[i][5] = "" + currentGames.get(i).getTeamH().getStrName();
				if (currentGames.get(i).isPlayed())
					data[i][6] = currentGames.get(i).getGoalsH() + " - " + currentGames.get(i).getGoalsG();
				else
					data[i][6] = " - ";
				data[i][7] = "" + currentGames.get(i).getTeamG().getStrName();
				try {
					data[i][4] = CtrlGroup.getFlagIcon(currentGames.get(i).getTeamH().getStrName());
					data[i][8] = CtrlGroup.getFlagIcon(currentGames.get(i).getTeamG().getStrName());
					data[i][13] = CtrlGroup.getFlagIcon("default");
				} catch (Exception e) {
					e.printStackTrace();
				}
				data[i][11] = " - ";
				data[i][10] = "";
				data[i][12] = "";
			}

			// boolean isFinished = false;
			// for (Game g : currentGames) {
			// isFinished = false;
			// if(g.getTeamH().getName().length() > 3
			// && g.getTeamG().getName().length() > 3)
			// isFinished = true;
			// }
			
			DefaultTableModel currentModel;
			currentModel = new DefaultTableModel(data, columns) {
				public boolean isCellEditable(int row, int column) {
					if (column == 12 || column == 10)
							return true;
					return false;
				}
			};

			groupModels.add(currentModel);

			JTable currentTable = new JTable(currentModel) {
				// Returning the Class of each column will allow different
				// renderers to be used based on Class
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}
			};
			((JLabel) currentTable.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
			currentTable.setIntercellSpacing(new Dimension(0, 0));

			// Cell Borders
			Color color = currentTable.getGridColor();
			BorderCellRenderer renderer = new BorderCellRenderer();
			TableColumnModel model = currentTable.getColumnModel();

			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(0).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(1).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(2).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(3).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(4).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(5).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(6).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(7).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(8).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(9).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(10).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(11).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(12).setCellRenderer(renderer);
			renderer = createRenderer(color, new Insets(0, 0, 1, 0));
			model.getColumn(13).setCellRenderer(renderer);

			currentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			int vColIndex = 1;
			int width = 50;
			TableColumn col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 2;
			width = 50;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 3;
			width = 115;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 4;
			width = 30;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 5;
			width = 110;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 6;
			width = 50;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 7;
			width = 110;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 8;
			width = 30;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 9;
			width = 30;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 10;
			width = 60;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 11;
			width = 30;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 12;
			width = 60;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			vColIndex = 13;
			width = 60;
			col = currentTable.getColumnModel().getColumn(vColIndex);
			col.setPreferredWidth(width);

			((JLabel) currentTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
			currentTable.getTableHeader().setReorderingAllowed(false);
			currentScrollPane.setViewportView(currentTable);

			groupTables.add(currentTable);
		}
		// load first data
		if (betterComboBox.getItemCount() > 0)
			loadBettingSheet((String) betterComboBox.getItemAt(0));
	}

	private Vector<Tip> getTipsFromTable() {
		Vector<Tip> tips = new Vector<Tip>();
		int id = 0;
		int gh = 0;
		int gg = 0;
		for (int i = 0; i < groupModels.size(); i++) {
			DefaultTableModel dtm = groupModels.get(i);
			for (int j = 0; j < dtm.getDataVector().size(); j++) {
				if (!dtm.getValueAt(j, 10).equals("") && !dtm.getValueAt(j, 12).equals("")) {
					try {
						gh = Integer.parseInt("" + dtm.getValueAt(j, 10));
						gg = Integer.parseInt("" + dtm.getValueAt(j, 12));
						id = Integer.parseInt("" + dtm.getValueAt(j, 0));
						tips.add(new Tip(id, gh, gg));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return tips;
	}

	private void initBetterComboBox() {
		if (betterComboBox == null) {
			betterComboBox = new JComboBox<String>();

			for (String better : betters) {
				betterComboBox.addItem(better);
			}
			if (betters.size() > 0) {
				uploadTipsButton.setEnabled(true);
			}

			betterComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if (betterComboBox.getItemCount() > 0) {
						loadBettingSheet((String) betterComboBox.getSelectedItem());
					}
				}

			});
		}
	}

	private void initComponents() {
		// Top Bar
		topBarPanel = new JPanel();
		labelNames = new JLabel("Choose better: ");
		addButton = new JButton();
		addButton.setIcon(CtrlGroup.getFlagIcon("plus"));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addButtonActionPerformed();
			}
		});
		uploadTipsButton = new JButton("Upload Tips");
		uploadTipsButton.setEnabled(false);
		uploadTipsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadButtonActionPerformed();
			}
		});
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeButtonActionPerformed();
			}
		});
		labelLegend = new JLabel("            Legend for Tip Result: ");
		labelFalse = new JLabel(" = false tip, ");
		labelFalse.setIcon(CtrlGroup.getFlagIcon("false"));
		labelRight = new JLabel(" = right tendency, ");
		labelRight.setIcon(CtrlGroup.getFlagIcon("correct"));
		labelPerfect = new JLabel(" = correct tip");
		labelPerfect.setIcon(CtrlGroup.getFlagIcon("crown"));
		labelStatus = new JLabel("");

		topBarPanel.setSize(500, 10);
		topBarPanel.add(labelNames);
		initBetterComboBox();
		topBarPanel.add(betterComboBox);
		topBarPanel.add(addButton);
		topBarPanel.add(uploadTipsButton);
		topBarPanel.add(closeButton);
		topBarPanel.add(labelLegend);
		topBarPanel.add(labelFalse);
		topBarPanel.add(labelRight);
		topBarPanel.add(labelPerfect);
		topBarPanel.add(labelStatus);

		// Game Panel
		gamesPanel = new JPanel();
		gamesPanel.setLayout(new GridBagLayout());
		((GridBagLayout) gamesPanel.getLayout()).columnWidths = new int[] { 0, 0 };
		((GridBagLayout) gamesPanel.getLayout()).rowHeights = new int[] { 10, 170, 10, 104, 10, 78, 10, 58, 10, 58 };
		((GridBagLayout) gamesPanel.getLayout()).columnWeights = new double[] { 0.0, 1.0E-4 };
		((GridBagLayout) gamesPanel.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0E-4 };
		gamesPanel.setPreferredSize(new Dimension(890, 600));

		final JScrollPane mainScrollPane = new JScrollPane(gamesPanel);
		JScrollBar jsb = new JScrollBar();
		jsb.setAutoscrolls(false);
		jsb.setUnitIncrement(15);
		jsb.setBlockIncrement(15);
		mainScrollPane.setVerticalScrollBar(jsb);

		MouseWheelListener mwl = new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelListener[] tmp = mainScrollPane.getMouseWheelListeners();
				for (int i = 0; i < tmp.length; i++) {
					tmp[i].mouseWheelMoved(e);
				}
			}
		};

		// Round of 16
		label16 = new JLabel("Round of 16: ");
		Font f = label16.getFont();
		label16.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		gamesPanel.add(label16, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.5, GridBagConstraints.FIRST_LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));
		jScrollPane16 = new JScrollPane();
		jScrollPane16.setBorder(new EmptyBorder(0, 0, 0, 0));
		jScrollPane16.addMouseWheelListener(mwl);
		gamesPanel.add(jScrollPane16, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));

		// Quarterfinals
		labelQuarter = new JLabel("Quarterfinals: ");
		f = labelQuarter.getFont();
		labelQuarter.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		gamesPanel.add(labelQuarter, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));
		jScrollPaneQuarter = new JScrollPane();
		jScrollPaneQuarter.setBorder(new EmptyBorder(0, 0, 0, 0));
		jScrollPaneQuarter.addMouseWheelListener(mwl);
		gamesPanel.add(jScrollPaneQuarter, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));

		// Semifinals
		labelSemi = new JLabel("Semifinals: ");
		f = labelSemi.getFont();
		labelSemi.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		gamesPanel.add(labelSemi, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));
		jScrollPaneSemi = new JScrollPane();
		jScrollPaneSemi.setBorder(new EmptyBorder(0, 0, 0, 0));
		jScrollPaneSemi.addMouseWheelListener(mwl);
		gamesPanel.add(jScrollPaneSemi, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));

		// Third
		labelThird = new JLabel("Match for third place: ");
		f = labelThird.getFont();
		labelThird.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		gamesPanel.add(labelThird, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));
		jScrollPaneThird = new JScrollPane();
		jScrollPaneThird.setBorder(new EmptyBorder(0, 0, 0, 0));
		jScrollPaneThird.addMouseWheelListener(mwl);
		gamesPanel.add(jScrollPaneThird, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));

		// Final
		labelFinal = new JLabel("Final: ");
		f = labelFinal.getFont();
		labelFinal.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		gamesPanel.add(labelFinal, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));
		jScrollPaneFinal = new JScrollPane();
		jScrollPaneFinal.setBorder(new EmptyBorder(0, 0, 0, 0));
		jScrollPaneFinal.addMouseWheelListener(mwl);
		gamesPanel.add(jScrollPaneFinal, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.LINE_START,
				GridBagConstraints.BOTH, new Insets(5, 15, 5, 5), 0, 0));

		Container contentPane = getContentPane();
		this.setModal(true);
		contentPane.setLayout(new BorderLayout());

		contentPane.add(topBarPanel, BorderLayout.BEFORE_FIRST_LINE);
		contentPane.add(mainScrollPane, BorderLayout.CENTER);

		setSize(920, 500);
		setTitle("World Cup Betting");
		setLocationRelativeTo(getOwner());
		this.setResizable(false);
	}

	private void loadBettingSheet(String betterName) {
		Vector<Tip> tips = downloadTipsFromServer(betterName);

		resetModels();

		for (Tip t : tips) {
			for (int i = 0; i < groupModels.size(); i++) {
				DefaultTableModel current = groupModels.get(i);
				for (int j = 0; j < current.getRowCount(); j++) {
					try {
						if (Integer.parseInt((String) current.getValueAt(j, 0)) == t.getGameId()) {
							current.setValueAt(t.getGoalsHome(), j, 10);
							current.setValueAt(t.getGoalsGuest(), j, 12);
							for (Game g : globalGameList) {
								if (g.getIntId() == t.getGameId() && g.isPlayed()) {
									if (g.getGoalsH() == t.getGoalsHome() && g.getGoalsG() == t.getGoalsGuest()) {
										current.setValueAt(CtrlGroup.getFlagIcon("crown"), j, 13);

									} else if ((g.getGoalsH() > g.getGoalsG() && t.getGoalsHome() > t.getGoalsGuest())
											|| (g.getGoalsH() == g.getGoalsG() && t.getGoalsHome() == t.getGoalsGuest())
											|| (g.getGoalsH() < g.getGoalsG()
													&& t.getGoalsHome() < t.getGoalsGuest())) {
										current.setValueAt(CtrlGroup.getFlagIcon("correct"), j, 13);

									} else
										current.setValueAt(CtrlGroup.getFlagIcon("false"), j, 13);

								}
							}
						}
					} catch (Exception e) {

					}
				}
			}
		}
	}

	private void resetModels() {
		for (DefaultTableModel d : groupModels) {
			for (int i = 0; i < d.getDataVector().size(); i++) {
				d.setValueAt("", i, 10);
				d.setValueAt("", i, 12);
				d.setValueAt(CtrlGroup.getFlagIcon("default"), i, 13);
			}
		}
	}

	private void uploadButtonActionPerformed() {
		Vector<Tip> tips = getTipsFromTable();
		String betterEmail = (String) betterComboBox.getSelectedItem();
		String betterPin = JOptionPane.showInputDialog("Please enter pin: ");
		if (betterPin == null || betterPin.isEmpty()) {
			return;
		}
		uploadTipsToServer(tips, betterEmail, betterPin);
	}

	private Vector<Tip> downloadTipsFromServer(String betterEmail) {
		Vector<Tip> tips = new Vector<Tip>();
		String websitename = "http://swc.dbis.info/api/Betting/"+betterEmail;
		try {
			URL website = new URL(websitename);
			InputStream datastream = website.openStream();
			JsonReader reader = Json.createReader(datastream);

			JsonArray myTips = reader.readArray();
			for(JsonObject tip: myTips.getValuesAs(JsonObject.class)) {
				int gameId = tip.getInt("gameId");
				int goalsHome = tip.getInt("goalsHome");
				int goalsGuest = tip.getInt("goalsGuest");
				Tip newTip = new Tip(gameId, goalsHome, goalsGuest);
				tips.add(newTip);
			}

			reader.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(this, "An error occured while connecting to the server: "+e.getMessage(), "Error while downloading tips", JOptionPane.ERROR_MESSAGE);
			return new Vector<Tip>();
		}
		return tips;
	}

	private void uploadTipsToServer(Vector<Tip> tips, String betterEmail, String betterPin) {
		TipUploaderThread.resetUploadSuccesful();
		ArrayList<TipUploaderThread> list = new ArrayList<>();
		for(Tip tip: tips){
			TipUploaderThread thread = new TipUploaderThread(tip, betterPin, betterEmail);
			list.add(thread);
		}

		for(TipUploaderThread thread: list){
			thread.start();
		}

		for(TipUploaderThread thread: list){
			try {
				thread.join();
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(this, "An error occured while sending the tips", "Program Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!TipUploaderThread.wasUploadSuccesful()){
				JOptionPane.showMessageDialog(this, "Wrong PIN for " + betterEmail, "PIN Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		JOptionPane.showMessageDialog(this, "Tips have been uploaded!", "Success", JOptionPane.INFORMATION_MESSAGE);
	}
}