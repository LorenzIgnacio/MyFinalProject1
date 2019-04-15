package com.ignacio.lorenz.prrcmobile.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ignacio.lorenz.prrcmobile.Fragments.Accepted;
import com.ignacio.lorenz.prrcmobile.Fragments.AllDocument;
import com.ignacio.lorenz.prrcmobile.Fragments.Archive;
import com.ignacio.lorenz.prrcmobile.Fragments.InActive;
import com.ignacio.lorenz.prrcmobile.Fragments.MyDocument;
import com.ignacio.lorenz.prrcmobile.Fragments.Received;

public class ViewpagerAdapter extends FragmentPagerAdapter {

    int num;
    FragmentManager fmngr;

    public ViewpagerAdapter(FragmentManager fm){
        super(fm);
    }

    static final int NUM_ITEMS = 6;

    MyDocument my = new MyDocument();
    AllDocument all = new AllDocument();
    Accepted accept = new Accepted();
    Received receive = new Received();
    InActive inactive = new InActive();
    Archive archive = new Archive();

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
//        switch(position) {
//            case 0:
//                return home;
//                break;
//            case 1:
//                return all;
//                break;
//            case 2:
//                return accept;
//                break;
//            case 3:
//                return receive;
//                break;
//            case 4:
//                return inactive;
//                break;
//            case 5:
//                return archive;
//                break;
//            default:
//                return null;
//        }
        if(position == 0){
            return all;
        }
        else if(position == 1){
            return my;
        }
        else if(position == 2){
            return accept;
        }
        else if(position == 3){
            return receive;
        }
        else if(position == 4){
            return inactive;
        }
        else if(position == 5){
            return archive;
        }
        return null;
    }

}
