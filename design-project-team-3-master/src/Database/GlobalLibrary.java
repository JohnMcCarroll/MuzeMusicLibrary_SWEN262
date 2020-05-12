package Database;

public class GlobalLibrary extends MusicLibrary {

    private static GlobalLibrary globalLibraryInstance = null;

    //Initializes a global library with a table
    public GlobalLibrary(SongsTable songs, ArtistsTable artists, ReleasesTable releases){
        super(songs, artists, releases);
    }

    // singleton implementation
    public static GlobalLibrary getGlobalLibrary(SongsTable songs, ArtistsTable artists, ReleasesTable releases) {
        if (globalLibraryInstance == null) globalLibraryInstance = new GlobalLibrary(songs, artists, releases);
        return globalLibraryInstance;
    }
}
