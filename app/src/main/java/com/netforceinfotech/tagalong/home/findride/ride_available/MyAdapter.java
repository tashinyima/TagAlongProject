package com.netforceinfotech.tagalong.home.findride.ride_available;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.driverchat.DriverChatActivity;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL = 0;
    private static final int START = 1;
    private final LayoutInflater inflater;
    private Context context;
    ArrayList<MyData> myDatas;

    public MyAdapter(Context context, ArrayList<MyData> myDatas) {
        this.myDatas = myDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /*  @Override
      public int getItemViewType(int position) {
          if (itemList.get(position).image.isEmpty()) {
              return SIMPLE_TYPE;
          } else {
              return IMAGE_TYPE;
          }
      }
  */


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.row_rides_available, parent, false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RideAvailDetailActivity.class);
                Intent intent = new Intent(context, Ride_detail.class);
                ride_detail_pojos_position.add(ride_detail_pojos.get(position));
             //   Ride_detail.ride_detail_pojos.set(position,ride_detail_pojos.get(position));

                //intent.putExtras("ridedetail",ride_detail_pojos.get(position))
               // intent.
                context.startActivity(intent);
            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        // return myDatas.size();
        return myDatas.size();
    }
}
