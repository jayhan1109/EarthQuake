package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.util.List;

/**
 * Created by jungh on 2/22/2018.
 */

public class EarthQuakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String LOG_TAG = "EarthQuakeLoader.class";
    String url;

    public EarthQuakeLoader(Context context, String url) {
        super(context);
        Log.v(LOG_TAG, "EarthQuakeLoaderConstructor");
        this.url=url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.v(LOG_TAG, "loadInBackground");
        if (url==null) {
            return null;
        }
        List<Earthquake> earthquakes= QueryUtils.makeEarthquakeList(url);
        return earthquakes;

    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "onStartLoading");
        forceLoad();
    }
}
