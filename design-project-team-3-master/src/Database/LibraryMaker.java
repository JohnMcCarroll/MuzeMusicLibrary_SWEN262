package Database;

import java.util.ArrayList;

// Director class for creation of complex object DatabaseController
public class LibraryMaker {

    public enum LibraryPath {
        USER, GLOBAL
    }

    private ArtistTableBuilder artistTableBuilder;
    private ArtistsTable artistsTable;

    private ReleaseTableBuilder releaseTableBuilder;
    private ReleasesTable releasesTable;

    private SongTableBuilder songTableBuilder;
    private SongsTable songsTable;

    private String globalLibraryPath = "data/global/";
    private String userLibraryPath = "data/User/USER_";

    public LibraryMaker(){
        this.artistTableBuilder = new ArtistTableBuilder();
        this.releaseTableBuilder = new ReleaseTableBuilder();
        this.songTableBuilder = new SongTableBuilder();
    }

    // unify these methods and have them take a "user" or "global" enum to determine path...
    // OR add path to root directory of csv files and pass through


    // coordinates global library construction
    public GlobalLibrary createGlobalLibrary(){
        // have builders construct respective table
        this.songsTable = songTableBuilder.createDatabaseTable(globalLibraryPath);
        this.artistsTable = artistTableBuilder.createDatabaseTable(globalLibraryPath);
        this.releasesTable = releaseTableBuilder.createDatabaseTable(globalLibraryPath);

        // feed tables to DatabaseController constructor
        return GlobalLibrary.getGlobalLibrary(songsTable, artistsTable, releasesTable);
    }

    // coordinates user library construction
    public UserLibrary createUserLibrary(){
        // have builders construct respective table
        this.songsTable = songTableBuilder.createDatabaseTable(userLibraryPath);
        this.artistsTable = artistTableBuilder.createDatabaseTable(userLibraryPath);
        this.releasesTable = releaseTableBuilder.createDatabaseTable(userLibraryPath);

        // feed tables to DatabaseController constructor
        return UserLibrary.getUserLibrary(songsTable, artistsTable, releasesTable);
    }

}