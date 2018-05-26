package swc.gui;

import java.util.*;

import swc.ctrl.CtrlGroup;
import swc.data.*;
import javax.swing.table.*;

/**
 * GameModel is an implementation of AbstractTableModel.
 * This model is used when showing games.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
final class GameModel extends AbstractTableModel{
	/**
	 * Custom serialVersionUID for a Swing class.
	 */
	private static final long serialVersionUID = 1646639819385125821L;
	/**
	 * The games we are looking on.
	 */
	private Vector<Game> games = new Vector<>();

	/**
	 * Create a new GameModel object.
	 * @param games 
	 */
	public GameModel(Vector<Game> games) {
		super();
		this.games = games;
	}

	/**
	 * Another constructor to show a single game.
	 */
	public GameModel(Game singleGame){
		super();
		games.add(singleGame);
	}

	/**
	 * Returns how many rows the table should show.
	 * @return {@link Vector#size()} - int
	 */
	@Override
	public int getRowCount() {
		return games.size();
	}

	/**
	 * Returns how many columns the table has.
	 * We have 9 fields in a game table.
	 * @return 9 - int
	 */
	@Override
	public int getColumnCount() {
		return 9;
	}

	/**
	 * Returns if a cell is editable.
	 * @return false
	 * No parameters of a Game can be edited directly in a table.
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * Returns the class of the objects in a column.
	 * With that, we can show icons properly.
	 * @param c - int
	 * <ul> The column.
	 * @return {@link Object#getClass()}
	 * @return null for a empty table.
	 */
	@Override
    public Class getColumnClass(int c) {
		if(games.size() > 0)
			return getValueAt(0, c).getClass();
		return "".getClass();
    }

	/**
	 * Returns the name of the data value of column.
	 * The values and their data types are:
	 * 
	 * <li> "Match" for col = 0 (Number of the game, Integer)
	 * <li> "Date" for col = 1 (String)
	 * <li> "Time" for col = 2 (String)
	 * <li> "Venue" for col = 3 (String)
	 * <li> "" for col = 4 (Placeholder for Home team icon)
	 * <li> "" for col = 5 (Home team, String)
	 * <li> "Result" for col = 6 (String)
	 * <li> "" for col = 7 (Guest team, String)
	 * <li> "" for col = 8 (Placeholder for Guest team icon)
	 * 
	 * @param col - int
	 * @return String
	 */
	@Override
	public String getColumnName(int col) {
		switch(col){
		case 0:
			return "Match";
		case 1:
			return "Date";
		case 2:
			return "Time";
		case 3:
			return "Venue";
		case 6:
			return "Result";
        default:
			return "";
		}
    }

	/**
	 * Returns a data field of an Game object.
	 * @param row - int
	 * <ul> Selects the Team object.
	 * @param col - int
	 * <ul> Selects the field to be shown.
	 * See {@link GameModel#getColumnName(int)} for further information
	 * about data types.
	 * @return Object
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Game g = games.elementAt(row);
		switch(col){
		case 0:
			return g.getIntId();
		case 1:
			return g.getDate();
		case 2:
			return g.getTime();
		case 3:
			return g.getLocation();
		case 4:
			return CtrlGroup.getFlagIcon(g.getTeamH().getStrName());
		case 5:
			return g.getTeamH().getStrName();
		case 6:
			return g.getGoalsH()+"-"+g.getGoalsG();
		case 7:
			return g.getTeamG().getStrName();
		case 8:
			return CtrlGroup.getFlagIcon(g.getTeamG().getStrName());
		default:
			return "";
		}
	}
}