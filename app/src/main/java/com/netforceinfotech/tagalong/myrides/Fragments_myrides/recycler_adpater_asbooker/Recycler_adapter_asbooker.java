package com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asbooker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyData;
import com.netforceinfotech.tagalong.chat.MyHolder;
import com.netforceinfotech.tagalong.chat.driverchat.DriverChatActivity;
import com.netforceinfotech.tagalong.myrides.Fragments_myrides.Asbooker_fragment;

import java.util.ArrayList;

/**
 * Created by Nishant on 12/19/2016.
 */

public class Recycler_adapter_asbooker extends RecyclerView.Adapter<Asbooker_viewholder> {

    private static final int NORMAL = 0;
    private static final int START = 1;
    private final LayoutInflater inflater;
    private Context context;
    ArrayList<MyData> myDatas;

    public Recycler_adapter_asbooker(Context context, ArrayList<MyData> myDatas) {
        this.myDatas = myDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /*  @Override
      public int getItemViewType(int position) {
          if (itemList.get(position).image.isEmpty()) {
              return SIMPLE_TYPE;
          } else {
              return IMAGE_TYPE;
          }
      }
  */


    @Override
    public Asbooker_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.row_as_driver, parent, false);
        Asbooker_viewholder viewHolder = new Asbooker_viewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Asbooker_viewholder holder, int position) {
        holder.rl_message_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, DriverChatActivity.class);
                context.startActivity(intent);



            }
        });

    }





    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        // return myDatas.size();
        return 10;
    }
}