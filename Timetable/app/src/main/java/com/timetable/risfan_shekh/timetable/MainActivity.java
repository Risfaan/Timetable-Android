package com.timetable.risfan_shekh.timetable;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.database.Cursor;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DatabaseHelper dh = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();
        int ca = c.get(Calendar.DAY_OF_WEEK);

        String day="";
        switch (ca){
            case Calendar.SUNDAY:
                day="Sunday";
                break;
            case Calendar.MONDAY:
                day="Monday";
                break;
            case Calendar.TUESDAY:
                day="Tuesday";
                break;
            case Calendar.WEDNESDAY:
                day="Wednesday";
                break;
            case Calendar.THURSDAY:
                day="Thursday";
                break;
            case Calendar.FRIDAY:
                day="Friday";
                break;
            case Calendar.SATURDAY:
                day="Saturday";
                break;
        }

        ImageButton cl_v = (ImageButton)findViewById(R.id.view_class);
        cl_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,clas.class);
                startActivity(i);
            }
        });

        ImageButton as_v = (ImageButton)findViewById(R.id.view_ass);
        as_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,assignment.class);
                startActivity(i);
            }
        });

        ImageButton ex_v = (ImageButton)findViewById(R.id.view_exam);
        ex_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,exam.class);
                startActivity(i);
            }
        });


        Cursor cl = dh.class_home(day);
        TextView home_subj = (TextView) findViewById(R.id.sub_card_home);
        if(cl.getCount()==0)
        {
            //showMessage("Error","Nothing Found !");
            home_subj.setText("No Classes Today ! Enjoy !");
        }
        else {

            while (cl.moveToNext()) {


                TextView home_start_end = (TextView) findViewById(R.id.start_end_home);
                TextView home_teacher = (TextView) findViewById(R.id.teacher_card_home);
                TextView home_class_no = (TextView) findViewById(R.id.class_num_card_home);


                home_subj.setText(cl.getString(1));
                home_start_end.setText(cl.getString(4)+" to "+cl.getString(5));
                home_teacher.setText(cl.getString(2));
                home_class_no.setText(cl.getString(3));


            }

            //class_mon.add(new Classes_mon("a","b","c","s","e","f"));
            //
            //showMessage("ABC",buffer.toString());
        }

        Cursor cl_ass = dh.assignment_get_home();
        TextView weightage = (TextView) findViewById(R.id.weightage_card_home);
        TextView date_home = (TextView) findViewById(R.id.date_ass_home);
        TextView topic_home = (TextView) findViewById(R.id.topic_card_ass_home);
        if(cl_ass.getCount()==0)
        {
            //showMessage("Error","Nothing Found !");
            topic_home.setText("No Due Today ! Enjoy !");
        }
        else {

            while (cl_ass.moveToNext()) {




                TextView sub_home = (TextView) findViewById(R.id.subject_card_ass_home);
                TextView desc_home = (TextView) findViewById(R.id.desc_card_ass_home);



                date_home.setText(cl_ass.getString(5));
                topic_home.setText(cl_ass.getString(2));
                sub_home.setText(cl_ass.getString(1));
                desc_home.setText(cl_ass.getString(4));
                weightage.setText(cl_ass.getString(3));


            }

            //class_mon.add(new Classes_mon("a","b","c","s","e","f"));
            //
            //showMessage("ABC",buffer.toString());
        }

        Cursor cl_exam = dh.exam_get_home();
        TextView date_home_exam = (TextView) findViewById(R.id.date_ecard_home);
        if(cl_exam.getCount()==0)
        {
            //showMessage("Error","Nothing Found !");
             date_home_exam.setText("No Exam Due Today ! Enjoy !");
        }
        else {

            while (cl_exam.moveToNext()) {



                TextView fromto_home_exam = (TextView) findViewById(R.id.fromto_ecard_home);
                TextView sub_home_exam = (TextView) findViewById(R.id.sub_ecard_home);
                TextView room_home_exam = (TextView) findViewById(R.id.room_ecard_home);



                date_home_exam.setText(cl_exam.getString(5));
                fromto_home_exam.setText(cl_exam.getString(3)+" to "+cl_exam.getString(4));
                sub_home_exam.setText(cl_exam.getString(1));
                room_home_exam.setText(cl_exam.getString(2));



            }

            //class_mon.add(new Classes_mon("a","b","c","s","e","f"));
            //
            //showMessage("ABC",buffer.toString());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_index) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_class) {
            Intent i = new Intent(this,clas.class);
            startActivity(i);
        } else if (id == R.id.nav_assignments) {
            Intent i = new Intent(this,assignment.class);
            startActivity(i);

        } else if (id == R.id.nav_exams) {
            Intent i = new Intent(this,exam.class);
            startActivity(i);

        } else if (id == R.id.nav_teachers) {
            Intent i = new Intent(this,teacher.class);
            startActivity(i);

        } else if (id == R.id.nav_aboutus) {
            Intent i = new Intent(this,about_us.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
