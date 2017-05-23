package android.example.com.quakereport;

import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

/**
 * Created by saurav on 4/5/17.
 */

public class EarthQuakeLoader extends AsyncTaskLoader<List<EarthQuakes>> {

    private static final String LOG_TAG = EarthQuakeLoader.class.getName();

    private String mUrl;




    public EarthQuakeLoader(Context context, String url){

        super(context);

        mUrl = url;
    }

    @Override protected void onStartLoading() {
        forceLoad();
    }


    @Override public List<EarthQuakes> loadInBackground() {
        if(mUrl == null){
            return null;
        }

        List<EarthQuakes> earthQuakes = QueryUtils.fetchEarthQuakes(mUrl);
        return earthQuakes;
    }


}
