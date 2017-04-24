package com.netforceinfotech.tagalong.googledistance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;

import org.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoogleMapActivity extends AppCompatActivity implements RoutingListener, GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks {

    LatLng start,waypoint,end;
    Context context;
    TextView textone;
    ArrayList<LatLng> waypoints = new ArrayList<>();


    String Google_API ="AIzaSyA6ncdN6xB8jK2aum_IlJfn2Uh_KqDaCX0";






   LatLng origin,destination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        context=this;

//        textone = (TextView) findViewById(R.id.textone);
//        start = new LatLng(28.672816, 77.234638); // delhi
//        waypoints.add(start);
       Double lat =28.672816;
        Double longi = 77.234638;
        Double dlat=13.049044;
        Double dlongi=77.586200;
       String origind= new LatLng(28.672816, 77.234638).toString(); // Delhi
       String destinations = new LatLng(13.049044, 77.586200).toString(); //Bangalore...



        String url = "https://maps.googleapis.com/maps/api/directions/json?";
                url=url+"origin="+lat+","+longi+"&"+"destination="+dlat+","+dlongi+"&key="+Google_API;

        Log.d("Result",url);

        Ion.with(this).load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                       Log.d("Result", String.valueOf(result));

                        if(result!=null){

                            JsonArray ja= result.getAsJsonArray("routes");


                            for( int i=0; i<ja.size();i++){

                                JsonObject rs= ja.get(i).getAsJsonObject();

                                   String name= rs.get("copyrights").getAsString();

                                Log.d("Result",name);

                            }



                        }

                    }
                });



//        start = new LatLng(13.049044, 77.586200); // bangalore
//        waypoints.add(start);
//        start = new LatLng(22.849602,85.693359); // bangaladesh
//        waypoints.add(start);
        // 3928km  de-bangal -bangdesh
        // 3126 - delhi-bagdesh-bangalore...









//        Routing routing = new Routing.Builder()
//                .travelMode(Routing.TravelMode.DRIVING)
//                .withListener(this)
//                .waypoints(waypoints)
//                .optimize(true)
//                .alternativeRoutes(true)
//                .build();
//
//
//        routing.execute();

    }

    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {


      textone.setText(arrayList.get(i).getDistanceText());
    }

    @Override
    public void onRoutingCancelled() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
