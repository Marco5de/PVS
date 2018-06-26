package swc.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import swc.ctrl.CtrlGroup;
import swc.data.Game;
import swc.data.Group;
import swc.data.Team;

public class GroupPanel extends JPanel {
	private static final long serialVersionUID = 14335377L;
	private Group group;
	private Mainframe parent;
	
	public GroupPanel(Mainframe parent, Group toSet){
		this.group = toSet;
		this.parent = parent;
		initComponents();
		drawScoreboard();
		drawMatches();
	}
	
	private void initComponents() {	
		this.setLayout(new GridLayout());
		setLayout(new GridBagLayout());
		((GridBagLayout)this.getLayout()).columnWidths = new int[] {0, 0};
		((GridBagLayout)this.getLayout()).rowHeights = new int[] {20, 101, 20, 133, 0};
		((GridBagLayout)this.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
		((GridBagLayout)this.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
		
		labelGroup = new JLabel();
		Font f = labelGroup.getFont();
		labelGroup.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		labelGroup.setText("Table for "+ group.getStrGroupName()+ " - Top two teams will advance.");
		labelGroup.setSize(522, 18);
		this.add(labelGroup, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.0,
				GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH,
				new Insets(10, 15, 5, 5), 0, 0));
		
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setPreferredSize(new Dimension(622, 91));
		jScrollPane1.setBorder(new EmptyBorder(0,0,0,0));
		this.add(jScrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(5, 15, 5, 5), 0, 0));
			
		labelMatches = new JLabel();
		labelMatches.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));		
		labelMatches.setText("Matches for "+ group.getStrGroupName()+":");		
		labelMatches.setSize(193, 16);
		this.add(labelMatches, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(15, 15, 5, 5), 0, 0));
		
		jScrollPane2 = new JScrollPane();		
		jScrollPane2.setSize(622, 123);
		jScrollPane2.setBorder(new EmptyBorder(0,0,0,0));
		this.add(jScrollPane2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(5, 15, 5, 5), 0, 0));
	}

	@SuppressWarnings("serial")
	private void drawScoreboard() {
		Object[][] data = new Object[4][11];
		String[] columns =  { "#", "", "Team", "Played", "Won", "Draw", "Loss", "GF", "GA", "Difference", "Points" };
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + (i+1);
			data[i][2] = "" + group.getTeams().get(i).getName();
			data[i][3] = "" + group.getTeams().get(i).getPlayed();
			data[i][4] = "" + group.getTeams().get(i).getWon();
			data[i][5] = "" + group.getTeams().get(i).getDraw();
			data[i][6] = "" + group.getTeams().get(i).getLoss();
			data[i][7] = "" + group.getTeams().get(i).getGf();
			data[i][8] = "" + group.getTeams().get(i).getGa();
			data[i][9] = "" + (group.getTeams().get(i).getGf() - group.getTeams().get(i).getGa());
			data[i][10] = "" + group.getTeams().get(i).getPoints();
			
			try{
				data[i][1] = CtrlGroup.getFlagIcon(group.getTeams().get(i).getName());
			}catch (Exception e){
				e.printStackTrace();
			}
		}
			
		model1 = new DefaultTableModel(data, columns){
			public boolean isCellEditable(int row,
                    int column){
				return false;
			}
		};
		table = new JTable(model1){
			//  Returning the Class of each column will allow different
			//  renderers to be used based on Class
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int column){
				return getValueAt(0, column).getClass();
			}
		};
		((JLabel)table.getDefaultRenderer(String.class)).setHorizontalAlignment (JLabel.CENTER); 
		table.setIntercellSpacing(new Dimension(0,0));
		
		// Cell Borders
		Color color = table.getGridColor();
	    BorderCellRenderer renderer = new BorderCellRenderer();
	    TableColumnModel model = table.getColumnModel();
	     
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(0).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(1).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(2).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(3).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(4).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(5).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(6).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(7).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(8).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(9).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,1)); 	   
	    model.getColumn(10).setCellRenderer(renderer);
			
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		int vColIndex = 1; 
		int width = 30; 
		TableColumn col = table.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 2; 
		width = 90; 
		col = table.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 4; 
		width = 40; 
		col = table.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 5; 
		width = 40; 
		col = table.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 6; 
		width = 40;  
		col = table.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 7; 
		width = 40; 
		col = table.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 8; 
		width = 40; 
		col = table.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 	
		
		MouseListener tableMouseListener = new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {	
			}
			
			public void mousePressed(MouseEvent arg0) {		
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {			
			}
			
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					Team selected = group.getTeams().get(table.getSelectedRow());
					EditGroupDialog egd = new EditGroupDialog(parent, selected);
					egd.setVisible(true);
					if(egd.wasSuccessful()){
						parent.callFinalCalucalion();
						refreshMatches();
						refreshScoreboard();
					}
				}
			}
		};	
		table.addMouseListener(tableMouseListener);
		
		((JLabel)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment (JLabel.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(table);
		table.setBounds(0, 22, 518, 32);
	}
	
	private void refreshScoreboard() {
		Object[][] data = new Object[4][11];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + (i+1);
			data[i][2] = "" + group.getTeams().get(i).getName();
			data[i][3] = "" + group.getTeams().get(i).getPlayed();
			data[i][4] = "" + group.getTeams().get(i).getWon();
			data[i][5] = "" + group.getTeams().get(i).getDraw();
			data[i][6] = "" + group.getTeams().get(i).getLoss();
			data[i][7] = "" + group.getTeams().get(i).getGf();
			data[i][8] = "" + group.getTeams().get(i).getGa();
			data[i][9] = "" + (group.getTeams().get(i).getGf() - group.getTeams().get(i).getGa());
			data[i][10] = "" + group.getTeams().get(i).getPoints();
			
			try{
				data[i][1] = CtrlGroup.getFlagIcon(group.getTeams().get(i).getName());
			}catch (Exception e){
				e.printStackTrace();
			}	
			model1.setValueAt(data[i][0], i, 0);
			model1.setValueAt(data[i][1], i, 1);
			model1.setValueAt(data[i][2], i, 2);
			model1.setValueAt(data[i][3], i, 3);
			model1.setValueAt(data[i][4], i, 4);
			model1.setValueAt(data[i][5], i, 5);
			model1.setValueAt(data[i][6], i, 6);
			model1.setValueAt(data[i][7], i, 7);
			model1.setValueAt(data[i][8], i, 8);
			model1.setValueAt(data[i][9], i, 9);
			model1.setValueAt(data[i][10], i, 10);
		}
	}

	@SuppressWarnings("serial")
	private void drawMatches() {
		Object[][] data = new Object[6][9];
		String[] columns =  { "Match", "Date", "Time", "Venue", "", "", "Result", "", "" };
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + group.getGames().get(i).getIntId();
			data[i][1] = "" + group.getGames().get(i).getDate();
			data[i][2] = "" + group.getGames().get(i).getTime();
			data[i][3] = "" + group.getGames().get(i).getLocation();
			data[i][5] = "" + group.getGames().get(i).getTeamH().getName();
			if(group.getGames().get(i).isPlayed())
				data[i][6] = group.getGames().get(i).getGoalsH() + " - " + group.getGames().get(i).getGoalsG();
			else
				data[i][6] = " - ";
			data[i][7] = "" + group.getGames().get(i).getTeamG().getName();			
			try{
				data[i][4] = CtrlGroup.getFlagIcon(group.getGames().get(i).getTeamH().getName());
				data[i][8] = CtrlGroup.getFlagIcon(group.getGames().get(i).getTeamG().getName());
			}catch (Exception e){
				e.printStackTrace();
			}		
		}	
		
		model2 = new DefaultTableModel(data, columns){
			public boolean isCellEditable(int row,
                    int column){
				return false;
			}
		};
		tableMatches = new JTable(model2){
			//  Returning the Class of each column will allow different
			//  renderers to be used based on Class
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int column){
				return getValueAt(0, column).getClass();
			}
		};
		((JLabel)tableMatches.getDefaultRenderer(String.class)).setHorizontalAlignment (JLabel.CENTER); 
		tableMatches.setIntercellSpacing(new Dimension(0,0));
		
		// Cell Borders
		Color color = tableMatches.getGridColor();
	    BorderCellRenderer renderer = new BorderCellRenderer();
	    TableColumnModel model = tableMatches.getColumnModel();
	     
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(0).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(1).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(2).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(3).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(4).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(5).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(6).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(7).setCellRenderer(renderer);
	    renderer = createRenderer(color, new Insets(0,0,1,0)); 	   
	    model.getColumn(8).setCellRenderer(renderer);
	   
		
		tableMatches.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		int vColIndex = 1; 
		int width = 50; 
		TableColumn col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 2; 
		width = 50; 
		col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 3; 
		width = 115; 
		col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 4; 
		width = 30; 
		col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 5; 
		width = 110; 
		col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 6; 
		width = 50;  
		col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 7; 
		width = 110; 
		col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 
		
		vColIndex = 8; 
		width = 30; 
		col = tableMatches.getColumnModel().getColumn(vColIndex); 
		col.setPreferredWidth(width); 	
		
		MouseListener tableMouseListener = new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {	
			}
			
			public void mousePressed(MouseEvent arg0) {		
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {			
			}
			
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					Game selected = group.getGames().get(tableMatches.getSelectedRow());
					EditGameDialog egd = new EditGameDialog(parent, selected);
					egd.setVisible(true);
					if(egd.wasSuccessful()){
						CtrlGroup.calculateGroupTable(group);
						if(group.isGroupCompleted())
							parent.callFinalCalucalion();
						parent.createHeadLine();
						refreshMatches();
						refreshScoreboard();
					}
				}
			}
		};	
		tableMatches.addMouseListener(tableMouseListener);
		
		((JLabel)tableMatches.getTableHeader().getDefaultRenderer()).setHorizontalAlignment (JLabel.CENTER);
		tableMatches.getTableHeader().setReorderingAllowed(false);
		jScrollPane2.setViewportView(tableMatches);
		tableMatches.setBounds(0, 22, 518, 32);
	}

	private void refreshMatches() {
		Object[][] data = new Object[6][9];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + group.getGames().get(i).getIntId();
			data[i][1] = "" + group.getGames().get(i).getDate();
			data[i][2] = "" + group.getGames().get(i).getTime();
			data[i][3] = "" + group.getGames().get(i).getLocation();
			data[i][5] = "" + group.getGames().get(i).getTeamH().getName();
			if(group.getGames().get(i).isPlayed())
				data[i][6] = group.getGames().get(i).getGoalsH() + " - " + group.getGames().get(i).getGoalsG();
			else
				data[i][6] = " - ";
			data[i][7] = "" + group.getGames().get(i).getTeamG().getName();			
			try{
				data[i][4] = CtrlGroup.getFlagIcon(group.getGames().get(i).getTeamH().getName());
				data[i][8] = CtrlGroup.getFlagIcon(group.getGames().get(i).getTeamG().getName());
			}catch (Exception e){
				e.printStackTrace();
			}		
			model2.setValueAt(data[i][0], i, 0);
			model2.setValueAt(data[i][1], i, 1);
			model2.setValueAt(data[i][2], i, 2);
			model2.setValueAt(data[i][3], i, 3);
			model2.setValueAt(data[i][4], i, 4);
			model2.setValueAt(data[i][5], i, 5);
			model2.setValueAt(data[i][6], i, 6);
			model2.setValueAt(data[i][7], i, 7);
			model2.setValueAt(data[i][8], i, 8);
		}	
	}

	private static BorderCellRenderer createRenderer(Color color, Insets insets) {
	    BorderCellRenderer renderer = new BorderCellRenderer();
	    renderer.setColumnBorder(new LinesBorder(color, insets));    
	    return renderer;
	 }  
	
	private JLabel labelGroup;
	private JTable table;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTable tableMatches;
	private JLabel labelMatches;
	private TableModel model1;
	private TableModel model2;

}
