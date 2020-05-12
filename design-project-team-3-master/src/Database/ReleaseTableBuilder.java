package Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// ReleaseTable Builder
// Parses CSV Data and builds a database table
// containing ReleaseEntries
public class ReleaseTableBuilder implements TableBuilder{

    private String releasesCSVPath = "releases.csv";
    private SongsTable songsTable;                              // not needed if we keep songs as GUIDs

    //public ReleasesTable releasesTable = new ReleasesTable(createDatabaseTable(songsTable));

    // parses csv and returns an releases hashmap
    public ReleasesTable createDatabaseTable(String rootPath){
        HashMap<String, DBEntry> releases = new HashMap<>();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(rootPath + releasesCSVPath));
            while ((line = reader.readLine()) != null) {
                String title;
                ArrayList<String> songs = new ArrayList<>();
                String[] ReleaseInfo = line.split(",");
                // check if release title has a comma (ie. starts w/ double quote)
                if (ReleaseInfo[2].startsWith("\"")){
                    title = ReleaseInfo[2] + "," + ReleaseInfo[3];
                    for (int i = 6; i < ReleaseInfo.length; i++) {      // populate list of song GUIDs
                        songs.add(ReleaseInfo[i]);
                    }
                    releases.put(ReleaseInfo[0], new ReleaseEntry(ReleaseInfo[0],ReleaseInfo[1], title, ReleaseInfo[4], ReleaseInfo[5], songs));
                } else {
                    for (int i = 5; i < ReleaseInfo.length; i++) {      // populate list of song GUIDs
                        songs.add(ReleaseInfo[i]);
                    }
                    releases.put(ReleaseInfo[0], new ReleaseEntry(ReleaseInfo[0],ReleaseInfo[1], ReleaseInfo[2], ReleaseInfo[3], ReleaseInfo[4], songs));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User has no release collection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ReleasesTable(releases);
    }
}