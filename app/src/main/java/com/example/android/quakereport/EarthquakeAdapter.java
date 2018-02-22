package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jungh on 2/20/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {



    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list, parent, false);
        }

        final Earthquake earthquake = getItem(position);

        Date timeObject = new Date(earthquake.getTime());

        TextView magnitude = (TextView) listItemView.findViewById(R.id.list_magnitude);
        magnitude.setText(formatMag(earthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor =magBackgroundColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        TextView location = (TextView) listItemView.findViewById(R.id.list_location);
        location.setText(realLocation(earthquake.getLocation()));

        TextView nation = (TextView) listItemView.findViewById(R.id.list_nation);
        nation.setText(realNation(earthquake.getLocation()));

        TextView listDate = (TextView) listItemView.findViewById(R.id.list_date);
        String date = formatDate(timeObject);
        listDate.setText(date);

        TextView listTime = (TextView) listItemView.findViewById(R.id.list_time);
        String time = formatTime(timeObject);
        listTime.setText(time);


        return listItemView;
    }

    private String formatMag(double mag) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(mag);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, YYYY", Locale.ENGLISH);
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date timeObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        return dateFormat.format(timeObject);
    }

    private String realLocation(String location) {
        if (location.contains("of")) {
            int ofStart = location.indexOf(" of ");
            return location.substring(0, ofStart + 3);
        } else
            return "Near the";
    }

    private String realNation(String nation) {
        if (nation.contains("of")) {
            int ofStart = nation.indexOf(" of ");
            return nation.substring(ofStart + 4, nation.length());
        } else
            return nation;
    }

    private int magBackgroundColor(double mag) {
        int circleBackgroundColor;
        int integerMag=(int)mag;
        switch (integerMag) {
            case 0:
            case 1:
                circleBackgroundColor= R.color.magnitude1;
                break;
            case 2:
                circleBackgroundColor= R.color.magnitude2;
                break;
            case 3:
                circleBackgroundColor= R.color.magnitude3;
            break;

            case 4:
                circleBackgroundColor= R.color.magnitude4;
            break;

            case 5:
                circleBackgroundColor= R.color.magnitude5;
            break;

            case 6:
                circleBackgroundColor= R.color.magnitude6;
            break;

            case 7:
                circleBackgroundColor= R.color.magnitude7;
            break;

            case 8:
                circleBackgroundColor= R.color.magnitude8;
            break;

            case 9:
                circleBackgroundColor= R.color.magnitude9;
            break;

            default:
                circleBackgroundColor= R.color.magnitude10plus;
            break;

        }
        return ContextCompat.getColor(getContext(), circleBackgroundColor);
    }
}
