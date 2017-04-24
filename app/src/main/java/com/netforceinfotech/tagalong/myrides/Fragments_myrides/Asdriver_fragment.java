package com.netforceinfotech.tagalong.myrides.Fragments_myrides;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asbooker.Recycler_adapter_asbooker;
import com.netforceinfotech.tagalong.myrides.Fragments_myrides.recycler_adpater_asdriver.Recycler_adapter_asdriver;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Asdriver_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Asdriver_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Asdriver_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Asdriver_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Asdriver_fragment newInstance(String param1, String param2) {
        Asdriver_fragment fragment = new Asdriver_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_asdriver_fragment, container, false);

        setupRecyclerView(v);



        // Inflate the layout for this fragment
        return v;
    }
    private void setupRecyclerView(View v) {
        RecyclerView recyclerView= (RecyclerView)v.findViewById(R.id.recycler_asdriver);
        Recycler_adapter_asdriver myAdapter=new Recycler_adapter_asdriver(getActivity(),null);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

}
