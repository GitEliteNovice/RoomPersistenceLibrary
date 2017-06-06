package com.aryan.dhankar.roompersistencelibrary;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.aryan.dhankar.roompersistencelibrary.entity.AppDatabase;
import com.aryan.dhankar.roompersistencelibrary.entity.User;

import java.util.List;

/**
 * Created by pooja on 6/5/2017.
 */

public class GetDataViewModel extends AndroidViewModel {

    public final LiveData<List<User>> books;
    AppController Obj;
    private AppDatabase mDb;

    public GetDataViewModel(Application application) {
        super(application);
Obj=AppController.getInstance();
        mDb=Obj.getDb();
        // Books is a LiveData object so updates are observed.
        books = mDb.userDao().getAll();
    }
}
