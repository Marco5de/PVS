package swc.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
 
/**
 * @version 1.0 03/06/99
 */
public class BorderCellRenderer extends JLabel
    implements TableCellRenderer {
	private static final long serialVersionUID = 3513286372728738315L;
	protected Border noFocusBorder; 
	protected Border columnBorder; 
 
	public BorderCellRenderer() {
		noFocusBorder = new EmptyBorder(1, 2, 1, 2);
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
                 boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			if(row%2 != 0)
				setBackground(new Color(202, 225, 255));
			else
				setBackground(table.getBackground());
		}
		setFont(table.getFont());
    
		if (hasFocus) {
			setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
			if (table.isCellEditable(row, column)) {
				setForeground( UIManager.getColor("Table.focusCellForeground") );
				setBackground( UIManager.getColor("Table.focusCellBackground") );
			}
		} else {
			if (columnBorder != null) {
				setBorder(columnBorder);
			} else {
				setBorder(noFocusBorder);
			}
		}
		setHorizontalAlignment(JLabel.CENTER);
		if(value instanceof Icon){
			setIcon((Icon) value);
		}else {
			setText((value == null) ? "" : value.toString());   
		}
		return this;
	}
  
	public void setColumnBorder(Border border) {
		columnBorder = border;
	}
  
	public Border getColumnBorder() {
		return columnBorder;
	}
}
