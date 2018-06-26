package swc.data;

import java.util.Vector;


public class SoccerWC {	
	private Vector<Group> groups;
	private Final finals;
	private String name;
	private String filename;
	
	public SoccerWC(String filename, String wcName) {
		setGroups(new Vector<Group>());
		setFinals(new Final());
		setName(wcName);
		setFilename(filename);
	}
	
	public SoccerWC(String wcName) {
		setGroups(new Vector<Group>());
		setFinals(new Final());
		setName(wcName);
	}
	
	public SoccerWC() {
		setGroups(new Vector<Group>()); 
		setFinals(new Final());
	}
	
	public void addGroup(Group toAdd) {
		groups.add(toAdd);
	}

	public void setGroups(Vector<Group> groups) {
		this.groups = groups;
	}

	public Vector<Group> getGroups() {
		return groups;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFinals(Final finals) {
		this.finals = finals;
	}

	public Final getFinals() {
		return finals;
	}

}
