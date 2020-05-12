package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;
import Database.ReleaseEntry;
import Database.ReleasesTable;

import java.util.ArrayList;

public class EntrySongNameSearch implements SearchStrategy {

    /**
     * Performs a search by Song Name, can be performed on releases.
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData) {
        if(data instanceof ReleasesTable){
            ArrayList<DBEntry> releasesBySongName = new ArrayList<>();
            for(DBEntry dbe : data.getEntries().values()){
                ReleaseEntry re = (ReleaseEntry)dbe;
                for(String songGUID : re.getSongs()){
                    if(extraData.getByGUID(songGUID).containsName(searchQuery)) {
                        if(!releasesBySongName.contains(re)){
                            releasesBySongName.add(re);
                        }
                    }
                }
            }
            return releasesBySongName;
        }
        return null;
    }
}
