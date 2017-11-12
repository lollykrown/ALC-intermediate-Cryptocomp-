package com.example.android.cryptocomp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static com.example.android.cryptocomp.CurrencyAdapter.KEY_BTC_CUR;
import static com.example.android.cryptocomp.CurrencyAdapter.KEY_BTC_TAG;
import static com.example.android.cryptocomp.CurrencyAdapter.KEY_ETH_CUR;
import static com.example.android.cryptocomp.CurrencyAdapter.KEY_ETH_TAG;
import static com.example.android.cryptocomp.MainActivity.PREFS_NAME;
/**
 * Created by Kayode Agboola on 01-Nov-17.
 */

public class ConversionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //various variables declaration
    private TextView ethResult;
    private TextView btcResult;
    Button ethConvertButton;
    Button btcConvertButton;
    Button ethClearButton;
    Button btcClearButton;
    private double result;
    private String resultString;
    private TextView ethHeading;
    private TextView btcHeading;

    SharedPreferences settings;
    int p;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        //attaching variables to views
        Spinner ethSpinner = findViewById(R.id.eth_spinner);
        Spinner btcSpinner = findViewById(R.id.btc_spinner);
        final EditText ethField = findViewById(R.id.eth_field);
        final EditText btcField = findViewById(R.id.btc_field);
        ethResult = findViewById(R.id.eth_result);
        btcResult = findViewById(R.id.btc_result);
        ethConvertButton = findViewById(R.id.eth_convert_button);
        btcConvertButton = findViewById(R.id.btc_convert_button);
        ethClearButton = findViewById(R.id.eth_clear_button);
        btcClearButton = findViewById(R.id.btc_clear_button);
        ethHeading = findViewById(R.id.eth_heading);
        btcHeading = findViewById(R.id.btc_heading);

        // Spinner click listener
        btcSpinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> curAdapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        curAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the curAdapter to the spinner
        btcSpinner.setAdapter(curAdapter);

        //Spinner click listener
        ethSpinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> cryptoAdapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        cryptoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the cryptoAdapter to the spinner
        ethSpinner.setAdapter(cryptoAdapter);

        //load preference from the MainActivity
        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        p = settings.getInt("p", 0);
        int eth_tag = settings.getInt(KEY_ETH_TAG, 0);
        int btc_tag = settings.getInt(KEY_BTC_TAG, 0);

        ethHeading.setText(eth_tag);
        btcHeading.setText(btc_tag);

        //sets spinner selection to user's list item selection
        ethSpinner.setSelection(p);
        btcSpinner.setSelection(p);

        //disable spinner selection
        ethSpinner.setEnabled(false);
        btcSpinner.setEnabled(false);

        ethConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get user input and convert to string
                String user_input = ethField.getText().toString().trim();

                //if there's a user input convert, otherwise do nothing
                if (user_input.length() != 0) {
                double eth_value = Double.parseDouble(user_input);

                /**get user crypto currency selection in the main activity and compute
                 ** conversion calculation with it
                 */
                settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                String eth_cur = settings.getString(KEY_ETH_CUR, null);
                double eth_cur_value = Double.parseDouble(eth_cur);

                result = eth_cur_value * eth_value;
                resultString = String.valueOf(new DecimalFormat("##.##").format(result));
                ethResult.setText(resultString);
                }else{
                    Toast.makeText(getApplicationContext(), "Please input a number to be converted", Toast.LENGTH_LONG).show();
                }
            }
        });

        btcConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get user's btc input and convert to string
                String user_input = btcField.getText().toString().trim();

                //if there's a user input convert, otherwise do nothing
                if (user_input.length() != 0) {
                double btc_value = Double.parseDouble(user_input);

                /**get user crypto currency selection in the main activity and compute
                 ** conversion calculation with it
                 */
                settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                String btc_cur = settings.getString(KEY_BTC_CUR, null);
                double btc_cur_value = Double.parseDouble(btc_cur);

                result = btc_cur_value * btc_value;
                resultString = String.valueOf(new DecimalFormat("##.##").format(result));
                btcResult.setText(resultString);
                }else{
                    Toast.makeText(getApplicationContext(), "Please input a number to be converted", Toast.LENGTH_LONG).show();
                }

            }
        });

        ethClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ethField.setText("");
                btcField.setText("");
                ethResult.setText("0.00");
                ethResult.setText("0.00");
            }
        });

        btcClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ethField.setText("");
                btcField.setText("");
                ethResult.setText("0.00");
                ethResult.setText("0.00");
            }
        });
    }

    @Override
    public void onItemSelected (AdapterView< ? > parent, View view, int position, long id){
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // TODO Auto-generated method stub
    }

    private void onHomeClick () {
        // Create a new intent to open the {@link NamesActivity}
        Intent intent = new Intent(ConversionActivity.this, MainActivity.class);

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
                Toast.makeText(getApplicationContext(), "Goto Home page", Toast.LENGTH_SHORT).show();
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
