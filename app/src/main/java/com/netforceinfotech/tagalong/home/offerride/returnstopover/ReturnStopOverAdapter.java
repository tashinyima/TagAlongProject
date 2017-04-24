package com.netforceinfotech.tagalong.home.offerride.returnstopover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;

import java.util.ArrayList;

/**
 * Created by JitendraSingh on 12/12/2016.
 */

public class ReturnStopOverAdapter extends RecyclerView.Adapter<ReturnStopOverViewHolder> {
    ArrayList<RestopOverData> restopOverDatas;
    Context context;

    public ReturnStopOverAdapter(Context context, ArrayList<RestopOverData> restopOverDatas) {

        this.context = context;
        this.restopOverDatas = restopOverDatas;

    }


    @Override
    public ReturnStopOverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restopover_list, parent, false);

        return new ReturnStopOverViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ReturnStopOverViewHolder holder, int position) {

        holder.edittextReStopOver.setText(restopOverDatas.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return restopOverDatas.size();
    }

    public void RemoveItem(int position){

        restopOverDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,restopOverDatas.size());
    }
}
