package Database;

import UserTier.*;

public class UserLibrary extends MusicLibrary {

    private static UserLibrary userLibraryInstance = null;

    ///User
    public User user;

    //Creates a UserLibrary
    public UserLibrary(SongsTable songs, ArtistsTable artists, ReleasesTable releases){
        super(songs, artists, releases);
    }

    // singleton implementation
    public static UserLibrary getUserLibrary(SongsTable songs, ArtistsTable artists, ReleasesTable releases) {
        if (userLibraryInstance == null) userLibraryInstance = new UserLibrary(songs, artists, releases);
        return userLibraryInstance;
    }

    //getter for user
    public User getUser(){
        return this.user;
    }

}
