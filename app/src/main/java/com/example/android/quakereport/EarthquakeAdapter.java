package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();


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
        quake_magnitude.setText(currentEarthquake.getMagnitude());

        // Find the TextView in the quake_list.xml layout with the ID version_number
        TextView quake_location = (TextView) listItemView.findViewById(R.id.quake_location);
        // Get the quake location from the current Earthquake object and
        // set this text on the quake_location TextView
        quake_location.setText(currentEarthquake.getLocation());

        // Find the TextView in the quake_list.xml layout with the ID version_number
        TextView quake_date = (TextView) listItemView.findViewById(R.id.quake_date);
        // Get the quake date from the current Earthquake object and
        // set this text on the quake_date TextView
        quake_date.setText(currentEarthquake.getDate());



        /*
        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        */
        return listItemView;
    }
}
