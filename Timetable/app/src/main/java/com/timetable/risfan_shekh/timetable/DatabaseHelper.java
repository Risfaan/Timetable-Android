package com.timetable.risfan_shekh.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  final static String DATABASE_NAME="TimeTable.db";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE IF NOT EXISTS tt_class " +
                "(class_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "subject VARCHAR(50) NOT NULL, " +
                "teacher VARCHAR(50) NOT NULL, " +
                "room VARCHAR(50) NOT NULL, " +
                "class_from VARCHAR(50) NOT NULL, " +
                "class_to VARCHAR(50) NOT NULL, " +
                "day VARCHAR(10) NOT NULL);";
        db.execSQL(query1);

        String query2 = "CREATE TABLE IF NOT EXISTS tt_exam(" +
                "exam_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "subject VARCHAR(50) NOT NULL, " +
                "room VARCHAR(50) NOT NULL, " +
                "class_from VARCHAR(50) NOT NULL, " +
                "class_to VARCHAR(50) NOT NULL," +
                "exam_date VARCHAR(20) NOT NULL);";
        db.execSQL(query2);

        String query3 = "CREATE TABLE IF NOT EXISTS tt_assignment(" +
                "ass_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "subject VARCHAR(50) NOT NULL, " +
                "title VARCHAR(50) NOT NULL, " +
                "weightage VARCHAR(50) NOT NULL, " +
                "description VARCHAR(50) NOT NULL, " +
                "assignment_date VARCHAR(20) NOT NULL," +
                "status_done INTEGER(10) NOT NULL);";
        db.execSQL(query3);

        String query4 = "CREATE TABLE IF NOT EXISTS tt_teacher(" +
                "teacher_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "post VARCHAR(50) NOT NULL, " +
                "phone VARCHAR(50) NOT NULL, " +
                "email VARCHAR(50) NOT NULL, " +
                "office VARCHAR(100) NOT NULL);";
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_v, int new_v) {

        String q = "DROP TABLE tt_class;";
        db.execSQL(q);

        String q1 = "DROP TABLE tt_assignment;";
        db.execSQL(q1);

        String q2 = "DROP TABLE tt_exam;";
        db.execSQL(q2);

        String q3 = "DROP TABLE tt_teacher;";
        db.execSQL(q3);

        onCreate(db);

    }

    //Class Table Operation

    public boolean class_insert(String subj,String teacher,String room,String from,String to,String day)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("subject",subj);
        cv.put("teacher",teacher);
        cv.put("room",room);
        cv.put("class_from",from);
        cv.put("class_to",to);
        cv.put("day",day);

        long a = db.insert("tt_class",null,cv);

        if(a==-1)
        {
            return false;
        }

        return true;
    }
    public Cursor class_get(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM tt_class WHERE class_id = '"+id+"';";
        Cursor cursor = db.rawQuery(q,null);
        return cursor;
    }
    public Cursor class_home(String d)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM tt_class WHERE day = '"+d+"';";
        Cursor cursor = db.rawQuery(q,null);
        return cursor;

    }
    public  boolean class_update(String subj,String teacher,String room,String from,String to)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return  true;
    }
    public Integer class_delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tt_class","class_id = ?",new String[]{id});


    }
    public Cursor class_getall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tt_class order by day",null);
        return res;

    }

    //Assignment Table Operation

    public boolean assignment_insert(String subj,String title,String weightage,String desc,String ass_date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int comp= 0;
        ContentValues cv = new ContentValues();
        cv.put("subject",subj);
        cv.put("title",title);
        cv.put("weightage",weightage);
        cv.put("description",desc);
        cv.put("assignment_date",ass_date);
        cv.put("status_done",comp);

        long a = db.insert("tt_assignment",null,cv);

        if(a==-1)
        {
            return false;
        }

        return true;
    }
    public Cursor assignment_get(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM tt_assignment WHERE ass_id = '"+id+"';";
        Cursor cursor = db.rawQuery(q,null);
        return cursor;
    }

    public Cursor assignment_get_home()
    {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("d/M/y");
        String id = df.format(c);
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM tt_assignment WHERE assignment_date = '"+id+"';";
        Cursor cursor = db.rawQuery(q,null);
        return cursor;
    }

    public  boolean assignment_update_status(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status_done",1);
        db.update("tt_assignment",contentValues,"ass_id=?",new String[]{id});

        return  true;
    }
    public Integer assignment_delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tt_assignment","ass_id=?",new String[]{id});
    }
    public Cursor assignment_getall_done()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tt_assignment WHERE status_done==1",null);
        return res;

    }
    public Cursor assignment_getall_notdone()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tt_assignment  WHERE status_done==0",null);
        return res;

    }

    //Exam Table Operation

    public boolean exam_insert(String subj,String room,String from,String to,String exam_date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("subject",subj);
        cv.put("room",room);
        cv.put("class_from",from);
        cv.put("class_to",to);
        cv.put("exam_date",exam_date);

        long a = db.insert("tt_exam",null,cv);

        if(a==-1)
        {
            return false;
        }

        return true;
    }
    public Cursor exam_get(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM tt_exam WHERE exam_id = '"+id+"';";
        Cursor cursor = db.rawQuery(q,null);
        return cursor;
    }

    public Cursor exam_get_home()
    {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("d/M/y");
        String id = df.format(c);

        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM tt_exam WHERE exam_date = '"+id+"';";
        Cursor cursor = db.rawQuery(q,null);
        return cursor;
    }

    public  boolean exam_update()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        return  true;
    }
    public Integer exam_delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tt_exam","exam_id=?",new String[]{id});

    }
    public Cursor exam_getall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tt_exam",null);
        return res;

    }

    //Teacher Table Operation

    public boolean teacher_insert(String name,String post,String phone,String email,String office)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("post",post);
        cv.put("phone",phone);
        cv.put("email",email);
        cv.put("office",office);

        long a = db.insert("tt_teacher",null,cv);

        if(a==-1)
        {
            return false;
        }


        return true;
    }
    public Cursor teacher_get(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM tt_teacher WHERE teacher_id = '"+id+"';";
        Cursor cursor = db.rawQuery(q,null);
        return cursor;
    }
    public  boolean teacher_update()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        return  true;
    }
    public Integer teacher_delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tt_teacher","teacher_id=?",new String[]{id});

    }
    public Cursor teacher_getall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tt_teacher",null);
        return res;

    }


}
