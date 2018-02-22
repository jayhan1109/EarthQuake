package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import java.net.URL;
import java.util.List;

/**
 * Created by jungh on 2/22/2018.
 */

public class EarthQuakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String url;

    public EarthQuakeLoader(Context context, String url) {
        super(context);
        this.url=url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (url==null) {
            return null;
        }
        List<Earthquake> earthquakes= QueryUtils.makeEarthquakeList(url);
        return earthquakes;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
