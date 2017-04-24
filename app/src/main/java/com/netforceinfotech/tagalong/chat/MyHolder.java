package com.netforceinfotech.tagalong.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyHolder extends RecyclerView.ViewHolder {


    View view;

    public MyHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
    }
}