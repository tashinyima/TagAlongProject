package com.netforceinfotech.tagalong.driverProfile;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.netforceinfotech.tagalong.R;

/**
 * Created by JitendraSingh on 11/21/2016.
 */

public class RideViewHolder extends RecyclerView.ViewHolder {

    public TextView fromText,seatText;
    public TextView toText;
    public TextView dateofRides, numberofSeat, hoursTextView, costPerSeat;

    public RideViewHolder(View itemView) {
        super(itemView);

        fromText = (TextView) itemView.findViewById(R.id.fromTextView);
        toText = (TextView) itemView.findViewById(R.id.toTextView);
        dateofRides = (TextView) itemView.findViewById(R.id.departDateTextView);
        numberofSeat = (TextView) itemView.findViewById(R.id.totalSeatTextView);
        hoursTextView = (TextView) itemView.findViewById(R.id.departHourTextView);
        costPerSeat = (TextView) itemView.findViewById(R.id.costPerSeatTextView);
        seatText = (TextView) itemView.findViewById(R.id.seatTextView);
    }
}
