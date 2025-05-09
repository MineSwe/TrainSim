import java.awt.Color;

public class Track extends SimObject {
    private static int trackLength;
    private Track nextTrack;
    private boolean isNextToStation = false;
    private boolean isEmpty = true;
    private Station stationNextToTrack;
    // 0 degrees is north and increases counterclockwise (to account for unit circle)
    private final int angle;
    // This is for calculations, since the GUI (which takes from SimObject) doesn't support negative width
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;

    Track(int _x, int _y, int _angle, Color _color)
    {
        this.startX = _x;
        this.startY = _y;
        this.endX = (int) (Track.trackLength * Math.sin(Math.toRadians(_angle)) * -1);
        this.endY = (int) (Track.trackLength * Math.cos(Math.toRadians(_angle)) * -1);
        this.angle = _angle;
        this.setColor(_color);
        // This is for the display, since it doesn't support negative width
        this.setX(Math.min(_x, _x + endX));
        this.setY(Math.min(_y, _y + endY));
        this.setXScale(Math.abs(endX));
        this.setYScale(Math.abs(endY));
        // In case x-/y-scale = 0, add one for width
        if (this.getXScale() == 0)
        {
            this.setXScale(1);
        }
        if (this.getYScale() == 0)
        {
            this.setYScale(1);
        }
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

    public int getStartX()
    {
        return this.startX;
    }

    public int getStartY()
    {
        return this.startY;
    }

    public int getEndX()
    {
        return this.endX;
    }

    public int getEndY()
    {
        return this.endY;
    }
}
