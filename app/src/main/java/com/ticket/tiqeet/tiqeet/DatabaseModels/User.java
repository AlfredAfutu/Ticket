package com.ticket.tiqeet.DatabaseModels;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cted on 10/4/15.
 */
@DatabaseTable(tableName = "users")
public class User {
    private static  final String TAG = "User" ;
    public static Dao<User, Integer> userDao ;

    @DatabaseField(id = true )
    public String userId ;

    @DatabaseField()
    public String fName ;

    @DatabaseField()
    public String lName ;

    @DatabaseField()
    public String email ;

    @DatabaseField()
    public boolean isThisUser;

    @DatabaseField()
    public boolean isLoggedOut;

    @DatabaseField()
    public boolean isFbkUser;

    @DatabaseField()
    public boolean isGooglePlusUser;

    /*@DatabaseField
    public String profilePhoto ;*/

    public boolean save(){
        //Log.e(TAG, "this user abt to save");
        if( this.fName!=null && this.fName.contains("https://fbcdn-profile")){
            //dont save it, there is a problem
            // Log.e(TAG, "this user can not be saved it has errors in fName");
            //return false ;
        }
        //Log.e(TAG, "this user abt to save 1");
        try {
            //Log.e(TAG, "this user abt to save 2");
            Dao.CreateOrUpdateStatus createOrUpdateStatus = userDao.createOrUpdate(this) ;
            // Log.e(TAG, "this user abt to save 3");
            return (createOrUpdateStatus.isCreated() || createOrUpdateStatus.isUpdated()) ;

        } catch (Exception e) {
            //Log.e(TAG, "this user abt to save 4");
            e.printStackTrace();
            return false ;
        }
    }

    static public User getThisUser(){
        try {
            List<User> userList = userDao.queryBuilder().where().eq("isThisUser", true).query();
            return userList.get(0) ;
        } catch ( Exception e) {
            e.printStackTrace();
            //Log.e(TAG,  + "> doesnt exist in db") ;
            return null;
        }
    }

    static public List<User> getUsers(){
        try {
            List<User> userList = userDao.queryBuilder().query();
            return userList ;
        } catch ( Exception e) {
            e.printStackTrace();
            //Log.e(TAG,  + "> doesnt exist in db") ;
            return null;
        }
    }

    static public User getUser(String id){
        try {
            List<User> userList = userDao.queryBuilder().where().eq("userId", id).query();
            return userList.get(0) ;
        } catch ( Exception e) {
            //e.printStackTrace();
            //Log.e(TAG, id + "> doesnt exist in db") ;
            User newUser = new User() ;
            newUser.userId = id ;
            return newUser;
        }

    }

    public String getUserInitials(){
        String initials = "";
        if( this.fName != null && !this.fName.equalsIgnoreCase("") ){
            //return fName.substring(0,1).toUpperCase() ;
            initials = fName.substring(0,1).toUpperCase() ;
        }
        else if( this.lName != null && !this.lName.equalsIgnoreCase("") ){
            //return lName.substring(0,1).toUpperCase() ;
            initials = lName.substring(0,1).toUpperCase() ;
        }

        return initials;
        //return fName.substring(0,1).toUpperCase() ;
        //return "S".toUpperCase() ;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                builder.append(field.getName() + "=" + field.get(this) + ",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }

    public boolean delete(){
        try{
            userDao.delete(this);
            return true ;
        }catch (Exception exe){
            exe.printStackTrace();
            return false ;
        }
    }

    public static boolean deleteAllUsers(){
        DeleteBuilder<User, Integer> deleteBuilder = userDao.deleteBuilder();
        try {

            deleteBuilder.delete();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}