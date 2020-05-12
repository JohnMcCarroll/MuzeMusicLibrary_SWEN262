package Command.SearchStrategies;

import Database.ArtistEntry;
import Database.ArtistsTable;
import Database.DBEntry;
import Database.DatabaseTable;

import java.util.ArrayList;

public class EntryTypeSearch implements SearchStrategy{

    /**
     * Performs a search by type, can be performed on a release.
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData) {
        if(data instanceof ArtistsTable){
            ArrayList<DBEntry> artistByType = new ArrayList<>();
            for(DBEntry dbe : data.getEntries().values()){
                ArtistEntry ae = (ArtistEntry) dbe;
                if((ae.getDisambiguation().toLowerCase()).equals(searchQuery.toLowerCase())){
                    artistByType.add(ae);
                }
            }
            return artistByType;
        }
        return null;
    }
}
