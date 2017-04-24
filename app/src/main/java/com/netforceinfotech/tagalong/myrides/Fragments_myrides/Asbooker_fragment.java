package com.netforceinfotech.tagalong.myrides.Fragments_myrides;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyAdapter;
import com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asbooker.Recycler_adapter_asbooker;

/**

 */
public class Asbooker_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match



    public Asbooker_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_asbooker_fragment, container, false);
        setupRecyclerView(v);
        // Inflate the layout for this fragment
        return v;
    }



    private void setupRecyclerView(View v) {
        RecyclerView recyclerView= (RecyclerView)v.findViewById(R.id.recycler_asbooker);
        Recycler_adapter_asbooker myAdapter=new Recycler_adapter_asbooker(getActivity(),null);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }



}
