package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;
import Database.ReleasesTable;
import Database.SongsTable;

import java.util.ArrayList;
import java.util.HashSet;

public class EntryArtistNameSearch implements SearchStrategy{

    /**
     * Performs a search by name, can be performed on all entries
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraTable) {
        HashSet<String> artistByName = new HashSet<>();
        for(DBEntry dbe: extraTable.getByName(searchQuery)){
            artistByName.add(dbe.getGUID());
        }
        if(data instanceof SongsTable) {
            return ((SongsTable)data).getByArtistGUID(artistByName);
        } else if (data instanceof ReleasesTable) {
            return ((ReleasesTable)data).getByArtistGUID(artistByName);
        }
        return null;
    }
}
