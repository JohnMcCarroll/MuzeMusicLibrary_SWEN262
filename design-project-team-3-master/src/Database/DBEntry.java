package Database;


// DBEntry
// Generic parent class for the 3 types
// of database entries
public abstract class DBEntry implements Comparable<DBEntry>{

    private String GUID;
    private String name;


    // DBEntry object constructor
    public DBEntry(String GUID, String name){
        this.GUID = GUID;
        this.name = name;
    }

    // returns GUID
    public String getGUID(){
        return this.GUID;
    }

    //returns name
    public String getName(){
        return this.name;
    }

    //checks if a name is contained in a string
    public boolean containsName(String name){
        return (this.name.toLowerCase()).contains(name.toLowerCase());
    }

    //abstract csv string formatting method
    public abstract String toString();

    //Compares the name of entries.
    @Override
    public int compareTo(DBEntry dbe) {
        return this.name.toLowerCase().compareTo(dbe.name.toLowerCase());
    }

}