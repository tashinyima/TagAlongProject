package com.netforceinfotech.tagalong.home.findride.ride_available;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.home.findride.applyfilter.ApplyFilterActivity;
import com.netforceinfotech.tagalong.home.findride.paymentmodes.PaymentModeActivity;

public class BookingSeatsActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    Context context;
    Toolbar toolbar;
    private TextView textViewMinus, textViewPlus, textViewRequiredSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_seats);
        context = this;
        initView();
        setupToolBar(getString(R.string.bookingseat));
    }

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemChat:
                intent = new Intent(context, MyChatActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemNotification:
                // Exit option clicked.
                showMessage("Notification method called");
                return true;

            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        findViewById(R.id.linearLayoutProceedPayment).setOnClickListener(this);
        textViewRequiredSeat = (TextView) findViewById(R.id.textViewRequiredSeat);
        textViewMinus = (TextView) findViewById(R.id.textViewMinus);
        textViewPlus = (TextView) findViewById(R.id.textViewPlus);
        textViewPlus.setOnClickListener(this);
        textViewMinus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayoutProceedPayment:
                intent = new Intent(context, PaymentModeActivity.class);
                startActivity(intent);
                break;
            case R.id.textViewMinus:
                PerformSubtraction();
                break;
            case R.id.textViewPlus:
                PerformAddition();
                break;
        }
    }

    private void PerformAddition() {

        showMessage("Do validation if they exceed the total seats..");
        int in = Integer.parseInt(textViewRequiredSeat.getText().toString());
        in++;
        String value = String.valueOf(in);
        textViewRequiredSeat.setText(value);



    }

    private void PerformSubtraction() {

        int in = Integer.parseInt(textViewRequiredSeat.getText().toString());
        if (in > 1) {

            in--;
            String value = String.valueOf(in);

            textViewRequiredSeat.setText(value);
        } else {

            showMessage("Please select at least one seat");
        }

    }
}
