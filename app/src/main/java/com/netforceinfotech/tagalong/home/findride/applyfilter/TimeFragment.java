package com.netforceinfotech.tagalong.home.findride.applyfilter;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.general.Global_variables;

import org.jetbrains.annotations.NotNull;

import me.bendik.simplerangeview.SimpleRangeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends Fragment implements RangeBar.OnRangeBarChangeListener{

    private RangeBar rangeBar;
    Context context;
    SimpleRangeView simplerangeview;

    public TimeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_time,container,false);
        simplerangeview = (SimpleRangeView) view.findViewById(R.id.fixed_rangeview);







        simplerangeview.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView rangeView, int start) {
                simplerangeview.setShowLabels(true);
                Global_variables.min_time=String.valueOf(start);
//                rangeView.setOnRangeLabelsListener(this);

                //editStart.setText(String.valueOf(start));
            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView rangeView, int end) {
                simplerangeview.setShowLabels(true);
                Global_variables.max_time=String.valueOf(end);
            }
        });



        simplerangeview.setOnRangeLabelsListener(new SimpleRangeView.OnRangeLabelsListener() {

            @Override
            public String getLabelTextForPosition(@NotNull SimpleRangeView rangeView, int pos, @NotNull SimpleRangeView.State state) {
                return String.valueOf(pos+1);
            }
        });
//        if(Global_variables.min_time==null)
//        {
//
//        }else{
//            Log.e("leftPinIndex",Global_variables.min_time+"*****"+Global_variables.max_time);
//            rangeBar.setTickStart(Float.parseFloat(Global_variables.min_time));
//            rangeBar.setTickEnd(Float.parseFloat(Global_variables.max_time));
//
//
////            rangeBar.setLeft(Integer.parseInt(Global_variables.min_time));
////            rangeBar.setRight(Integer.parseInt(Global_variables.max_time));
//        }
//
//        // Inflate the layout for this fragment
//
//
//        rangeBar.setOnRangeBarChangeListener(this);
        return view;
    }

    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {


//Log.e("leftPinIndex",leftPinValue+"*****"+rightPinValue);
//        Global_variables.min_time=leftPinValue;
//        Global_variables.max_time=rightPinValue;
//        rangeBar.setTickStart(Float.parseFloat(leftPinValue));
//        rangeBar.setTickEnd(Float.parseFloat(rightPinValue));
    }
}
