import java.util.ArrayList;

public class Train extends SimObject {
    private static int loadTimeInGameTicks;
    private static int speed;

    private final ArrayList<Station> trainRoute = new ArrayList<>();
    private int routeIndex = 0;
    private boolean isOnTrack = false;
    private boolean isInStation = true;
    private Track currentTrack;
    private Station currentStation;
    private int dx;
    private int dy;

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
                Station _stationThatExistInRoute = trainRoute.get(j);
                if (_stationToAdd == _stationThatExistInRoute)
                {
                    _canAddStation = false;
                }
            }

            if (_canAddStation == true)
            {
                trainRoute.add(_stationToAdd);
            }
            else
            {
                i--;
            }
        }
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
        if (this.isOnTrack)
        {
            this.moveTrainAlongTrack();
            if (Math.abs(this.x - this.currentTrack.x) < Math.abs(this.dx) && 
            Math.abs(this.y - this.currentTrack.y) < Math.abs(this.dy))
            {
                this.moveTrainFromTrack();
            }
        }
        else if (this.isInStation)
        {
            this.currentTrack = this.currentStation.getNextTrack();
        }
    }

    private void moveTrainAlongTrack()
    {
        this.x += this.dx;
        this.y += this.dy;
    }

    private void moveTrainFromTrack()
    {
        if (this.currentTrack.isNextToStation() == true)
        {
            if (this.currentTrack.getStationNextToTrack() == this.trainRoute.get(routeIndex) && 
            this.currentTrack.getStationNextToTrack().isTrackAvailable() == true)
            {
                this.moveToStation(this.currentTrack.getStationNextToTrack());
            }
        }
        else
        {
            // If track has no trains
            if (this.currentTrack.getNextTrack().isEmpty())
            {
                this.currentTrack = this.currentTrack.getNextTrack();

                // 0 degrees is north and increases clockwise
                dx = (int) Math.sin(this.currentTrack.getAngle()) * Train.speed;
                dy = (int) Math.cos(this.currentTrack.getAngle()) * Train.speed;
            }
        }
    }

    private void moveToStation(Station _station)
    {
        this.currentStation = _station;
        this.currentTrack = null;
        _station.setAmountOfTrainsOnStation(_station.getAmountOfTrainsOnStation() + 1);
        this.isOnTrack = false;
        this.isInStation = true;
    }
}
