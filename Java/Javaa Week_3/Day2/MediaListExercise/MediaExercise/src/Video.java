public class Video extends Media{

    private int durationMinutes;
    private String videoResolution;

    public Video (String name,int durationMinutes, String videoResolution){
        super(name);
        this.durationMinutes = durationMinutes;
        this.videoResolution = videoResolution;

    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(String videoResolution) {
        this.videoResolution = videoResolution;
    }

    @Override
    public void play(){
        System.out.println();
    }

    @Override
    public String getDescription() {
        return "";
    }
}
