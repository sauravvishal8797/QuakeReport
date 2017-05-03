package android.example.com.quakereport;

/**
 * Created by saurav on 21/4/17.
 */

import static java.lang.Long.getLong;

import static android.example.com.quakereport.EarthquakeActivity.LOG_TAG;
import static android.example.com.quakereport.QueryUtils.readFromStream;
import static android.example.com.quakereport.R.color.magnitude1;
import static android.example.com.quakereport.R.id.magnitude;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;

import android.icu.text.DecimalFormat;
import android.icu.text.SimpleDateFormat;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.util.Log;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private static String[] parts;
    private static String part1;
    private static String part2;
    private static final String LOCATION_SEPARATOR = " of ";
   /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link EarthQuakes} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<EarthQuakes> extractEarthquakes(String JsonResponse) {

        if(TextUtils.isEmpty(JsonResponse)){
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthQuakes> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject root = new JSONObject(JsonResponse);
            JSONArray features = root.getJSONArray("features");
            for(int i=0; i<features.length(); i++){
                JSONObject earthQuakes = features.getJSONObject(i);
                JSONObject properties = earthQuakes.getJSONObject("properties");
                double magnitude1 = (properties.getDouble("mag"));
                String url = properties.getString("url");
                String place = properties.getString("place");
                if(place.contains(LOCATION_SEPARATOR)){
                    parts = place.split(LOCATION_SEPARATOR);
                    part1 = parts[0] + LOCATION_SEPARATOR;
                    part2 = parts[1];
                }
                long timeinmilliseconds = properties.getLong("time");
                Date date = new Date(timeinmilliseconds);
                SimpleDateFormat dateformat = new SimpleDateFormat("MMM DD, YYYY");
                String DateToDisplay = dateformat.format(date);

                EarthQuakes earthQuake = new EarthQuakes(magnitude1, part1, part2, timeinmilliseconds, url);

                earthquakes.add(earthQuake);



            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    public static List<EarthQuakes> fetchEarthQuakes(String RequestUrl){

        URL url = CreateUrl(RequestUrl);

        String JsonREsponse = null;

        try{
            JsonREsponse = makeHttprequest(url);

        }catch(IOException e){
            Log.e(LOG_TAG, "PROBLEM MAKING HTTPREQUEST.", e);
        }

        List<EarthQuakes> earthQuakes = extractEarthquakes(JsonREsponse);

        return earthQuakes;

    }

    private static URL CreateUrl(String RequestUrl){

        URL url = null;
        try{
            url = new URL(RequestUrl);

        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttprequest(URL url) throws IOException{

        String JsonResponse = "";

        if(url == null){
            return JsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                JsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        }finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return JsonResponse;


    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();

    }



}