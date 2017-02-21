package com.example.user.volleyjson;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
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
 * Created by USER on 2/21/2017.
 */

public class QuestionAdapter extends ArrayAdapter{

    List list1= new ArrayList();
    public QuestionAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(questiongetter object) {
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
        QuestionAdapter.Book bookshow;
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.textlayout_question,parent,false);
            bookshow =new QuestionAdapter.Book();
            bookshow.year=(TextView)convertView.findViewById(R.id.year);
            bookshow.types=(TextView)convertView.findViewById(R.id.types);

            //bookshow.bookpdf=(TextView)convertView.findViewById(R.id.pdf1);
            bookshow.downloadpdf=(Button)convertView.findViewById(R.id.button_question_pdf);

            //subjectshow.credit=(TextView)row.findViewById(R.id.credit);
            //subjectshow.syllabus=(TextView)row.findViewById(R.id.syllabus);

            convertView.setTag(bookshow);


        }
        else{
            bookshow=(QuestionAdapter.Book)convertView.getTag();
        }
        final questiongetter notes=(questiongetter) this.getItem(position);
//        System.out.println(bookdisplay.getPdf());
//        System.out.println("<<here<<<<<<<<<<<<<<<<,");
//        System.out.println(bookdisplay.getBookName());
//        String name=bookdisplay.getBookName();

        // bookshow..setText(name);
        bookshow.year.setText(notes.getYears());
        bookshow.types.setText(notes.getTypes());
        //bookshow.bookpdf.setText(bookdisplay.getPdf());
        bookshow.downloadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"downloading..",Toast.LENGTH_SHORT).show();
                DownloadManager downloadManager=(DownloadManager)getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(notes.getPdf());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("pdf_"+notes.getYears());
                request.setDescription("Downloading");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //request.setDestinationUri(Uri.parse("file://" + folderName + "/myfile.mp3"));
                downloadManager.enqueue(request);
            }
        });
        //bookshow.bookpdf.setMovementMethod(LinkMovementMethod.getInstance());
//        subjectshow.credit.setText(subjectdisplay.getCredit());
//        subjectshow.syllabus.setText(subjectdisplay.getSyllabus());


        return convertView;
    }

    static class Book{

        TextView year,types;
        Button downloadpdf;



    }
}
