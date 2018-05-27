package swc.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

		// The second menu "Extra" is for umiplemented features.
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

		// Add the menu and the main content panel.
		getContentPane().add(bar, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	/** 
	 * Handles menu events. Currently implemented are:
	 * <li> Create a new WorldCup.
	 * <li> Show the "About" dialog.
	 * <li> End the program.
	 * @param e - ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("New World Cup")) {
			CreateDialog cd = new CreateDialog(this, worldCup);
			cd.setVisible(true);
		}
		else if(action.equals("About")) {
			JOptionPane.showMessageDialog(this, "Soccer World Cup Milestone 3, \nDevelopers:\nDeuscher Marco and Jutz Benedikt", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(action.equals("Exit")) {
			System.exit(0);
		}
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
		tabPane.addTab("Finals", new FinalsPanel(worldCup.getFinals()));

		worldName.setText(worldCup.getName());
		statusText.setText(message);
	}
}