package Database;

// SongEntry
// Song object contained within the SongsTable database
public class SongEntry extends DBEntry {
    private String artistGUID;
    private String duration;
    private float rating;

    // SongEntry object constructor
    public SongEntry(String GUID, String artistGUID, String duration, String title, String rating) {
        super(GUID, title);
        this.artistGUID = artistGUID;
        this.duration = duration;
        this.rating = Float.parseFloat(rating.replace(",",""));
    }

    // returns song ArtistGUID
    public String getArtistGUID() {
        return this.artistGUID;
    }

    // returns song Duration
    public String getDuration() {
        return this.duration;
    }

    // adds a rating to a song
    public void addRating(float rating){
        this.rating = rating;
    }

    // gets the rating of a song
    public float getRating(){
        return this.rating;
    }

    // converts a song to a csv string
    @Override
    public String toString(){
        return this.getGUID()+","+this.getArtistGUID()+","+this.getDuration()+","+this.getName()+","+this.getRating();
    }
}
