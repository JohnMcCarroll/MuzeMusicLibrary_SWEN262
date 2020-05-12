package Database;

import java.util.*;

// SongsTable
// Holds constructed data for later
// use at the database controller
public class SongsTable extends DatabaseTable {

    // SongsTable object constructor
    public SongsTable(HashMap<String, DBEntry> songs) {
        super(songs);
    }

    //gets by artist guid
    public ArrayList<DBEntry> getByArtistGUID(HashSet<String> artistGUID){
        ArrayList<DBEntry> queriedEntries = new ArrayList<>();
        for(DBEntry dbe : this.getEntries().values()){
            if(dbe instanceof SongEntry){
                if (artistGUID.contains(((SongEntry)dbe).getArtistGUID())){
                    queriedEntries.add(dbe);
                }
            }
        }
        return queriedEntries;
    }

    //gets entries by minimum rating
    public ArrayList<DBEntry> getByMinRating(int minRating) {
        ArrayList<DBEntry> queriedEntries = new ArrayList<>();
        for(DBEntry dbe : this.getEntries().values()) {
            if(dbe instanceof SongEntry){
                if(((SongEntry)dbe).getRating()>= minRating) {
                    queriedEntries.add(dbe);
                }
            }
        }
        return queriedEntries;
    }

    //adds an entry to their personal release table
    public void add(SongEntry entry){
        super.getEntries().put(entry.getGUID(), entry);
        System.out.println(entry.getName() + " was added to your song collection");
    }

    //removes an entry from their personal release table
    public boolean remove(SongEntry entry){
        DBEntry removed = super.getEntries().remove(entry.getGUID());
        if (removed != null) {
            return true;
        } else {
            return false;
        }
    }

}

