package com.example.android.cryptocomp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Kayode Agboola on 22-Oct-17.
 */

public class CurrencyLoader extends AsyncTaskLoader<List<Curency>> {

    //Query URL
    private String mUrl;

    /**
     * Constructs a new {@link Curency}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public CurrencyLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Curency> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of currencies.
        List<Curency> currencies = QueryUtils.fetchCurrencyData(mUrl);
        return currencies;
    }
}