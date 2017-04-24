package com.netforceinfotech.tagalong.home.offerride.returnstopover;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.netforceinfotech.tagalong.R;

/**
 * Created by JitendraSingh on 12/12/2016.
 */

public class ReturnStopOverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    EditText edittextReStopOver;
    ImageView RedeleteImg;
    ReturnStopOverAdapter reAdapter;

    public ReturnStopOverViewHolder(View itemView ,ReturnStopOverAdapter reAdapter) {
        super(itemView);
        this.reAdapter = reAdapter;
        edittextReStopOver = (EditText) itemView.findViewById(R.id.restopOverEditText);
        RedeleteImg = (ImageView) itemView.findViewById(R.id.redeleteImageView);
        RedeleteImg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
 //Delete item....

        if(v.getId()==R.id.redeleteImageView){

            reAdapter.RemoveItem(getAdapterPosition());
        }


    }
}
