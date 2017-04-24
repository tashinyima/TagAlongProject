package com.netforceinfotech.tagalong.myCars.carlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.tagalong.R;

/**
 * Created by JitendraSingh on 12/6/2016.
 */

public class CarViewHolder extends RecyclerView.ViewHolder {

    public TextView seatTotalTextView;
    public TextView carNameTextView;
    public TextView carComfortTextView;
    public ImageView carImage;
    public LinearLayout recyclerLinearLayout;

    public CarViewHolder(View itemView) {
        super(itemView);

        seatTotalTextView = (TextView) itemView.findViewById(R.id.seatsTextView);

        carNameTextView = (TextView) itemView.findViewById(R.id.cardNameTextView);
        carComfortTextView = (TextView) itemView.findViewById(R.id.comfortTextView);
        carImage = (ImageView) itemView.findViewById(R.id.carListImages);
        recyclerLinearLayout = (LinearLayout) itemView.findViewById(R.id.recyclerLinearLayout);

    }
}
