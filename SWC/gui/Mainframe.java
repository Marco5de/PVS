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

		// Add the menu and the JTabbedPane.
		getContentPane().add(bar, BorderLayout.NORTH);
		getContentPane().add(tabPane, BorderLayout.CENTER);
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
	 * Create the GroupPanesl and the FinalsPanel.
	 * To be called when a new WorldCup object is loaded.
	 */
	public void loadTabs() {
		tabPane.removeAll();
		for(swc.data.Group group: worldCup.getGroups()) {
			tabPane.addTab(group.getStrGroupName(), new GroupPanel(group));
		}
		tabPane.addTab("Finals", new FinalsPanel(worldCup.getFinals()));
	}
}