package Command;

import Database.DBEntry;
import Database.MusicLibrary;

import java.util.ArrayList;

public class Help implements MusicCommand{

    //prints help messages
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary musicLibrary) {
        if(data.length > 1 && (data[1].toLowerCase()).equals("search")) {
            System.out.println("Search Formatting -");
            System.out.println("  search (EntryType) (SearchType) (SearchQuery)");
            System.out.println("  search (songs, releases, artist) (name) (name)");
            System.out.println("  search (songs, releases, artist) (GUID) (guid)");
            System.out.println("  search (songs, releases) (artistname) (artistname)");
            System.out.println("  search (songs, releases) (artistGUID) (artistGUID)");
            System.out.println("  search (songs, releases) (rating) (min rating)");
            System.out.println("  search (songs, releases) (duration) (duration(ms)) (min, max)");
            System.out.println("  search (releases) (songname) (songname)");
            System.out.println("  search (releases) (songGUID) (songGUID)");
            System.out.println("  search (releases) (medium) (medium)");
            System.out.println("  search (releases) (releasedate) (StartDate (YYYY-MM-DD)) (EndDate (YYYY-MM-DD))");
            System.out.println("  search (artists) (type) (artisttype)");
        } else {
            System.out.println("The following commands are acceptable: ");
            System.out.println("  help - get a list of available commands");
            System.out.println("  switchLibrary - switch from your user library to the global library or vice versa");
            System.out.println("  showItems - display all entries");
            System.out.println("\t Modifiers - songs, artists, releases");
            System.out.println("  selectEntry - select the current entry that you are looking at");
            System.out.println("  rateSong - rate the first song selected from 1-5 stars");
            System.out.println("  addEntry - add an entry to your music library");
            System.out.println("  removeEntry - remove an entry from your music library");
            System.out.println("  save - exports all user data to csv file for storage");
            System.out.println("  search - type 'help search' for search formatting");
        }
        return null;
    }
}
