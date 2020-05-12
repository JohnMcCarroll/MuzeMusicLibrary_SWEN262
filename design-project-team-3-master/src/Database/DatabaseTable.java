package Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class DatabaseTable{

    private HashMap<String, DBEntry> entries;

    public DatabaseTable(HashMap<String, DBEntry> entries){
        this.entries = entries;
    }

    public HashMap<String, DBEntry> getEntries(){
        return this.entries;
    }

    /**
     * Gets entries from the table.
     * @param GUID : A list of GUID's to lookup.
     * @return : An ArrayList of all the entries that were found.
     */
    public ArrayList<DBEntry> getByGUID(HashSet<String> GUID) {
        ArrayList<DBEntry> entriesByGUID = new ArrayList<>();
        for(String guid : GUID) {
            entriesByGUID.add(entries.get(GUID));
        }
        return entriesByGUID;
    }

    /**
     * Get a single entry from the table.
     * @param GUID : The GUID to search for.
     * @return : A single entry
     */
    public DBEntry getByGUID(String GUID) {
        return entries.get(GUID);
    }

    /**
     * Finds any entry that contains the name string.
     * @param name : The string to search each name for
     * @return : An ArrayList of all entries containing the name string.
     */
    public ArrayList<DBEntry> getByName(String name) {
        ArrayList<DBEntry> entriesByName = new ArrayList<>();
        for (DBEntry entry : entries.values()){
            if(entry.containsName(name)){
                entriesByName.add(entry);
            }
        }
        return entriesByName;
    }

    /**
     * Makes ArrayList of entries that are ready to save to a CSV.
     * @return : ArrayList of entries as strings.
     */
    public ArrayList<String> exportToCSV() {
        ArrayList<String> entriesCSV = new ArrayList<>();
        for (DBEntry entry : entries.values()) {
            entriesCSV.add(entry.toString());
        }
        return entriesCSV;
    }

    public ArrayList<DBEntry> printItems() {
        int counter = 0;
        ArrayList<DBEntry> items = new ArrayList<>();
        for (Map.Entry<String, DBEntry> entry: this.entries.entrySet()) {
            DBEntry value = entry.getValue();
            items.add(value);
            System.out.println(counter + ": " + value.getName());
            counter++;
        }
        return items;
    }
}