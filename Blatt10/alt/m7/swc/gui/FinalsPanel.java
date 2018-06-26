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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
import swc.data.Final;
import swc.data.Game;

public class FinalsPanel extends JPanel {
	private static final long serialVersionUID = 134535L;
	private Mainframe parent;
	private Final finals;
	private JScrollPane parentScrollPane;
	
	public FinalsPanel(Mainframe parent, Final toSet, JScrollPane pSP){
		this.finals = toSet;
		this.parent = parent;
		this.parentScrollPane = pSP;
		initComponents();
		drawMatches();
	}
	
	@SuppressWarnings("serial")
	private void initComponents() {
		this.setLayout(new GridLayout());
		setLayout(new GridBagLayout());
		((GridBagLayout)this.getLayout()).columnWidths = new int[] {0, 0};
		((GridBagLayout)this.getLayout()).rowHeights = new int[] {10, 170, 10, 106, 10, 74, 10, 58, 10, 58, 180, 0};
		((GridBagLayout)this.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
		((GridBagLayout)this.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
		
		//setting up labels and scrollpanes
		label16 = new JLabel();
		Font f = label16.getFont();
		label16.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		label16.setText("Round of 16: ");
		this.add(label16, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.0,
				GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH,
				new Insets(55, 15, 5, 5), 0, 0));
		
		jScrollPane1 = new JScrollPane();
		this.add(jScrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(5, 15, 5, 5), 0, 0));
		jScrollPane1.setBorder(new EmptyBorder(0,0,0,0));
		jScrollPane1.setFocusable(false);
		jScrollPane1.addMouseWheelListener(new MouseWheelListener() {			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelListener[] tmp = parentScrollPane.getMouseWheelListeners();
				for (int i = 0; i < tmp.length; i++) {
					tmp[i].mouseWheelMoved(e);
				}
			}
		});
		
