package Command;

import Database.DBEntry;
import Database.MusicLibrary;
import Database.UserLibrary;

import java.util.ArrayList;

public class RemoveCommand implements MusicCommand {

    //removes an entry from the users library
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary library) {
        if (library instanceof UserLibrary) {
            UserLibrary lib = (UserLibrary) library;
            lib.remove(data[0]);
        }
        return null;
    }
}
