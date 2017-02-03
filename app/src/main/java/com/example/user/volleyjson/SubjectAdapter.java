package com.example.user.volleyjson;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            //subjectshow.syllabus=(TextView)row.findViewById(R.id.syllabus);
            subjectshow.download_syllabus=(Button)row.findViewById(R.id.button_syllabus);

            row.setTag(subjectshow);


        }
else{
            subjectshow=(Subjectshow)row.getTag();
        }
        final Subjectdisplay subjectdisplay=(Subjectdisplay)this.getItem(position);
        subjectshow.subjectcode.setText(subjectdisplay.getSubjectcode());
        subjectshow.subjectname.setText(subjectdisplay.getSubjectname());
        subjectshow.credit.setText(subjectdisplay.getCredit());
        //subjectshow.syllabus.setText(subjectdisplay.getSyllabus());
        subjectshow.download_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"downloading..",Toast.LENGTH_SHORT).show();
                DownloadManager downloadManager=(DownloadManager)getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(subjectdisplay.getSyllabus());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("syllabus_"+subjectdisplay.getSubjectname());
                request.setDescription("Downloading");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //request.setDestinationUri(Uri.parse("file://" + folderName + "/myfile.mp3"));
                downloadManager.enqueue(request);
            }
        });


        return row;
    }

    static class Subjectshow{

    TextView subjectcode,subjectname,credit,syllabus;
        Button download_syllabus;



    }
}
