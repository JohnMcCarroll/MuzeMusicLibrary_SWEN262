package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;
import Database.ReleaseEntry;
import Database.ReleasesTable;

import java.util.ArrayList;

public class EntryMediumSearch implements SearchStrategy{

    /**
     * Performs a search by artist medium, can be performed on an artist
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData) {
        if(data instanceof ReleasesTable){
            ArrayList<DBEntry> releasesByMedium = new ArrayList<>();
            for(DBEntry dbe : data.getEntries().values()){
                ReleaseEntry re = (ReleaseEntry)dbe;
                if((re.getMedium().toLowerCase()).equals(searchQuery.toLowerCase())){
                    releasesByMedium.add(re);
                }
            }
            return releasesByMedium;
        }
        return null;
    }
}
