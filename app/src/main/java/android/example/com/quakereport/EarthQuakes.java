package android.example.com.quakereport;

/**
 * Created by saurav on 21/4/17.
 */

public class EarthQuakes {

    private String mMagnitude;
    private String mPlace;
    private String mDate;
    private long mtimeinmilliseconds;

    public EarthQuakes(String Magnitude, String Place, long timeinmilliseconds){

        mMagnitude = Magnitude;
        mPlace = Place;
        mtimeinmilliseconds = timeinmilliseconds;
    }

    public String getmMagnitude(){
        return mMagnitude;
    }

    public String getmPlace(){
        return mPlace;
    }

    public String getmDate(){
        return mDate;
    }

    public long getTimeinmilliseconds(){
        return mtimeinmilliseconds;
    }
}
