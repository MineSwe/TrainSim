import java.util.ArrayList;

public class Train extends SimObject {
    private static int loadTimeInGameTicks;
    private static int speed;

    private String status;
    private final ArrayList<Station> trainRoute = new ArrayList<>();
    private int trainRouteIndex = 0;
    private boolean isOnTrack = false;
    private boolean isInStation = true;
    private int loadTimeInGameTicksLeft;
    private Track currentTrack;
    private Station currentStation;
    private int dx = 0;
    private int dy = 0;

    Train(ArrayList<Station> _stations)
    {
        generateTrainRoute(_stations);
    }

    private void generateTrainRoute(ArrayList<Station> _stations)
    {
        for (int i = 0; i < _stations.size(); i++)
        {
            int _randomStationsIndex = (int)(Math.random() * _stations.size());
            Station _stationToAdd = _stations.get(_randomStationsIndex);
            boolean _canAddStation = true;

            for (int j = 0; j < trainRoute.size(); j++)
            {
                Station _stationThatExistInRoute = this.trainRoute.get(j);
                if (_stationToAdd == _stationThatExistInRoute)
                {
                    _canAddStation = false;
                }
            }

            if (_canAddStation == true)
            {
                this.trainRoute.add(_stationToAdd);
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

    @Override
    public void gameTick()
    {
        System.out.println(this.status + ". x:" + this.getX() + ", y: " + this.getY() + 
        ", dx:" + this.dx + ", dy: " + this.dy);

        if (this.isOnTrack)
        {
            this.moveTrainAlongTrack();
            if (Math.abs(this.getX() - this.currentTrack.getX()) < Math.abs(this.dx) && 
            Math.abs(this.getY() - this.currentTrack.getY()) < Math.abs(this.dy))
            {
                this.moveTrainFromTrack();
            }
        }
        else if (this.isInStation)
        {
            if (this.loadTimeInGameTicksLeft == 0)
            {
                this.currentTrack = this.currentStation.getNextTrack();
                this.isInStation = false;
                this.isOnTrack = true;
                moveToTrack(this.currentTrack.getNextTrack());
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
            Station _nextStation = this.currentTrack.getStationNextToTrack();
            if (_nextStation == this.trainRoute.get(trainRouteIndex) && _nextStation.isTrackAvailable() == true)
            {
                this.moveToStation(_nextStation);
                this.trainRouteIndex++;
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
            this.currentTrack.setEmpty(true);
            this.currentTrack = _track;
            _track.setEmpty(false);
            // 0 degrees is north and increases counterclockwise (to account for unit circle)
            this.dx = (int) Math.sin(_track.getAngle()) * Train.speed;
            this.dy = (int) Math.cos(_track.getAngle()) * Train.speed;

            this.status = "Moving along track " + _track + " towards " + this.trainRoute.get(this.trainRouteIndex);
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
        _station.setAmountOfTrainsOnStation(_station.getAmountOfTrainsOnStation() + 1);
        this.isOnTrack = false;
        this.isInStation = true;
        this.loadTimeInGameTicksLeft = Train.loadTimeInGameTicks;
        this.status = "Loading to train, " + this.loadTimeInGameTicksLeft + " gameticks left";
    }
}
