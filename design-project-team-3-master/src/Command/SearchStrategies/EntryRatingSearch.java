package Command.SearchStrategies;

import Database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EntryRatingSearch implements SearchStrategy {

    /**
     * Performs a search by rating, can be performed on all entries
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData) {
        int minRating;
        try {
            minRating = Integer.parseInt(searchQuery);
        } catch (NumberFormatException ne) {
            return null;
        }
        if (data instanceof SongsTable) {
            return ((SongsTable)data).getByMinRating(minRating);
        } else if (data instanceof ArtistsTable) {
            ArrayList<DBEntry> artistByRating = new ArrayList<>();
            HashMap<String, DBEntry> songEntries = extraData.getEntries();
            HashMap<String, LinkedList<Integer>> artistRatings = new HashMap<>();
            for(DBEntry dbe : songEntries.values()) {
                SongEntry se = (SongEntry)dbe;
                if (!artistRatings.containsKey(se.getArtistGUID())) {
                    LinkedList<Integer> ratings = new LinkedList<>();
                    ratings.add((int)se.getRating());
                    artistRatings.put(se.getArtistGUID(), ratings);
                } else {
                    artistRatings.get(se.getArtistGUID()).add((int)se.getRating());
                }
            }
            for(String artistGUID : artistRatings.keySet()){
                float tempRating = 0;
                int tempLength = 0;
                for (int num : artistRatings.get(artistGUID)) {
                    if (num != 0){
                        tempRating += num;
                        tempLength += 1;
                    }
                }
                if(tempLength > 0){
                    tempRating = tempRating/(float)tempLength;
                }
                if (tempRating >= minRating){
                    artistByRating.add(data.getByGUID(artistGUID));
                } else {
                    System.out.println(artistGUID);
                }
            }
            return artistByRating;
        } else {
            ArrayList<DBEntry> releaseByRating = new ArrayList<>();
            HashMap<String, DBEntry> songEntries = extraData.getEntries();
            for(DBEntry dbe : data.getEntries().values()){
                int tempRating = 0;
                int tempLength = 0;
                ReleaseEntry re = (ReleaseEntry)dbe;
                for(String strDBE :re.getSongs()){
                    SongEntry se = (SongEntry)songEntries.get(strDBE);
                    float rating = se.getRating();
                    if (rating > 0){
                        tempRating += rating;
                        tempLength += 1;
                    }
                }
                if (tempLength > 0 && tempRating/(float)tempLength >= minRating) {
                    releaseByRating.add(dbe);
                }
            }
        }
        return null;
    }
}
