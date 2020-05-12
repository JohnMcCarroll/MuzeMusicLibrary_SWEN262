package Database;

import java.util.*;

// ReleasesTable
// Holds constructed data for later
// use at the database controller
public class ReleasesTable extends DatabaseTable{
    private SongsTable songDataBase;

    // ReleasesTable object constructor
    public ReleasesTable(HashMap<String, DBEntry> releases) {
        super(releases);
    }

    //gets an arraylist of releases by artist GUID
    public ArrayList<DBEntry> getByArtistGUID(HashSet<String> artistGUID){
        ArrayList<DBEntry> queriedEntries = new ArrayList<>();
        for(DBEntry dbe : this.getEntries().values()){
            if(dbe instanceof ReleaseEntry){
                if (artistGUID.contains(((ReleaseEntry)dbe).getArtistGUID())){
                    queriedEntries.add(dbe);
                }
            }
        }
        return queriedEntries;
    }

    //adds an entry to their personal release table
    public void add(ReleaseEntry entry){
        super.getEntries().put(entry.getGUID(), entry);
        System.out.println(entry.getName() + " was added to your release collection");
    }

    //removes an entry from their personal release table
    public boolean remove(ReleaseEntry entry){
        DBEntry removed = super.getEntries().remove(entry.getGUID());
        if (removed != null) {
            return true;
        } else {
            return false;
        }
    }
}
