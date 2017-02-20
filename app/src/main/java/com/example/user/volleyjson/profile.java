package com.example.user.volleyjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class profile extends AppCompatActivity {
EditText name;
    Spinner spinner_profile_faculty;
    Spinner Spinner_profile_semester;
    Button Signup,view;
    DatabaseHelper mydb;
    TextView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(EditText)findViewById(R.id.editText_name);
        spinner_profile_faculty=(Spinner)findViewById(R.id.spinner_faculty_profile);
        Spinner_profile_semester=(Spinner)findViewById(R.id.spinner4);
        String Faculty[] = {"CE", "CS"};
        final String Semester[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Signup=(Button)findViewById(R.id.button_profile);
        mydb=new DatabaseHelper(this);
        view=(Button)findViewById(R.id.view);
        view1=(TextView)findViewById(R.id.textView14);

        Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> spinnerArrayAdapter_faculty = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Faculty);
        spinnerArrayAdapter_faculty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner_profile_faculty.setAdapter(spinnerArrayAdapter_faculty);

        ArrayAdapter<String> spinnerArrayAdapter_semester = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Semester);
        spinnerArrayAdapter_semester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        Spinner_profile_semester.setAdapter(spinnerArrayAdapter_semester);

                final Getprofile getprofile=mydb.getAllLabels();
        if(getprofile!=null){
            name.setText(getprofile.getName());
            int spinnerPosition = spinnerArrayAdapter_faculty.getPosition(getprofile.getFaculty());
            spinner_profile_faculty.setSelection(spinnerPosition);
            int spinnerPosition1 = spinnerArrayAdapter_semester.getPosition(getprofile.getSemester());
            Spinner_profile_semester.setSelection(spinnerPosition1);

        }





        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=  mydb.insertData(name.getText().toString(),spinner_profile_faculty.getSelectedItem().toString(),Spinner_profile_semester.getSelectedItem().toString());

                if(isInserted==true){

                    Toast.makeText(profile.this,"data inserted successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(profile.this, homeactivity.class);

                    startActivity(intent);
                }
                else{
                    Toast.makeText(profile.this,"already inserted",Toast.LENGTH_LONG).show();
                    System.out.println(getprofile.getFaculty());
                }

                String url = "http://knowbook.herokuapp.com/subject/getsubject/?Faculty=" +spinner_profile_faculty.getSelectedItem().toString() + "&Semester=" +Spinner_profile_semester.getSelectedItem().toString();
                String bookurl = "http://knowbook.herokuapp.com/books/Request2";

                String routineurl = "http://knowbook.herokuapp.com/routine/getroutine/";
                JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, routineurl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                try {
                                    System.out.println("<<<<<<<<<<<<<<<<<<<");

                                    JSONArray jsonObject = response.getJSONArray("data");
                                    DatabaseHelper mydb = new DatabaseHelper(getApplicationContext());
                                    DatabaseHelper.Routine routine=mydb.new Routine();
                                    if(routine!=null){
                                        mydb.clear2();
                                    }


                                    for (int i = 0; i < jsonObject.length(); i++) {
                                        System.out.println("<<<<<<<<<<<<<<<<<<<");
                                        JSONObject object1 = jsonObject.getJSONObject(i);

                                        String id = object1.getString("_id");

                                        String day = object1.getString("day");
                                        String startingtime = object1.getString("startingTime");
                                        String endingtime = object1.getString("endingTime");
                                        String teacher = object1.getString("Teacher");
                                        JSONObject picture = object1.getJSONObject("Subjectid");
                                        String subjectname = picture.getString("SubjectName");
                                        String subjectcode = picture.getString("subjectcode");
                                        String faculty = picture.getString("Faculties");
                                        String semester = picture.getString("Semester");



                                        System.out.println("<<<<<<<<<<<<<<<<<<<");
                                        System.out.println(day);
                                        // System.out.println(spinner_routine.getSelectedItem().toString());
                                        System.out.print(startingtime);
                                        System.out.print(endingtime);
                                        System.out.println(teacher);
                                        System.out.println(subjectname);
                                        System.out.println(subjectcode);
                                        System.out.println(faculty);

                                        System.out.println(semester);
                                        String date1=null;
                                        String date3 = null;

                                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                        df.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                                        try {
                                            Date date= (Date) df.parseObject(startingtime);
                                            Date date2=(Date) df.parseObject(endingtime);
                                            //date.getTimezoneOffset(TimeZone.getTimeZone("GMT+5:45"));
//                                   df.setTimeZone(TimeZone.getDefault());
//                                    String formattedDate = df.format(date);
//                                   System.out.println(formattedDate);

                                            date1= new SimpleDateFormat("HH:mm aa").format(date);
                                            date3=new SimpleDateFormat("HH:mm aa").format(date2);
                                            System.out.println(date1);
                                            System.out.println(date3);




                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        // javax.xml.bind.DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z");


                                        System.out.println("<<<<<<<<<<<<<<<<<<<from");
                                        if (faculty.equals(spinner_profile_faculty.getSelectedItem().toString()) && semester.equals(Spinner_profile_semester.getSelectedItem().toString()) ) {
                                            System.out.println("<<<<<getjherer");
                                            mydb.insertroutine(id, day, date1, date3, teacher, subjectcode, subjectname);


                                            System.out.println("<<<<<<<<<<<<<<<<<<<");
                                            System.out.println(id);
                                            System.out.print(day);
                                            System.out.print(date1);
                                            System.out.println(date3);
                                            System.out.println(teacher);
                                            System.out.println(subjectcode);
                                            System.out.println(subjectname);
                                            System.out.println("<<<<<<<<<<<<<<<<<<<");


                                        } else {
                                            System.out.println("something is wrong");
                                        }
                                    }
//                            System.out.println(mArrayList);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(profile.this, "something went wrong", Toast.LENGTH_LONG).show();

                    }

                });
                // System.out.println(mArrayList);
                MySingleton.getInstance(profile.this).addToRequestQueue(jsonObjectRequest2);


                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, bookurl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                try {
                                    System.out.println("<<<<<<<<<<<<<<<<<<<");
                                    JSONArray jsonObject = response.getJSONArray("data");
                                    DatabaseHelper mydb = new DatabaseHelper(getApplicationContext());
                                    DatabaseHelper.Books books=mydb.new Books();
                                    if(books!=null){
                                        mydb.clear1();
                                    }
                                    for (int i = 0; i < jsonObject.length(); i++) {
                                        System.out.println("<<<<<<<<<<<<<<<<<<<");
                                        JSONObject object1 = jsonObject.getJSONObject(i);

                                        String bookid = object1.getString("_id");
                                        String bookName = object1.getString("bookName");
                                        String writer = object1.getString("writer");
                                        String  booktype= object1.getString("booktype");
                                        String  price= object1.getString("price");
                                        String  availability= object1.getString("availability");
                                        String publication = object1.getString("publication");
                                        String rackNumber = object1.getString("rackNumber");
                                        JSONObject picture=object1.getJSONObject("pdf");
                                        String pdf=picture.getString("url");
                                        JSONObject subjectid=object1.getJSONObject("Subjectid");
                                        String subid=subjectid.getString("_id");
                                        String faculty=subjectid.getString("Faculties");
                                        String semester=subjectid.getString("Semester");

                                        System.out.println("<<<<<<<<<<<<< i m here");

                                        if(faculty.equals(spinner_profile_faculty.getSelectedItem().toString()) && semester.equals(Spinner_profile_semester.getSelectedItem().toString())) {
                                            mydb.insertbook(bookid, bookName, writer, booktype, price, availability, publication, rackNumber, pdf, subid);


                                            System.out.println("<<<<<<<<<<<<<<<<<<<");
                                            System.out.println(bookName);
                                            System.out.print(writer);
                                            System.out.print(booktype);
                                            System.out.println(price);
                                            System.out.println(availability);
                                            System.out.println(publication);
                                            System.out.println(pdf);
                                            System.out.println("<<<<<<<<<<<<<<<<<<<");
                                        }

                                    }
//                            System.out.println(mArrayList);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(profile.this, "something went wrong", Toast.LENGTH_LONG).show();

                    }

                });
                // System.out.println(mArrayList);
                MySingleton.getInstance(profile.this).addToRequestQueue(jsonObjectRequest1);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                try {
                                    System.out.println("<<<<<<<<<<<<<<<<<<<");
                                    JSONArray jsonObject = response.getJSONArray("data");
                                    DatabaseHelper mydb = new DatabaseHelper(getApplicationContext());
                                    DatabaseHelper.Subject subject=mydb.new Subject();
                                    if(subject!=null){
                                        mydb.clear();
                                    }
                                    for (int i = 0; i < jsonObject.length(); i++) {
                                        System.out.println("<<<<<<<<<<<<<<<<<<<");
                                        JSONObject object1 = jsonObject.getJSONObject(i);

                                        String subjectcode = object1.getString("subjectcode");
                                        String subjectid = object1.getString("_id");
                                        String subjectname = object1.getString("SubjectName");
                                        String credit = object1.getString("Credit");
                                        JSONObject picture = object1.getJSONObject("picture");

                                        String syllabus = picture.getString("url");



                                        mydb.insertsubject(subjectid, subjectname, subjectcode, credit, syllabus,spinner_profile_faculty.getSelectedItem().toString() , Spinner_profile_semester.getSelectedItem().toString());


                                        System.out.println("<<<<<<<<<<<<<<<<<<<");
                                        System.out.println(subjectid);
                                        System.out.print(subjectcode);
                                        System.out.print(subjectname);
                                        System.out.println(credit);
                                        System.out.println(syllabus);
                                        System.out.println("<<<<<<<<<<<<<<<<<<<");


                                        // mArrayList.add(subjectname);
                                        //adapter.notifyDataSetChanged();
                                    }
//                            System.out.println(mArrayList);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(profile.this, "something went wrong", Toast.LENGTH_LONG).show();

                    }

                });
                // System.out.println(mArrayList);
                MySingleton.getInstance(profile.this).addToRequestQueue(jsonObjectRequest);


            }







        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
