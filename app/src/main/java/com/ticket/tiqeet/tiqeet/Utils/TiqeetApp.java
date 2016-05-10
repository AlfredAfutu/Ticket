package com.ticket.tiqeet.Utils;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.ticket.tiqeet.DatabaseModels.DatabaseHelper;
import com.ticket.tiqeet.DatabaseModels.Event;
import com.ticket.tiqeet.DatabaseModels.User;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by cted on 10/4/15.
 */
public class TiqeetApp extends Application {
    public static String USER_FBK_ACCOUNT_TOKEN = null ;
    public static TiqeetApp app ;

    private static final String PROPERTY_ID = "UA-49882267-2";

    private final String TAG = getClass().getName() ;

    public Dao<User, Integer> userDao ;

    public Dao<Event, Integer> eventDao ;

    public TiqeetApp() {
        super();
    }

    public TiqeetApp getAppInstance(){return app;}

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        FacebookSdk.sdkInitialize(app);
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(this,
                DatabaseHelper.class);
        try {
            userDao = databaseHelper.getUserDao() ;
            User.userDao = userDao ;

            eventDao = databaseHelper.getEventDao() ;
            Event.eventDao = eventDao ;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearApplicationData(Context context) {
        try {
            File cacheDir = context.getCacheDir();
            if (cacheDir != null && cacheDir.isDirectory()) {
                deleteDir(cacheDir);
            }
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


}
