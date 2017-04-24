package com.netforceinfotech.tagalong.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.dashboard.MyDashboardActivity;
import com.netforceinfotech.tagalong.driverProfile.DriverProfile;
import com.netforceinfotech.tagalong.home.findride.CantFindRideActivity;
import com.netforceinfotech.tagalong.login.LoginActivity;
import com.netforceinfotech.tagalong.login.SignUpActivity;
import com.netforceinfotech.tagalong.myCars.MyCarActivity;
import com.netforceinfotech.tagalong.myCars.carlist.CarListActivity;
import com.netforceinfotech.tagalong.myprofile.MyProfileActivity;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Context context;
    private NavigationView navigationView;
    private Menu menu;
    DrawerLayout drawerLayout;
    private Intent intent;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    SharedPreferences sp ;
    private DatabaseReference _user_group;
    String userid;
    ProgressDialog pd;
    private boolean child_group_exist = false;

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pd=new ProgressDialog(this);
        sp = getSharedPreferences(
                getString(R.string.preference_tagalong), Context.MODE_PRIVATE);
        context = this;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        userid=sp.getString("userid","notlogin");
        Log.e("userid",userid);
       // setupFirebase(userid,"");
        //testFirebase();
        setupToolBar(getString(R.string.home).toUpperCase());
        setupNavigationView();
        setupTab();


        //firebase testing



