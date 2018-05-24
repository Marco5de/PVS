package swc.gui;

import java.util.*;

import swc.ctrl.CtrlGroup;
import swc.data.*;
import javax.swing.table.*;

/**
 * TeamModel is an implementation of AbstractTableModel.
 * JTables use it to access the data they are showing.
 * This model is used when showing teams.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
final class TeamModel extends AbstractTableModel{
	/**
	 * Custom serialVersionUID for a Swing class.
	 */
	private static final long serialVersionUID = 1646639819385125821L;
	/**
	 * The teams we are looking on.
	 */
	private Vector<Team> teams;

	/**
	 * Create a new TeamModel object.
	 * @param teams Vector<Team>
	 */
	public TeamModel(Vector<Team> teams) {
		super();
		setTeams(teams);
	}

	/**
	 * Receive the team objects to be shown.
	 * @param teams Vector<Team>
	 */
	public void setTeams(Vector<Team> teams) {
		this.teams = teams;
	}

	/**
	 * Returns how many rows the table should show.
	 * @return {@link Vector#size()} int
	 */
	@Override
	public int getRowCount() {
		return teams.size();
	}

	/**
	 * Returns how many columns the table has.
	 * We have 11 fields (9 data, 1 icon, 1 position)
	 * @return 11
	 */
	@Override
	public int getColumnCount() {
		return 11;
	}

	/**
	 * Returns if a cell is editable.
	 * @return false
	 * No parameters of a Team can be edited in a table.
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * Returns the class of the objects in a column.
	 * With that, we can show icons properly.
	 * @param c int The column.
	 * @return {@link TeamModel#getValueAt(int, int)#getClass()}
	 * @return null for a empty table.
	 */
	@Override
    public Class getColumnClass(int c) {
		if(teams.size() > 0)
			return getValueAt(0, c).getClass();
		return "".getClass();
    }

	/**
	 * Returns the name of the data value of column.
	 * These values and their data types are:
	 * 
	 * <li> "#" for col = 0 (placeholder for Integer value)
	 * <li> "" for col = 1 (position for the flag, ImageIcon)
	 * <li> "Team" for col = 2 (String)
	 * <li> "Played (Games)" for col = 3 (Integer)
	 * <li> "Won (Games)" for col = 4(Integer)
	 * <li> "Drawn (Games)" for col = 5 (Integer)
	 * <li> "Lost (Games)" for col = 6 (Integer)
	 * <li> "GF" for col = 7 (Integer)
	 * <li> "GA" for col = 8 (Integer)
	 * <li> "Difference (GF-GA)" for col = 9 (Integer)
	 * <li> "Points" for col = 10 (Integer)
	 * 
	 * @param col - int
	 * @return String
	 */
	@Override
	public String getColumnName(int col) {
        switch(col) {
        case 0:
        	return "#";
        case 1:
        	return "";
        case 2:
        	return "Team";
        case 3:
        	return "Played";
        case 4:
        	return "Won";
        case 5:
        	return "Draw";
        case 6:
        	return "Loss";
        case 7:
        	return "GF";
        case 8:
        	return "GA";
        case 9:
        	return "Difference";
        case 10:
        	return "Points";
        default:
        	return "";
        }
    }

	/**
	 * Returns a data field of an Team object.
	 * @param row - int
	 * <ul> Selects the Team object.
	 * @param column - int
	 * <ul> Selects the field to be shown.
	 * See {@link TeamModel#getColumnName(int)} for further information
	 * about data types.
	 * @return Object
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Team t = (Team) teams.get(row);
        switch(col) {
        case 0:
        	return new Integer(row+1);
        case 1:
        	return CtrlGroup.getFlagIcon(t.getStrName());
        case 2:
        	return t.getStrName();
        case 3:
        	return new Integer(t.getPlayed());
        case 4:
        	return new Integer(t.getWon());
        case 5:
        	return new Integer(t.getDraws());
        case 6:
        	return new Integer(t.getLost());
        case 7:
        	return new Integer(t.getGf());
        case 8:
        	return new Integer(t.getGa());
        case 9:
        	return new Integer(t.getGf()-t.getGa());
        case 10:
        	return new Integer(t.getPoints());
        default:
        	return "";
        }
	}
}