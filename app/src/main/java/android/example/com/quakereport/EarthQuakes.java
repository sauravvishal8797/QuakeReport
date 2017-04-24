package android.example.com.quakereport;

/**
 * Created by saurav on 21/4/17.
 */

public class EarthQuakes {

    private double mMagnitude;
    private String mPlace1;
    private String mPlace2;
    private long mtimeinmilliseconds;
    private String mUrl;

    public EarthQuakes(double Magnitude, String Place1, String Place2, long timeinmilliseconds, String Url){

        mMagnitude = Magnitude;
        mPlace1 = Place1;
        mPlace2 = Place2;
        mtimeinmilliseconds = timeinmilliseconds;
        mUrl = Url;
    }

    public double getmMagnitude(){
        return mMagnitude;
    }

    public String getmPlace1(){
        return mPlace1;
    }

    public String getmPlace2(){
        return mPlace2;
    }

    public long getTimeinmilliseconds(){
        return mtimeinmilliseconds;
    }

    public String getmUrl(){
        return mUrl;
    }
}
