package Command;

import Database.*;

import java.util.ArrayList;

public class AddCommand implements MusicCommand {
    private MusicLibrary global;

    /**
     * Instantiated with a reference to the global library to get an Entry by its GUID.
     */
    public AddCommand(MusicLibrary global){
        this.global = global;
    }

    /**
     * Adds an entry by its GUID to the users proper library table, returns null as its not needed.
     */
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary library) {
        DatabaseTable table = null;
        DatabaseTable songTable = null;
        DatabaseTable artistTable = null;
        DBEntry entry = null;

        if (library instanceof UserLibrary) {
            UserLibrary lib = (UserLibrary) library;
            if (data[1].equals("class Database.SongEntry")) {
                table = this.global.getSongsTable();
                artistTable = this.global.getArtistsTable();
                entry = table.getByGUID(data[0]);
                SongEntry se = (SongEntry)entry;
                lib.add(artistTable.getByGUID((se.getArtistGUID())));
            } else if (data[1].equals("class Database.ArtistEntry")) {
                table = this.global.getArtistsTable();
                entry = table.getByGUID(data[0]);
            } else if (data[1].equals("class Database.ReleaseEntry")) {
                table = this.global.getReleasesTable();
                songTable = this.global.getSongsTable();
                artistTable = this.global.getArtistsTable();
                entry = table.getByGUID(data[0]);
                ReleaseEntry re = (ReleaseEntry)entry;
                for(String songGUID : re.getSongs()){
                    lib.add(songTable.getByGUID(songGUID));
                }
                lib.add(artistTable.getByGUID((re.getArtistGUID())));
            }
            lib.add(entry);
        }
        return null;
    }
}
