package com.example.android.cryptocomp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kayode Agboola on 22-Oct-17.
 */

public class QueryUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the CRYPTOCOMP dataset and return a list of {@link Curency} objects.
     */
    public static List<Curency> fetchCurrencyData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Curency}s
        List<Curency> currencies = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Curency}s
        return currencies;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the cryptocompare JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Curency} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Curency> extractFeatureFromJson(String currenciesJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(currenciesJSON)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding currencies to
        List<Curency> currencies = new ArrayList<>();
        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(currenciesJSON);

            JSONObject ethCurrency = baseJsonResponse.getJSONObject("ETH");

            JSONObject btcCurrency = baseJsonResponse.getJSONObject("BTC");

            //get the double values from eth and btc object then store as string
            String ethNgn = String.valueOf(ethCurrency.getDouble("NGN"));
            String ethUsd = String.valueOf(ethCurrency.getDouble("USD"));
            String ethEur = String.valueOf(ethCurrency.getDouble("EUR"));
            String ethGbp = String.valueOf(ethCurrency.getDouble("GBP"));
            String ethJpy = String.valueOf(ethCurrency.getDouble("JPY"));
            String ethCny = String.valueOf(ethCurrency.getDouble("CNY"));
            String ethChf = String.valueOf(ethCurrency.getDouble("CHF"));
            String ethCad = String.valueOf(ethCurrency.getDouble("CAD"));
            String ethAud = String.valueOf(ethCurrency.getDouble("AUD"));
            String ethNzd = String.valueOf(ethCurrency.getDouble("NZD"));
            String ethZar = String.valueOf(ethCurrency.getDouble("ZAR"));
            String ethInr = String.valueOf(ethCurrency.getDouble("INR"));
            String ethRub = String.valueOf(ethCurrency.getDouble("RUB"));
            String ethKrw = String.valueOf(ethCurrency.getDouble("KRW"));
            String ethSek = String.valueOf(ethCurrency.getDouble("SEK"));
            String ethBrl = String.valueOf(ethCurrency.getDouble("BRL"));
            String ethMxn = String.valueOf(ethCurrency.getDouble("MXN"));
            String ethSgd = String.valueOf(ethCurrency.getDouble("SGD"));
            String ethPln = String.valueOf(ethCurrency.getDouble("PLN"));
            String ethTry = String.valueOf(ethCurrency.getDouble("TRY"));

            String btcNgn = String.valueOf(ethCurrency.getDouble("NGN"));
            String btcUsd = String.valueOf(btcCurrency.getDouble("USD"));
            String btcEur = String.valueOf(btcCurrency.getDouble("EUR"));
            String btcGbp = String.valueOf(btcCurrency.getDouble("GBP"));
            String btcJpy = String.valueOf(btcCurrency.getDouble("JPY"));
            String btcCny = String.valueOf(btcCurrency.getDouble("CNY"));
            String btcChf = String.valueOf(btcCurrency.getDouble("CHF"));
            String btcCad = String.valueOf(btcCurrency.getDouble("CAD"));
            String btcAud = String.valueOf(btcCurrency.getDouble("AUD"));
            String btcNzd = String.valueOf(btcCurrency.getDouble("NZD"));
            String btcZar = String.valueOf(btcCurrency.getDouble("ZAR"));
            String btcInr = String.valueOf(btcCurrency.getDouble("INR"));
            String btcRub = String.valueOf(btcCurrency.getDouble("RUB"));
            String btcKrw = String.valueOf(btcCurrency.getDouble("KRW"));
            String btcSek = String.valueOf(btcCurrency.getDouble("SEK"));
            String btcBrl = String.valueOf(btcCurrency.getDouble("BRL"));
            String btcMxn = String.valueOf(btcCurrency.getDouble("MXN"));
            String btcSgd = String.valueOf(btcCurrency.getDouble("SGD"));
            String btcPln = String.valueOf(btcCurrency.getDouble("PLN"));
            String btcTry = String.valueOf(btcCurrency.getDouble("TRY"));

            //add the data to the currency object
            currencies.add(new Curency(R.string.eth_nig, ethNgn, R.string.btc_nig, btcNgn));
            currencies.add(new Curency(R.string.eth_usd, ethUsd, R.string.btc_usd, btcUsd));
            currencies.add(new Curency(R.string.eth_eur, ethEur, R.string.btc_eur, btcEur));
            currencies.add(new Curency(R.string.eth_gbp, ethGbp, R.string.btc_gbp, btcGbp));
            currencies.add(new Curency(R.string.eth_jpy, ethJpy, R.string.btc_jpy, btcJpy));
            currencies.add(new Curency(R.string.eth_cny, ethCny, R.string.btc_cny, btcCny));
            currencies.add(new Curency(R.string.eth_chf, ethChf, R.string.btc_chf, btcChf));
            currencies.add(new Curency(R.string.eth_cad, ethCad, R.string.btc_cad, btcCad));
            currencies.add(new Curency(R.string.eth_aud, ethAud, R.string.btc_aud, btcAud));
            currencies.add(new Curency(R.string.eth_nzd, ethNzd, R.string.btc_nzd, btcNzd));
            currencies.add(new Curency(R.string.eth_zar, ethZar, R.string.btc_zar, btcZar));
            currencies.add(new Curency(R.string.eth_inr, ethInr, R.string.btc_inr, btcInr));
            currencies.add(new Curency(R.string.eth_rub, ethRub, R.string.btc_rub, btcRub));
            currencies.add(new Curency(R.string.eth_krw, ethKrw, R.string.btc_krw, btcKrw));
            currencies.add(new Curency(R.string.eth_sek, ethSek, R.string.btc_sek, btcSek));
            currencies.add(new Curency(R.string.eth_brl, ethBrl, R.string.btc_brl, btcBrl));
            currencies.add(new Curency(R.string.eth_mxn, ethMxn, R.string.btc_mxn, btcMxn));
            currencies.add(new Curency(R.string.eth_sgd, ethSgd, R.string.btc_sgd, btcSgd));
            currencies.add(new Curency(R.string.eth_pln, ethPln, R.string.btc_pln, btcPln));
            currencies.add(new Curency(R.string.eth_try, ethTry, R.string.btc_try, btcTry));

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the cryptocompare JSON results", e);
        }

        // Return the list of currencies
        return currencies;
    }


}