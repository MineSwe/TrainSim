import java.util.ArrayList;

public class Train extends SimObject {
    private static int loadTimeInMilliseconds;
    private final ArrayList<Station> trainRoute = new ArrayList<>();
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

    private void moveTrainToNextTrack()
    {
        Track _nextTrack = this.currentStation.getNextTrack();
        // Get angle and calculate dx & dy
    }
}
