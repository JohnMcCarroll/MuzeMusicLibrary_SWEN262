package Command.SearchStrategies;


import Database.DBEntry;
import Database.DatabaseTable;
import Database.ReleaseEntry;
import Database.ReleasesTable;

import java.util.ArrayList;

public class EntryReleaseDateSearch implements SearchStrategy {

    /**
     * Performs a search by release data, can be performed on releases.
     */
    public ArrayList<DBEntry> doSearch(DatabaseTable data, String searchQuery, DatabaseTable extraData) {
        if(data instanceof ReleasesTable){
            ArrayList<DBEntry> releasesByDate = new ArrayList<>();
            for(DBEntry dbe : data.getEntries().values()){
                ReleaseEntry re = (ReleaseEntry)dbe;
                String[] query = searchQuery.split(" ");
                if(query.length != 2) {
                    return null;
                }
                if (inBetween(re.getIssueDate(), query[0], query[1])){
                    releasesByDate.add(re);
                }
            }
            return releasesByDate;
        }
        return null;
    }

    /**
     * Private method to check if a date is inbetween two dates.
     */
    private boolean inBetween(String releaseDate, String dateStart, String dateEnd){
        String[] tempDate = releaseDate.split("-");
        String[] ds = dateStart.split("-");
        String[] de = dateEnd.split("-");
        String[] rd = new String[3];
        for(int i = 0; i < 3; i++){
            if(i<tempDate.length){
                rd[i] = tempDate[i];
            } else {
                rd[i] = "01";
            }
        }
        try {
            if (Integer.parseInt(rd[0]) > Integer.parseInt(ds[0]) && Integer.parseInt(rd[0]) < Integer.parseInt(de[0])) {
                return true;
            }
            if (Integer.parseInt(rd[1]) > Integer.parseInt(ds[1]) && Integer.parseInt(rd[1]) < Integer.parseInt(de[1])) {
                return true;
            }
            if (Integer.parseInt(rd[2]) >= Integer.parseInt(ds[2]) && Integer.parseInt(rd[2]) <= Integer.parseInt(de[2])) {
                return true;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            return false;
        }
        return false;
    }

}
