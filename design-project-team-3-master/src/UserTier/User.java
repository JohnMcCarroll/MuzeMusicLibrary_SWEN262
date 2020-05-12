package UserTier;

import Database.DBEntry;
import Database.UserLibrary;

// Simple user class to represent unique music collector accounts
public class User {

    private final String username;
    private UserLibrary library;

    //Constructor for user that is created with a username and a library
    public User(final String username, UserLibrary library){
        this.username = username;
        this.library = library;
    }

    // simple getter for the username
    public String getUsername() {
        return username;
    }

    // simple mutators for library
    public void addToLibray(final DBEntry entry){
        library.add(entry);
    }

    // removes an entry from a library
    public void removeFromLibrary(final DBEntry entry){
        library.remove(entry.getGUID());
    }

    // gets a users personal library
    protected UserLibrary getLibrary() {
        return library;
    }
}
