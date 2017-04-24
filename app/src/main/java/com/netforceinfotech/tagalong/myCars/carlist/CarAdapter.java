package com.netforceinfotech.tagalong.myCars.carlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.myCars.MyCarActivity;

import java.util.List;

/**
 * Created by JitendraSingh on 12/6/2016.
 */

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    Context context;
    List<CarDatas> carDatasList;

    public CarAdapter(Context context, List<CarDatas> carDatases) {
        this.context = context;
        this.carDatasList = carDatases;

    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_row_list, parent, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, final int position) {

        final CarDatas cars = carDatasList.get(position);
        holder.carComfortTextView.setText(cars.getComfort());
        holder.carNameTextView.setText(cars.getCarName());
        Glide.with(context).load(cars.getCarImgUrl()).error(R.drawable.audi_car).into(holder.carImage);
        holder.recyclerLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MyCarActivity.class);
                intent.putExtra("CARID",cars.getCar_id());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return carDatasList.size();
    }
}
