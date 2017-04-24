package com.netforceinfotech.tagalong.home.findride.paymentmodes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.tagalong.R;

/**
 * Created by JitendraSingh on 11/24/2016.
 */

public class CardListVieHolder extends RecyclerView.ViewHolder{

    public TextView cardNoTextView;

    public CardListVieHolder(View itemView) {
        super(itemView);
        cardNoTextView = (TextView) itemView.findViewById(R.id.userCardNoTextView);

    }
}
