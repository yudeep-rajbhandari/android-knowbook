package com.example.user.volleyjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Routineviewer extends AppCompatActivity {
    RoutineAdapter routineAdapter;
    Spinner spinner_routine;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routineviwer);

        final String get_faculty = getIntent().getStringExtra("get_faculty");
        final String get_semester = getIntent().getStringExtra("get_semester");
        Toast.makeText(Routineviewer.this, get_faculty, Toast.LENGTH_LONG).show();
        String routineurl = "http://knowbook.herokuapp.com/routine/getroutine/";
        spinner_routine = (Spinner) findViewById(R.id.spinner_routine);
        String day[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        final ArrayAdapter<String> spinnerArrayAdapter_day = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, day);
        spinnerArrayAdapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner_routine.setAdapter(spinnerArrayAdapter_day);
        listView = (ListView) findViewById(R.id.listview_routine);
        routineAdapter = new RoutineAdapter(this, R.layout.textlayout_routine);
        spinner_routine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Routineviewer.this, spinner_routine.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Routineviewer.this, "select somthing", Toast.LENGTH_SHORT).show();
            }
        });


        listView.setAdapter(routineAdapter);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, routineurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("<<<<<<<<<<<<<<<<<<<");
                        try {
                            System.out.println("<<<<<<<<<<<<<<<<<<<");
                            JSONArray jsonObject = response.getJSONArray("data");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                JSONObject object1 = jsonObject.getJSONObject(i);


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
                                System.out.println(spinner_routine.getSelectedItem().toString());
                                System.out.print(startingtime);
                                System.out.print(endingtime);
                                System.out.println(teacher);
                                System.out.println(subjectname);
                                System.out.println(subjectcode);
                                System.out.println(faculty);
                                System.out.println(get_faculty);
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
                                if ((get_faculty.equals(faculty)) && (get_semester.equals(semester)) && (spinner_routine.getSelectedItem().toString().equals(day))) {
                                    System.out.println("<<<<<getjherer");
                                    Routinedisplay routinedisplay = new Routinedisplay(faculty, semester, subjectname, subjectcode, day, date1, date3, teacher);
                                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<12312123");
                                    System.out.println(routinedisplay);
                                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<,");
                                    routineAdapter.add(routinedisplay);

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


                Toast.makeText(Routineviewer.this, "something went wrong", Toast.LENGTH_LONG).show();

            }

        });
        // System.out.println(mArrayList);
        MySingleton.getInstance(Routineviewer.this).addToRequestQueue(jsonObjectRequest);



    }
    public static Calendar toCalendar(final String iso8601string)
            throws ParseException {
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Invalid length", 0);
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        calendar.setTime(date);
        return calendar;
    }
}
