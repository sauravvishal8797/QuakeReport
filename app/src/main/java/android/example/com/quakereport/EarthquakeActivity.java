package android.example.com.quakereport;
import android.example.com.quakereport.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        // Create a fake list of earthquake locations.
        ArrayList<EarthQuakes> earthquakes = new ArrayList<>();
        earthquakes.add(new EarthQuakes("7", "San Francisco", "Feb  2, 2016"));
        earthquakes.add(new EarthQuakes("8.2", "London", "March 3, 2016"));
        earthquakes.add(new EarthQuakes("8.3", "Tokyo", "March 23, 2016"));
        earthquakes.add(new EarthQuakes("8.5", "Mexico City", "April 2, 2016"));
        earthquakes.add(new EarthQuakes("8.7", "Moscow", "April 12, 2016"));
        earthquakes.add(new EarthQuakes("9.0", "paris", "April 22, 2016"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakeAdapter QuakeAdapter = new EarthQuakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(QuakeAdapter);
    }
}
