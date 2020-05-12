package Command;

import Database.DBEntry;
import Database.MusicLibrary;

import java.util.ArrayList;

// Music Command Interface
// Defines functionality for all user command classes
public interface MusicCommand {

    //method for executing a command
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary library);
}