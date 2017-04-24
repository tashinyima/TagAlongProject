package com.netforceinfotech.tagalong.home.offerride;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends Fragment implements View.OnClickListener {

    Context context;
    private Intent intent;
    private Bundle bundle;

    public OfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offer, container, false);
        context = getActivity();
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.relativeLayoutOneWayTrip).setOnClickListener(this);
        view.findViewById(R.id.relativeLayoutRoundTrip).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relativeLayoutOneWayTrip:
                intent = new Intent(context, RideDetailActivity.class);
                bundle = new Bundle();
                bundle.putBoolean("oneway", true);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                break;
            case R.id.relativeLayoutRoundTrip:
                intent = new Intent(context, RideDetailActivity.class);
                bundle = new Bundle();
                bundle.putBoolean("oneway", false);
                intent.putExtras(bundle);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
        }
    }
}
