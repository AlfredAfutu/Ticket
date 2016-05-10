package com.ticket.tiqeet.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ticket.tiqeet.Adapters.DataSetAdapter;
import com.ticket.tiqeet.DataObjects.EventDetails;
import com.ticket.tiqeet.R;

import java.util.ArrayList;

/**
 * Created by cted on 4/29/15.
 */
public class CategoriesFragment extends Fragment {
    private String TAG = getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<EventDetails> eventDetailsArrayList = new ArrayList<>();




    public CategoriesFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "In On create of fragment");
        for (int i = 0; i<= 6; i++){
            switch(i){
                case 0:
                    EventDetails eventDetails1 = new EventDetails("Fast & Furious 7", "Achimota, Accra", "Paid", "", "02-07-15", "Movies", "GHC30");
                    eventDetailsArrayList.add(eventDetails1);
                    break;
                case 1:
                    EventDetails eventDetails2 = new EventDetails("Crazy Spoons", "Ahodwo, Kumasi", "Free", "", "14-03-15", "Sports", "GHC30");
                    eventDetailsArrayList.add(eventDetails2);
                    break;
                case 2:
                    EventDetails eventDetails3 = new EventDetails("Fuse Music Festival", "West Hills, Accra", "Free", "", "30-03-15", "Cinema", "GHC30");
                    eventDetailsArrayList.add(eventDetails3);
                    break;
                case 3:
                    EventDetails eventDetails4= new EventDetails("Radio Show", "Naboso, Tarkwa", "Paid", "", "12-04-15", "Wedding", "GHC30");
                    eventDetailsArrayList.add(eventDetails4);
                    break;
                case 4:
                    EventDetails eventDetails5 = new EventDetails("Digitalization", "Bay, Takoradi", "Paid", "", "03-04-15", "Music Show", "GHC30");
                    eventDetailsArrayList.add(eventDetails5);
                    break;
                case 5:
                    EventDetails eventDetails6 = new EventDetails("Pent Week", "Legon, Accra", "Free", "", "17-05-15", "Sports", "GHC30");
                    eventDetailsArrayList.add(eventDetails6);
                    break;
                case 6:
                    EventDetails eventDetails7 = new EventDetails("Republic Hall Week", "Tech, Kumasi", "Free", "", "11-06-15", "Theatre", "GHC30");
                    eventDetailsArrayList.add(eventDetails7);
                    break;
            }


        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "In On create View of fragment");
        View fragmentView = inflater.inflate(R.layout.fragment_a, container, false);
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DataSetAdapter(eventDetailsArrayList);
        mRecyclerView.setAdapter(mAdapter);

        return fragmentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "In On Activity created of fragment");


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
