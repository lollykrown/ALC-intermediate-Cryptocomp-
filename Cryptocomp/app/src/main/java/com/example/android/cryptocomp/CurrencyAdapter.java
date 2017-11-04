package com.example.android.cryptocomp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kayode Agboola on 22-Oct-17.
 */

public class CurrencyAdapter extends ArrayAdapter<Curency> {

    public static final String KEY_ETH_TAG = "ethTag";
    public static final String KEY_ETH_CUR = "ethCurrency";
    public static final String KEY_BTC_TAG = "btcTag";
    public static final String KEY_BTC_CUR = "btcCurrency";
    /**
     * Constructs a new {@link CurrencyAdapter}.
     *  @param mContext of the app
     * @param currencies is the list of java developers which is the data source of the adapter
     */
    public CurrencyAdapter(Context mContext, ArrayList<Curency> currencies) {
        super(mContext, 0, currencies);
    }


    /**
     * Returns a list item view that displays btc and eth conversion to other currencies
     * at the given position in the list of crypto currency.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.items_currency, parent, false);
        }

        // Find the crypto currency at the given position in the list of currencies
        Curency currentElement = getItem(position);

        // Find the TextView with view ID eth_tag
        TextView ethTag = listItemView.findViewById(R.id.eth_tag);

        // Display the ethTag in that TextView
        ethTag.setText(currentElement.getEthTag());

        // Find the TextView with view ID eth_textview
        TextView eth = listItemView.findViewById(R.id.eth_textview);

        // Display the eth price in that TextView
        eth.setText(currentElement.getEthCurrency());

        // Find the TextView with view ID btc_tag
        TextView btcTag = listItemView.findViewById(R.id.btc_tag);

        // Display the btcTag in that TextView
        btcTag.setText(currentElement.getBtcTag());

        // Find the TextView with view ID btc_textview
        TextView btc = listItemView.findViewById(R.id.btc_textview);

        // Display the btc price in that TextView
        btc.setText(currentElement.getBtcCurrency());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

}