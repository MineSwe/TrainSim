public class Track extends SimObject {
    private static int trackIndex = 0;
    private static int trackLength;
    private Track nextTrack;
    private boolean isNextToStation;
    private boolean isEmpty;
    private Station stationNextToTrack;
    // 0 degrees is north and increases counterclockwise (to account for unit circle)
    private final int angle;

    Track(int _x, int _y, int _angle)
    {
        this.setX(_x);
        this.setY(_y);
        this.angle = _angle;
        this.setXScale((int) (Track.trackLength * Math.sin(Math.toRadians(_angle)) * -1) + 1); // In case x-/y-scale = 0, add one for width
        this.setYScale((int) (Track.trackLength * Math.cos(Math.toRadians(_angle)) * -1) + 1);
        System.out.print("Track " + trackIndex + ": Angle = " + angle + ", xScale = " + this.getXScale() + ", yScale = " + this.getYScale());
        trackIndex++;
    }

    public static void setTrackLength(int _trackLength)
    {
        Track.trackLength = _trackLength;
    }

    public void setNextTrack(Track _nextTrack)
    {
        this.nextTrack = _nextTrack;
    }

    public Track getNextTrack()
    {
        return this.nextTrack;
    }

    public boolean isNextToStation()
    {
        return this.isNextToStation;
    }

    public boolean isEmpty()
    {
        return this.isEmpty;
    }

    public void setStationNextToTrack(Station _stationNextToTrack)
    {
        this.stationNextToTrack = _stationNextToTrack;
    }

    public Station getStationNextToTrack()
    {
        return this.stationNextToTrack;
    }

    public int getAngle()
    {
        return this.angle;
    }
}
