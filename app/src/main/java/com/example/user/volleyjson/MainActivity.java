package com.example.user.volleyjson;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    Button button;
    TextView textView;
    //String url = "http://192.168.100.4:8080/information/faculty";
    String url1 = "https://crackberry.com/sites/crackberry.com/files/styles/large/public/topic_images/2013/ANDROID.png?itok=xhm7jaxS";
    RequestQueue requestQueue;
    ImageView imageView;
    TextView faculty;
    TextView semester;
    TextView subject;
    Button button_json;
    ListView listView;
    ArrayList<String> mArrayList;
    ArrayAdapter adapter;
    Spinner spinner_faculty;
    Spinner spinner_semester;
    Button button_showsubject;
    String send_semester;
    Button button_showroutine;
    String send_faculty;
    Button profile;
    Button notes;
    Button question;
    Button getmysubject;
    Button getmyroutine;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // mArrayList = new ArrayList<String>();
//        adapter = new ArrayAdapter<String>(this,
//                R.layout.textlayout, R.id.subject, mArrayList);

        // listView = (ListView) findViewById(R.id.list_view);
        // listView.setAdapter(adapter);
       // profile=(Button)findViewById(R.id.profile);
        spinner_faculty = (Spinner) findViewById(R.id.spinner);
        spinner_semester = (Spinner) findViewById(R.id.spinner2);
        button_showsubject = (Button) findViewById(R.id.button_subject);
        getmyroutine=(Button)findViewById(R.id.see_my_routine);
        listView = (ListView) findViewById(R.id.list_view);
        String Faculty[] = {"CE", "CS"};
        String Semester[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        button_showroutine=(Button)findViewById(R.id.button_routine);
        notes=(Button )findViewById(R.id.button_note);
        question=(Button)findViewById(R.id.button_question);
        profile=(Button)findViewById(R.id.button_profile);
        getmysubject=(Button)findViewById(R.id.button_seemysubject);






     question.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             Intent intent = new Intent(MainActivity.this, question.class);
             intent.putExtra("get_faculty", spinner_faculty.getSelectedItem().toString());
             intent.putExtra("get_semester", spinner_semester.getSelectedItem().toString());
             startActivity(intent);

         }
     });


        button_showroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Routineviewer2.class);
                intent.putExtra("get_faculty", spinner_faculty.getSelectedItem().toString());
                intent.putExtra("get_semester", spinner_semester.getSelectedItem().toString());
                startActivity(intent);
            }
        });


        ArrayAdapter<String> spinnerArrayAdapter_faculty = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Faculty);
        spinnerArrayAdapter_faculty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner_faculty.setAdapter(spinnerArrayAdapter_faculty);

        ArrayAdapter<String> spinnerArrayAdapter_semester = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Semester);
        spinnerArrayAdapter_semester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner_semester.setAdapter(spinnerArrayAdapter_semester);



        button_showsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, showsubject.class);
                intent.putExtra("get_faculty", spinner_faculty.getSelectedItem().toString());
                intent.putExtra("get_semester", spinner_semester.getSelectedItem().toString());
                startActivity(intent);
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, notes.class);
                intent.putExtra("get_faculty", spinner_faculty.getSelectedItem().toString());
                intent.putExtra("get_semester", spinner_semester.getSelectedItem().toString());
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }






    /*   button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              ImageRequest imageRequest=new ImageRequest(url1, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        imageView.setImageBitmap(response);
                    }
                },0,0,ImageView.ScaleType.CENTER_CROP,null
                      , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("something went wrong");
                        error.printStackTrace();

                    }
                });
MySingleton.getInstance(MainActivity.this).addToRequestQueue(imageRequest);
            }

        });*/


}


