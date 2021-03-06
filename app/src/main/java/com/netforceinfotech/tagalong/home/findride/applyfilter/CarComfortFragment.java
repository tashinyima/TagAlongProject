package com.netforceinfotech.tagalong.home.findride.applyfilter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.general.Global_variables;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarComfortFragment extends Fragment {
    RadioGroup radioGroup;


    public CarComfortFragment() {
        // Required empty public constructor

//        Lasso tashi
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_car_comfort, container, false);


        radioGroup=(RadioGroup)view.findViewById(R.id.radio_grp_carcomfort);

        if(Global_variables.carcomfort_id!=100)
        {
            radioGroup.check(Global_variables.carcomfort_id);
        }





        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=(RadioButton)view.findViewById(checkedId);
                Global_variables.carcomfort=rb.getText().toString();
                Global_variables.carcomfort_id=checkedId;

                //Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
