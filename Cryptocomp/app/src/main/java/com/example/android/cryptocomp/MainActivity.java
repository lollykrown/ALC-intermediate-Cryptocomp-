package com.example.android.cryptocomp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Curency>> {

    /** URL for cryptocompare data*/
    public static final String CRYPTOCOMP_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=ETH,BTC&tsyms=USD,NGN,EUR,GBP,JPY,CNY,CHF,CAD,AUD,NZD,ZAR,INR,RUB,KRW,SEK,BRL,MXN,SGD,PLN,TRY";

    /**Prefs variable for shared preferences*/
    public static final String PREFS_NAME = "MyPrefsFile";

    /** Adapter for the list of cryptocurrencies */
    public static CurrencyAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    /**variables for the various intent keys*/
    public static final String KEY_ETH_TAG = "ethTag";
    public static final String KEY_ETH_CUR = "ethCurrency";
    public static final String KEY_BTC_TAG = "btcTag";
    public static final String KEY_BTC_CUR = "btcCurrency";

    /**
     * Constant value for the crypto currency loader ID.
     */
    public static final int CUR_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        final ListView cur = findViewById(R.id.list);

        // Create a new adapter that takes an empty list of crypto currencies as input
        mAdapter = new CurrencyAdapter(this, new ArrayList<Curency>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        cur.setAdapter(mAdapter);


        // Set an item click listener on the ListView, which sends an intent to the conversion activity
        cur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //get items position
                Curency curList = mAdapter.getItem(position);

                //set shared pref data
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt(KEY_ETH_TAG, curList.getEthTag());
                editor.putString(KEY_ETH_CUR, curList.getEthCurrency());
                editor.putInt(KEY_BTC_TAG, curList.getBtcTag());
                editor.putString(KEY_BTC_CUR, curList.getBtcCurrency());
                editor.putInt("p", position);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), com.example.android.cryptocomp.CardActivity.class);
                startActivity(intent);
            }
        });

        /**get the empty state textview and set it*/
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        cur.setEmptyView(mEmptyStateTextView);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(CUR_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<Curency>> onCreateLoader(int i, Bundle bundle) {

        Uri baseUri = Uri.parse(CRYPTOCOMP_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        return new CurrencyLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Curency>> loader, List<Curency> currencies) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No crypto data found."
        mEmptyStateTextView.setText(R.string.no_crypto_data);

        // Clear the adapter of previous dev data
        mAdapter.clear();

        // If there is a valid list of {@link Curency}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (currencies != null && !currencies.isEmpty()) {
            mAdapter.addAll(currencies);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<com.example.android.cryptocomp.Curency>> loader) {
        //Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem pinMenuItem = menu.findItem(R.id.home);

        pinMenuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}