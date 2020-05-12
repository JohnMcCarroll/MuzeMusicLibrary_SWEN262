package Command.SearchStrategies;

import Database.DBEntry;
import Database.DatabaseTable;

import java.util.ArrayList;


public interface SearchStrategy {

    /**
     * Strategy Design Pattern
     * Performs a search on a DBTable.
     * @param data the specific table that the search query will be performed on.
     * @param searchQuery the specific search query different formats for anything.
     * @param extraData any other table that a search might need
     * @return An ArrayList of DBEntries.
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData);
}