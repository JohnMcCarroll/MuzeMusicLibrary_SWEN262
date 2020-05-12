package Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// ArtistMaker
// Parses CSV Data and builds a database table
// containing ArtistEntries
public class ArtistTableBuilder implements TableBuilder{

    //File Path
    private String artistCSVPath = "artists.csv";

    /**
     * Creates a table of artists from the CSV file.
     * @param rootPath : the path that leads to the CSV file
     * @return : an ArtistTable that is full of ArtistEntries
     */
    @Override
    public ArtistsTable createDatabaseTable(String rootPath){
        HashMap<String, DBEntry> artists = new HashMap<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(rootPath + artistCSVPath));
            while ((line = reader.readLine()) != null) {
                String[] ArtistInfo = line.split(",");
                if (ArtistInfo.length == 3) {
                    artists.put(ArtistInfo[0], new ArtistEntry(ArtistInfo[0], ArtistInfo[1], ArtistInfo[2]));
                } else {
                    artists.put(ArtistInfo[0], new ArtistEntry(ArtistInfo[0], ArtistInfo[1], ""));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User has no artist collection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArtistsTable(artists);
    }
}