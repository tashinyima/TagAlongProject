package com.netforceinfotech.tagalong.chat.driverchat;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    private static final int YOU = 0;
    private static final int OTHER = 1;
    private final LayoutInflater inflater;
    private Context context;
    ArrayList<MyData> myDatas;

    public MyAdapter(Context context, ArrayList<MyData> myDatas) {
        this.myDatas = myDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (myDatas.get(position).you) {
            return YOU;
        } else {
            return OTHER;
        }
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==YOU) {
            View view = inflater.inflate(R.layout.row_my_comment, parent, false);
            MyHolder viewHolder = new MyHolder(view);

            return viewHolder;
        } else {
            View view = inflater.inflate(R.layout.row_other_comment, parent, false);
            MyHolder viewHolder = new MyHolder(view);

            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(MyHolder viewHolder , final int position) {


        viewHolder.message.setText(myDatas.get(position).message);
        viewHolder.username.setText(myDatas.get(position).name);


    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        // return myDatas.size();
        return myDatas.size();
    }
}
