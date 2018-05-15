package swc.data;

import java.util.Vector;

public class SoccerWC {
	private Vector<Group> groups = new Vector<>();
	private Final finals;
	private String name;
	private String filename;

	public SoccerWC(String filename, String wcName) {
		this.filename = filename;
		this.name = wcName;
	}

	public SoccerWC(String wcName) {
		this.filename = "Unspecified file name";
		this.name = wcName;
	}

	public SoccerWC() {
		this.filename = "Unspecified file name";
		this.name = "unspecified world cup name";
	}

	public void addGroup(Group toAdd) {
		groups.add(toAdd);
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the groups
	 */
	public Vector<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Vector<Group> groups) {
		this.groups = groups;
	}

	/**
	 * @return the finals
	 */
	public Final getFinals() {
		return finals;
	}

	/**
	 * @param finals the finals to set
	 */
	public void setFinals(Final finals) {
		this.finals = finals;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}