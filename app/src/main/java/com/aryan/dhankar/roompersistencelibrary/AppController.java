package com.aryan.dhankar.roompersistencelibrary;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.aryan.dhankar.roompersistencelibrary.entity.AppDatabase;

/**
 * Created by pooja on 6/5/2017.
 */

public class AppController extends Application {
    private static AppController mInstance;
    AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

    }
    public AppDatabase getDb(){
        return db;
    }
    public static synchronized AppController getInstance() {
        return mInstance;
    }

}
