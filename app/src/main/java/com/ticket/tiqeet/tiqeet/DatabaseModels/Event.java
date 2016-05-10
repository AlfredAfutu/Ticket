package com.ticket.tiqeet.DatabaseModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cted on 10/4/15.
 */
@DatabaseTable(tableName = "events")
public class Event {
    private static final String TAG = "Event";
    public static Dao<Event, Integer> eventDao;

    @DatabaseField(id = true)
    public String eventId;

    @DatabaseField()
    public String eventName;

    @DatabaseField()
    public String eventTitle;

    @DatabaseField()
    public String eventLatitude;

    @DatabaseField()
    public String eventLongitude;

    @DatabaseField()
    public String eventDate;

    @DatabaseField()
    public String eventTag;

    @DatabaseField()
    public String eventBlurb;

    @DatabaseField()
    public String eventPoster;

    @DatabaseField()
    public String eventBanner;

    @DatabaseField()
    public String eventAmount;

    static public Event getEvent(String eventId){
        try{
            List<Event> event = eventDao.queryBuilder().where().eq("eventId", eventId).query();
            return event.get(0);
        }catch(SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public List<Event> getEventsByCategory(String category){
        try{
            List<Event> eventList = eventDao.queryBuilder().where().eq("eventCategory", category).query();
            return eventList;
        }catch(SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public boolean deleteEvent(){
        try{
            eventDao.delete(this);
            return true ;
        }catch (SQLException exception){
            exception.printStackTrace();
            return false ;
        }
    }

    public static boolean deleteAllEventsByCategory(String category){

        try{
            DeleteBuilder<Event, Integer> deleteBuilder = eventDao.deleteBuilder();
            deleteBuilder.where().eq("eventCategory", category);
            deleteBuilder.delete();
            return true;
        }catch(SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAllEvents(){
        DeleteBuilder<Event, Integer> deleteBuilder = eventDao.deleteBuilder();
        try {

            deleteBuilder.delete();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
