package com.example.user.volleyjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * Created by USER on 2/4/2017.
 */

public class RoutineAdapter extends ArrayAdapter {

    List list1= new ArrayList();
    public RoutineAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Routinedisplay object) {
        super.add(object);
        list1.add(object);
    }


    public int getCount() {
        return list1.size();
    }


    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        View row;
//        row=convertView;
        RoutineAdapter.Routine routineshow;
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.textlayout_routine,parent,false);
            routineshow =new RoutineAdapter.Routine();
            routineshow.startingtime=(TextView)convertView.findViewById(R.id.textView_startingtime);
            routineshow.endingtime=(TextView)convertView.findViewById(R.id.textView_endingtime);
            routineshow.subjectcode=(TextView)convertView.findViewById(R.id.textView_subjectcode);
            routineshow.subjectname=(TextView)convertView.findViewById(R.id.textView_subjectname);
            routineshow.teacher=(TextView)convertView.findViewById(R.id.textView_teachername);



            convertView.setTag(routineshow);


        }
        else{
            routineshow=(RoutineAdapter.Routine)convertView.getTag();
        }
       final Routinedisplay routinedisplay=(Routinedisplay)this.getItem(position);


        if(routinedisplay==null){
            routineshow.startingtime.setText("");
            routineshow.endingtime.setText("");
            routineshow.subjectcode.setText("");
            routineshow.subjectname.setText("");
            routineshow.teacher.setText("");


        }
            else {
            routineshow.startingtime.setText(routinedisplay.getStartingtime());
            routineshow.endingtime.setText(routinedisplay.getEndingtime());
            routineshow.subjectcode.setText(routinedisplay.getSubjectcode());
            routineshow.subjectname.setText(routinedisplay.getSubjectname());
            routineshow.teacher.setText(routinedisplay.getTeacher());
        }

        //bookshow.bookpdf.setText(bookdisplay.getPdf());

        //bookshow.bookpdf.setMovementMethod(LinkMovementMethod.getInstance());
//        subjectshow.credit.setText(subjectdisplay.getCredit());
//        subjectshow.syllabus.setText(subjectdisplay.getSyllabus());


        return convertView;
    }

    static class Routine{

        TextView startingtime,endingtime,teacher,subjectname,subjectcode;




    }

}
