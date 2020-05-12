package Database;

import java.util.ArrayList;

// The main interface to our database
public abstract class MusicLibrary {

    private SongsTable songsTable;
    private ArtistsTable artistsTable;
    private ReleasesTable releasesTable;

    //Constructor for a library
    public MusicLibrary(SongsTable songs, ArtistsTable artists, ReleasesTable releases){
        this.songsTable = songs;
        this.artistsTable = artists;
        this.releasesTable = releases;
    }

    //getter for song table
    public SongsTable getSongsTable() {
        return this.songsTable;
    }

    //getter for artist table
    public ArtistsTable getArtistsTable(){
        return this.artistsTable;
    }

    //getter for release table
    public ReleasesTable getReleasesTable(){
        return this.releasesTable;
    }

    //gets songs csv strings
    public ArrayList<DBEntry> printSongs() {
        return songsTable.printItems();
    }

    //gets artists csv strings
    public ArrayList<DBEntry> printArtists() {
        return artistsTable.printItems();
    }

    //gets release csv strings
    public ArrayList<DBEntry> printReleases() {
        return releasesTable.printItems();
    }

    //adds an entry to the library
    public void add(DBEntry entry){
        // parse DBEntry type and add to respective list
        if (entry instanceof SongEntry){
            songsTable.add((SongEntry) entry);
        } else if (entry instanceof ArtistEntry){
            artistsTable.add((ArtistEntry) entry);
        } else if (entry instanceof ReleaseEntry){
            releasesTable.add((ReleaseEntry) entry);
        }
    }

    //removes an entry from a library
    public boolean remove(String entryGUID){
        // check tables
        SongEntry song = (SongEntry) songsTable.getByGUID(entryGUID);
        ArtistEntry artist = (ArtistEntry) artistsTable.getByGUID(entryGUID);
        ReleaseEntry release = (ReleaseEntry) releasesTable.getByGUID(entryGUID);

        // remove entity from respective table
        if (song != null){
            return songsTable.remove(song);
        } else if (artist != null){
            return artistsTable.remove(artist);
        } else if (release != null){
            return releasesTable.remove(release);
        } else {
            return false;
        }
    }

    public ArrayList<ArrayList<String>> getGuidList() {
        ArrayList<ArrayList<String>> list = new ArrayList<>(3);
        ArrayList<String> list1 = songsTable.exportToCSV();
        list.add(list1);
        ArrayList<String> list2 = artistsTable.exportToCSV();
        list.add(list2);
        ArrayList<String> list3 = releasesTable.exportToCSV();
        list.add(list3);
        return list;
    }
}
