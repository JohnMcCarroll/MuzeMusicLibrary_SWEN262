package Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// SongMaker
// Parses CSV Data and builds a database table
// containing SongEntries
public class SongTableBuilder implements TableBuilder{

    private String releasesCSVPath = "songs.csv";

    //public SongsTable songsTable = new SongsTable(createDatabaseTable());

    // parses csv and returns an songs hashmap
    @Override
    public SongsTable createDatabaseTable(String rootPath){
        HashMap<String, DBEntry> songs = new HashMap<>();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(rootPath + releasesCSVPath));
            while ((line = reader.readLine()) != null) {
                String title;
                String rating = "0";
                Pattern pattern = Pattern.compile("\".*\"");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()){                                          // if the title is enclosed with ", get the title
                    title = matcher.group();
                    String[] lineWithoutTitle = line.split("\".*\"");       // remove title from line

                    if (lineWithoutTitle.length == 2) {
                        rating = lineWithoutTitle[1];       // store rating
                    }
                    String[] restOfLine = lineWithoutTitle[0].split(",");
                    songs.put(restOfLine[0], new SongEntry(restOfLine[0], restOfLine[1], restOfLine[2], title, rating));
                } else {
                    String[] SongInfo = line.split(",");            // split the string
                    // check if song has rating
                    if (SongInfo.length >= 5) {
                        rating = SongInfo[4];
                    }
                    songs.put(SongInfo[0], new SongEntry(SongInfo[0], SongInfo[1], SongInfo[2], SongInfo[3], rating));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User has no song collection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SongsTable(songs);
    }

}