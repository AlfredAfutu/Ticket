package com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.Fragments.CategoriesFragment;
import com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.Fragments.FragmentC;
import com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.Fragments.FragmentD;
import com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.Fragments.HomeFragment;

/**
 * Created by cted on 4/29/15.
 */
public class EventsAdapter extends FragmentStatePagerAdapter {

    private Context context;
    public EventsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){

            case 0:
                fragment =  new CategoriesFragment();
                break;
            case 1:
                fragment = new CategoriesFragment();
                break;
            case 2:
                fragment = new FragmentC();
                break;
            case 3:
                fragment = new FragmentD();
                break;
            case 4:
                fragment = new CategoriesFragment();
                break;
            case 5:
                fragment = new HomeFragment();
                break;
            case 6:
                fragment = new FragmentC();
                break;
            case 7:
                fragment = new FragmentD();
                break;
            /*case 8:
                fragment = new FragmentA();
                break;
            case 9:
                fragment = new FragmentB();
                break;
            case 10:
                fragment = new FragmentC();
                break;
            case 11:
                fragment = new FragmentD();
                break;
            case 12:
                fragment = new FragmentA();
                break;*/

        }
        return fragment;
        //return null;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence charSequence = null;
        switch(position){

            case 0:
                charSequence =  "Categories";
                break;
            case 1:
                charSequence = "Home";
                break;
            case 2:
                charSequence =  "Conference";
                break;
            case 3:
                charSequence =  "Training";
                break;
            case 4:
                charSequence =  "Meeting";
                break;
            case 5:
                charSequence =  "Seminar";
                break;
            case 6:
                charSequence =  "Funeral";
                break;
            case 7:
                charSequence =  "Party";
                break;
           /* case 7:
                charSequence =  "Press Conference";
                break;
            case 8:
                charSequence =  "Business";
                break;
            case 9:
                charSequence =  "Dinner";
                break;
            case 10:
                charSequence =  "Product Launch";
                break;
            case 11:
                charSequence =  "Birthday";
                break;
            case 12:
                charSequence =  "Wedding";
                break;*/
        }
        return charSequence;
    }
}
