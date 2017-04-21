package android.example.com.quakereport;

/**
 * Created by saurav on 21/4/17.
 */

public class EarthQuakes {

    private double mMagnitude;
    private String mPlace;
    private long mDate;

    public EarthQuakes(double Magnitude, String Place, long Date){

        mMagnitude = Magnitude;
        mPlace = Place;
        mDate = Date;
    }

    public double getmMagnitude(){
        return mMagnitude;
    }

    public String getmPlace(){
        return mPlace;
    }

    public long getmDate(){
        return mDate;
    }
}
