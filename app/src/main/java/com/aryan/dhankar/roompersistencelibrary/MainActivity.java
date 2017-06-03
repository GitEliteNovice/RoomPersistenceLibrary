package com.aryan.dhankar.roompersistencelibrary;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aryan.dhankar.roompersistencelibrary.entity.AppDatabase;
import com.aryan.dhankar.roompersistencelibrary.entity.User;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText firstname,lastname;
    Button insert,retreive;
    TextView datatoshow;
    String fname,lname;
    User user;
    List<User> result;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        //edit text
        firstname=(EditText)findViewById(R.id.firstname);
        lastname=(EditText)findViewById(R.id.lastname);

        //button
        insert=(Button)findViewById(R.id.insert);
        retreive=(Button)findViewById(R.id.retreive);

        //texttview
        datatoshow=(TextView)findViewById(R.id.datatoshow);

        insert.setOnClickListener(this);
        retreive.setOnClickListener(this);
    }

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
            new getdata().execute();
        }
    }

    public class getdata extends AsyncTask<String,String,String>
    {
        String response="nothing";
        ProgressDialog pd;


        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("Inserting");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                result= db.userDao().getAll();
                String data=" ";
                for (int i=0;i<result.size();i++){
                    data+=result.get(i).getFirstName()+" "+result.get(i).getLastName()+", ";
                }

                response=data;

            }catch (Exception e)
            {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
         pd.dismiss();
                datatoshow.setText(s);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    public class setdata extends AsyncTask<String,String,String>
    {
        String response="nothing";
        ProgressDialog pd;


        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                db.userDao().insert(user);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                pd.dismiss();
                TastyToast.makeText(MainActivity.this, "Successfully inserted", TastyToast.LENGTH_LONG,
                        TastyToast.SUCCESS);

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
