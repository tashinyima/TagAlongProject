package com.netforceinfotech.tagalong.home.offerride.stopover;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.netforceinfotech.tagalong.R;

/**
 * Created by JitendraSingh on 12/8/2016.
 */

public class StopOverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    EditText edittextStopOver;
    ImageView deleteImg;
    StopOverAdapter myAdapter;

    public StopOverViewHolder(View itemView, StopOverAdapter myAdapter) {
        super(itemView);
        this.myAdapter=myAdapter;

        edittextStopOver = (EditText) itemView.findViewById(R.id.stopOverEditText);
        deleteImg = (ImageView) itemView.findViewById(R.id.deleteImageView);
        deleteImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.deleteImageView){
            myAdapter.removeItem(getAdapterPosition());


        }
    }
}
