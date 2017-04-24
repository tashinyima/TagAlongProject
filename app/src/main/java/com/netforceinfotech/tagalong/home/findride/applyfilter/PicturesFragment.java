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
public class PicturesFragment extends Fragment {
    RadioGroup radioGroup;

    public PicturesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.fragment_pictures, container, false);
        radioGroup=(RadioGroup)v.findViewById(R.id.radio_grp_picture);

        if(Global_variables.pictures_id==100)
        {
            radioGroup.check(Global_variables.pictures_id);
        }





        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=(RadioButton)v.findViewById(checkedId);
                Global_variables.pictures=rb.getText().toString();
                Global_variables.pictures_id=checkedId;

                //Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });



        // Inflate the layout for this fragment
        return v;
    }

}
