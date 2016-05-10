package com.ticket.tiqeet.DatabaseModels;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "tiqeet.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    //private Dao<, Integer> messageDao = null;

    private Dao<User, Integer> userDao = null;
    private Dao<Event, Integer> eventDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            //Log.i(DatabaseHelper.class.getName(), "onCreate");
            //tagsDao = DaoManager.createDao(connectionSource, Tags.class);

            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Event.class);


            //Log.i("@databaseHelper","tables created, req, resp, user");
            //Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: ");
        } catch (SQLException e) {
            //Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            //Log.e("DataBaseHelper", "oldVersion is >>" + oldVersion+" newVersion is >>" + newVersion );
           /* if(oldVersion < 2) {
                //Dao<User, Integer> dao = getUserDao();

                getUserDao().executeRaw("ALTER TABLE `users` ADD COLUMN isGooglePlusUser BOOLEAN DEFAULT 0;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN date String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN date String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN date String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN inviteLink String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN shareLink String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN bitmapString String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN inviteLink String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN shareLink String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN bitmapString String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN inviteLink String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN shareLink String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN bitmapString String;");
                getPhotoDao().executeRaw("ALTER TABLE `photo_items` ADD COLUMN num_tags long;");
                getMyStreamsPhotoDao().executeRaw("ALTER TABLE `my_streams_photo_items` ADD COLUMN num_tags long;");
                getStreamMembersPhotoDao().executeRaw("ALTER TABLE `stream_members_streams_photo_items` ADD COLUMN num_tags long;");
                TableUtils.createTable(connectionSource, PhotoComment.class);
                TableUtils.createTable(connectionSource, Activities.class);
                TableUtils.createTable(connectionSource, PhotoTags.class);
                // TableUtils.createTable(connectionSource, PhotoTag.class);
            }

            else if(oldVersion == 2){
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN date String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN date String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN date String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN inviteLink String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN shareLink String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN bitmapString String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN inviteLink String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN shareLink String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN bitmapString String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN inviteLink String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN shareLink String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN bitmapString String;");
                getPhotoDao().executeRaw("ALTER TABLE `photo_items` ADD COLUMN num_tags long;");
                getMyStreamsPhotoDao().executeRaw("ALTER TABLE `my_streams_photo_items` ADD COLUMN num_tags long;");
                getStreamMembersPhotoDao().executeRaw("ALTER TABLE `stream_members_streams_photo_items` ADD COLUMN num_tags long;");
                TableUtils.createTable(connectionSource, PhotoComment.class);
                TableUtils.createTable(connectionSource, Activities.class);
                TableUtils.createTable(connectionSource, PhotoTags.class);
                //TableUtils.createTable(connectionSource, PhotoTag.class);

            }

            else if(oldVersion == 3){
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN inviteLink String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN shareLink String;");
                getStreamDao().executeRaw("ALTER TABLE `streams` ADD COLUMN bitmapString String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN inviteLink String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN shareLink String;");
                getMyStreamsDao().executeRaw("ALTER TABLE `myStreams` ADD COLUMN bitmapString String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN inviteLink String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN shareLink String;");
                getStreamMembersStreamsDao().executeRaw("ALTER TABLE `streamMembersStreams` ADD COLUMN bitmapString String;");
                getPhotoDao().executeRaw("ALTER TABLE `photo_items` ADD COLUMN num_tags long;");
                getMyStreamsPhotoDao().executeRaw("ALTER TABLE `my_streams_photo_items` ADD COLUMN num_tags long;");
                getStreamMembersPhotoDao().executeRaw("ALTER TABLE `stream_members_streams_photo_items` ADD COLUMN num_tags long;");
                TableUtils.createTable(connectionSource, Activities.class);
                TableUtils.createTable(connectionSource, PhotoTags.class);
                //TableUtils.createTable(connectionSource, PhotoTag.class);
            }*/
            //Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			/*TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, PhotoItem.class, true );
            TableUtils.dropTable(connectionSource, MyStreamsPhotoItem.class, true );
            TableUtils.dropTable(connectionSource, StreamMembersPhotoItem.class, true );
            TableUtils.dropTable(connectionSource, Stream.class, true );
            TableUtils.dropTable(connectionSource, MyStreams.class, true );
            TableUtils.dropTable(connectionSource, StreamMembersStreams.class, true );
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);*/
          /*  switch (oldVersion) {
                case 1:
                    updateFromVersion1(db, connectionSource, oldVersion, newVersion);
                    break;
                case 2:
                    updateFromVersion2(db, connectionSource, oldVersion, newVersion);
                    break;
                default:
                    // no updates needed
                    break;
            }*/
        } catch (Exception e) {
            //Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

   /* private void updateFromVersion1(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // do some stuff here
        onUpgrade(database, connectionSource, oldVersion + 1, newVersion);
    }

    private void updateFromVersion2(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // do some stuff here
        onUpgrade(database, connectionSource, oldVersion + 1, newVersion);
    }*/
    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public Dao<Event, Integer> getEventDao() throws SQLException {
        if (eventDao == null) {
            eventDao = getDao(Event.class);
        }
        return eventDao;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
        eventDao = null ;

    }
}
