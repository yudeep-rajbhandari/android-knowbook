package com.example.user.volleyjson;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class showbooks extends AppCompatActivity {
    ListView listView1;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbooks);
        listView1=(ListView)findViewById(R.id.list_view_books);
        String subjectcode=getIntent().getStringExtra("subjectcode");
        Toast.makeText(showbooks.this,subjectcode,Toast.LENGTH_LONG).show();
        String bookurl = "http://knowbook.herokuapp.com/books/Requests/"+subjectcode;

        bookAdapter=new BookAdapter(this,R.layout.textlayout2);
        listView1.setAdapter(bookAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bookdisplay item=(Bookdisplay)listView1.getItemAtPosition(i);
                //Toast.makeText(showbooks.this,"bookname :"+item.getBookName(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder myalert=new AlertDialog.Builder(showbooks.this);
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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, bookurl, null,
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


                                String bookName = object1.getString("bookName");
                                String writer = object1.getString("writer");
                                String  booktype= object1.getString("booktype");
                                String  price= object1.getString("price");
                                String  availability= object1.getString("availability");
                                String publication = object1.getString("publication");
                                String rackNumber = object1.getString("rackNumber");
                                JSONObject picture=object1.getJSONObject("pdf");
                                String pdf=picture.getString("url");


                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                System.out.println(bookName);
                                System.out.print(writer);
                                System.out.print(booktype);
                                System.out.println(price);
                                System.out.println(availability);
                                System.out.println(publication);
                                System.out.println(pdf);
                                System.out.println("<<<<<<<<<<<<<<<<<<<");
                                Bookdisplay bookdisplay=new Bookdisplay(bookName,writer,booktype,price,availability,publication,rackNumber,pdf);
                                bookAdapter.add(bookdisplay);
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


                Toast.makeText(showbooks.this, "something went wrong", Toast.LENGTH_LONG).show();

            }

        });
        // System.out.println(mArrayList);
        MySingleton.getInstance(showbooks.this).addToRequestQueue(jsonObjectRequest);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
