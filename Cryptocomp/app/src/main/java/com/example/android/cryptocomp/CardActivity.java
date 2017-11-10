package com.example.android.cryptocomp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.cryptocomp.CurrencyAdapter.KEY_BTC_CUR;
import static com.example.android.cryptocomp.CurrencyAdapter.KEY_BTC_TAG;
import static com.example.android.cryptocomp.CurrencyAdapter.KEY_ETH_CUR;
import static com.example.android.cryptocomp.CurrencyAdapter.KEY_ETH_TAG;
import static com.example.android.cryptocomp.MainActivity.PREFS_NAME;

/**
 * Created by Kayode Agboola on 23-Oct-17.
 */

public class CardActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        /**find the id of the various activity_card views*/
        TextView btcAmountTextView = findViewById(R.id.btc_amount);
        TextView ethAmountTextView = findViewById(R.id.eth_amount);
        TextView btcCurrencyTag = findViewById(R.id.btc_tag);
        TextView ethCurrencyTag = findViewById(R.id.eth_tag);
        Button convertButton = findViewById(R.id.convert_btn);

        //load shared pref data
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String eth_cur = settings.getString(KEY_ETH_CUR, null);
        String btc_cur = settings.getString(KEY_BTC_CUR, null);
        int eth_tag = settings.getInt(KEY_ETH_TAG, 0);
        int btc_tag = settings.getInt(KEY_BTC_TAG, 0);


        //se the various data from shared prefs into the views
        btcAmountTextView.setText(btc_cur);
        ethAmountTextView.setText(eth_cur);

        btcCurrencyTag.setText(btc_tag);
        ethCurrencyTag.setText(eth_tag);

        convertButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ConversionActivity}
                Intent i = new Intent(CardActivity.this, ConversionActivity.class);


                // Start the new activity
                startActivity(i);
            }
        });

    }

    public void onHomeClick () {
        // Create a new intent to open the {@link NamesActivity}
        Intent intent = new Intent(CardActivity.this, MainActivity.class);

        // Start the new activity
        startActivity(intent);
    }

    Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem pinMenuItem2 = menu.findItem(R.id.refresh);

        pinMenuItem2.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
                onHomeClick();
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}