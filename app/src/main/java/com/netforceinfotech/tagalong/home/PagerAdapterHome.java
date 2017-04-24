package com.netforceinfotech.tagalong.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.tagalong.home.findride.FindRideFragment;
import com.netforceinfotech.tagalong.home.offerride.OfferFragment;

public class PagerAdapterHome extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterHome(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FindRideFragment home = new FindRideFragment();
                return home;
            case 1:
                OfferFragment currentBet = new OfferFragment();
                return currentBet;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }





}