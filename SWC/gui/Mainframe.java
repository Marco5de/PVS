package swc.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import swc.ctrl.CtrlCSV;
import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.data.SoccerWC;

/**
 * Mainframe houses the main window with GroupPanels
 * and a FinalsPanel. From it, new world cups can be edited
 * and created.
 * Also, we can show the name of the world cup and the program status.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class Mainframe extends JFrame implements ActionListener{
	/**
	 * The world cup object.
	 */
	private SoccerWC worldCup;
	/**
	 * Determines whether the finals
	 * should be updated or not.
	 */
	private boolean updateFinals = false;
	/**
	 * SerialVersion UID for a javax.swing class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The JTabbedPane houses all GroupPanels and a
	 * FinalsPanel.
	 */
	private JTabbedPane tabPane = new JTabbedPane();
	/**
	 * Text labels which provide user information.
	 */
	private JLabel worldName = new JLabel("Unknown"),
			statusText = new JLabel("No world cup set.");

	/**
	 * Create a new Mainframe.
	 * @param worldCup - SoccerWC
	 */
	public Mainframe(SoccerWC worldCup) {
		// Set up the window itself.
		this.worldCup = worldCup;
		setTitle("Soccer World Cup");
		setSize(800, 600);
		setLayout(new BorderLayout());
		setIconImage(CtrlGroup.getFlagIcon("icon1").getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Create a new Menu bar.
		JMenuBar bar = new JMenuBar();
		JMenu menu;
		JMenuItem item;

		/*
		 * The first menu "File" allows to load,
		 * create and save world cups, or to end
		 * the program.
		 */
		menu = new JMenu("File");
		item = new JMenuItem("Load World Cup");
		item.addActionListener(this);
		menu.add(item);
		item = new JMenuItem("New World Cup");
		item.addActionListener(this);
		menu.add(item);
		item = new JMenuItem("Save");
		item.addActionListener(this);
		menu.add(item);
		item = new JMenuItem("Save As...");
		item.addActionListener(this);
		menu.add(item);
		item = new JMenuItem("Exit");
		item.addActionListener(this);
		menu.add(item);
		bar.add(menu);

		// The second menu "Extra" is for uminplemented features.
		menu = new JMenu("Extra");
		item = new JMenuItem("World Cup betting");
		item.addActionListener(this);
		menu.add(item);
		item = new JMenuItem("Load from server...");
		item.addActionListener(this);
		menu.add(item);
		bar.add(menu);

		// From the third menu, we can show a "About" dialog.
		menu = new JMenu("Help");
		item = new JMenuItem("About");
		item.addActionListener(this);
		menu.add(item);
		bar.add(menu);

		/*
		 * Create a new JPanel which shows the program status.
		 * Also, take care of text formatting. 
		 */
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		JPanel statusPanel = new JPanel(layout);
		Font font = new Font(null, Font.BOLD, 14);
		statusPanel.add(new JLabel("World Cup"));
		worldName.setFont(font);
		statusPanel.add(worldName);
		statusPanel.add(new JLabel("Status: "));
		font = new Font(null, Font.BOLD, 13);
		statusText.setFont(font);
		statusText.setText("No world cup set.");
		statusPanel.add(statusText);

		/*
		 * A main panel houses the JTabbedPane and the status panel.
		 */
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(statusPanel, BorderLayout.NORTH);
		mainPanel.add(tabPane, BorderLayout.CENTER);
		/*
		 * Add a change listener which will trigger
		 * the recalculation of the finals.
		 */
		tabPane.addChangeListener(new ChangeListener() {
			// The last tab should be the finals tab.
			@Override
			public void stateChanged(ChangeEvent e) {
				int index = tabPane.getSelectedIndex();
				if(index == tabPane.getTabCount() - 1 && updateFinals) {
					CtrlFinals.calculateFinals(worldCup);
				}
			}
		});

		// Add the menu and the main content panel.
		getContentPane().add(bar, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	/** 
	 * Handles menu events. Currently implemented are:
	 * <li> Create a new WorldCup.
	 * <li> Load a new world cup from a CSV file.
	 * <li> Show the "About" dialog.
	 * <li> End the program.
	 * @param e - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		/*
		 * Open a FileChooser to load a .csv-file
		 * from which a world cup can be read in.
		 */
		if(action.equals("Load World Cup")) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text or CSV files", "txt", "csv");
			JFileChooser fileOpener = new JFileChooser();
			fileOpener.setDialogTitle("Load a world cup from a .csv file");
			fileOpener.setFileFilter(filter);

			int option = fileOpener.showOpenDialog(this);
			if(option == JFileChooser.APPROVE_OPTION) {
				try {
					updateFinals = false;
					String filename = fileOpener.getSelectedFile().getPath();
					SoccerWC newWorldCup = CtrlCSV.createFromFile(filename);
					this.worldCup = newWorldCup;
					CtrlFinals.calculateFinals(newWorldCup);
					updateView("New world cup successfuly loaded!");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex.toString(), "Couldn't load world cup from file", JOptionPane.ERROR_MESSAGE);
				}
				finally{
					updateFinals = true;
				}
			}
		}
		/*
		 * Open the "Create new world cup" dialogue.
		 */
		else if(action.equals("New World Cup")) {
			CreateDialog cd = new CreateDialog(this, worldCup);
			cd.setVisible(true);
		}
		/*
		 * Save a .csv file to a known location.
		 * If unknown, call saveWorldCup().
		 */
		else if(action.equals("Save")) {
			if(worldCup.getFilename().equals(""))
				saveWorldCupToNewLocation();
			else {
				try {
					CtrlCSV.writeToCSV(worldCup, worldCup.getFilename());
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex.toString(), "Error while saving a .csv file", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(action.equals("Save As...")) {
			saveWorldCupToNewLocation();
		}
		/*
		 * Show an "About" screen.
		 */
		else if(action.equals("About")) {
			JOptionPane.showMessageDialog(this, "Soccer World Cup Milestone 5, \nDevelopers:\nDeuscher Marco and Jutz Benedikt", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		/*
		 * Exit the program without warning.
		 */
		else if(action.equals("Exit")) {
			System.exit(0);
		}
	}

	/**
	 * Opens a JFileChooser to save a .csv file.
	 * Will also print error messages if necessary.
	 */
	private void saveWorldCupToNewLocation() {
		JFileChooser fileChooser = new JFileChooser();
		int save = fileChooser.showSaveDialog(this);
		String path = fileChooser.getSelectedFile().getPath();
		try {
			CtrlCSV.writeToCSV(worldCup, path);
			worldCup.setFilename(path);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(this, ex.toString(), "Error while saving a .csv file", JOptionPane.ERROR_MESSAGE);
		}				
		System.out.println(path);
	}

	/**
	 * To be called when a new WorldCup object is loaded.
	 * Creates the GroupPanels and the FinalsPanel. Also
	 * shows a custom message on the status panel.
	 * 
	 * @param message - String
	 */
	public void updateView(String message) {
		tabPane.removeAll();
		for(swc.data.Group group: worldCup.getGroups()) {
			tabPane.addTab(group.getStrGroupName(), new GroupPanel(group));
		}
		tabPane.addTab("Finals", new FinalsPanel(worldCup));
		worldName.setText(worldCup.getName());
		statusText.setText(message);
		updateFinals = true;
	}
}