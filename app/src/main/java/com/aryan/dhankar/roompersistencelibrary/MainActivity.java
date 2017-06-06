package com.aryan.dhankar.roompersistencelibrary;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aryan.dhankar.roompersistencelibrary.entity.AppDatabase;
import com.aryan.dhankar.roompersistencelibrary.entity.User;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends LifecycleActivity implements View.OnClickListener{
    EditText firstname,lastname;
    Button insert,retreive,retreivedatausingRxjava;
    TextView datatoshow;
    String fname,lname;
    User user;
    public AppDatabase db;
    AppController Obj;
    GetDataViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable;
    long us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Obj= AppController.getInstance();
        //edit text
        firstname=(EditText)findViewById(R.id.firstname);
        lastname=(EditText)findViewById(R.id.lastname);
       db =Obj.getDb();

        mCompositeDisposable = new CompositeDisposable();

        //button

        insert=(Button)findViewById(R.id.insert);
        retreive=(Button)findViewById(R.id.retreive);
        retreivedatausingRxjava=(Button)findViewById(R.id.retreivedatausingRxjava);
        //texttview
        datatoshow=(TextView)findViewById(R.id.datatoshow);

        mViewModel = ViewModelProviders.of(this).get(GetDataViewModel.class);

        insert.setOnClickListener(this);
        retreive.setOnClickListener(this);
        retreivedatausingRxjava.setOnClickListener(this);
        //subscribeUiBooks();
    }

    private void subscribeUiBooks() {
mViewModel.books.observe(this,this::handleResponse);
        /*
        mViewModel.books.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@NonNull final List<User> books) {
                showBooksInUi(books, datatoshow);
            }
        });*/
    }
/*
    private static void showBooksInUi(final @NonNull List<User> books,
                                      final TextView booksTextView) {
        StringBuilder sb = new StringBuilder();

        for (User book : books) {
            sb.append(book.getFirstName()+" "+book.getLastName());
            sb.append("\n");

        }
        booksTextView.setText(sb.toString());
    }*/
    @Override
    public void onClick(View v) {
        if (v==insert){
            if (firstname.getText().toString().isEmpty()){
                firstname.setError("Empty Field");
            }else if (lastname.getText().toString().isEmpty()){
                lastname.setError("Empty Field");
            }else {
                fname=firstname.getText().toString();
                lname=lastname.getText().toString();
                user=new User();
                user.setFirstName(fname);
                user.setLastName(lname);

                new setdata().execute();
            }

        }

        if (v==retreive){
            datatoshow.setText("");
            subscribeUiBooks();
        }
        if (v==retreivedatausingRxjava){
            datatoshow.setText("");
            mCompositeDisposable.add(db.userDao().getrxall()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleError));

        }
    }

    private void handleResponse(List<User> users) {
        StringBuilder sb = new StringBuilder();

        for (User book : users) {
            sb.append(book.getFirstName()+" "+book.getLastName());
            sb.append("\n");

        }
        datatoshow.setText(sb.toString());

    }

    private void handleError(Throwable throwable) {
        TastyToast.makeText(MainActivity.this, "Error", TastyToast.LENGTH_LONG,
                TastyToast.SUCCESS);

    }



    private class setdata extends AsyncTask<Object, Object, Long>
    {

        ProgressDialog pd;


        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait...");
            pd.show();
        }

        @Override
        protected Long doInBackground(Object... params) {
            try{
            us=   db.userDao().insert(user);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            return us;
        }

        @Override
        protected void onPostExecute(Long s) {
            try{
                pd.dismiss();

                    TastyToast.makeText(MainActivity.this, "Successfully inserted"+us, TastyToast.LENGTH_LONG,
                            TastyToast.SUCCESS);



            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
