package Database;

import java.util.ArrayList;

// ReleaseEntry
// Release object contained within the ReleasesTable database
public class ReleaseEntry extends DBEntry {
    private String artistGUID;
    private String issueDate;
    private String medium;
    private ArrayList<String> songs;

    // ReleaseEntry object constructor
    public ReleaseEntry(String GUID, String artistGUID, String title, String medium, String issueDate, ArrayList<String> songs) {
        super(GUID, title);
        this.artistGUID = artistGUID;
        this.issueDate = issueDate;
        this.medium = medium;
        this.songs = songs;
    }

    //gets the ArtistGUID
    public String getArtistGUID() {
        return this.artistGUID;
    }

    //gets the IssueDate
    public String getIssueDate() {
        return this.issueDate;
    }

    //gets the Medium
    public String getMedium() {
        return this.medium;
    }

    //gets the Songs
    public ArrayList<String> getSongs() {
        return this.songs;
    }

    //converts to csv string
    @Override
    public String toString(){
        String songStrings = "";
        for (String song : this.songs){
            songStrings += song +",";

        }
        return this.getGUID()+","+this.getArtistGUID()+","+this.getName()+","+this.getIssueDate()+","+this.getMedium()
                +","+songStrings;
    }
}
