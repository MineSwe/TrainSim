import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;

public class TrainController {
    private final GUI gui;
    private final int frameSizeX = 400;
    private final int frameSizeY = 400;
    private final int trackBottomRightX = 200;
    private final int trackBottomRightY = 200;
    private final int trackLength = 40;
    private final int stationWidth = 20;
    private final int trainWidth = 5;

    private final Color trackColor = Color.GRAY;
    private final Color stationColor = Color.RED;
    private final Color trainColor = Color.BLUE;

    private final int amountOfTracksBetweenEachCorner = 4;
    private final int amountOfStations = 4;
    private final int amountOfTracksPerStation = 1;
    private final int amountOfTrains = 10;
    private final int trainSpeed = 5;
    private final int loadTimeInMilliseconds = 5000;
    private final int gameTicksPerMilliseconds = 34;
    private boolean gameIsRunning = true; // DON'T DO IT FINAL, PLEASE

    private final ArrayList<SimObject> simObjects = new ArrayList<>();
    private final ArrayList<Track> tracks = new ArrayList<>();
    private final ArrayList<Station> stations = new ArrayList<>();
    private final ArrayList<Train> trains = new ArrayList<>();

    TrainController()
    {
        this.gui = new GUI(this.frameSizeX, this.frameSizeY);
        createTracks();
        linkTracks();
        createStations();
        addStationsToTrack();
        createTrains();
        createGUIComponents();
        startGameTick();
    }

    private void createTracks()
    {
        Track.setTrackLength(this.trackLength);
        int _x = this.trackBottomRightX;
        int _y = this.trackBottomRightY;
        int _angle = 0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < this.amountOfTracksBetweenEachCorner; j++)
            {
                Track _track = new Track(_x, _y, _angle, this.trackColor);
                this.tracks.add(_track);
                this.simObjects.add(_track);

                _x += _track.getEndX();
                _y += _track.getEndY();
            }
            _angle -= 90;
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
            Station _station = new Station(this.stationColor);
            _station.setXScale(this.stationWidth);
            _station.setYScale(this.stationWidth);
            this.stations.add(_station);
            this.simObjects.add(_station);
        }
    }

    // Adds 4 stations to tracks that end in a corner, then 4 stations to tracks that end in the middle
    private void addStationsToTrack()
    {
        for (int i = 0; i < this.amountOfStations; i++)
        {
            int _trackIndex;
            if (i < 4)
            {
                _trackIndex = i * this.amountOfTracksBetweenEachCorner + this.amountOfTracksBetweenEachCorner - 1;
            }
            else
            {
                _trackIndex = ((i-4) * this.amountOfTracksBetweenEachCorner + (this.amountOfTracksBetweenEachCorner / 2)) - 1;
            }
            Track _track = tracks.get(_trackIndex);
            Station _station = stations.get(i);
            _track.setStationNextToTrack(_station);
            _track.setNextToStation(true);
            Track _nextTrack = _track.getNextTrack();
            _station.setNextTrack(_nextTrack);

            System.out.println("Angle " + i + ": " + _track.getAngle());

            int _dx, _dy;
            switch (_track.getAngle())
            {
                case 0: _dx = 0; _dy = 0; break;
                case -90: _dx = 0; _dy = -1; break;
                case -180: _dx = -1; _dy = -1; break;
                case -270: _dx = -1; _dy = 0; break;
                default: _dx = 0; _dy = 0;
            }
            _station.setX(_track.getStartX() + _track.getEndX() + _dx * this.stationWidth);
            _station.setY(_track.getStartY() + _track.getEndY() + _dy * this.stationWidth);
        }
    }

    private void createTrains()
    {
        Train.setWidth(trainWidth);
        int _loadTimeInGameTicks = (int) Math.ceil(this.loadTimeInMilliseconds / this.gameTicksPerMilliseconds);
        Train.setLoadTimeInGameTicks(_loadTimeInGameTicks);
        Train.setSpeed(trainSpeed);
        for (int i = 0; i < this.amountOfTrains; i++)
        {
            // "stations" is needed for train route
            Train _train = new Train(this.stations, this.trainColor);
            this.trains.add(_train);
            this.simObjects.add(_train);
        }
    }

    private void createGUIComponents()
    {
        for (SimObject _simObject : simObjects)
        {
            JLabel _label = this.gui.createObject(
                    _simObject.getX(), 
                    _simObject.getY(), 
                    _simObject.getXScale(), 
                    _simObject.getYScale(), 
                    _simObject.getColor());
            _simObject.setLabel(_label);
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
        for (SimObject _simObject : simObjects)
        {
            _simObject.gameTick();
            JLabel _newLabel = this.gui.updateComponent(_simObject.getLabel(), _simObject.getX(), _simObject.getY(), 
            _simObject.getXScale(), _simObject.getYScale(), _simObject.getIsShowing());
            _simObject.setLabel(_newLabel);
        }
    }
}
