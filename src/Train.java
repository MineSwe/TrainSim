import java.util.ArrayList;

public class Train extends SimObject {
    private static int loadTimeInMilliseconds;
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
            int randomStationsIndex = (int)(Math.random() * _stations.size());
            Station stationToAdd = _stations.get(randomStationsIndex);
            boolean canAddStation = true;

            for (int j = 0; j < trainRoute.size(); j++)
            {
                Station stationThatExistInRoute = trainRoute.get(j);
                if (stationToAdd == stationThatExistInRoute)
                {
                    canAddStation = false;
                }
            }

            if (canAddStation == true)
            {
                trainRoute.add(stationToAdd);
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

    static public void setLoadTimeInMilliseconds(int _loadTimeInMilliseconds)
    {
        Train.loadTimeInMilliseconds = _loadTimeInMilliseconds;
    }

    @Override
    public void gameTick()
    {
        if (isOnTrack)
        {
            moveTrain();
            if (this.x == this.currentTrack.x)
            {
                moveTrainFromTrack();
            }
        }
        else if (isInStation)
        {
            this.currentTrack = this.currentStation.getNextTrack();
        }
    }

    private void moveTrain()
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
            this.currentStation = this.currentTrack.getStationNextToTrack();
            this.currentTrack = null;
            this.currentStation.setAmountOfTrainsOnStation(this.currentStation.getAmountOfTrainsOnStation() + 1);
        }
        else
        {
            if (this.currentTrack.getNextTrack().isEmpty())
            {
                this.currentTrack = this.currentTrack.getNextTrack();
                // If dy is negative the components of the angle (dy, dx) change places
                if (dy < 0)
                {
                    dx = (int) (dy / Math.tan(this.currentTrack.getAngle()));
                    dy = (int) (dx * Math.tan(this.currentTrack.getAngle()));
                }
                else
                {
                    dx = (int) (dy * Math.tan(this.currentTrack.getAngle()));
                    dy = (int) (dx / Math.tan(this.currentTrack.getAngle()));
                }
            }
        }
    }
}
