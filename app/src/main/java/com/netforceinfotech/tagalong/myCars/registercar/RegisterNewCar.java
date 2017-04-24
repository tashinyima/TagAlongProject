package com.netforceinfotech.tagalong.myCars.registercar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;

public class RegisterNewCar extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_car);
        setupToolbar(getString(R.string.registercar));

        initView();

    }

    private void initView() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
                return true;

            case R.id.itemChat:

                intent = new Intent(RegisterNewCar.this, MyChatActivity.class);
                startActivity(intent);
                return true;

            case R.id.itemNotification:
                showMessge("Clicked on Notification");
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void showMessge(String s) {

        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s.toUpperCase());

    }
}
