import java.awt.Color;
import java.util.ArrayList;

public class Train extends SimObject {
    private static int loadTimeInGameTicks;
    private static int speed;
    private static int width;

    private String status;
    private final ArrayList<Station> trainRoute = new ArrayList<>();
    private final ArrayList<Station> startTrainRoute = new ArrayList<>();
    private int trainRouteIndex = 0;
    private boolean isOnTrack = false;
    private boolean isInStation = true;
    private int loadTimeInGameTicksLeft;
    private Track currentTrack;
    private Station currentStation;
    private int dx = 0;
    private int dy = 0;

    Train(ArrayList<Station> _stations, Color _color)
    {
        generateTrainRoute(_stations);
        this.setColor(_color);
        this.setXScale(Train.width);
        this.setYScale(Train.width);
    }

    private void generateTrainRoute(ArrayList<Station> _stations)
    {
        for (int i = 0; i < _stations.size(); i++)
        {
            System.out.println("Trying for train " + this + ", route " + i);
            int _randomStationsIndex = (int)(Math.random() * _stations.size());
            Station _stationToAdd = _stations.get(_randomStationsIndex);
            boolean _canAddStation = true;

            for (Station _stationThatExistInRoute : this.trainRoute)
            {
                if (_stationToAdd == _stationThatExistInRoute)
                {
                    _canAddStation = false;
                }
            }

            if (i == 0)
            {
                if (_stationToAdd.isTrackAvailable() == false)
                {
                    _canAddStation = false;
                }
            }

            if (_canAddStation == true)
            {
                this.trainRoute.add(_stationToAdd);
                if (i == 0)
                {
                    startTrainRoute.add(_stationToAdd);
                }
            }
            else
            {
                i--;
            }
        }
        moveToStation(this.trainRoute.get(0));
    }

    static public void setSpeed(int _speed)
    {
        Train.speed = _speed;
    }

    static public void setLoadTimeInGameTicks(int _loadTimeInGameTicks)
    {
        Train.loadTimeInGameTicks = _loadTimeInGameTicks;
    }

    static public void setWidth(int _width)
    {
        Train.width = _width;
    }

    @Override
    public void gameTick()
    {
        //System.out.println(this.status);

        if (this.isOnTrack)
        {
            if (Math.abs(this.currentTrack.getStartX() - this.getX() + this.currentTrack.getEndX()) <= Math.abs(this.dx)+1 && 
            Math.abs(this.currentTrack.getStartY() - this.getY() + this.currentTrack.getEndY()) <= Math.abs(this.dy)+1)
            {
                this.moveTrainFromTrack();
            }
            else
            {
                this.moveTrainAlongTrack();
            }
        }
        else if (this.isInStation)
        {
            if (this.loadTimeInGameTicksLeft == 0)
            {
                if (this.currentStation.getNextTrack().isEmpty())
                {
                    this.currentStation.setAmountOfTrainsOnStation(this.currentStation.getAmountOfTrainsOnStation()-1);
                    System.out.println("Station " + this + " has " + currentStation.getAmountOfTrainsOnStation() + " tracks");
                    this.setIsShowing(true);
                    moveToTrack(this.currentStation.getNextTrack());
                }
            }
            else
            {
                this.loadTimeInGameTicksLeft--;
                this.status = "Loading to train, " + this.loadTimeInGameTicksLeft + " gameticks left";
            }
        }
    }

    private void moveTrainAlongTrack()
    {
        this.setX(this.getX() + this.dx);
        this.setY(this.getY() + this.dy);
    }

    private void moveTrainFromTrack()
    {
        if (this.currentTrack.isNextToStation() == true)
        {
            this.status = "Track = " + currentTrack.getStationNextToTrack() + ", Train " + this + " wants = " + this.trainRoute.get(this.trainRouteIndex);
            Station _nextStation = this.currentTrack.getStationNextToTrack();
            if (_nextStation == this.trainRoute.get(this.trainRouteIndex))
            {
                if (_nextStation.isTrackAvailable() == true)
                {
                    this.currentTrack.setEmpty(true);
                    this.trainRouteIndex++;
                    if (this.trainRouteIndex == trainRoute.size()-1)
                    {
                        this.trainRouteIndex = 0;
                    }
                    this.moveToStation(_nextStation);
                }
                else
                {
                    System.out.println(this + " can't reach station");
                }
            }
            else
            {
                moveToTrack(this.currentTrack.getNextTrack());
            }
        }
        else
        {
            moveToTrack(this.currentTrack.getNextTrack());
        }
    }

    private void moveToTrack(Track _track)
    {
        // If next track has no trains
        if (_track.isEmpty())
        {
            if (this.isOnTrack == true)
            {
                this.currentTrack.setEmpty(true);
            }
            this.currentTrack = _track;
            this.isInStation = false;
            this.isOnTrack = true;
            _track.setEmpty(false);
            this.setX(_track.getStartX());
            this.setY(_track.getStartY());
            // 0 degrees is north and increases counterclockwise (to account for unit circle)
            this.dx = (int) Math.sin(Math.toRadians(_track.getAngle())) * -1 * Train.speed;
            this.dy = (int) Math.cos(Math.toRadians(_track.getAngle())) * -1 * Train.speed;

            //this.status = "Moving along track " + _track + " towards " + this.trainRoute.get(this.trainRouteIndex);
        }
        else
        {
            this.status = "Waiting to get to track " + _track;
        }
        
    }

    private void moveToStation(Station _station)
    {
        this.currentStation = _station;
        this.currentTrack = null;
        this.setIsShowing(false);
        _station.setAmountOfTrainsOnStation(_station.getAmountOfTrainsOnStation() + 1);
        System.out.println("Station " + _station + " has " + _station.getAmountOfTrainsOnStation() + " tracks");
        this.isOnTrack = false;
        this.isInStation = true;
        this.loadTimeInGameTicksLeft = Train.loadTimeInGameTicks;
        this.status = "Loading to train, " + this.loadTimeInGameTicksLeft + " gameticks left";
    }
}
