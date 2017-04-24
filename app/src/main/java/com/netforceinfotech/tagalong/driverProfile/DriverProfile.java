package com.netforceinfotech.tagalong.driverProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.findride.paymentmodes.PaymentModeActivity;

import java.util.ArrayList;
import java.util.List;

public class DriverProfile extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView profileRecycler;
    private List<RideDetailDatas> rideDetailDatases;
    private RideDetailAdapter rideDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
        setupToolbar("DRIVERS PROFILE");
        InitiateValue();

    }

    private void InitiateValue() {

        profileRecycler = (RecyclerView) findViewById(R.id.profileRideListRcycler);

        rideDetailDatases = new ArrayList<RideDetailDatas>();
        rideDetailAdapter = new RideDetailAdapter(this,rideDetailDatases);
        RecyclerView.LayoutManager rmanager= new LinearLayoutManager(getApplicationContext());
        profileRecycler.setAdapter(rideDetailAdapter);
        profileRecycler.setLayoutManager(rmanager);

        GetRidesDatas();

    }

    private void GetRidesDatas() {


        RideDetailDatas rides= new RideDetailDatas("Delhi,India","Mumbai, Delhi","23/04/2016","5","9:40 PM","$300");
        rideDetailDatases.add(rides);
        rides= new RideDetailDatas("Bangalore,India","Chennai, Delhi","23/06/2016","8","9:40 PM","$700");
        rideDetailDatases.add(rides);
        rides= new RideDetailDatas("Haryana,India","Simla, Delhi","23/09/2016","3","9:40 PM","$600");
        rideDetailDatases.add(rides);
        rideDetailAdapter.notifyDataSetChanged();


    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.home_menu);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return onOptionsItemSelected(item);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case R.id.itemChat:
                Intent intent = new Intent(DriverProfile.this,PaymentModeActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Chat", Toast.LENGTH_LONG).show();
                break;
            case R.id.itemNotification:

                Toast.makeText(this, "Notification Selected", Toast.LENGTH_SHORT)
                        .show();
                break;

            default:
                break;
        }

        return true;

    }
}
