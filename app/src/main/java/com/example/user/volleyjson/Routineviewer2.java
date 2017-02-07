package com.example.user.volleyjson;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Routineviewer2 extends AppCompatActivity{
//Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    RoutineAdapter routineAdapter;
    //Spinner spinner_routine;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routineviewer2);
//        toolbar=(Toolbar)findViewById(R.id.app_bar);
//        setSupportActionBar(toolbar);
        final String get_faculty = getIntent().getStringExtra("get_faculty");
        final String get_semester = getIntent().getStringExtra("get_semester");
        Toast.makeText(Routineviewer2.this, get_faculty, Toast.LENGTH_LONG).show();
        String routineurl = "http://knowbook.herokuapp.com/routine/getroutine/";
//        listView = (ListView) findViewById(R.id.listview_routine);
        routineAdapter = new RoutineAdapter(this, R.layout.activity_routineviewer2);
//        listView.setAdapter(routineAdapter);

        ViewPager viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(),routineAdapter,this));

        tabLayout= (TabLayout) findViewById(R.id.tab1);
        tabLayout.setupWithViewPager(viewPager);



    }



}
