package com.netforceinfotech.tagalong.home.findride.ride_available;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.driverchat.*;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.netforceinfotech.tagalong.home.findride.ride_available.RidesActivity.ride_detail_pojos;

public class Ride_detail extends AppCompatActivity {
    TextView book_this_ride,membername,tripprize,to,from,date,time,schdule,detour,luggagesize,available_seat,
    car_brand,car_comfort,car_type,car_plateno;
   public static ArrayList<Ride_detail_pojo> ride_detail_pojos;
    CircleImageView memberimage;
    RatingBar  rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_detail2);
        if(MyAdapter.ride_detail_pojos_position.size()!=0)
        {initview();}
        setupToolBar(getString(R.string.rides_available));
    }

    private void setupToolBar(String string) {



    }

    private void initview() {

        book_this_ride=(TextView)findViewById(R.id.book_this_ride);


       //

        membername=(TextView)findViewById(R.id.membername);
        memberimage=(CircleImageView) findViewById(R.id.imageViewDp);
        rating=(RatingBar)findViewById(R.id.ratingbar);
        tripprize=(TextView)findViewById(R.id.trip_prize);
        to=(TextView)findViewById(R.id.to_tv);
        from=(TextView)findViewById(R.id.from_tv);
        date=(TextView)findViewById(R.id.date_tv);
        time=(TextView)findViewById(R.id.tv_time);
        schdule=(TextView)findViewById(R.id.tv_schdule);
        detour=(TextView)findViewById(R.id.detour_tv);
        luggagesize=(TextView)findViewById(R.id.luggage_size);
        available_seat=(TextView)findViewById(R.id.totalSeattextView);
        car_brand=(TextView)findViewById(R.id.car_brand);
        car_comfort=(TextView)findViewById(R.id.tv_luxury);
        car_type=(TextView)findViewById(R.id.car_type);
        car_plateno=(TextView)findViewById(R.id.tv_car_plate_no);


        book_this_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ride_detail.this, BookingSeatsActivity.class);
              startActivity(intent);

            }
        });

        membername.setText(MyAdapter.ride_detail_pojos_position.get(0).drivername);
        rating.setStar(Float.parseFloat(MyAdapter.ride_detail_pojos_position.get(0).rating));
        tripprize.setText(MyAdapter.ride_detail_pojos_position.get(0).ride_price);
        to.setText(MyAdapter.ride_detail_pojos_position.get(0).to);
        from.setText(MyAdapter.ride_detail_pojos_position.get(0).from);
        date.setText(MyAdapter.ride_detail_pojos_position.get(0).date);
        time.setText(MyAdapter.ride_detail_pojos_position.get(0).time);
        schdule.setText(MyAdapter.ride_detail_pojos_position.get(0).schudle);
        detour.setText(MyAdapter.ride_detail_pojos_position.get(0).detour);
        luggagesize.setText(MyAdapter.ride_detail_pojos_position.get(0).luggagesize);
        available_seat.setText(MyAdapter.ride_detail_pojos_position.get(0).avail_seat);
       // car_brand.setText(MyAdapter.ride_detail_pojos_position.get(0).car_brand);
        //car_plateno.setText(MyAdapter.ride_detail_pojos_position.get(0).car_plateno);
       // car_comfort.setText(MyAdapter.ride_detail_pojos_position.get(0).car_comfort);
        //car_type.setText(MyAdapter.ride_detail_pojos_position.get(0).car_type);
      //  car_brand.setText(ride_detail_pojos.get(0).car_brand);





    }


}
