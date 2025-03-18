public class Station {
    private static int amountOfTracksPerStation;

    public static void setAmountOfTracksPerStation(int _amountOfTracksPerStation)
    {
        Station.amountOfTracksPerStation = _amountOfTracksPerStation;
    }

    public static int getAmountOfTracksPerStation()
    {
        return amountOfTracksPerStation;
    }
}
