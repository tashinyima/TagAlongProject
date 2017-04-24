package com.netforceinfotech.tagalong.home.findride.applyfilter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.general.Global_variables;
import com.netforceinfotech.tagalong.home.findride.CantFindRideActivity;
import com.netforceinfotech.tagalong.home.findride.FindRideFragment;
import com.netforceinfotech.tagalong.home.findride.ride_available.MyData;
import com.netforceinfotech.tagalong.home.findride.ride_available.Ride_detail_pojo;
import com.netforceinfotech.tagalong.home.findride.ride_available.RidesActivity;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ApplyFilterActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    RadioButton radioButtonTime, radioButtonPrice, radioButtonPictures, radioButtonRideType, radioButtonCarComfort, radioButtonRating;
    TextView textViewTime, textViewPrice, textViewPicture, textViewRideType, textViewCarComfort, textViewRating;
    private Context context;
    private String tagName = "";
    Toolbar toolbar;
    private Intent intent;
    ProgressDialog pd;
    LinearLayout ll_filter_Time,ll_filter_Price,ll_filter_Picture,ll_filter_RideType,ll_filter_CarComfort,ll_filter_Rating;
    public static ArrayList<MyData> mydata;


    public static ArrayList<Ride_detail_pojo> ridedetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_filter);
        pd=new ProgressDialog(this);
        context = this;
        mydata = new ArrayList<MyData>();
        ridedetail=new ArrayList<Ride_detail_pojo>();
        initView();
        setupToolbar(getString(R.string.apply_filter));
    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s.toUpperCase());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case R.id.itemChat:
                intent = new Intent(context, MyChatActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemNotification:
                // Exit option clicked.
                showMessage("Notification method called");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        findViewById(R.id.buttonApply).setOnClickListener(this);
        ll_filter_Time=(LinearLayout)findViewById(R.id.ll_filter_Time) ;
        ll_filter_CarComfort=(LinearLayout)findViewById(R.id.ll_filter_CarComfort) ;
        ll_filter_Picture=(LinearLayout)findViewById(R.id.ll_filter_Picture) ;
        ll_filter_Rating=(LinearLayout)findViewById(R.id.ll_filter_Rating) ;
        ll_filter_RideType=(LinearLayout)findViewById(R.id.ll_filter_RideType) ;
        ll_filter_Price=(LinearLayout)findViewById(R.id.ll_filter_Price) ;
        ll_filter_Rating.setOnClickListener(this);
        ll_filter_Price.setOnClickListener(this);
        ll_filter_RideType.setOnClickListener(this);
        ll_filter_CarComfort.setOnClickListener(this);
        ll_filter_Time.setOnClickListener(this);
        ll_filter_Picture.setOnClickListener(this);


        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewPicture = (TextView) findViewById(R.id.textViewPicture);
        textViewRideType = (TextView) findViewById(R.id.textViewRideType);
        textViewCarComfort = (TextView) findViewById(R.id.textViewCarComfort);
        textViewRating = (TextView) findViewById(R.id.textViewRating);
        radioButtonTime = (RadioButton) findViewById(R.id.radioButtonTime);
        radioButtonPrice = (RadioButton) findViewById(R.id.radioButtonPrice);
        radioButtonPictures = (RadioButton) findViewById(R.id.radioButtonPicture);
        radioButtonRideType = (RadioButton) findViewById(R.id.radioButtonRideType);
        radioButtonCarComfort = (RadioButton) findViewById(R.id.radioButtonCarcomfort);
        radioButtonRating = (RadioButton) findViewById(R.id.radioButtonRating);
        radioButtonTime.setOnCheckedChangeListener(this);
        radioButtonPrice.setOnCheckedChangeListener(this);
        radioButtonPictures.setOnCheckedChangeListener(this);
        radioButtonRideType.setOnCheckedChangeListener(this);
        radioButtonCarComfort.setOnCheckedChangeListener(this);
        radioButtonRating.setOnCheckedChangeListener(this);
        //textViewTime.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        radioButtonTime.setChecked(false);
        radioButtonTime.setChecked(true);
        textViewTime.setOnClickListener(this);
        textViewCarComfort.setOnClickListener(this);
        textViewPicture.setOnClickListener(this);
        textViewPrice.setOnClickListener(this);
        textViewRating.setOnClickListener(this);
        textViewRideType.setOnClickListener(this);
    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fromLayout, newFragment, tag);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.radioButtonCarcomfort:
                if (b) {
                    textViewCarComfort.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupCarComfortFragment();
                } else {
                    textViewCarComfort.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonPicture:
                if (b) {
                    textViewPicture.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupPictureFragment();
                } else {
                    textViewPicture.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonPrice:
                if (b) {
                    textViewPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupPriceFragment();
                } else {
                    textViewPrice.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonRating:
                if (b) {
                    textViewRating.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupRatingFragment();
                } else {
                    textViewRating.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonTime:
                if (b) {
                    textViewTime.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupTimeFragment();
                } else {
                    textViewTime.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonRideType:
                if (b) {
                    textViewRideType.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupRideTypeFragment();
                } else {
                    textViewRideType.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
        }

    }

    private void setupRideTypeFragment() {
        RideTypeFragment rideTypeFragment = new RideTypeFragment();
        tagName = rideTypeFragment.getClass().getName();
        replaceFragment(rideTypeFragment, tagName);
    }

    private void setupTimeFragment() {
        TimeFragment timeFragment = new TimeFragment();
        tagName = timeFragment.getClass().getName();
        replaceFragment(timeFragment, tagName);
    }

    private void setupRatingFragment() {
        RatingFragment ratingFragment = new RatingFragment();
        tagName = ratingFragment.getClass().getName();
        replaceFragment(ratingFragment, tagName);
    }

    private void setupPriceFragment() {
        PriceFragment priceFragment = new PriceFragment();
        tagName = priceFragment.getClass().getName();
        replaceFragment(priceFragment, tagName);
    }

    private void setupPictureFragment() {
        PicturesFragment picturesFragment = new PicturesFragment();
        tagName = picturesFragment.getClass().getName();
        replaceFragment(picturesFragment, tagName);
    }

    private void setupCarComfortFragment() {

        CarComfortFragment carComfortFragment = new CarComfortFragment();
        tagName = carComfortFragment.getClass().getName();
        replaceFragment(carComfortFragment, tagName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        // TextView textViewTime, textViewPrice, textViewPicture, textViewRideType, textViewCarComfort, textViewRating;
        switch (view.getId()) {
            case R.id.buttonApply:
                showMessage("Filter applied");
                finish();
                callfilter_webservice();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;
            case R.id.ll_filter_Time:
                radioButtonTime.setChecked(true);
                break;
            case R.id.ll_filter_CarComfort:
                radioButtonCarComfort.setChecked(true);
                break;
            case R.id.ll_filter_Picture:
                radioButtonPictures.setChecked(true);
                break;
            case R.id.ll_filter_Price:
                radioButtonPrice.setChecked(true);
                break;
            case R.id.ll_filter_RideType:
                radioButtonRideType.setChecked(true);
                break;
            case R.id.ll_filter_Rating:
                radioButtonRating.setChecked(true);
                break;

        }
    }
void callfilter_webservice()
{
//    Global_variables.ride_date=selectDateEditText.getText().toString();
//    Global_variables.from_lat_long=From_lat_long;
//    Global_variables.to_lat_long=To_lat_long;
    mydata.clear();


//    pd.show();
    setupSelfSSLCert();

    JsonObject js=new JsonObject();
    js.addProperty("type","find_ride");
    js.addProperty("searchdate",Global_variables.ride_date);
    js.addProperty("From_lat_long",Global_variables.from_lat_long);
    js.addProperty("To_lat_long",Global_variables.to_lat_long);
    js.addProperty("search","place");
    js.addProperty("memrating",Global_variables.rating);
    js.addProperty("fromhr",Global_variables.min_time);
    js.addProperty("tohr",Global_variables.max_time);
    js.addProperty("ridetype[]",Global_variables.ridetype);
    js.addProperty("carcomfort",Global_variables.carcomfort);
    js.addProperty("fltimg",Global_variables.pictures);
    js.addProperty("ePriceType",Global_variables.price);








        /*js.addProperty("type","find_ride");
        js.addProperty("searchdate",selectDateEditText.getText().toString().trim());
        js.addProperty("From_lat_long",From_lat_long);
        js.addProperty("To_lat_long",To_lat_long);
        js.addProperty("search","place");*/



    String find_ride_webservice="https://tag-along.net/webservice.php";
    Log.e("find_ride_webservice",find_ride_webservice);
    Log.e("js_login",js.toString());
    Ion.with(context)
            .load(find_ride_webservice)
            .setJsonObjectBody(js)
            .asJsonObject()
            .setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                    if(result!=null) {
                        try {
//                            String mainarr=result.get("mainarr").getAsString();
//                            if(mainarr.equals("No result found"))
//                            {
//
//                                Toast.makeText(getActivity(),"No result found",Toast.LENGTH_SHORT).show();
//                            }
//                            else {
                            if (result.get("mainarr").isJsonArray()) {
                                JsonArray mainarray = result.getAsJsonArray("mainarr");
                                if (mainarray.size() != 0) {
                                    for (int i = 0; i < mainarray.size(); i++) {
                                        // "userid","userimageurl","username","ride_price","sourceaddress","destinationaddress","departuredata","departuretime",2

                                        JsonObject js = mainarray.get(i).getAsJsonObject();
                                        String Memberid = js.get("iMemberId").getAsString();
                                        String userimageurl = js.get("image").getAsString();
                                        String username = js.get("FirstName").getAsString();
                                        String ride_price = js.get("price").getAsString();
                                        String sourceaddress = js.get("from").getAsString();
                                        String destinationaddress = js.get("to").getAsString();
                                        String departuredate = js.get("start_date").getAsString();
                                        String departuretime = js.get("start_time").getAsString();
                                        String schedule = js.get("flexibility").getAsString();
                                        Float rating = Float.valueOf(js.get("rating").getAsString());
                                        String detour = js.get("detour").getAsString();
                                        String luggage_size = js.get("Luggage").getAsString();
                                        String carname = js.get("carname").getAsString();
                                        String carcomfort = js.get("carcomfort").getAsString();
                                        String car_type = "";
                                        String seats = js.get("seats").getAsString();
                                        //js.get("car_type").getAsString();
                                        String car_plateno = "";
                                        //js.get("car_plateno").getAsString();


                                        ridedetail.add(new Ride_detail_pojo(Memberid, userimageurl, username, rating.toString(), sourceaddress,
                                                destinationaddress, ride_price, departuredate, departuretime, schedule, detour, luggage_size, carname,
                                                carcomfort, car_type, car_plateno, null, null, null, null, null, seats));


                                        Log.e("result_ride_webservice", Memberid + userimageurl + username + ride_price + sourceaddress + destinationaddress + departuredate +
                                                departuretime + rating);
                                        mydata.add(new MyData(Memberid, userimageurl, username, ride_price, sourceaddress, destinationaddress, departuredate, departuretime, rating));
                                    }

//                                    Intent intent = new Intent(context, RidesActivity.class);
//                                    finish();

                                    // ridedetail=RidesActivity.ride_detail_pojos;

//                                    intent.putParcelableArrayListExtra(
//                                            "com.netforceinfotech.tagalong",
//                                            (ArrayList<? extends Parcelable>) ridedetail);
                                   // startActivity(intent);
                                    //overridePendingTransition(R.anim.enter, R.anim.exit);

                                }
                            } else {


                                Intent intent = new Intent(context, CantFindRideActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                                //Toast.makeText(getActivity(),"No result found",Toast.LENGTH_SHORT).show();


                            }
//                            String login_status=result.get("action").getAsString();
//                            if(login_status.contains("1"))
//                            {
//
//                                Intent intent = new Intent(context, RidesActivity.class);
//                                startActivity(intent);
//                                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
//                            }
//                            else{
//                                showMessage("result null ");
//                            }
                            Log.e("result_ride_webservice", result.toString());


                            if (pd != null) {
                                pd.dismiss();
                            }


                        }
                        catch (Exception e1)
                        {
                            Log.e("Exception", e1.toString());
                        }
                    }
                        else{
//                        Intent intent = new Intent(context, RidesActivity.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.enter, R.anim.exit);
                            Log.e("result_null", "result_null");
                        }

                    // do stuff with the result or error
                }
            });













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
            Log.e("NoSuchAlgori",e.toString());
        } catch (final KeyManagementException e) {
            Log.e("KeyManagementException",e.toString());
        }
    }

}
