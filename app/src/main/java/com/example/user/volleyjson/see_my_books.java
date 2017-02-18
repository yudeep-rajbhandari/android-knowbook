package com.example.user.volleyjson;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class see_my_books extends AppCompatActivity {
    ListView listView;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_books);
        String subjectcode=getIntent().getStringExtra("subjectcode");
        listView=(ListView)findViewById(R.id.see_my_books);
        bookAdapter = new BookAdapter(this, R.layout.textlayout2);

        listView.setAdapter(bookAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bookdisplay item=(Bookdisplay)listView.getItemAtPosition(i);
                //Toast.makeText(showbooks.this,"bookname :"+item.getBookName(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder myalert=new AlertDialog.Builder(see_my_books.this);
                View mview=getLayoutInflater().inflate(R.layout.bookdetails,null);
                final TextView bookname=(TextView)mview.findViewById(R.id.textView_bookname);
                final TextView booktype=(TextView)mview.findViewById(R.id.textview_booktype_edit);
                final TextView author =(TextView)mview.findViewById(R.id.textview_author);
                final TextView publication=(TextView)mview.findViewById(R.id.textview_publication);
                final TextView price=(TextView)mview.findViewById(R.id.textview_price);
                final TextView availability=(TextView)mview.findViewById(R.id.textview_availability);
                final TextView racknumber=(TextView)mview.findViewById(R.id.textview_racknumber);

                bookname.setText(item.getBookName());
                booktype.setText(item.getBooktype());
                author.setText(item.getWriter());
                publication.setText(item.getPublication());
                price.setText(item.getPrice());
                availability.setText(item.getAvailability());
                racknumber.setText(item.getRackNumber());
                myalert.setView(mview);
                AlertDialog dialog=myalert.create();
                dialog.show();








            }
        });


        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        ArrayList<DatabaseHelper.Books> bookList=db.getBooks(subjectcode);
        System.out.println("<<<<<<<<<from here");
        System.out.println(bookList);


        for(DatabaseHelper.Books books:bookList){
            //subject.printSubject();
            Bookdisplay bookdisplay = new Bookdisplay( books.book_name, books.writer, books.booktype, books.price,books.availabiltiy,books.publication,books.racknumber,books.pdf);
            System.out.println(books.book_name);
            System.out.println(books.writer);
            System.out.println("<<<<<<<<<<<<<<<<hereererer");
            bookAdapter.add(bookdisplay);
        }
    }
}