//        Log.e("mDatabase.getRoot()", mDatabase.getRoot().toString());

        if (true) {
           // showReviewPopUp();
        }
    }



    private void setupFirebase(final String userid, final String user_image) {
        _user_group = FirebaseDatabase.getInstance().getReference().child("chat_title");
        _user_group.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(userid)){
                    createUserChild(dataSnapshot, userid, user_image);
                } else {
                    insertnewUser(dataSnapshot, userid, user_image);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void insertnewUser(DataSnapshot dataSnapshot, final String user_id, final String image) {
        final DatabaseReference _user_id = _user_group.child(user_id);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(user_id,"");



        _user_id.updateChildren(map);
        _user_id.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DatabaseReference _group_id = _user_id.child(user_id);
                Map<String, Object> map1 = new HashMap<String, Object>();


                map1.put("user_name", "demo");
                map1.put("timestamp", ServerValue.TIMESTAMP);
                map1.put("key", ServerValue.TIMESTAMP);
                map1.put("image", image);

                _group_id.updateChildren(map1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void createUserChild(DataSnapshot dataSnapshot, final String user_id, final String image) {
        final HashMap<String, Object> user_id_map = new HashMap<String, Object>();
        //user_id_map.put("user_id",user_id);
        _user_group.updateChildren(user_id_map);
        _user_group.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                insertnewUser(dataSnapshot, user_id, image);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }































    private void testFirebase() {
        DatabaseReference chat_title = FirebaseDatabase.getInstance().getReference().child("chat-title").push();
        DatabaseReference chat_detail = FirebaseDatabase.getInstance().getReference().child("chat_detail").push();

        Map<String, Object> map = new HashMap<>();
        map.put("id", "123");
        chat_title.updateChildren(map);

        // chat_title_child reference

        DatabaseReference chat_detail_child =chat_title.getRef().child("child_chat_detail").push();
        Map<String, Object> chat_detail_child_map = new HashMap<>();
        chat_detail_child_map.put("sender_userid", "123");
        chat_detail_child_map.put("receiving_userid", "123");
        chat_detail_child.updateChildren(chat_detail_child_map);

        DatabaseReference chat_detail_sender_child =chat_title.getRef().child("child_chat_detail").push();
        Map<String, Object> chat_detail_child_chat_detail = new HashMap<>();
        chat_detail_child_map.put("sender_userid", "123");
        chat_detail_child_map.put("sender_imageurl", "https://");
        chat_detail_child_map.put("sender_name", "arvind");
        chat_detail_child_map.put("sender_key", System.currentTimeMillis()+"tagalong");
        chat_detail_sender_child.updateChildren(chat_detail_child_chat_detail);

        DatabaseReference chat_detail_Receivng_child =chat_title.getRef().child("child_chat_detail").push();
        Map<String, Object> chat_detail_child_chat_receiving_detail = new HashMap<>();
        chat_detail_child_map.put("receiving_userid", "123");
        chat_detail_child_map.put("receiving_imageurl", "https://");
        chat_detail_child_map.put("receiving_name", "arvind");
        chat_detail_child_map.put("receiving_key", System.currentTimeMillis()+"tagalong");
        chat_detail_Receivng_child.updateChildren(chat_detail_child_chat_receiving_detail);





        // chat_title_child reference
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", "123");
        chat_detail.updateChildren(map2);

        DatabaseReference chat_title_child =chat_detail.getRef().child("child_chat_detail").push();
        Map<String, Object> chat_title_child_map = new HashMap<>();
        chat_title_child_map.put("userid", "123");
        chat_title_child_map.put("username", "nishant");
        chat_title_child_map.put("image_url", "https://");
        chat_title_child_map.put("chatkey",System.currentTimeMillis()+"tagalong");
        chat_title_child.updateChildren(chat_title_child_map);

    }

    private void showReviewPopUp() {
        new MaterialDialog.Builder(this)
                .customView(R.layout.review_pop_up, false)
                .positiveText(getString(R.string.submit))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        showMessage("Submit review");
                        dialog.dismiss();
                    }
                })
                .show();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupTab() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        //For some reason, setting minWidth in xml and then accessing it here doesn't work, returns 0
        int minWidth = 80;
        tabLayout.setMinimumWidth(minWidth);

        //If there are less tabs than needed to necessitate scrolling, set to fixed/fill
        if(tabLayout.getTabCount() < dpWidth/minWidth){
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            //Otherwise, set to scrollable
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        }



        tabLayout.addTab(tabLayout.newTab().setText(R.string.find_a_ride_cap));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.offer_a_ride_cap));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapterHome adapter = new PagerAdapterHome
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


     /*   tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });*/
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

    private void setupNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        final List<MenuItem> items = new ArrayList<>();

        menu = navigationView.getMenu();
        menu.add(getString(R.string.my_profile)).setIcon(R.drawable.ic_user);
        menu.add(getString(R.string.my_car)).setIcon(R.drawable.ic_car);
        menu.add(getString(R.string.my_booking)).setIcon(R.drawable.ic_booking);
        menu.add(getString(R.string.my_dashboard)).setIcon(R.drawable.ic_dash);
        menu.add(getString(R.string.find_ride)).setIcon(R.drawable.ic_search);
        menu.add(getString(R.string.offer_a_ride)).setIcon(R.drawable.ic_offer);
        menu.add(getString(R.string.setting)).setIcon(R.drawable.ic_setting);
        menu.add(getString(R.string.how_it_work)).setIcon(R.drawable.ic_help);
        menu.add(getString(R.string.latest_ride)).setIcon(R.drawable.ic_latest);
        menu.add(getString(R.string.Logout)).setIcon(R.drawable.ic_user);


        for (int i = 0; i < menu.size(); i++) {
            items.add(menu.getItem(i));
        }
        menu.setGroupCheckable(0, true, false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action
                switch (items.indexOf(menuItem)) {
                    case 0:
                    callmyprofile_webservice();

                        break;
                    case 1:
                        intent = new Intent(context, CarListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 2:
                        break;
                    case 3:
                        intent = new Intent(context, MyDashboardActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        intent = new Intent(context, CantFindRideActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 8:
                        intent = new Intent(context, DriverProfile.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 9:
                        try{
                            LoginManager.getInstance().logOut();
                        }
                        catch (Exception e)
                        {

                        }

                            sp.edit().putString("userid","notlogin").commit();
                            intent = new Intent(context, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.enter, R.anim.exit);

                            break;




                    default:


                }
                return false;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }

        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    public void callmyprofile_webservice() {



        pd.show();
        setupSelfSSLCert();
        String userid = sp.getString("userid","notlogin");
//        if(userid!="notlogin")
//        {
//
//        }
        JsonObject js=new JsonObject();
        js.addProperty("type", "personal_information");


        js.addProperty("MemberId",userid);


        Log.e("js_login",js.toString());

        // setupSelfSSLCert();
        String Webservice_myprofile=getResources().getString(R.string.webservice_api_url);
        Log.e("Webservice_login_url",Webservice_myprofile);


                 Ion.with(context)
                .load(Webservice_myprofile)
                .setJsonObjectBody(js)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

JsonObject js=result.getAsJsonObject("personal_information");
                String memberId=js.get("iMemberId").getAsString();
                String vAddress=js.get("vAddress").getAsString();
                String vPhone=js.get("vPhone").getAsString();
                String language=js.get("vLanguageCode").getAsString();
                String Description=js.get("tDescription").getAsString();
                String dob=js.get("iBirthYear").getAsString();
                String vEmail=js.get("vEmail").getAsString();






                intent = new Intent(context, MyProfileActivity.class);
                intent.putExtra("vAddress",vAddress);
                intent.putExtra("vPhone",vPhone);
                intent.putExtra("language",language);
                intent.putExtra("Description",Description);
                intent.putExtra("dob",dob);
                intent.putExtra("vEmail",vEmail);


                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

                Log.e("JsonObject_result",result.toString());

            }
        });


        if(pd!=null)
        {
            pd.dismiss();
        }
















    }

    @Override
    public void onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);




        // do nothing.
    }

    public static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    public void setupSelfSSLCert() {
        final Trust trust = new Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
