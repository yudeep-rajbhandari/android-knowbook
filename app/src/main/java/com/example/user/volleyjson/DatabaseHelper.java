package com.example.user.volleyjson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linux on 11/24/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "KnowBooks.db";
    public static final String TABLE_NAME1 = "users";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Faculty";
    public static final String COL_3 = "Semester";


    public static final String TABLE_NAME4 = "Routine";
    public static final String Rout_1 = "ID";
    public static final String Rout_2 = "day";
    public static final String Rout_3 = "starting_time";
    public static final String Rout_4 = "ending_time";
    public static final String Rout_5 = "teacher";
    public static final String Rout_6 = "subjectname";
    public static final String Rout_7 = "subjectcode";





    public static final String TABLE_NAME3 = "Books";
    public static final String BOOK_1 = "ID";
    public static final String BOOK_2 = "BOOK_name";
    public static final String BOOK_3 = "writer";
    public static final String BOOK_4 = "booktype";
    public static final String BOOK_5 = "price";
    public static final String BOOK_6 = "availabiltiy";
    public static final String BOOK_7 = "publication";
    public static final String BOOK_8 = "racknumber";
    public static final String BOOK_9 = "pdf";
    public static final String BOOK_10 ="subjectid";

    public static final String TABLE_NAME2 = "Subjects";
    public static final String SUB_1 = "ID";
    public static final String SUB_2 = "subject_name";
    public static final String SUB_3 = "subject_code";
    public static final String SUB_4 = "credit";
    public static final String SUB_5 = "syllabus";
    public static final String SUB_6 = "faculty";
    public static final String SUB_7 = "semester";
    public class Subject{
        String id,sub_name,sub_code,credit,syllabus,faculty,semester;
        public  Subject(String id,String sub_name,String sub_code,String credit,String syllabus,String faculty,String semester){
            this.id=id;
            this.sub_name=sub_name;
            this.sub_code=sub_code;
            this.credit=credit;
            this.syllabus=syllabus;
            this.faculty=faculty;
            this.semester=semester;
        }
        public  Subject(){

        }
        public void printSubject(){
            System.out.println(id+" "+sub_name+" "+sub_code+""+credit+" "+syllabus+" "+faculty+" "+semester);
        }
    };

    public class Books{
        String ID,book_name,writer,booktype, price,availabiltiy,publication,racknumber,pdf,subjectid;
        public  Books(String ID, String book_name, String writer, String booktype, String price, String availabiltiy, String publication,String racknumber,String pdf,String subjectid
        ){
                this.ID=ID;
            this.book_name=book_name;
            this.writer=writer;
            this.booktype=booktype;
            this.price=price;
            this.availabiltiy=availabiltiy;
            this.publication=publication;
            this.racknumber=racknumber;
            this.pdf=pdf;
            this.subjectid=subjectid;
        }
        public  Books(){

        }
        public void printbook(){
            System.out.println(ID+" "+writer+" "+booktype+""+price+" "+availabiltiy+" "+publication+" "+racknumber);
        }

    }

    public class Routine{
        String ID,day,starting_time,ending_time,teacher, subjectname,subjectcode;
        public  Routine(String ID,String day, String starting_time, String ending_time, String teacher, String subjectname, String subjectcode
        ){
            this.ID=ID;
            this.starting_time=starting_time;
            this.ending_time=ending_time;
            this.teacher=teacher;
            this.subjectname=subjectname;
            this.subjectcode=subjectcode;
            this.day=day;

        }
        public  Routine(){

        }


    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Faculty TEXT,Semester INTEGER)");
        sqLiteDatabase.execSQL("create table " + TABLE_NAME2 + " (ID INTEGER,subject_name TEXT,subject_code TEXT,credit TEXT,syllabus TEXT,faculty TEXT,semester INTEGER)");
        sqLiteDatabase.execSQL("create table " + TABLE_NAME3 + " (ID INTEGER,book_name TEXT,writer TEXT,booktype TEXT,price TEXT,availabiltiy TEXT,publication TEXT,racknumber TEXT,pdf TEXT,subjectid TEXT)");
        sqLiteDatabase.execSQL("create table " + TABLE_NAME4 + " (ID INTEGER,day TEXT,starting_time TEXT,ending_time TEXT,teacher TEXT,subjectname TEXT,subjectcode TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME4);

        onCreate(sqLiteDatabase);


    }

    public String searchpass(String username) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "Select Name,Password from " + TABLE_NAME1;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(username)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());


        }
        return b;
    }
