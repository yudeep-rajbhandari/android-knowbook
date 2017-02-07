package com.example.user.volleyjson;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

/**
 * Created by USER on 2/6/2017.
 */

public class Wednesday extends android.support.v4.app.Fragment {
    RoutineAdapter routineAdapter;
    ListView listView;

    Routineviewer2 routineviewer2;
    ListFragment listview;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,container,false);
        final String  get_faculty=getActivity().getIntent().getStringExtra("get_faculty");
        final String  get_semester=getActivity().getIntent().getStringExtra("get_semester");
        listView = (ListView)view.findViewById(R.id.listview_routine);
        routineAdapter = new RoutineAdapter(getContext(), R.layout.fragment1);
        listView.setAdapter(routineAdapter);
        String routineurl = "http://knowbook.herokuapp.com/routine/getroutine/";
        Toast.makeText(getContext(),get_faculty,Toast.LENGTH_SHORT);
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
                                // System.out.println(spinner_routine.getSelectedItem().toString());
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
                                if ((get_faculty.equals(faculty)) && (get_semester.equals(semester))&&(day.equals("Wednesday")) ) {
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


                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();

            }

        });
        // System.out.println(mArrayList);
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

        return view;







        //Toast.makeText(getApplicationContext(),"new",Toast.LENGTH_SHORT).show();





    }
}