		labelQuarter = new JLabel();
		f = labelQuarter.getFont();
		labelQuarter.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		labelQuarter.setText("Quarterfinals: ");
		this.add(labelQuarter, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(15, 15, 5, 5), 0, 0));
		
		jScrollPane2 = new JScrollPane();
		this.add(jScrollPane2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(5, 15, 5, 5), 0, 0));
		jScrollPane2.setBorder(new EmptyBorder(0,0,0,0));
		jScrollPane2.addMouseWheelListener(new MouseWheelListener() {			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelListener[] tmp = parentScrollPane.getMouseWheelListeners();
				for (int i = 0; i < tmp.length; i++) {
					tmp[i].mouseWheelMoved(e);
				}
			}
		});
		
		labelSemi = new JLabel();
		f = labelSemi.getFont();
		labelSemi.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		labelSemi.setText("Semifinals: ");
		this.add(labelSemi, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(15, 15, 5, 5), 0, 0));
		
		jScrollPane3 = new JScrollPane();
		this.add(jScrollPane3, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(5, 15, 5, 5), 0, 0));
		jScrollPane3.setBorder(new EmptyBorder(0,0,0,0));
		jScrollPane3.addMouseWheelListener(new MouseWheelListener() {			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelListener[] tmp = parentScrollPane.getMouseWheelListeners();
				for (int i = 0; i < tmp.length; i++) {
					tmp[i].mouseWheelMoved(e);
				}
			}
		});
		
		labelThird = new JLabel();
		f = labelThird.getFont();
		labelThird.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		labelThird.setText("Match for third Place: ");
		this.add(labelThird, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(15, 15, 5, 5), 0, 0));
		
		jScrollPane4 = new JScrollPane();
		this.add(jScrollPane4, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(5, 15, 5, 5), 0, 0));
		jScrollPane4.setBorder(new EmptyBorder(0,0,0,0));
		jScrollPane4.addMouseWheelListener(new MouseWheelListener() {			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelListener[] tmp = parentScrollPane.getMouseWheelListeners();
				for (int i = 0; i < tmp.length; i++) {
					tmp[i].mouseWheelMoved(e);
				}
			}
		});
		
		labelFinal = new JLabel();
		f = labelFinal.getFont();
		labelFinal.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		labelFinal.setText("Final: ");
		this.add(labelFinal, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(15, 15, 5, 5), 0, 0));
		
		jScrollPane5 = new JScrollPane();
		this.add(jScrollPane5, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(5, 15, 5, 5), 0, 0));
		jScrollPane5.setBorder(new EmptyBorder(0,0,0,0));
		jScrollPane5.addMouseWheelListener(new MouseWheelListener() {			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelListener[] tmp = parentScrollPane.getMouseWheelListeners();
				for (int i = 0; i < tmp.length; i++) {
					tmp[i].mouseWheelMoved(e);
				}
			}
		});
		
		JPanel winnerPanel = new JPanel();
		winnerPanel.setLayout(null);
		
		labelWinner = new JLabel();
		labelWinner.setFont(new Font("Arial", Font.BOLD, 16));
		labelWinner.setText("World Cup Winner: ");
		labelWinner.setBounds(260, 0, 200, 30);
		winnerPanel.add(labelWinner);
		
		labelCup1 = new JLabel();
		labelCup1.setBounds(220, 10, 30, 60);
		winnerPanel.add(labelCup1);
		
		labelCup2 = new JLabel();
		labelCup2.setBounds(425, 10, 30, 60);
		winnerPanel.add(labelCup2);
		
		labelWinnerFlag = new JLabel();
		labelWinnerFlag.setBounds(280, 30, 30, 30);
		winnerPanel.add(labelWinnerFlag);
		
		labelWinnerName = new JLabel();
		labelWinnerName.setFont(new Font("Arial", Font.BOLD, 14));
		labelWinnerName.setBounds(310, 30, 200, 30);
		winnerPanel.add(labelWinnerName);
		
		this.add(winnerPanel, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(25, 15, 5, 5), 0, 0));
		
		// setting up tables
		
		// round of 16
			Object[][] data16 = new Object[8][9];
			String[] columns =  { "Match", "Date", "Time", "Venue", "", "", "Result", "", "" };
			
			model1 = new DefaultTableModel(data16, columns){
				public boolean isCellEditable(int row,
	                    int column){
					return false;
				}
			};
			table16 = new JTable(model1){
				//  Returning the Class of each column will allow different
				//  renderers to be used based on Class
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int column){
					return getValueAt(0, column).getClass();
				}
			};
		
			((JLabel)table16.getDefaultRenderer(String.class)).setHorizontalAlignment (JLabel.CENTER); 
			table16.setIntercellSpacing(new Dimension(0,0));
			
			// Cell Borders
			Color color = table16.getGridColor();
		    BorderCellRenderer renderer = new BorderCellRenderer();
		    TableColumnModel model = table16.getColumnModel();
		    
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
		    
		    table16.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
			((JLabel)table16.getTableHeader().getDefaultRenderer()).setHorizontalAlignment (JLabel.CENTER);
			table16.getTableHeader().setReorderingAllowed(false);
			
		// quarterfinals
			
			Object[][] dataQuarter = new Object[4][9];
			
			model2 = new DefaultTableModel(dataQuarter, columns){
				public boolean isCellEditable(int row,
	                    int column){
					return false;
				}
			};
			tableQuarter = new JTable(model2){
				//  Returning the Class of each column will allow different
				//  renderers to be used based on Class
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int column){
					return getValueAt(0, column).getClass();
				}
			};
		
			((JLabel)tableQuarter.getDefaultRenderer(String.class)).setHorizontalAlignment (JLabel.CENTER); 
			tableQuarter.setIntercellSpacing(new Dimension(0,0));
			
			// Cell Borders
			
			model = tableQuarter.getColumnModel();	   
			
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
		    
		    tableQuarter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
			((JLabel)tableQuarter.getTableHeader().getDefaultRenderer()).setHorizontalAlignment (JLabel.CENTER);
			tableQuarter.getTableHeader().setReorderingAllowed(false);
			
		// semifinals
			
			Object[][] dataSemi = new Object[2][9];
			
			model3 = new DefaultTableModel(dataSemi, columns){
				public boolean isCellEditable(int row,
	                    int column){
					return false;
				}
			};
			tableSemi = new JTable(model3){
				//  Returning the Class of each column will allow different
				//  renderers to be used based on Class
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int column){
					return getValueAt(0, column).getClass();
				}
			};
		
			((JLabel)tableSemi.getDefaultRenderer(String.class)).setHorizontalAlignment (JLabel.CENTER); 
			tableSemi.setIntercellSpacing(new Dimension(0,0));
			
			// Cell Borders
			model = tableSemi.getColumnModel();

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
			
		    tableSemi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
			((JLabel)tableSemi.getTableHeader().getDefaultRenderer()).setHorizontalAlignment (JLabel.CENTER);
			tableSemi.getTableHeader().setReorderingAllowed(false);
		
		// match for third
			
			Object[][] dataThird = new Object[1][9];
			
			model4 = new DefaultTableModel(dataThird, columns){
				public boolean isCellEditable(int row,
	                    int column){
					return false;
				}
			};
			tableThird = new JTable(model4){
				//  Returning the Class of each column will allow different
				//  renderers to be used based on Class
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int column){
					return getValueAt(0, column).getClass();
				}
			};
		
			((JLabel)tableThird.getDefaultRenderer(String.class)).setHorizontalAlignment (JLabel.CENTER); 
			tableThird.setIntercellSpacing(new Dimension(0,0));
			
			// Cell Borders
			model = tableThird.getColumnModel();

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
		    
		    tableThird.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
			((JLabel)tableThird.getTableHeader().getDefaultRenderer()).setHorizontalAlignment (JLabel.CENTER);
			tableThird.getTableHeader().setReorderingAllowed(false);	
			
		// final
			
			Object[][] dataFinal = new Object[1][9];
			
			model5 = new DefaultTableModel(dataFinal, columns){
				public boolean isCellEditable(int row,
	                    int column){
					return false;
				}
			};
			tableFinal = new JTable(model5){
				//  Returning the Class of each column will allow different
				//  renderers to be used based on Class
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int column){
					return getValueAt(0, column).getClass();
				}
			};
		
			((JLabel)tableFinal.getDefaultRenderer(String.class)).setHorizontalAlignment (JLabel.CENTER); 
			tableFinal.setIntercellSpacing(new Dimension(0,0));
			
			// Cell Borders
			model = tableFinal.getColumnModel();

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
		    
		    tableFinal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
			((JLabel)tableFinal.getTableHeader().getDefaultRenderer()).setHorizontalAlignment (JLabel.CENTER);
			tableFinal.getTableHeader().setReorderingAllowed(false);
			
		// setup column sizes
			
			int vColIndex = 1; 
			int width = 50; 
			TableColumn col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
			vColIndex = 2; 
			width = 50; 
			col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
			vColIndex = 3; 
			width = 115; 
			col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
			vColIndex = 4; 
			width = 30; 
			col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
			vColIndex = 5; 
			width = 110; 
			col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
			vColIndex = 6; 
			width = 50;  
			col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
			vColIndex = 7; 
			width = 110; 
			col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
			vColIndex = 8; 
			width = 30; 
			col = table16.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 	
			col = tableQuarter.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableSemi.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableFinal.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width); 
			col = tableThird.getColumnModel().getColumn(vColIndex); 
			col.setPreferredWidth(width);
			
		// setting up listener
			
			MouseListener table16MouseListener = new MouseListener() {
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
						Game selected = finals.getRoundOf16().get(table16.getSelectedRow());
						if(selected.getTeamH().getName().length() > 2 && 
								selected.getTeamG().getName().length() > 2){
							EditGameDialog egd = new EditGameDialog(parent, selected);
							egd.setVisible(true);
							if(egd.wasSuccessful()){
								parent.callFinalCalucalion();
								parent.createHeadLine();
							}
						}
					}
				}
			};	
			MouseListener tableQuarterMouseListener = new MouseListener(){
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
						Game selected = finals.getQuarterFinals().get(tableQuarter.getSelectedRow());
						if(selected.getTeamH().getName().length() > 3 && 
								selected.getTeamG().getName().length() > 3){
							EditGameDialog egd = new EditGameDialog(parent, selected);
							egd.setVisible(true);
							if(egd.wasSuccessful()){
								parent.callFinalCalucalion();
								parent.createHeadLine();
							}
						}
					}
				}
			};	
			MouseListener tableSemiMouseListener = new MouseListener(){
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
						Game selected = finals.getSemiFinals().get(tableSemi.getSelectedRow());
						if(selected.getTeamH().getName().length() > 3 && 
								selected.getTeamG().getName().length() > 3){
							EditGameDialog egd = new EditGameDialog(parent, selected);
							egd.setVisible(true);
							if(egd.wasSuccessful()){
								parent.callFinalCalucalion();
								parent.createHeadLine();
							}
						}
					}
				}
			};	
			MouseListener tableThirdMouseListener = new MouseListener(){
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
						Game selected = finals.getThirdGame();
						if(selected.getTeamH().getName().length() > 3 && 
								selected.getTeamG().getName().length() > 3){
							EditGameDialog egd = new EditGameDialog(parent, selected);
							egd.setVisible(true);
							if(egd.wasSuccessful()){
								parent.callFinalCalucalion();
								parent.createHeadLine();
							}
						}
					}
				}
			};	
			MouseListener tableFinalMouseListener = new MouseListener(){
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
						Game selected = finals.getFinalGame();
						if(selected.getTeamH().getName().length() > 3 && 
								selected.getTeamG().getName().length() > 3){
							EditGameDialog egd = new EditGameDialog(parent, selected);
							egd.setVisible(true);
							if(egd.wasSuccessful()){
								parent.callFinalCalucalion();
								parent.createHeadLine();
							}
						}
					}
				}
			};	
			
			table16.addMouseListener(table16MouseListener);
			tableQuarter.addMouseListener(tableQuarterMouseListener);
			tableSemi.addMouseListener(tableSemiMouseListener);
			tableThird.addMouseListener(tableThirdMouseListener);
			tableFinal.addMouseListener(tableFinalMouseListener);
			
		// adding to gui
			
			jScrollPane1.setViewportView(table16);
			table16.setBounds(0, 22, 518, 32);
			jScrollPane2.setViewportView(tableQuarter);
			tableQuarter.setBounds(0, 22, 518, 32);
			jScrollPane3.setViewportView(tableSemi);
			tableSemi.setBounds(0, 22, 518, 32);
			jScrollPane4.setViewportView(tableThird);
			tableThird.setBounds(0, 22, 518, 32);	    
			jScrollPane5.setViewportView(tableFinal);
			tableFinal.setBounds(0, 22, 518, 32);	
	}

	public void drawMatches() {
		draw16();
		drawQuarter();
		drawSemi();
		drawThird();
		drawFinal();
		setWinner();
	}

	private void draw16() {
		Object[][] data = new Object[8][9];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + finals.getRoundOf16().get(i).getIntId();
			data[i][1] = "" + finals.getRoundOf16().get(i).getDate();
			data[i][2] = "" + finals.getRoundOf16().get(i).getTime();
			data[i][3] = "" + finals.getRoundOf16().get(i).getLocation();
			data[i][5] = "" + finals.getRoundOf16().get(i).getTeamH().getName();
			if(finals.getRoundOf16().get(i).isPlayed())
				data[i][6] = finals.getRoundOf16().get(i).getGoalsH() + " - " + finals.getRoundOf16().get(i).getGoalsG();
			else
				data[i][6] = " - ";
			data[i][7] = "" + finals.getRoundOf16().get(i).getTeamG().getName();			
			try{		
				if(finals.getRoundOf16().get(i).getTeamH().getName().length() > 3){
					data[i][4] = CtrlGroup.getFlagIcon(finals.getRoundOf16().get(i).getTeamH().getName());
				}
				else 
					data[i][4] = CtrlGroup.getFlagIcon("default");
				if(finals.getRoundOf16().get(i).getTeamG().getName().length() > 3){
					data[i][8] = CtrlGroup.getFlagIcon(finals.getRoundOf16().get(i).getTeamG().getName());
				}		
				else 
					data[i][8] = CtrlGroup.getFlagIcon("default");
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
			//table16.tableChanged(new TableModelEvent(table16.getModel()));
		}			
	}

	private void drawQuarter() {
		Object[][] data = new Object[4][9];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + finals.getQuarterFinals().get(i).getIntId();
			data[i][1] = "" + finals.getQuarterFinals().get(i).getDate();
			data[i][2] = "" + finals.getQuarterFinals().get(i).getTime();
			data[i][3] = "" + finals.getQuarterFinals().get(i).getLocation();
			data[i][5] = "" + finals.getQuarterFinals().get(i).getTeamH().getName();
			if(finals.getQuarterFinals().get(i).isPlayed())
				data[i][6] = finals.getQuarterFinals().get(i).getGoalsH() + " - " + finals.getQuarterFinals().get(i).getGoalsG();
			else
				data[i][6] = " - ";
			data[i][7] = "" + finals.getQuarterFinals().get(i).getTeamG().getName();			
			try{
				if(finals.getQuarterFinals().get(i).getTeamH().getName().length() > 3)
					data[i][4] = CtrlGroup.getFlagIcon(finals.getQuarterFinals().get(i).getTeamH().getName());
				else 
					data[i][4] = CtrlGroup.getFlagIcon("default");
				if(finals.getQuarterFinals().get(i).getTeamG().getName().length() > 3)
					data[i][8] = CtrlGroup.getFlagIcon(finals.getQuarterFinals().get(i).getTeamG().getName());
				else 
					data[i][8] = CtrlGroup.getFlagIcon("default");
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

	private void drawSemi() {
		Object[][] data = new Object[2][9];
		
		for (int i = 0; i < data.length; i++) {			
			data[i][0] = "" + finals.getSemiFinals().get(i).getIntId();
			data[i][1] = "" + finals.getSemiFinals().get(i).getDate();
			data[i][2] = "" + finals.getSemiFinals().get(i).getTime();
			data[i][3] = "" + finals.getSemiFinals().get(i).getLocation();
			data[i][5] = "" + finals.getSemiFinals().get(i).getTeamH().getName();
			if(finals.getSemiFinals().get(i).isPlayed())
				data[i][6] = finals.getSemiFinals().get(i).getGoalsH() + " - " + finals.getSemiFinals().get(i).getGoalsG();
			else
				data[i][6] = " - ";
			data[i][7] = "" + finals.getSemiFinals().get(i).getTeamG().getName();			
			try{
				if(finals.getSemiFinals().get(i).getTeamH().getName().length() > 3)
					data[i][4] = CtrlGroup.getFlagIcon(finals.getSemiFinals().get(i).getTeamH().getName());
				else 
					data[i][4] = CtrlGroup.getFlagIcon("default");
				if(finals.getSemiFinals().get(i).getTeamG().getName().length() > 3)
					data[i][8] = CtrlGroup.getFlagIcon(finals.getSemiFinals().get(i).getTeamG().getName());
				else 
					data[i][8] = CtrlGroup.getFlagIcon("default");
			}catch (Exception e){
				e.printStackTrace();
			}
			model3.setValueAt(data[i][0], i, 0);
			model3.setValueAt(data[i][1], i, 1);
			model3.setValueAt(data[i][2], i, 2);
			model3.setValueAt(data[i][3], i, 3);
			model3.setValueAt(data[i][4], i, 4);
			model3.setValueAt(data[i][5], i, 5);
			model3.setValueAt(data[i][6], i, 6);
			model3.setValueAt(data[i][7], i, 7);
			model3.setValueAt(data[i][8], i, 8);
		}
	}

	private void drawThird() {
		Object[] data = new Object[9];
		
		data[0] = "" + finals.getThirdGame().getIntId();
		data[1] = "" + finals.getThirdGame().getDate();
		data[2] = "" + finals.getThirdGame().getTime();
		data[3] = "" + finals.getThirdGame().getLocation();
		data[5] = "" + finals.getThirdGame().getTeamH().getName();
		if(finals.getThirdGame().isPlayed())
			data[6] = finals.getThirdGame().getGoalsH() + " - " + finals.getThirdGame().getGoalsG();
		else
			data[6] = " - ";
		data[7] = "" + finals.getThirdGame().getTeamG().getName();			
		try{
			if(finals.getThirdGame().getTeamH().getName().length() > 3)
				data[4] = CtrlGroup.getFlagIcon(finals.getThirdGame().getTeamH().getName());
			else 
				data[4] = CtrlGroup.getFlagIcon("default");
			if(finals.getThirdGame().getTeamG().getName().length() > 3)
				data[8] = CtrlGroup.getFlagIcon(finals.getThirdGame().getTeamG().getName());
			else 
				data[8] = CtrlGroup.getFlagIcon("default");
		}catch (Exception e){
			e.printStackTrace();
		}
		model4.setValueAt(data[0], 0, 0);
		model4.setValueAt(data[1], 0, 1);
		model4.setValueAt(data[2], 0, 2);
		model4.setValueAt(data[3], 0, 3);
		model4.setValueAt(data[4], 0, 4);
		model4.setValueAt(data[5], 0, 5);
		model4.setValueAt(data[6], 0, 6);
		model4.setValueAt(data[7], 0, 7);
		model4.setValueAt(data[8], 0, 8);
	}

	private void drawFinal() {
		Object[] data = new Object[9];
			
		data[0] = "" + finals.getFinalGame().getIntId();
		data[1] = "" + finals.getFinalGame().getDate();
		data[2] = "" + finals.getFinalGame().getTime();
		data[3] = "" + finals.getFinalGame().getLocation();
		data[5] = "" + finals.getFinalGame().getTeamH().getName();
		if(finals.getFinalGame().isPlayed())
			data[6] = finals.getFinalGame().getGoalsH() + " - " + finals.getFinalGame().getGoalsG();
		else
			data[6] = " - ";
		data[7] = "" + finals.getFinalGame().getTeamG().getName();			
		try{
			if(finals.getFinalGame().getTeamH().getName().length() > 3)
				data[4] = CtrlGroup.getFlagIcon(finals.getFinalGame().getTeamH().getName());
			else 
				data[4] = CtrlGroup.getFlagIcon("default");
			if(finals.getFinalGame().getTeamG().getName().length() > 3)
				data[8] = CtrlGroup.getFlagIcon(finals.getFinalGame().getTeamG().getName());
			else 
				data[8] = CtrlGroup.getFlagIcon("default");
		}catch (Exception e){
			e.printStackTrace();
		}
		model5.setValueAt(data[0], 0, 0);
		model5.setValueAt(data[1], 0, 1);
		model5.setValueAt(data[2], 0, 2);
		model5.setValueAt(data[3], 0, 3);
		model5.setValueAt(data[4], 0, 4);
		model5.setValueAt(data[5], 0, 5);
		model5.setValueAt(data[6], 0, 6);
		model5.setValueAt(data[7], 0, 7);
		model5.setValueAt(data[8], 0, 8);
	}

	private void setWinner() {
		if(finals.getFinalGame().isPlayed()){
			labelCup1.setIcon(CtrlGroup.getFlagIcon("cup"));
			labelWinnerFlag.setIcon(CtrlGroup.getFlagIcon(finals.getWinner()));
			labelWinnerName.setText(finals.getWinner());
			labelCup2.setIcon(CtrlGroup.getFlagIcon("cup"));
		}
		
	}
	
	private static BorderCellRenderer createRenderer(Color color, Insets insets) {
	    BorderCellRenderer renderer = new BorderCellRenderer();
	    renderer.setColumnBorder(new LinesBorder(color, insets));    
	    return renderer;
	}  

	private JLabel label16;
	private JLabel labelQuarter;
	private JLabel labelSemi;
	private JLabel labelThird;
	private JLabel labelFinal;
	private JLabel labelWinner;
	private JLabel labelCup1;
	private JLabel labelCup2;
	private JLabel labelWinnerName;
	private JLabel labelWinnerFlag;
	private JTable table16;
	private JTable tableQuarter;
	private JTable tableSemi;
	private JTable tableThird;
	private JTable tableFinal;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane5;
	private TableModel model1;
	private TableModel model2;
	private TableModel model3;
	private TableModel model4;
	private TableModel model5;
}
