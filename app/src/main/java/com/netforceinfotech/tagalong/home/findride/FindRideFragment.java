package com.netforceinfotech.tagalong.home.findride;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.general.Global_variables;
import com.netforceinfotech.tagalong.home.HomeActivity;
import com.netforceinfotech.tagalong.home.findride.ride_available.MyData;
import com.netforceinfotech.tagalong.home.findride.ride_available.Ride_detail;
import com.netforceinfotech.tagalong.home.findride.ride_available.Ride_detail_pojo;
import com.netforceinfotech.tagalong.home.findride.ride_available.RidesActivity;
import com.netforceinfotech.tagalong.login.LoginActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindRideFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {


    String TAG = "google_result";
    private static final int PLACE_FROM = 101;
    private static final int PLACE_TO = 105;
    ImageView imageFindRide;
    Context context;
    ProgressDialog pd;
    String place_From_Address, place_To_Address, place_To_lat, place_To_long, place_From_lat, To_lat_long, From_lat_long;

    public static ArrayList<MyData> mydata;

    private EditText selectDateEditText, editTextFrom, editTextTo;
    public static ArrayList<Ride_detail_pojo> ridedetail;

    public FindRideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_ride, container, false);
        mydata = new ArrayList<MyData>();
        ridedetail = new ArrayList<Ride_detail_pojo>();
        pd = new ProgressDialog(getActivity());
        context = getActivity();
        initView(view);

        return view;
    }

    private void initView(View view) {
        editTextTo = (EditText) view.findViewById(R.id.et_to);
        editTextTo.setOnClickListener(this);

        editTextFrom = (EditText) view.findViewById(R.id.et_from);
        editTextFrom.setOnClickListener(this);
        imageFindRide = (ImageView) view.findViewById(R.id.imageFindRide);
        Glide.with(context)
                .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.image).into(imageFindRide);
        view.findViewById(R.id.buttonSearch).setOnClickListener(this);

        selectDateEditText = (EditText) view.findViewById(R.id.selectDateEditText);
        selectDateEditText.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_to:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_TO);

                } catch (GooglePlayServicesRepairableException e) {

                    Log.d("Error...", String.valueOf(e));
                } catch (GooglePlayServicesNotAvailableException e) {
                }
                break;

            case R.id.et_from:

                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_FROM);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                    Log.d("Errorrr", String.valueOf(e));
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                break;
            case R.id.buttonSearch:

                if (editTextFrom.getText().length() != 0)

                {
                    if (editTextTo.getText().length() != 0) {
                        if (selectDateEditText.getText().length() != 0) {
                            call_find_ride_webservice(getActivity());


                        }
                    } else {
                        Toast.makeText(getActivity(), "Please Enter To address", Toast.LENGTH_SHORT).show();


                    }

                } else {
                    Toast.makeText(getActivity(), "Please Enter From address", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.selectDateEditText:
                showSelectDatePopup();
                break;
        }
    }

    public void call_find_ride_webservice(FragmentActivity activity) {


        Global_variables.ride_date = selectDateEditText.getText().toString();
        Global_variables.from_lat_long = From_lat_long;
        Global_variables.to_lat_long = To_lat_long;
        mydata.clear();


        pd.show();
        setupSelfSSLCert();

        JsonObject js = new JsonObject();
        js.addProperty("type", "find_ride");
        js.addProperty("searchdate", Global_variables.ride_date);
        js.addProperty("From_lat_long", Global_variables.from_lat_long);
        js.addProperty("To_lat_long", Global_variables.to_lat_long);
        js.addProperty("search", "place");
        js.addProperty("memrating", Global_variables.rating);
        js.addProperty("fromhr", Global_variables.min_time);
        js.addProperty("tohr", Global_variables.max_time);
        js.addProperty("ridetype[]", Global_variables.ridetype);
        js.addProperty("carcomfort", Global_variables.carcomfort);
        js.addProperty("fltimg", Global_variables.pictures);
        js.addProperty("ePriceType", Global_variables.price);








        /*js.addProperty("type","find_ride");
        js.addProperty("searchdate",selectDateEditText.getText().toString().trim());
        js.addProperty("From_lat_long",From_lat_long);
        js.addProperty("To_lat_long",To_lat_long);
        js.addProperty("search","place");*/


        String find_ride_webservice = "https://tag-along.net/webservice.php";
        Log.e("find_ride_webservice", find_ride_webservice);
        Log.e("js_login", js.toString());
        Ion.with(context)
                .load(find_ride_webservice)
                .setJsonObjectBody(js)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result != null) {
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

                                    Intent intent = new Intent(context, RidesActivity.class);
                                    Global_variables.ride_date = selectDateEditText.getText().toString().trim();
                                    Global_variables.from_lat_long = From_lat_long;
                                    Global_variables.to_lat_long = To_lat_long;
                                    // ridedetail=RidesActivity.ride_detail_pojos;

//                                    intent.putParcelableArrayListExtra(
//                                            "com.netforceinfotech.tagalong",
//                                            (ArrayList<? extends Parcelable>) ridedetail);
                                    startActivity(intent);

                                    getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                                }
                            } else {


                                Intent intent = new Intent(context, CantFindRideActivity.class);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                                Toast.makeText(getActivity(), "No result found", Toast.LENGTH_SHORT).show();


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


                        } else {
                            Intent intent = new Intent(context, RidesActivity.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                            Log.e("result_null", "result_null");
                        }
                        // do stuff with the result or error
                    }
                });


    }

    private void showSelectDatePopup() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FindRideFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Date date2 = new Date();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            monthOfYear = monthOfYear + 1;
            date2 = date_format.parse(year + "-" + monthOfYear + "-" + dayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat outDate = new SimpleDateFormat("dd MMM yyyy");
        String n = outDate.format(date2).toString();
        Log.e("n", n + "");
        selectDateEditText.setText(n);


//
//        Date now = new Date();
//        String datetimeStr = now.toString();
//        System.out.println("1. " + datetimeStr);
//        SimpleDateFormat format = new SimpleDateFormat(
//                "DD MMM yyyy");
//        System.out.println("2. " + format.format(now));
//        selectDateEditText.setText(format.format(now));


        //  Date date2 = new Date();
//        SimpleDateFormat date_format = new SimpleDateFormat("DD MMMM yyyy");
//        try {
//            Date date2 = date_format.parse(year +" "+ monthOfYear+" " + dayOfMonth);
//            selectDateEditText.setText(date_format.format(date2));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        //  SimpleDateFormat outDate = new SimpleDateFormat("MMMM DD yyyy");


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_FROM) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                String plaze = place.getName().toString();
                Log.e(TAG, "Place: " + place.getName() + place.getLatLng().latitude + "," + place.getName() + place.getLatLng().longitude + place.getAddress());
                // showMessage("The Place name is"+place.getName());

                From_lat_long = "(" + String.valueOf(place.getLatLng().latitude) + "," + " " + String.valueOf(place.getLatLng().longitude) + ")";


                place_From_Address = place.getAddress().toString().trim();

                editTextFrom.setText(plaze);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
                Log.i(TAG, status.toString());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } // ends here...

        if (requestCode == PLACE_TO) {

            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                String plaze = place.getName().toString();
                editTextTo.setText(plaze);
                place_To_Address = place.getAddress().toString().trim();

                To_lat_long = "(" + String.valueOf(place.getLatLng().latitude) + "," + " " + String.valueOf(place.getLatLng().longitude) + ")";

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Log.d("StatusInfo", "StatusMessage=" + status.getStatusMessage() + "Status=" + status.getStatus());
            }
        }


    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
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
            Log.e("NoSuchAlgori", e.toString());
        } catch (final KeyManagementException e) {
            Log.e("KeyManagementException", e.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Global_variables.source_address = "";
        Global_variables.destination_address = "";
        Global_variables.from_lat_long = "";
        Global_variables.to_lat_long = "";

        Global_variables.ride_date = "";
        Global_variables.min_time = "0";
        Global_variables.max_time = "24";
        Global_variables.price = "All";
        Global_variables.pictures = "Allphoto";
        Global_variables.ridetype = "Yes";

        Global_variables.carcomfort = "All";
        Global_variables.rating = "All";

    }
}







