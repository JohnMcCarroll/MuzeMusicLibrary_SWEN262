package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;
import Database.ReleaseEntry;
import Database.ReleasesTable;

import java.util.ArrayList;

public class EntrySongGUIDSearch implements SearchStrategy {

    /**
     * Performs a search by Song Guid, can be performed on releases.
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData) {
        if (data instanceof ReleasesTable){
            ArrayList<DBEntry> releaseBySongGUID = new ArrayList<>();
            for(DBEntry dbEntry : data.getEntries().values()) {
                ReleaseEntry re = (ReleaseEntry)dbEntry;
                if(re.getSongs().contains(searchQuery)){
                    releaseBySongGUID.add(re);
                }
            }
            return releaseBySongGUID;
        }
        return null;
    }
}
