package com.aryan.dhankar.roompersistencelibrary.entity;

import android.arch.persistence.room.Database;

import android.arch.persistence.room.RoomDatabase;


import com.aryan.dhankar.roompersistencelibrary.dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

}
