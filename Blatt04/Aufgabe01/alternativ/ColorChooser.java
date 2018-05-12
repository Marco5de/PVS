package uebung_4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public abstract class ColorChooser extends JFrame implements ActionListener{
	protected static final long serialVersionUID = 1L;
	protected TreeMap<String, Color> map = new TreeMap<>();
	protected String [] colors = {"red", "green", "blue"};

	public ColorChooser() {
		map.put("red", Color.RED);
		map.put("green", Color.GREEN);
		map.put("blue", Color.BLUE);

		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
	}
}