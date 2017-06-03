package com.aryan.dhankar.roompersistencelibrary.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aryan.dhankar.roompersistencelibrary.entity.User;

import java.util.List;

/**
 * Created by dell on 6/3/2017.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insert(User user);

}
