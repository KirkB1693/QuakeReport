package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();
    private static final String LOCATION_SEPARATOR = " of ";


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context     The current context. Used to inflate the layout file.
     * @param earthquakes A List of Earthquake objects to display in a list
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_list, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the quake_list.xml layout with the ID version_name
        TextView quake_magnitude = (TextView) listItemView.findViewById(R.id.quake_magnitude);

        // Get the quake magnitude from the current Earthquake object and
        // set this text on the quake_magnitude TextView
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        quake_magnitude.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) quake_magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView in the quake_list.xml layout with the ID version_number
        TextView quake_location_offset = (TextView) listItemView.findViewById(R.id.quake_location_offset);
        // Get the quake location from the current Earthquake object and then get the location offset portion of the text
        // set this text on the quake_location_offset TextView
        quake_location_offset.setText(getLocationOffset(currentEarthquake.getLocation()));

        // Find the TextView in the quake_list.xml layout with the ID version_number
        TextView quake_location_primary = (TextView) listItemView.findViewById(R.id.quake_location_primary);
        // Get the quake location from the current Earthquake object and then get the primary location portion of the text
        // set this text on the quake_location_offset TextView
        quake_location_primary.setText(getPrimaryLocation(currentEarthquake.getLocation()));

        // Find the TextView in the quake_list.xml layout with the ID version_number
        TextView quake_date = (TextView) listItemView.findViewById(R.id.quake_date);
        // Get the quake date from the current Earthquake object and
        // set this text on the quake_date TextView
        String date = formatDate(new Date(currentEarthquake.getDate()));
        quake_date.setText(date);

        TextView quake_time = (TextView) listItemView.findViewById(R.id.quake_time);
        String time = formatTime(new Date(currentEarthquake.getDate()));
        quake_time.setText(time);



        /*
        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        */
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getCutPoint(String location) {
        int cutPoint = 0;
        if (location.contains(LOCATION_SEPARATOR)) {
            cutPoint = location.indexOf(LOCATION_SEPARATOR) + 3;
        }
        return cutPoint;
    }

    private String getLocationOffset(String location) {
        String locationOffset = getContext().getString(R.string.near_the);
        if (location.contains(LOCATION_SEPARATOR)) {
            locationOffset = location.substring(0, getCutPoint(location));
        }

        return locationOffset;
    }

    private String getPrimaryLocation(String location) {
        String primaryLocation = location;
        if (location.contains(LOCATION_SEPARATOR)) {
            primaryLocation = location.substring(getCutPoint(location) + 1);
        }

        return primaryLocation;
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int mag = (int) Math.floor(magnitude);
        int magnitudeColorID;
        switch (mag) {
            case 0:
            case 1:
                magnitudeColorID = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorID = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorID = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorID = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorID = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorID = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorID = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorID = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorID = R.color.magnitude9;
                break;
            default:
                magnitudeColorID = R.color.magnitude10plus;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorID);
    }
}
