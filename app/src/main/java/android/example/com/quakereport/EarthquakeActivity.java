package android.example.com.quakereport;
import android.content.Intent;
import android.example.com.quakereport.R;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private EarthQuakeAdapter mEarthQuakeAdapter;
    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        mEarthQuakeAdapter = new EarthQuakeAdapter(this, new ArrayList<EarthQuakes>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mEarthQuakeAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EarthQuakes earthQuakes = mEarthQuakeAdapter.getItem(i);
                Uri uri = Uri.parse(earthQuakes.getmUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private LoaderManager.LoaderCallbacks<List<EarthQuakes>> mLoaderManager = new LoaderManager.LoaderCallbacks<List<EarthQuakes>>() {
        @Override public Loader<List<EarthQuakes>> onCreateLoader(int id, Bundle args) {
            return new EarthQuakeLoader(EarthquakeActivity.this, USGS_REQUEST_URL);
        }

        @Override public void onLoadFinished(Loader<List<EarthQuakes>> loader, List<EarthQuakes> data) {

            mEarthQuakeAdapter.clear();

            if(data != null && !data.isEmpty())
            {
                mEarthQuakeAdapter.addAll(data);


            }



        }

        };

        @Override public void onLoaderReset(Loader<List<EarthQuakes>> loader) {

            mEarthQuakeAdapter.clear();

        }
    }



