package com.example.user.volleyjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 1/30/2017.
 */

public class SubjectAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public SubjectAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Subjectdisplay object) {
        super.add(object);
        list.add(object);
    }


    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        Subjectshow subjectshow;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.textlayout,parent,false);
            subjectshow =new Subjectshow();
            subjectshow.subjectcode=(TextView)row.findViewById(R.id.subjectcode);
            subjectshow.subjectname=(TextView)row.findViewById(R.id.subjectname);
            subjectshow.credit=(TextView)row.findViewById(R.id.credit);
            subjectshow.syllabus=(TextView)row.findViewById(R.id.syllabus);

            row.setTag(subjectshow);


        }
else{
            subjectshow=(Subjectshow)row.getTag();
        }
        Subjectdisplay subjectdisplay=(Subjectdisplay)this.getItem(position);
        subjectshow.subjectcode.setText(subjectdisplay.getSubjectcode());
        subjectshow.subjectname.setText(subjectdisplay.getSubjectname());
        subjectshow.credit.setText(subjectdisplay.getCredit());
        subjectshow.syllabus.setText(subjectdisplay.getSyllabus());


        return row;
    }

    static class Subjectshow{

    TextView subjectcode,subjectname,credit,syllabus;



    }
}
