package com.ignacio.lorenz.prrcmobile.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ignacio.lorenz.prrcmobile.Fragments.Details;
import com.ignacio.lorenz.prrcmobile.Fragments.Transactions;

public class ShowPagerAdapter extends FragmentPagerAdapter {

    public ShowPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    static final int NUM_ITEMS = 2;

    Details details = new Details();
    Transactions transactions = new Transactions();

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0 :
                return details;
            case 1:
                return transactions;
            default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
