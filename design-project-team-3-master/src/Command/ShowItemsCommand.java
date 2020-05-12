package Command;

import Database.DBEntry;
import Database.MusicLibrary;

import java.util.ArrayList;

public class ShowItemsCommand implements MusicCommand {

    //Shows current items selected
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary library) {
        ArrayList<DBEntry> items = new ArrayList<>();
        String modifier = data[1].toLowerCase();
        if (modifier.equals("songs")) {
            items = library.printSongs();
        } else if (modifier.equals("artists")) {
            items = library.printArtists();
        } else if (modifier.equals("releases")) {
            items = library.printReleases();
        }
        return items;
    }
}
