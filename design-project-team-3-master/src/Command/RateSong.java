package Command;

import Database.DBEntry;
import Database.MusicLibrary;
import Command.SearchStrategies.EntryNameSearch;
import Command.SearchStrategies.SearchStrategy.*;
import Database.*;

import java.util.ArrayList;

public class RateSong implements MusicCommand {

    //Rates a song when given a song guid and rating in data, has error handling
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary musicLibrary){

        SongEntry song = (SongEntry) musicLibrary.getSongsTable().getByGUID(data[0]);
        try {
            song.addRating(Float.parseFloat(data[1]));
            System.out.println(song.getName() + " was rated " + song.getRating() + " stars.");
        } catch (NumberFormatException ne) {
            System.out.println("Your rating should be a number from 1 to 5.");
        }


        return null;
    }
}