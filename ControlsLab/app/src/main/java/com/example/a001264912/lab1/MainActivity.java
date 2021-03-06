package com.example.a001264912.lab1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private Spinner chocolateType;
    private EditText numBars;
    private RadioGroup shippingGroup;
    private RadioButton shippingMethod;
    private Button saveOrder;
    private TextView displayResults;
    private ArrayList<Order> chocolateOrder = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Assign views to variables
        firstName = (EditText)findViewById(R.id.txtFirstName);
        lastName = (EditText)findViewById(R.id.txtLastName);
        chocolateType = (Spinner)findViewById(R.id.spnChocolateType);
        numBars = (EditText)findViewById(R.id.txtNumBars);
        shippingGroup = (RadioGroup)findViewById(R.id.rdgShipping);
        saveOrder = (Button)findViewById(R.id.btnSaveOrder);
        displayResults = (TextView)findViewById(R.id.lblDisplayResults);

        // Set action listeners
        saveOrder.setOnClickListener(ResultClickListener);
    }

    private OnClickListener ResultClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            // Create new order to work with
            Order newOrder = new Order();

            // Set the order's properties
            newOrder.setFirstName(firstName.getText().toString());
            newOrder.setLastName(lastName.getText().toString());
            newOrder.setChocolateType(chocolateType.getSelectedItem().toString());
            newOrder.setChocolateQuantity(Integer.parseInt(numBars.getText().toString()));

            int shippingId = shippingGroup.getCheckedRadioButtonId();
            shippingMethod = (RadioButton)findViewById(shippingId);
            if(shippingMethod.getText().equals("Expedited")) {
                newOrder.setExpeditedShipping(true);
            } else if(shippingMethod.getText().equals("Normal")) {
                newOrder.setExpeditedShipping(false);
            }

            // Add order to list of orders
            chocolateOrder.add(newOrder);

            // Print acknowledgement message
            displayResults.setText("Order added, there are now " + chocolateOrder.size() + " orders.");
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
