package com.netforceinfotech.tagalong.chat.driverchat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.tagalong.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyHolder extends RecyclerView.ViewHolder {


    View view;
    TextView message,username;

    public MyHolder(View itemView) {
        super(itemView);
        message=(TextView)itemView.findViewById(R.id.user_message);
        username=(TextView)itemView.findViewById(R.id.messanger_name);

        //implementing onClickListener
        view = itemView;







    }
}