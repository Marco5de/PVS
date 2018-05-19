package swc.gui;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import swc.ctrl.CtrlGroup;
import swc.data.SoccerWC;

public class Mainframe extends JFrame implements ActionListener{
	private SoccerWC worldCup;
	private static final long serialVersionUID = 1L;

	public Mainframe(SoccerWC worldCup) {
		this.worldCup = worldCup;
		setTitle("Soccer World Cup");
		setSize(600, 400);
		setLayout(new BorderLayout());
		setIconImage(CtrlGroup.getFlagIcon("icon1").getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JMenuBar bar = new JMenuBar();
		JMenu menu;
		JMenuItem item;

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

		menu = new JMenu("Extra");
		item = new JMenuItem("World Cup betting");
		item.addActionListener(this);
		menu.add(item);
		item = new JMenuItem("Load from server...");
		item.addActionListener(this);
		menu.add(item);
		bar.add(menu);

		menu = new JMenu("Help");
		item = new JMenuItem("About");
		item.addActionListener(this);
		menu.add(item);
		bar.add(menu);

		getContentPane().add(bar, BorderLayout.NORTH);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("New World Cup")) {
			CreateDialog cd = new CreateDialog(this, worldCup);
			cd.setVisible(true);
		}
		else if(action.equals("About")) {
			JOptionPane.showMessageDialog(this, "Soccer World Cup Milestone 2, \nDevelopers:\nDeuscher Marco and Jutz Benedikt", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}