package android.example.com.quakereport;

import android.graphics.drawable.GradientDrawable;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v4.content.ContextCompat;
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
import java.util.Date;
import java.util.logging.SimpleFormatter;

import static android.example.com.quakereport.R.id.date;
import static android.example.com.quakereport.R.id.magnitude;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by saurav on 30/1/17.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuakes> {

    Date dateformatted;


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
        double Magnitude = currentQuake.getmMagnitude();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedDecimal = decimalFormat.format(Magnitude);
        magnitude.setText(formattedDecimal);

        // Find the TextView in the list_item.xml layout with the ID places
        TextView places1 = (TextView) listItemView.findViewById(R.id.location1);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        places1.setText(currentQuake.getmPlace1());

        TextView places2 = (TextView) listItemView.findViewById(R.id.location2);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        places2.setText(currentQuake.getmPlace2());

        GradientDrawable magnitude_circle = (GradientDrawable) magnitude.getBackground();

        int magnitudeColor = getMagnitudeColor(currentQuake.getmMagnitude());

        magnitude_circle.setColor(magnitudeColor);


        long time = currentQuake.getTimeinmilliseconds();

        dateformatted = new Date(currentQuake.getTimeinmilliseconds());

        TextView timeShow = (TextView) listItemView.findViewById(R.id.time);

        String timeFormatted = formatTime(time);
        timeShow.setText(timeFormatted);

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        // Get the version number from the current EarthQuake object and
        // set this text on the number TextView
        String formatteddate = formatDate(dateformatted);
        date.setText(formatteddate);

        return listItemView;

    }

    private String formatDate(Date dateformatted) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL ddd, yyy");
        return dateFormat.format(dateformatted);

    }

    private String formatTime(long milliseconds){
        SimpleDateFormat timeformatted = new SimpleDateFormat("h:mm aa");
        return timeformatted.format(dateformatted);
    }

    private int getMagnitudeColor(double magnitude){

        int magFloor = (int) Math.floor(magnitude);
        int ColorMagnitudeId = 0;

        switch(magFloor){

            case 0:
            case 1:
                ColorMagnitudeId = R.color.magnitude1;
                break;
            case 2:
                ColorMagnitudeId = R.color.magnitude2;
                break;
            case 3:
                ColorMagnitudeId = R.color.magnitude3;
                break;
            case 4:
                ColorMagnitudeId = R.color.magnitude4;
                break;
            case 5:
                ColorMagnitudeId = R.color.magnitude5;
                break;
            case 6:
                ColorMagnitudeId = R.color.magnitude6;
                break;
            case 7:
                ColorMagnitudeId = R.color.magnitude7;
                break;
            case 8:
                ColorMagnitudeId = R.color.magnitude8;
                break;
            case 9:
                ColorMagnitudeId = R.color.magnitude9;
                break;
            case 10:
                ColorMagnitudeId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), ColorMagnitudeId);

    }

}




