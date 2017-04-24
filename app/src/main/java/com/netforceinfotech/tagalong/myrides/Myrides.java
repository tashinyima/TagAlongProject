package com.netforceinfotech.tagalong.myrides;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.home.PagerAdapterHome;

public class Myrides extends AppCompatActivity {
    private Toolbar toolbar;
    private Context context;
    private NavigationView navigationView;
    private Menu menu;
    DrawerLayout drawerLayout;
    private Intent intent;
   // private DatabaseReference mDatabase;
  //  private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrides);
        context = this;

        setupToolBar(getString(R.string.myrides).toUpperCase());

        setupTab();
    }
    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);


    }



    private void setupTab() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_my_rides);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.as_a_driver));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.as_a_offer));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter_myrides adapter = new PagerAdapter_myrides
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


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







}