public void clear(){
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    sqLiteDatabase.delete(TABLE_NAME2,null,null);
}

    public void clear1(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME3,null,null);
    }
    public void clear2(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME4,null,null);
    }
    public boolean insertsubject(String ID, String subject_name, String subject_code, String credit, String syllabus, String faculty, String semester) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(SUB_1, ID);
        contentValues.put(SUB_2, subject_name);
        contentValues.put(SUB_3, subject_code);
        contentValues.put(SUB_4, credit);
        contentValues.put(SUB_5, syllabus);
        contentValues.put(SUB_6, faculty);
        contentValues.put(SUB_7, semester);

        long result = sqLiteDatabase.insert(TABLE_NAME2, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }



    public boolean insertbook(String ID, String book_name, String writer, String booktype, String price, String availabiltiy, String publication,String racknumber,String pdf,String subjectid) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_1, ID);
        contentValues.put(BOOK_2, book_name);
        contentValues.put(BOOK_3, writer);
        contentValues.put(BOOK_4, booktype);
        contentValues.put(BOOK_5, price);
        contentValues.put(BOOK_6, availabiltiy);
        contentValues.put(BOOK_7, publication);
        contentValues.put(BOOK_8, racknumber);
        contentValues.put(BOOK_9,pdf );
        contentValues.put(BOOK_10,subjectid );

        long result = sqLiteDatabase.insert(TABLE_NAME3, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }


    public boolean insertroutine(String ID, String day, String starting_time, String ending_time, String teacher, String subjectname, String subjectcode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(Rout_1, ID);
        contentValues.put(Rout_2,day );
        contentValues.put(Rout_3,starting_time );
        contentValues.put(Rout_4,ending_time );
        contentValues.put(Rout_5, teacher);
        contentValues.put(Rout_6,subjectcode );
        contentValues.put(Rout_7,subjectname );

        long result = sqLiteDatabase.insert(TABLE_NAME4, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }

    public ArrayList<Routine> getRoutine(String getday){
        ArrayList<Routine> routinelist=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        String Query ="SELECT * FROM "+TABLE_NAME4+" WHERE day ='"+getday+"'" ;
        System.out.println(Query);
        Cursor cursor=database.rawQuery(Query,null);
        while(cursor.moveToNext()){
           routinelist.add(
                   new Routine(cursor.getString(cursor.getColumnIndex(Rout_1)),
                           cursor.getString(cursor.getColumnIndex(Rout_2)),
                           cursor.getString(cursor.getColumnIndex(Rout_3)),
                           cursor.getString(cursor.getColumnIndex(Rout_4)),
                           cursor.getString(cursor.getColumnIndex(Rout_5)),
                           cursor.getString(cursor.getColumnIndex(Rout_6)),
                           cursor.getString(cursor.getColumnIndex(Rout_7))
                   )
           );
        }
        return  routinelist;
    }

    public ArrayList<Subject> getSubjects(){
        ArrayList<Subject> subjectlist=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        String Query ="SELECT * FROM "+TABLE_NAME2;
        System.out.println(Query);
        Cursor cursor=database.rawQuery(Query,null);
        while(cursor.moveToNext()){
            subjectlist.add(
                    new Subject(cursor.getString(cursor.getColumnIndex(SUB_1)),
                            cursor.getString(cursor.getColumnIndex(SUB_2)),
                            cursor.getString(cursor.getColumnIndex(SUB_3)),
                            cursor.getString(cursor.getColumnIndex(SUB_4)),
                            cursor.getString(cursor.getColumnIndex(SUB_5)),
                            cursor.getString(cursor.getColumnIndex(SUB_6)),
                            cursor.getString(cursor.getColumnIndex(SUB_7))
                    )
            );
        }
        return  subjectlist;
    }
    public ArrayList<Books> getBooks(String subjectcode){
        ArrayList<Books> booklist=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        String Query ="SELECT * FROM "+TABLE_NAME3+ " WHERE subjectid ='"+subjectcode+"'";
        System.out.println(Query);
        Cursor cursor=database.rawQuery(Query,null);
       // System.out.println(cursor);
        while(cursor.moveToNext()){
            booklist.add(
                    new Books(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9)
                    )
            );
        }
        return  booklist;

    }



    public boolean insertData(String Name, String Faculty, String Semester) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        Getprofile getprofile = getAllLabels();
        System.out.println("getprofile = " + getprofile);
        if (getprofile.getId() == null) {
            System.out.println("i m here also");
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, Name);
            contentValues.put(COL_2, Faculty);
            contentValues.put(COL_3, Semester);

            long result = sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);

            if (result == -1) {
                return false;
            } else
                return true;


        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, Name);
            contentValues.put(COL_2, Faculty);
            contentValues.put(COL_3, Semester);

            long result1=sqLiteDatabase.update(TABLE_NAME1,contentValues,null,null);

            System.out.println("already done");

            if (result1 == -1) {
                return false;
            } else
                return true;
        }

    }


    public Getprofile getAllLabels() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Select All Query
        String selectQuery = "Select * from " + TABLE_NAME1;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        Getprofile getprofile = null;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);

                String faculty = cursor.getString(2);
                String semester = cursor.getString(3);
                getprofile = new Getprofile(id, name, faculty, semester);
                return getprofile;
                //list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());

        } else {
            getprofile = new Getprofile();
            return getprofile;

        }

        // closing connection


        // returning lables

    }

}
