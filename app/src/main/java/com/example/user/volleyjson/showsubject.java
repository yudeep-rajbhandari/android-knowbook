package com.example.user.volleyjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class showsubject extends AppCompatActivity {

ListView listView;
    ArrayList mArrayList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsubject);
        mArrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
               R.layout.textlayout, R.id.credit, mArrayList);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        String get_faculty=getIntent().getStringExtra("get_faculty");
        String get_semester=getIntent().getStringExtra("get_semester");
        Toast.makeText(showsubject.this,get_faculty,Toast.LENGTH_LONG).show();
        String url = "http://192.168.100.4:8080/subject/getsubject/?Faculty="+get_faculty+"&Semester="+get_semester;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonObject = response.getJSONArray("data");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                JSONObject object1 = jsonObject.getJSONObject(i);
                                String faculty = object1.getString("subjectcode");
                                String semester = object1.getString("SubjectName");
                                String subject = object1.getString("Credit");
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                System.out.print(faculty);
                                System.out.print(semester);
                                System.out.print(subject);
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                             mArrayList.add(semester);
                                adapter.notifyDataSetChanged();
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
}
