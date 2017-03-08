package com.example.user.volleyjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ashchal on 2/20/17.
 */

public class homeactivity  extends AppCompatActivity{

    Button profile;
    Button notes;
    Button question;
    Button getmysubject;
    Button getmyroutine;
    Button exploreall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.homeactivity);
        Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        profile = (Button) findViewById(R.id.button_profile);
        notes =(Button) findViewById(R.id.button_note);
        getmysubject = (Button) findViewById(R.id.button_seemysubject);
        getmyroutine =  (Button) findViewById(R.id.see_my_routine);
        exploreall = (Button) findViewById(R.id.explore);
        question=(Button)findViewById(R.id.button_myquestion);

//        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<");
//        System.out.println(day);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeactivity.this,profile.class);
                startActivity(intent);
            }
        });

        getmysubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(homeactivity.this,see_my_subject.class);
                startActivity(intent);
            }
        });

        getmyroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeactivity.this,Routineviewer2.class);
                intent.putExtra("get_faculty", "myfaculty");
                intent.putExtra("get_semester", "mysemester");

                startActivity(intent);
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeactivity.this,notes.class);
                intent.putExtra("get_faculty", "myfaculty");
                intent.putExtra("get_semester", "mysemester");

                startActivity(intent);

            }
        });
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeactivity.this,question.class);
                intent.putExtra("get_faculty", "myfaculty");
                intent.putExtra("get_semester", "mysemester");

                startActivity(intent);

            }
        });


        exploreall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeactivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
