package android.example.com.quakereport;

/**
 * Created by saurav on 21/4/17.
 */

public class EarthQuakes {

    private String mMagnitude;
    private String mPlace;
    private String mDate;

    public EarthQuakes(String Magnitude, String Place, String Date){

        mMagnitude = Magnitude;
        mPlace = Place;
        mDate = Date;
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
}
