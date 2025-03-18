import java.util.ArrayList;

public class Train {
    private final ArrayList<Station> trainRoute = new ArrayList<>();

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

    private void moveTrainToNextTrack()
    {
        
    }
}
