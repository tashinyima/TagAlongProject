package com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asbooker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.netforceinfotech.tagalong.R;

/**
 * Created by Nishant on 12/19/2016.
 */
public class Asbooker_viewholder extends RecyclerView.ViewHolder
{
    RelativeLayout rl_message_chat;
    public Asbooker_viewholder(View itemView) {

        super(itemView);
        rl_message_chat=(RelativeLayout)itemView.findViewById(R.id.rl_chat_meaasge);

    }
}