package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;

import java.util.ArrayList;
import java.util.HashSet;

public class EntryGUIDSearch implements SearchStrategy{

    /**
     * Performs a search by GUID, can be done on all entries.
    */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraTable) {
        HashSet<String> guid = new HashSet<>();
        guid.add(searchQuery);
        return data.getByGUID(guid);
    }
}
