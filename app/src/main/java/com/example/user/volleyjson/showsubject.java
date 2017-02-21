package com.example.user.volleyjson;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

public class showsubject extends AppCompatActivity {


    ListView listView;
    //ArrayList mArrayList;
    //ArrayAdapter adapter;
    SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsubject);


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mArrayList = new ArrayList<String>();
//        adapter = new ArrayAdapter<String>(this,
//               R.layout.textlayout, R.id.credit, mArrayList);
        listView = (ListView) findViewById(R.id.list_view);


        final String get_faculty = getIntent().getStringExtra("get_faculty");
        final String get_semester = getIntent().getStringExtra("get_semester");
        Toast.makeText(showsubject.this, get_faculty, Toast.LENGTH_LONG).show();
        System.out.println("<<<<<<<<<<<<<<<<<<<");
        String url = "http://knowbook.herokuapp.com/subject/getsubject/?Faculty=" + get_faculty + "&Semester=" + get_semester;
        subjectAdapter = new SubjectAdapter(this, R.layout.textlayout);


        listView.setAdapter(subjectAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subjectdisplay item = (Subjectdisplay) listView.getItemAtPosition(i);
                String hope = item.getSubjectid();
                Intent intent1 = new Intent(showsubject.this, showbooks.class);
                intent1.putExtra("subjectcode", item.getSubjectid());
                //intent.putExtra("get_semester",spinner_semester.getSelectedItem().toString());
                startActivity(intent1);
                //  long item=listView.getItemIdAtPosition(i);
                System.out.println("<<<<<<<<<<<<123");


                System.out.println(hope);
                System.out.println("<<<<<<<<<<<<<<<,");
                //Toast.makeText(showsubject.this,item,Toast.LENGTH_SHORT).show();
            }
        });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
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

                                String subjectcode = object1.getString("subjectcode");
                                String subjectid = object1.getString("_id");
                                String subjectname = object1.getString("SubjectName");
                                String credit = object1.getString("Credit");
                                JSONObject picture = object1.getJSONObject("picture");

                                String syllabus = picture.getString("url");

                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                System.out.println(subjectid);
                                System.out.print(subjectcode);
                                System.out.print(subjectname);
                                System.out.println(credit);
                                System.out.println(syllabus);
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                Subjectdisplay subjectdisplay = new Subjectdisplay(subjectcode, subjectname, credit, syllabus, subjectid);
                                subjectAdapter.add(subjectdisplay);

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


                Toast.makeText(showsubject.this, "something went wrong", Toast.LENGTH_LONG).show();

            }

        });
        // System.out.println(mArrayList);
        MySingleton.getInstance(showsubject.this).addToRequestQueue(jsonObjectRequest);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
