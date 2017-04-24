package com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asdriver;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.netforceinfotech.tagalong.R;

/**
 * Created by Nishant on 12/19/2016.
 */

public class viewholder_asdriver extends RecyclerView.ViewHolder {
    RelativeLayout rl_message_chat;
    public viewholder_asdriver(View itemView) {
        super(itemView);
        rl_message_chat=(RelativeLayout)itemView.findViewById(R.id.rl_chat_meaasge);
    }
}
