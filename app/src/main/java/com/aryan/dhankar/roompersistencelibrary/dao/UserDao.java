package com.aryan.dhankar.roompersistencelibrary.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aryan.dhankar.roompersistencelibrary.entity.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by dell on 6/3/2017.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user")
    Flowable<List<User>> getrxall();

    @Insert
    long insert(User user);

}
