public class Audio extends Media{
    private int durationInMinutes;
    private String artistName;

    public Audio(String name, int durationInMinutes, String artisName) {
        super(name);
        this.durationInMinutes = durationInMinutes;
        this.artistName = artisName;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }


    @Override
    public void play() {


    }

    @Override
    public String getDescription() {
        return "";
    }
}