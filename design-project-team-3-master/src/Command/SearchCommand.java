package Command;

import Command.SearchStrategies.*;
import Database.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SearchCommand implements MusicCommand{

    private SearchStrategy strategy;

    /**
     * Creates a search strategy and calls it based on the data queried.
     */
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary musicLibrary){
        String queryString ="";
        for(int i = 3; i < data.length; i++){
            queryString += data[i];
            if (i != data.length - 1){
                queryString += " ";
            }
        }
        DatabaseTable dbTable;
        switch (data[1].toLowerCase()){
            case "artist":
                dbTable = musicLibrary.getArtistsTable();
                break;
            case "song":
                dbTable = musicLibrary.getSongsTable();
                break;
            case "release":
                dbTable = musicLibrary.getReleasesTable();
                break;
            default:
                System.out.println("Invalid search query, for help type 'help search'.");
                return null;
        }
        ArrayList<DBEntry> queriedResults;
        switch (data[2].toLowerCase()) {
            case "name":
                selectStrategy(new EntryNameSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, null);
                break;
            case "guid":
                selectStrategy(new EntryGUIDSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, null);
                break;
            case "artistname":
                selectStrategy(new EntryArtistNameSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, musicLibrary.getArtistsTable());
                break;
            case "artistguid":
                selectStrategy(new EntryArtistGUIDSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, musicLibrary.getArtistsTable());
                break;
            case "rating":
                selectStrategy(new EntryRatingSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, musicLibrary.getSongsTable());
                break;
            case "songname":
                selectStrategy(new EntrySongNameSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, musicLibrary.getSongsTable());
                break;
            case "songguid":
                selectStrategy(new EntrySongGUIDSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, null);
                break;
            case "duration":
                selectStrategy(new EntryDurationSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, musicLibrary.getSongsTable());
                break;
            case "releasedate":
                selectStrategy(new EntryReleaseDateSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, null);
                break;
            case "medium":
                selectStrategy(new EntryMediumSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, null);
                break;
            case "type":
                selectStrategy(new EntryTypeSearch());
                queriedResults = strategy.doSearch(dbTable, queryString, null);
                break;
            default:
                System.out.println("Invalid search query, for help type 'help search'.");
                return null;
        }
        if (queriedResults != null && queriedResults.size() > 0) {
            Collections.sort(queriedResults);
            for(int i = 0; i < queriedResults.size(); i++) {
                System.out.println(i+": "+queriedResults.get(i).getName());
            }
        } else if (queriedResults == null) {
            System.out.println("Invalid search query, for help type 'help search'.");
        } else {
            System.out.println("Search came up blank. Try 'help search' for proper formatting.");
        }
        return queriedResults;
    }

    /**
     * Sets the strategy that needs to be performed.
     */
    private void selectStrategy(SearchStrategy strategy){
        this.strategy = strategy;
    }

    
}