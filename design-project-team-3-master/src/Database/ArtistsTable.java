package Database;

import java.util.*;

// ArtistsTable
// Holds constructed data for later
// use at the database controller
public class ArtistsTable extends DatabaseTable{
    private HashMap<String, DBEntry> artists = new HashMap<>();

    // AristsTable object constructor
    public ArtistsTable(HashMap<String, DBEntry> artists) {
        super(artists);
        this.artists = artists;
    }

    //returns how many items are in the hashmap
    public int getSize() {
        return this.artists.size();
    }

    // Adds an entry to the hashmap
    public void add(ArtistEntry entry){
        super.getEntries().put(entry.getGUID(), entry);
        System.out.println(entry.getName() + " was added to your artist collection");
    }

    // Removes an entry from the hashmap. Returns true if something is removed, false if not.
    public boolean remove(ArtistEntry entry){
        DBEntry removed = super.getEntries().remove(entry.getGUID());
        if (removed != null) {
            return true;
        } else {
            return false;
        }
    }
}