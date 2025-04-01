public class Track extends SimObject {
    private Track previousTrack;
    private Track nextTrack;
    private boolean isNextToStation;
    private Station stationNextToTrack;
    private int angle;

    public void setPreviousTrack(Track _previousTrack)
    {
        this.previousTrack = _previousTrack;
    }

    public void setNextTrack(Track _nextTrack)
    {
        this.nextTrack = _nextTrack;
    }

    public Track getNextTrack()
    {
        return this.nextTrack;
    }

    public void setStationNextToTrack(Station _stationNextToTrack)
    {
        this.stationNextToTrack = _stationNextToTrack;
    }

    public Station getStationNextToTrack()
    {
        return stationNextToTrack;
    }
}
