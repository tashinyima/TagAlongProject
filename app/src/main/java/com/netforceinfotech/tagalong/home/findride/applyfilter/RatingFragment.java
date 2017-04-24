package com.netforceinfotech.tagalong.home.findride.applyfilter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.general.Global_variables;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment {

    TextView ratingtext;
    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_rating, container, false);
        ratingtext=(TextView)v.findViewById(R.id.ratingtext);

        RatingBar rb=(RatingBar)v.findViewById(R.id.ratingbar);
        if(Global_variables.rating!=null)
        {
            ratingtext.setText("("+Global_variables.rating+")");
//            rb.setStar(Float.parseFloat(Global_variables.rating));

        }


        rb.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                ratingtext.setText("("+RatingCount+")");
                Global_variables.rating= String.valueOf(RatingCount);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
