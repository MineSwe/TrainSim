public class Track extends SimObject {
    private static int trackIndex = 0;
    private static int trackLength;
    private Track nextTrack;
    private boolean isNextToStation = false;
    private boolean isEmpty = true;
    private Station stationNextToTrack;
    // 0 degrees is north and increases counterclockwise (to account for unit circle)
    private final int angle;

    Track(int _x, int _y, int _angle)
    {
        this.setX(_x);
        this.setY(_y);
        this.angle = _angle;
        this.setXScale((int) (Track.trackLength * Math.sin(Math.toRadians(_angle)) * -1));
        this.setYScale((int) (Track.trackLength * Math.cos(Math.toRadians(_angle)) * -1));
        // In case x-/y-scale = 0, add one for width
        if (this.getXScale() == 0)
        {
            this.setXScale(1);
        }
        else if (this.getYScale() == 0)
        {
            this.setYScale(1);
        }
        System.out.print("Track " + this.trackIndex + ": ID = " + this + ", Angle = " + this.angle + ", xScale = " + this.getXScale() + ", yScale = " + this.getYScale());
        this.trackIndex++;
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

    public void setNextToStation(boolean _condition)
    {
        this.isNextToStation = _condition;
    }

    public boolean isNextToStation()
    {
        return this.isNextToStation;
    }

    public void setEmpty(boolean bool)
    {
        this.isEmpty = bool;
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
