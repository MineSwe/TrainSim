import java.util.ArrayList;

public class TrainController {
    private final int amountOfTracksBetweenEachCorner = 4;
    private final int amountOfStations = 6;
    private final int amountOfTracksPerStation = 2;
    private final int amountOfTrains = 4;
    private final int loadTimeInMilliseconds = 5000;
    private final int gameTicksPerMilliseconds = 1000;
    private boolean gameIsRunning = false; // DON'T DO IT FINAL, PLEASE

    private final ArrayList<SimObject> simObjects = new ArrayList<>();
    private final ArrayList<Track> tracks = new ArrayList<>();
    private final ArrayList<Station> stations = new ArrayList<>();
    private final ArrayList<Train> trains = new ArrayList<>();

    TrainController()
    {
        createTracks();
        linkTracks();
        createStations();
        addStationsToTrack();
        createTrains();
        startGameTick();
    }

    private void createTracks()
    {
        int _amountOfTracks = this.amountOfStations * this.amountOfTracksBetweenEachCorner;
        int _angle = 0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < _amountOfTracks; j++)
            {
                Track _track = new Track(_angle);
                this.tracks.add(_track);
                this.simObjects.add(_track);
            }
            _angle += 90;
        }
    }

    private void linkTracks()
    {
        for (int i = 0; i < this.tracks.size()-1; i++)
        {
            Track _track = tracks.get(i);
            Track _nextTrack = tracks.get(i+1);
            _track.setNextTrack(_nextTrack);
            // Makes last track connect to first track
            int _lastTrackIndex = this.tracks.size() - 1;
            Track _lastTrack = this.tracks.get(_lastTrackIndex);
            _lastTrack.setNextTrack(this.tracks.get(0));
        }
    }

    private void createStations()
    {
        Station.setAmountOfTracksPerStation(this.amountOfTracksPerStation);
        for (int i = 0; i < this.amountOfStations; i++)
        {
            Station _station = new Station();
            this.stations.add(_station);
            this.simObjects.add(_station);
        }
    }

    // Adds 4 stations to each corner, then 4 stations to the middle
    private void addStationsToTrack()
    {
        for (int i = 0; i < this.amountOfStations; i++)
        {
            int _trackIndex;
            if (i < 4)
            {
                _trackIndex = i*amountOfTracksBetweenEachCorner + amountOfTracksBetweenEachCorner - 1;
                Track _track = tracks.get(_trackIndex);
                Station _station = stations.get(i);
                _track.setStationNextToTrack(_station);
                this.simObjects.add(_track);
            }
            else
            {
                _trackIndex = ((i-4) * amountOfTracksBetweenEachCorner + (amountOfTracksBetweenEachCorner / 2)) -1;
                Track _track = tracks.get(_trackIndex);
                Station _station = stations.get(i);
                _track.setStationNextToTrack(_station);
                this.simObjects.add(_track);
            }
        }
    }

    private void createTrains()
    {
        int _loadTimeInGameTicks = (int) Math.ceil(loadTimeInMilliseconds / gameTicksPerMilliseconds);
        Train.setLoadTimeInGameTicks(_loadTimeInGameTicks);
        for (int i = 0; i < this.amountOfTrains; i++)
        {
            // "stations" is needed for train route
            Train _train = new Train(this.stations);
            this.trains.add(_train);
            this.simObjects.add(_train);
        }
    }

    private void startGameTick()
    {
        long _lastExecution = System.currentTimeMillis();
        while (gameIsRunning)
        {
            if (System.currentTimeMillis() - _lastExecution >= this.gameTicksPerMilliseconds)
            {
                this.gameTick();
                _lastExecution = System.currentTimeMillis();
            }
        }
    }

    private void gameTick()
    {
        for (int i = 0; i < this.simObjects.size(); i++)
        {
            SimObject _simObject = this.simObjects.get(i);
            _simObject.gameTick();
        }
    }
}
