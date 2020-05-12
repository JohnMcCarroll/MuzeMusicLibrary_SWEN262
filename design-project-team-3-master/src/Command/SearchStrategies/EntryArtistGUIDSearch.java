package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;
import Database.ReleasesTable;
import Database.SongsTable;

import java.util.ArrayList;
import java.util.HashSet;

public class EntryArtistGUIDSearch implements SearchStrategy {

    /**
     * Performs a search by the artist GUID, can be performed on a song table or a release table
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String query, DatabaseTable extraTable) {
        HashSet<String> artistGUIDS = new HashSet<>();
        artistGUIDS.add(query);
        if(data instanceof SongsTable) {
            return ((SongsTable)data).getByArtistGUID(artistGUIDS);
        } else if (data instanceof ReleasesTable) {
            return ((ReleasesTable)data).getByArtistGUID(artistGUIDS);
        }
        return null;
    }
}
