package Database;

// ArtistEntry
// Artist object contained within the ArtistsTable database
public class ArtistEntry extends DBEntry{
    private String disambiguation;

    // ArtistEntry object constructor
    public ArtistEntry(String GUID, String Name, String disambiguation) {
        super(GUID, Name);
        this.disambiguation = disambiguation;
    }

    // returns artist disambiguation
    public String getDisambiguation() {
        return this.disambiguation;
    }

    // returns a CSV formatted String.
    @Override
    public String toString() {
        return this.getGUID()+","+this.getName()+","+this.getDisambiguation();
    }
}
