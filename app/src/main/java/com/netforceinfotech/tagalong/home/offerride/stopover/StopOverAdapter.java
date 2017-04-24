package com.netforceinfotech.tagalong.home.offerride.stopover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.findride.ride_available.RideAvailDetailActivity;
import com.netforceinfotech.tagalong.home.offerride.RideDetailActivity;

import java.util.ArrayList;

/**
 * Created by JitendraSingh on 12/8/2016.
 */

public class StopOverAdapter extends RecyclerView.Adapter<StopOverViewHolder> {

    ArrayList<StopOverData> stopOverDatas;
    RideDetailActivity context;

    public StopOverAdapter(RideDetailActivity context, ArrayList<StopOverData> stopOverDatas) {

        this.context = context;
        this.stopOverDatas = stopOverDatas;
    }

    @Override
    public StopOverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_stop_over_list, parent, false);
        return new StopOverViewHolder(itemView, this);

    }

    @Override
    public void onBindViewHolder(StopOverViewHolder holder, int position) {


        holder.edittextStopOver.setText(stopOverDatas.get(position).getPlace());


    }

    @Override
    public int getItemCount() {

        return stopOverDatas.size();


    }

    public void removeItem(int position) {
        context.stopOverDatas.remove(position);
        context.stopOverlatlongs.remove(position);
        // call again the distance if it deletes a row...
        context.setStopOversData();

        Log.d("Result", String.valueOf(context.stopOverlatlongs.size()));
        context.stopOverAdapter.notifyDataSetChanged();


        // Add whatever you want to do when removing an Item
    }

}
