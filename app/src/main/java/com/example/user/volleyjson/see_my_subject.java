package com.example.user.volleyjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class see_my_subject extends AppCompatActivity {
    ListView listView;
   SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_subject);
        listView=(ListView)findViewById(R.id.see_my_subejct);

        subjectAdapter = new SubjectAdapter(this, R.layout.textlayout);


        Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView.setAdapter(subjectAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subjectdisplay item = (Subjectdisplay) listView.getItemAtPosition(i);
                String hope = item.getSubjectid();
                Intent intent1 = new Intent(see_my_subject.this, see_my_books.class);
                System.out.println(hope);
               intent1.putExtra("subjectcode", item.getSubjectid());
                startActivity(intent1);
               // intent.putExtra("get_semester",spinner_semester.getSelectedItem().toString());startActivity(intent1);
                //  long item=listView.getItemIdAtPosition(i);
                System.out.println("<<<<<<<<<<<<123");


                System.out.println(hope);
                System.out.println("<<<<<<<<<<<<<<<,");
                //Toast.makeText(showsubject.this,item,Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        ArrayList<DatabaseHelper.Subject> subList=db.getSubjects();

        for(DatabaseHelper.Subject subject:subList){
            subject.printSubject();
            Subjectdisplay subjectdisplay = new Subjectdisplay(subject.sub_code, subject.sub_name, subject.credit, subject.syllabus, subject.id);
            subjectAdapter.add(subjectdisplay);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}
