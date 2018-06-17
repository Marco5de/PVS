package swc.data;

import java.util.Vector;

public class SoccerWC {
    private Vector<Group> groups= new Vector<>();
    private Final finals = new Final();
    private String name;
    private String filename;

    public SoccerWC(String filename, String wcName){
        this.filename = filename;
        this.name = wcName;
    }
    public SoccerWC(String wcName){
        this.name = wcName;
    }
    public SoccerWC(){}

    public Final getFinals() {
        return finals;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public Vector<Group> getGroups() {
        return groups;
    }

    public void setGroups(Vector<Group> groups) {
        this.groups = groups;
    }

    public void setFinals(Final finals) {
        this.finals = finals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void addGroup(Group toAdd){
        groups.add(toAdd);
    }
}
