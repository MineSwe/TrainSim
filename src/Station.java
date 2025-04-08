public class Station extends SimObject {
    private static int amountOfTracksPerStation;
    private int amountOfTrainsOnStation = 0;
    private Track nextTrack;

    public static void setAmountOfTracksPerStation(int _amountOfTracksPerStation)
    {
        Station.amountOfTracksPerStation = _amountOfTracksPerStation;
    }

    public static int getAmountOfTracksPerStation()
    {
        return Station.amountOfTracksPerStation;
    }

    public void setAmountOfTrainsOnStation(int _amountOfTrainsOnStation)
    {
        this.amountOfTrainsOnStation = _amountOfTrainsOnStation;
    }

    public int getAmountOfTrainsOnStation()
    {
        return this.amountOfTrainsOnStation;
    }

    public void setNextTrack(Track _nextTrack)
    {
        this.nextTrack = _nextTrack;
    }

    public Track getNextTrack()
    {
        return this.nextTrack;
    }

    public boolean isTrackAvailable()
    {
        return this.amountOfTrainsOnStation <= Station.amountOfTracksPerStation;
    }
}
