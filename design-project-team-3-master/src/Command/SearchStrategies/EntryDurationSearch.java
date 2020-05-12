package Command.SearchStrategies;

import Database.*;

import java.util.ArrayList;

public class EntryDurationSearch implements SearchStrategy {
    /**
     * Performs a search by Duration, can be done on a song, or playlist.
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData) {
        String[] query = searchQuery.split(" ");
        if(query.length != 2) {
            return null;
        }
        int searchDuration;
        try {
            searchDuration = Integer.parseInt(query[0]);
        } catch (NumberFormatException ne) {
            return null;
        }
        if(data instanceof SongsTable){
            ArrayList<DBEntry> songsByDuration = new ArrayList<>();
            for(DBEntry dbe : data.getEntries().values()){
                SongEntry se = (SongEntry)dbe;
                int songDuration = Integer.parseInt(se.getDuration());
                if(meetsDurationQuery(songDuration, searchDuration, query[1])){
                    songsByDuration.add(se);
                }
            }
            return songsByDuration;
        }
        if(data instanceof ReleasesTable){
            ArrayList<DBEntry> releaseByDuration = new ArrayList<>();
            for(DBEntry dbe : data.getEntries().values()){
                ReleaseEntry re = (ReleaseEntry)dbe;
                int totalDuration = 0;
                for(String songGUID : re.getSongs()){
                    SongEntry se = (SongEntry)extraData.getEntries().get(songGUID);
                    totalDuration += Integer.parseInt(se.getDuration());
                }
                if(meetsDurationQuery(totalDuration, searchDuration, query[1])){
                    releaseByDuration.add(re);
                }

            }
            return releaseByDuration;
        }
        return null;
    }

    /**
     * Checks to see if a duration is within the min or max of a searchDuration
     */
    private boolean meetsDurationQuery(int duration, int searchDuration, String type){
        switch (type.toLowerCase()){
            case "max":
                if(duration <= searchDuration) {
                    return true;
                }
                break;
            case "min":
                if(duration >= searchDuration) {
                    return true;
                }
                break;
            default:
        }
        return false;
    }
}
