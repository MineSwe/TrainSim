import java.util.ArrayList;

public class Train extends SimObject {
    private final ArrayList<Station> trainRoute = new ArrayList<>();
    private boolean isOnTrack = false;
    private boolean isInStation = true;

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

    @Override
    public void gameTick()
    {
        if (isOnTrack)
        {
            moveTrain();
        }
        else if (isInStation)
        {

        }
    }

    private void moveTrain()
    {
        
    }

    private void moveTrainToNextTrack()
    {
        
    }
}
