package swc.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.data.SoccerWC;
import swc.file.CSVFiles;
import swc.file.XMLFiles;

/**
 * Mainframe houses the main window with GroupPanels
 * and a FinalsPanel. From it, new world cups can be edited and created.
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
	 * listener will call updateFinals when
	 * the Finals tab is selected.
	 */
	private TabListener listener;
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
		listener = new TabListener(worldCup, tabPane);
		tabPane.addChangeListener(listener);

		// Add the menu and the main content panel.
		getContentPane().add(bar, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	/** 
	 * Handles menu events. Currently implemented are:
	 * <li> Create a new WorldCup.
	 * <li> Load a new world cup from a CSV or XML file.
	 * <li> Save a world cup to such an file.
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
			loadWorldCup();
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
			saveWorldCup();
		}
		else if(action.equals("Save As...")) {
			saveWorldCupToNewLocation();
		}
		/*
		 * Exit the program without warning.
		 */
		else if(action.equals("Exit")) {
			System.exit(0);
		}
		/*
		 * Show an "About" screen.
		 */
		else if(action.equals("About")) {
			JOptionPane.showMessageDialog(this, "Soccer World Cup Milestone 6, \nDevelopers:\nDeuscher Marco and Jutz Benedikt", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		/*
		 * Open up the World cup betting dialogue.
		 */
		else if(action.equals("World Cup betting")) {
			BettingDialog bd = new BettingDialog(this, worldCup);
			bd.setVisible(true);
		}
	}

	/**
	 * Opens a JFileChooser which allows to load
	 * a world cup from a .xml or .csv file.
	 */
	private void loadWorldCup() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML or CSV files", "xml", "csv");
		JFileChooser fileOpener = new JFileChooser();
		fileOpener.setDialogTitle("Load a world cup from a .xml or .csv file");
		fileOpener.setFileFilter(filter);

		int option = fileOpener.showOpenDialog(this);
		if(option == JFileChooser.APPROVE_OPTION) {
			try {
				listener.setUpdateFinals(false);
				String filename = fileOpener.getSelectedFile().getPath();
				String [] path = filename.split("\\.");
				String ending = path[path.length-1];
				SoccerWC newWorldCup;
				if(ending.equals("xml"))
					newWorldCup = XMLFiles.createFromXMLFile(filename);
				else
					newWorldCup = CSVFiles.createFromFile(filename);
				newWorldCup.setFilename(filename);
				this.worldCup = newWorldCup;
				listener.setWorldCup(worldCup);
				CtrlFinals.calculateFinals(newWorldCup);
				updateView("New world cup successfuly loaded!");
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(this, ex.toString(), "Couldn't load world cup from file", JOptionPane.ERROR_MESSAGE);
			}
			finally{
				listener.setUpdateFinals(true);
			}
		}
	}

	/**
	 * Saves a world cup to a (new) location.
	 */
	private void saveWorldCup() {
		if(worldCup.getFilename() == null) {
			JOptionPane.showMessageDialog(this, "A world cup must be created or loaded first.", "Cannot save to a file", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(worldCup.getFilename().equals(""))
			saveWorldCupToNewLocation();
		else {
			try {
				String [] path = worldCup.getFilename().split("\\.");
				String ending = path[path.length-1];
				if(ending.equals("xml"))
					XMLFiles.writeToXMLFile(worldCup.getFilename(), worldCup);
				else
					CSVFiles.writeToCSV(worldCup, worldCup.getFilename());
				JOptionPane.showMessageDialog(this, "World cup data saved to "+worldCup.getFilename(), "Saving successful", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(this, ex.toString(), "Error while saving the world cup file", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Opens a JFileChooser to save a .csv or .xml file.
	 * Will also print error messages if necessary.
	 */
	private void saveWorldCupToNewLocation() {
		if(worldCup.getName() == null) {
			JOptionPane.showMessageDialog(this, "A world cup must be created or loaded first.", "Cannot save to a file", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		JFileChooser fileChooser = new JFileChooser();
		int save = fileChooser.showSaveDialog(this);
		if(save != JFileChooser.APPROVE_OPTION)
			return;

		String fileName = fileChooser.getSelectedFile().getPath();
		try {
			String [] path = fileName.split("\\.");
			String ending = path[path.length-1];
			if(ending.equals("xml"))
				XMLFiles.writeToXMLFile(fileName, worldCup);
			else
				CSVFiles.writeToCSV(worldCup, fileName);
			worldCup.setFilename(fileName);
			JOptionPane.showMessageDialog(this, "World cup data saved to "+worldCup.getFilename(), "Saving successful", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(this, ex.toString(), "Error while saving a world cup file", JOptionPane.ERROR_MESSAGE);
		}				
		System.out.println(fileName);
	}

	/**
	 * To be called when a new WorldCup object is loaded.
	 * Creates the GroupPanels and the FinalsPanel. Also
	 * shows a custom message on the status panel.
	 * 
	 * @param message - String
	 */
	public void updateView(String message) {
		listener.setUpdateFinals(false);
		tabPane.removeAll();
		for(swc.data.Group group: worldCup.getGroups()) {
			tabPane.addTab(group.getStrGroupName(), new GroupPanel(group));
		}
		tabPane.addTab("Finals", new FinalsPanel(worldCup));
		worldName.setText(worldCup.getName());
		listener.setWorldCup(worldCup);
		statusText.setText(message);
		listener.setUpdateFinals(true);
	}
}

/**
 * TabListener is an auxiliary class for
 * triggering {@link CtrlFinals#calculateFinals(SoccerWC)}
 * when the Finals tab is selected.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
final class TabListener implements ChangeListener{
	/**
	 * Can be set to determine whether the
	 * finals should be updated or not.
	 */
	private boolean updateFinals = false;
	/**
	 * The world cup to calculate finals for.
	 */
	private SoccerWC worldCup;
	/**
	 * The tab pane to monitor.
	 */
	private JTabbedPane tabPane;

	/**
	 * Sets whether to update the finals or not.
	 * @param newValue - boolean
	 */
	void setUpdateFinals(boolean newValue){
		updateFinals = newValue;
	}

	/**
	 * Sets the world cup to look at.
	 * @param worldCup - SoccerWC
	 */
	void setWorldCup(SoccerWC worldCup) {
		this.worldCup = worldCup;
	}

	/**
	 * Creates a new TabListener.
	 * @param worldCup - SoccerWC
	 * @param tabPane - JTabbedPane
	 */
	TabListener(SoccerWC worldCup, JTabbedPane tabPane){
		this.worldCup = worldCup;
		this.tabPane = tabPane;	
	}

	/**
	 * The main method. When the worldCup object is initialized
	 * and the last tab(for the finals) is selected, calculate the finals.
	 * @param e - ChangeEvent
	 */
	public void stateChanged(ChangeEvent e) {
		int index = tabPane.getSelectedIndex();
		if(index == tabPane.getTabCount() - 1 && updateFinals) {
			CtrlFinals.calculateFinals(worldCup);
		}
	}
}