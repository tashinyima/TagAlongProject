package com.netforceinfotech.tagalong.myrides;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.tagalong.home.findride.FindRideFragment;
import com.netforceinfotech.tagalong.home.offerride.OfferFragment;
import com.netforceinfotech.tagalong.myrides.Fragments_myrides.Asbooker_fragment;
import com.netforceinfotech.tagalong.myrides.Fragments_myrides.Asdriver_fragment;

public class PagerAdapter_myrides extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter_myrides(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Asdriver_fragment asdriver_fragment = new Asdriver_fragment();
                return asdriver_fragment;
            case 1:
                Asbooker_fragment asbooker_fragment = new Asbooker_fragment();
                return asbooker_fragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}