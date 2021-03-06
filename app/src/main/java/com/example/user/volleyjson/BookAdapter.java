package com.example.user.volleyjson;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
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
 * Created by USER on 2/2/2017.
 */

public class BookAdapter extends ArrayAdapter {
    List list1= new ArrayList();
    public BookAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Bookdisplay object) {
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
        Book bookshow;
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.textlayout2,parent,false);
            bookshow =new Book();
            bookshow.bookname=(TextView)convertView.findViewById(R.id.bookname1);
            //bookshow.bookpdf=(TextView)convertView.findViewById(R.id.pdf1);
            bookshow.downloadpdf=(Button)convertView.findViewById(R.id.download_pdf);

            //subjectshow.credit=(TextView)row.findViewById(R.id.credit);
            //subjectshow.syllabus=(TextView)row.findViewById(R.id.syllabus);

            convertView.setTag(bookshow);


        }
        else{
            bookshow=(Book)convertView.getTag();
        }
        final Bookdisplay bookdisplay=(Bookdisplay)this.getItem(position);
        System.out.println(bookdisplay.getPdf());
        System.out.println("<<here<<<<<<<<<<<<<<<<,");
        System.out.println(bookdisplay.getBookName());
        String name=bookdisplay.getBookName();

        bookshow.bookname.setText(name);
        bookshow.bookname.setText(bookdisplay.getBookName());
        //bookshow.bookpdf.setText(bookdisplay.getPdf());
        bookshow.downloadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"downloading..",Toast.LENGTH_SHORT).show();
                DownloadManager downloadManager=(DownloadManager)getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(bookdisplay.getPdf());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("pdf_"+bookdisplay.getBookName());
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

        TextView bookname,bookpdf;
        Button downloadpdf;



    }

}
