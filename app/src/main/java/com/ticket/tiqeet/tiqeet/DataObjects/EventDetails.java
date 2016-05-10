package com.ticket.tiqeet.DataObjects;

/**
 * Created by cted on 5/30/15.
 */
public class EventDetails {

    public String eventTitle, eventLocation, eventTag, eventPhoto, eventDate, eventCategory, eventAmount;

    public EventDetails(String eventTitle, String eventLocation, String eventTag, String eventPhoto, String eventDate, String eventCategory, String eventAmount){
        this.eventTitle = eventTitle;
        this.eventLocation = eventLocation;
        this.eventTag = eventTag;
        this.eventPhoto = eventPhoto;
        this.eventDate = eventDate;
        this.eventCategory = eventCategory;
        this.eventAmount = eventAmount;
    }

 /*   public String getEventTitle(){

        return eventTitle;
    }

    public void setEventTitle(String eventTitle){
        this.eventTitle = eventTitle
    }*/
}
