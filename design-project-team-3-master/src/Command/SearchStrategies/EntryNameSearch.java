package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;

import java.util.ArrayList;

public class EntryNameSearch implements SearchStrategy {

    /**
     * Performs a search on an entries name, can be done on all entries
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraTable) {
        return data.getByName(searchQuery);
    }
}
