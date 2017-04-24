package com.netforceinfotech.tagalong.home.offerride;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.text.Line;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.offerride.returnstopover.RestopOverData;
import com.netforceinfotech.tagalong.home.offerride.returnstopover.ReturnStopOverAdapter;
import com.netforceinfotech.tagalong.home.offerride.stopover.StopOverAdapter;
import com.netforceinfotech.tagalong.home.offerride.stopover.StopOverData;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RideDetailActivity extends AppCompatActivity implements View.OnClickListener, RoutingListener {
    String TAG = "google_result";
    private static final int PLACE_FROM = 101;
    private static final int PLACE_TO = 105;
    private static final int STOP_OVER_ADD = 100;
    private static final int RE_STOP_OVER_ADD = 103;
    boolean oneway = true;
    LinearLayout linearLayoutRoundTrip;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView textViewTitle;
    Context context;
    Toolbar toolbar;
    String rideDetail;
    private Intent intent;
    private Bundle bundle;
    private EditText departureDateEditText, departureTimeEditText, returnDateEditText, returnTimeEditText;
    EditText toEditText, fromEditText, priceEditText, stopoverEditText, returnstopoverEditText;

    ImageView stopoveroneImageView, returnStopOverImage;
    RecyclerView stopOverRecycler, restopOverRecycler;
    public ArrayList<StopOverData> stopOverDatas = new ArrayList<>();

    public StopOverAdapter stopOverAdapter;
    ReturnStopOverAdapter returnStopOverAdapter;
    ArrayList<RestopOverData> restopOverDatas = new ArrayList<>();
    public  List<LatLng> stopOverlatlongs = new ArrayList<LatLng>();
    public List<String> stopOverlatlongs2 = new ArrayList<String>();

    TextView distanceTextView, suggestedPriceTextView;
    private LatLng destination, origin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_detail);
        context = this;
        try {
            Bundle bundle = getIntent().getExtras();
            oneway = bundle.getBoolean("oneway");
        } catch (Exception ex) {
            Log.e("Exception",ex.toString());

        }
        if (oneway) {
            rideDetail = getString(R.string.one_way_trip);
        } else {
            rideDetail = getString(R.string.round_trip);
        }
        initView();

        setupToolbar(getString(R.string.rideDetail));

        setUpRecycler();


    }

    public void setStopOversData() {


        ArrayList<LatLng> waypoints = new ArrayList<>();
        waypoints.add(origin);
        waypoints.addAll(stopOverlatlongs);
        waypoints.add(destination);

        if (waypoints.size() == 0) {

            Routing routing = new Routing.Builder()
                    .travelMode(Routing.TravelMode.DRIVING)
                    .withListener(this)
                    .build();

            routing.execute();
        }

        Routing routing = new Routing.Builder()
                .travelMode(Routing.TravelMode.DRIVING)
                .withListener(this)
                .waypoints(waypoints)
                .build();

        routing.execute();


    }

    private void setUpRecycler() {

        restopOverRecycler = (RecyclerView) findViewById(R.id.RestopOverRecycler);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        returnStopOverAdapter = new ReturnStopOverAdapter(this, restopOverDatas);
        restopOverRecycler.setLayoutManager(lm);
        restopOverRecycler.setAdapter(returnStopOverAdapter);

        stopOverRecycler = (RecyclerView) findViewById(R.id.stopOverRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        stopOverAdapter = new StopOverAdapter(this, stopOverDatas);
        stopOverRecycler.setLayoutManager(linearLayoutManager);
        stopOverRecycler.setAdapter(stopOverAdapter);

    }

    private void initView() {


        suggestedPriceTextView = (TextView) findViewById(R.id.suggestedPriceTextView);

        priceEditText = (EditText) findViewById(R.id.priceEditText);

        distanceTextView = (TextView) findViewById(R.id.distanceTextView);
        returnStopOverImage = (ImageView) findViewById(R.id.returnStopOverImage);
        returnStopOverImage.setOnClickListener(this);
        stopoverEditText = (EditText) findViewById(R.id.stopoverEditText);
        stopoverEditText.setOnClickListener(this);
        returnstopoverEditText = (EditText) findViewById(R.id.returnStopOverEditText);
        returnstopoverEditText.setOnClickListener(this);
        stopoveroneImageView = (ImageView) findViewById(R.id.imageViewStopoverOne);
        stopoveroneImageView.setOnClickListener(this);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        linearLayoutRoundTrip = (LinearLayout) findViewById(R.id.linearLayoutRoundTrip);
        if (oneway) {
            linearLayoutRoundTrip.setVisibility(View.GONE);
        } else {
            textViewTitle.setText(getString(R.string.round_trip));
        }
        findViewById(R.id.textViewNext).setOnClickListener(this);

        departureDateEditText = (EditText) findViewById(R.id.departureEditText);
        departureDateEditText.setOnClickListener(this);
        departureTimeEditText = (EditText) findViewById(R.id.departTimeEditText);
        departureTimeEditText.setOnClickListener(this);
        returnDateEditText = (EditText) findViewById(R.id.returnDateEditText);
        returnDateEditText.setOnClickListener(this);
        returnTimeEditText = (EditText) findViewById(R.id.returnTimeEditText);
        returnTimeEditText.setOnClickListener(this);
        fromEditText = (EditText) findViewById(R.id.fromEditText);
        fromEditText.setOnClickListener(this);
        toEditText = (EditText) findViewById(R.id.toEditText);
        toEditText.setOnClickListener(this);
        context = this;


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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.returnStopOverEditText:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(this);
                    startActivityForResult(intent, RE_STOP_OVER_ADD);

                } catch (GooglePlayServicesRepairableException e) {

                    Log.d("Error...", String.valueOf(e));
                } catch (GooglePlayServicesNotAvailableException e) {
                }
                break;
            case R.id.stopoverEditText:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(this);
                    startActivityForResult(intent, STOP_OVER_ADD);

                } catch (GooglePlayServicesRepairableException e) {

                    Log.d("Error...", String.valueOf(e));
                } catch (GooglePlayServicesNotAvailableException e) {
                }
                break;
            case R.id.returnStopOverImage:
                showMessage("return clicked...");

                AddReturnStopOverData();

            case R.id.imageViewStopoverOne:

                showMessage("imageViewStopoverOne CAlled");

                AddStopOverDatas();


                break;
            case R.id.toEditText:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(this);
                    startActivityForResult(intent, PLACE_TO);

                } catch (GooglePlayServicesRepairableException e) {

                    e.fillInStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                }
                break;
            case R.id.fromEditText:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(this);
                    startActivityForResult(intent, PLACE_FROM);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                    Log.d("Errorrr", String.valueOf(e));
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                break;

            case R.id.textViewNext:

                GetDataBeforeNextActivity();

                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            case R.id.departureEditText:
                showDepartDate();
                break;
            case R.id.departTimeEditText:
                showTime();
                break;
            case R.id.returnDateEditText:
                showReturnDate();
                break;
            case R.id.returnTimeEditText:
                showReturnTime();


        }
    }

    private void GetDataBeforeNextActivity() {

        if (!fromEditText.getText().toString().isEmpty()) {

            if (!toEditText.getText().toString().isEmpty()) {

                if (!departureDateEditText.getText().toString().isEmpty()) {

                    if (!departureTimeEditText.getText().toString().isEmpty()) {


                        if (!priceEditText.getText().toString().isEmpty()) {

                            // if all the condition satisfied then perform the intent activity....

                            intent = new Intent(context, CarDetailActivity.class);

                            bundle = new Bundle();
                            bundle.putBoolean("oneway", oneway);
                            // tashiDev
                            bundle.putString("fromEditText", fromEditText.getText().toString());
                            bundle.putString("toEditText", toEditText.getText().toString());
                            bundle.putString("departureDateEditText", departureDateEditText.getText().toString());
                            bundle.putString("departureTimeEditText", departureTimeEditText.getText().toString());
                            bundle.putString("PriceEditText", priceEditText.getText().toString());
                            // tashi...

                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            showMessage("Please Enter the Price");
                        }
                    } else {
                        showMessage("Please Select the Departure Time");
                    }
                } else {
                    showMessage("Please Select the Departure Date");
                }
            } else {

                showMessage("Please Enter Destination Location");
            }


        } else {
            showMessage("Please Enter From Location");
        }


    }

    private void AddReturnStopOverData() {

        if (returnstopoverEditText.length() != 0) {

            restopOverDatas.add(new RestopOverData(returnstopoverEditText.getText().toString()));
            returnStopOverAdapter.notifyDataSetChanged();
            returnstopoverEditText.getText().clear();
        } else {
            showMessage("Enter Return Stop Over");
        }
    }

    private void AddStopOverDatas() {


        if (stopoverEditText.length() != 0) {
            stopOverDatas.add(new StopOverData(stopoverEditText.getText().toString()));
            stopOverAdapter.notifyDataSetChanged();
            stopoverEditText.getText().clear();
            // calcute the route distance....

            Log.d("DataSize", String.valueOf(stopOverlatlongs.size()));


            setStopOversData();


        } else {
            showMessage("StopOver Field is empty");

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        RE_STOP_OVER_ADD

        if (requestCode == PLACE_FROM) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                String plaze = place.getName().toString();
                LatLng latlng = place.getLatLng();
                double lat = latlng.latitude;
                double longi = latlng.longitude;
                origin = new LatLng(lat, longi);
                stopOverlatlongs.add(new LatLng(lat, longi));


                Log.i(TAG, "Place: " + place.getName());
                showMessage("The Place name is" + place.getName());
                fromEditText.setText(plaze);

                //TashiDev

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
                Log.i(TAG, status.toString());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }


        }
        if (requestCode == PLACE_TO) {

            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                String plaze = place.getName().toString();
                LatLng latlng = place.getLatLng();
                double lat = latlng.latitude;
                double longi = latlng.longitude;
                destination = new LatLng(lat, longi);
                toEditText.setText(plaze);

                showMessage("SetStopOverCalled");

                setStopOversData();

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
                Log.i(TAG, status.toString());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if (requestCode == STOP_OVER_ADD) {

            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                String plaze = place.getName().toString();
                LatLng la = place.getLatLng();

                double longi = la.longitude;
                double latit = la.latitude;
// passed the datas to find the distance...
                stopOverlatlongs.add(new LatLng(latit, longi));

                stopoverEditText.setText(plaze);

//                getthedistance....


                return;
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
                Log.i(TAG, status.toString());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }

        }

        if (requestCode == RE_STOP_OVER_ADD) {

            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                String plaze = place.getName().toString();
                LatLng latlng = place.getLatLng();
                double lat = latlng.latitude;
                double longi = latlng.longitude;

                stopOverlatlongs.add(new LatLng(lat, longi));

                returnstopoverEditText.setText(plaze);

                return;
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
                Log.i(TAG, status.toString());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }

        }


    }

    private void showReturnTime() {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        returnTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void showMessage(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void showReturnDate() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        returnDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    private void showTime() {


        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        departureTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }


    private void showDepartDate() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        departureDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }


    @Override
    public void onRoutingFailure(RouteException e) {

        e.fillInStackTrace();
        Log.d("Routing Failed", String.valueOf(e));

    }

    @Override
    public void onRoutingStart() {


    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {

        String distance = arrayList.get(i).getDistanceText().toString();

        distanceTextView.setText(distance);
        String distanceValue = String.valueOf(arrayList.get(i).getDistanceValue());


        double _rate = 0.5; // server ....

        // patterns haves to be applied...


        double amo = Double.parseDouble(distance.replace(",", "").replace("km", ""));
        double final_amount = amo * _rate;
        Log.d("dfasfsa", String.valueOf(final_amount));
        String final_Amount = String.valueOf(final_amount);

        suggestedPriceTextView.setText(final_Amount + "$");

        Log.d("Distance", distance);


    }

    @Override
    public void onRoutingCancelled() {


    }
}
