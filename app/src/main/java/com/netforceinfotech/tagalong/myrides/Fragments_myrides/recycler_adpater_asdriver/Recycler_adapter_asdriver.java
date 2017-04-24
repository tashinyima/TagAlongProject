package com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asdriver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyData;
import com.netforceinfotech.tagalong.chat.driverchat.DriverChatActivity;
import com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asbooker.Asbooker_viewholder;

import java.util.ArrayList;

/**
 * Created by Nishant on 12/19/2016.
 */

public class Recycler_adapter_asdriver  extends RecyclerView.Adapter<viewholder_asdriver> {

    private static final int NORMAL = 0;
    private static final int START = 1;
    private final LayoutInflater inflater;
    private Context context;
    ArrayList<MyData> myDatas;

    public Recycler_adapter_asdriver(Context context, ArrayList<MyData> myDatas) {
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
    public viewholder_asdriver onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.row_as_driver, parent, false);
        viewholder_asdriver viewHolder = new viewholder_asdriver(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewholder_asdriver holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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