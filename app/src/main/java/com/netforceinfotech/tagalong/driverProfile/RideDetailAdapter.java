package com.netforceinfotech.tagalong.driverProfile;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;

import java.util.List;

/**
 * Created by JitendraSingh on 11/21/2016.
 */

public class RideDetailAdapter extends RecyclerView.Adapter<RideViewHolder>{

    private Context mContext;
    private List<RideDetailDatas> rideDetailDatases;

    public RideDetailAdapter(Context mContext, List<RideDetailDatas> rideDetailDatases) {
        this.mContext = mContext;
        this.rideDetailDatases = rideDetailDatases;


    }

    @Override
    public RideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rideoffer_row_list, parent, false);
        return new RideViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RideViewHolder holder, int position) {

        RideDetailDatas rides =rideDetailDatases.get(position);

        holder.fromText.setText(rides.getFromText());
        holder.toText.setText(rides.getToText());
        holder.dateofRides.setText(rides.getDateofRides());
        holder.hoursTextView.setText(rides.getHoursTextView());
        holder.costPerSeat.setText(rides.getCostPerSeat()+"/Seat");
        holder.numberofSeat.setText(rides.getNumberofSeat());


    }

    @Override
    public int getItemCount() {
        return rideDetailDatases.size();
    }
}
