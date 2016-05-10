package com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ticket.tiqeet.R;
import com.ticket.tiqeet.tiqeet.com.ticket.tiqeet.DataObjects.EventDetails;

import java.util.ArrayList;

/**
 * Created by cted on 5/30/15.
 */
public class DataSetAdapter extends RecyclerView.Adapter<com.ticket.tiqeet.Adapters.DataSetAdapter.ViewHolder> {
    private ArrayList<EventDetails> eventDataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder /*extends RecyclerView.ViewHolde*/{

        public TextView eventTitleTextView, eventLocationTextView, eventTagTextView;
        public Button purchaseTicketButton;
        public ImageView eventPhotoImageView;
        public EventDetails eventDetail;
        public ViewHolder(View eventItemView){
            super(eventItemView);
            eventTitleTextView = (TextView) eventItemView.findViewById(R.id.eventTitle);
            eventLocationTextView = (TextView) eventItemView.findViewById(R.id.location);
            eventTagTextView = (TextView) eventItemView.findViewById(R.id.tag);
            purchaseTicketButton = (Button) eventItemView.findViewById(R.id.purchase_ticket);
            eventPhotoImageView = (ImageView) eventItemView.findViewById(R.id.event_photo);
        }

        public void bindEvent(EventDetails event) {
            eventDetail = event;
            eventTitleTextView.setText(eventDetail.eventTitle);
            eventLocationTextView.setText(eventDetail.eventLocation);
            eventTagTextView.setText(eventDetail.eventTag);
        }

    }

    public DataSetAdapter(ArrayList<EventDetails> dataSet) {
        eventDataSet = dataSet;
    }

    @Override
    public com.ticket.tiqeet.Adapters.DataSetAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_items, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

   /* @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }*/

   @Override
    public void onBindViewHolder(com.ticket.tiqeet.Adapters.DataSetAdapter.ViewHolder viewHolder, int i) {
            EventDetails eventDetails = eventDataSet.get(i);
            viewHolder.bindEvent(eventDetails);
    }

    @Override
    public int getItemCount() {
        return eventDataSet.size();
    }
}
