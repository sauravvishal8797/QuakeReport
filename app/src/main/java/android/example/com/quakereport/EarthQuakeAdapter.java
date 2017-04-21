package android.example.com.quakereport;

import android.widget.ArrayAdapter;

/**
 * Created by saurav on 21/4/17.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by saurav on 30/1/17.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuakes> {


    public EarthQuakeAdapter(Activity context, ArrayList<EarthQuakes> earthQuakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthQuakes );
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        EarthQuakes currentQuake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID magnitude
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        magnitude.setText(currentQuake.getmMagnitude());

        // Find the TextView in the list_item.xml layout with the ID places
        TextView places = (TextView) listItemView.findViewById(R.id.places);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        places.setText(currentQuake.getmPlace());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        // Get the version number from the current EarthQuake object and
        // set this text on the number TextView
        date.setText(currentQuake.getmPlace());

        return listItemView;

    }

}




