package com.example.user.volleyjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.HashMap;

public class question extends AppCompatActivity {
    Spinner spinner;
    HashMap<String ,String> hmLang = new HashMap<String,String>();
    ListView listView;
   QuestionAdapter questionAdapter;
    notesgetter getnotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        spinner=(Spinner)findViewById(R.id.spinner_question);
        String get_faculty = getIntent().getStringExtra("get_faculty");
        String get_semester = getIntent().getStringExtra("get_semester");
        final ArrayList<String> mArrayList = new ArrayList<String>();

        Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







        listView=(ListView)findViewById(R.id.listview_question);

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mArrayList);
        spinner.setAdapter(dataAdapter);
        if(get_faculty.equals("myfaculty") && get_semester.equals("mysemester")){
            DatabaseHelper db=new DatabaseHelper(getApplicationContext());
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    questionAdapter = new QuestionAdapter(question.this, R.layout.textlayout_question);
                    listView.setAdapter(questionAdapter);
                    String reqsubjectcode=spinner.getSelectedItem().toString();
                    DatabaseHelper db=new DatabaseHelper(getApplicationContext());
                    ArrayList<DatabaseHelper.Questions> quelist=db.getQuestion1(reqsubjectcode);
                    for(DatabaseHelper.Questions questions:quelist){

                        questiongetter getque = new questiongetter(questions.types, questions.year,questions.link);

                        questionAdapter.add(getque);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    System.out.println("<<<<<<<<<<<");

                }
            });

            ArrayList<DatabaseHelper.Questions> quelist=db.getQuestions();
            for(DatabaseHelper.Questions questions:quelist){
                //subject.printSubject();
                //  Routinedisplay routinedisplay = new Routinedisplay(notes.ID, notes.subject_name,notes.subject_code, notes.topic, notes.link);
                if (mArrayList.contains(questions.subject_code)) {
                    System.out.println("<<<<<<<<<");
                } else {
                    mArrayList.add(questions.subject_code);
                    // hmLang.put(subjectcode, subjectid);
                    dataAdapter.notifyDataSetChanged();
                }
            }
        }
        else {


            String url = "http://knowbook.herokuapp.com/pastquestion/Requestsubject/?Faculty=" + get_faculty + "&Semester=" + get_semester;
            //String notesurl = "http://knowbook.herokuapp.com/notes/Requests/"+key;
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String itemsel = adapterView.getItemAtPosition(i).toString();
                    questionAdapter = new QuestionAdapter(question.this, R.layout.textlayout_question);
                    listView.setAdapter(questionAdapter);

                    String key = hmLang.get(itemsel);
                    String notesurl = "http://knowbook.herokuapp.com/pastquestion/Requests/" + key;


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, notesurl, null,
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

                                            String years = object1.getString("Year");
                                            String types = object1.getString("Types");



                                            JSONObject picture = object1.getJSONObject("pdf");
                                            String pdf = picture.getString("url");


                                            System.out.println("<<<<<<<<<<<<<<<<<<<");
                                            System.out.print(years);
                                            System.out.println(pdf);
                                           questiongetter getquestion = new questiongetter(types,years,pdf);

                                            questionAdapter.add(getquestion);


//                                        mArrayList.add(subjectcode);
//                                        hmLang.put(subjectcode,subjectid);
//                                        dataAdapter.notifyDataSetChanged();
                                        }
//                            System.out.println(mArrayList);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            Toast.makeText(question.this, "something went wrong", Toast.LENGTH_LONG).show();

                        }

                    });
                    // System.out.println(mArrayList);
                    MySingleton.getInstance(question.this).addToRequestQueue(jsonObjectRequest);


                    Toast.makeText(question.this, key, Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(question.this, "select somthing", Toast.LENGTH_SHORT).show();

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

                                    JSONObject subject = object1.getJSONObject("Subjectid");
                                    String subjectcode = subject.getString("subjectcode");

                                    //String subjectcode = object1.getString("subjectcode");
                                    String subjectid = subject.getString("_id");


                                    System.out.println("<<<<<<<<<<<<<<<<<<<");
                                    System.out.print(subjectcode);
                                    System.out.println(subjectid);


                                    if (mArrayList.contains(subjectcode)) {
                                        System.out.println("<<<<<<<<<");
                                    } else {
                                        mArrayList.add(subjectcode);
                                        hmLang.put(subjectcode, subjectid);
                                        dataAdapter.notifyDataSetChanged();
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


                    Toast.makeText(question.this, "something went wrong", Toast.LENGTH_LONG).show();

                }

            });
            // System.out.println(mArrayList);
            MySingleton.getInstance(question.this).addToRequestQueue(jsonObjectRequest);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
