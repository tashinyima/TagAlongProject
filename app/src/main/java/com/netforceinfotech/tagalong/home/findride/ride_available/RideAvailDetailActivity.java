package com.netforceinfotech.tagalong.home.findride.ride_available;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;

public class RideAvailDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView bookthisTextView;
    private Toolbar toolbar;
    private Intent intent;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_avail_detail);
        initView();
        setupToolbar(getString(R.string.rideDetails));


    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s.toUpperCase());
    }

    private void initView() {

        context = this;

        bookthisTextView = (TextView) findViewById(R.id.bookthisride);
        bookthisTextView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemChat:
                intent = new Intent(context, MyChatActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemNotification:
                // Exit option clicked.
                showMessage("Notification method called");
                return true;
            case android.R.id.home:

                intent = new Intent(RideAvailDetailActivity.this,RidesActivity.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);

    }

    private void showMessage(String s) {
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bookthisride:

                intent = new Intent(RideAvailDetailActivity.this,BookingSeatsActivity.class);
                startActivity(intent);
                break;

        }

    }
}
