package com.timetable.risfan_shekh.timetable;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import java.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import static android.icu.util.Calendar.getInstance;

public class class_add extends AppCompatActivity  {

    FloatingActionButton fab_cl;
    String subject="",teacher="",room="",from="",to="",day="Monday";
    DatabaseHelper dh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab_cl =(FloatingActionButton) findViewById(R.id.fab);

        final Button button_from = (Button) findViewById(R.id.time_from) ;

        button_from.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(java.util.Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(class_add.this,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                    @Override
                    public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                        button_from.setText(hourofDay + " : " + minute);

                        from = hourofDay + " : " + minute;

                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });

        final Button button_to= (Button) findViewById(R.id.time_to) ;

        button_to.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(java.util.Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(class_add.this,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                                button_to.setText(hourofDay + " : " + minute);
                                to = hourofDay + " : " + minute;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });

        Spinner s = (Spinner) findViewById(R.id.spinn);
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,R.layout.activity_class_add_item,days);
        s.setAdapter(ad);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        day = "Monday";
                        break;
                    case 1:
                        day = "Tuesday";
                        break;
                    case 2:
                        day = "Wednesday";
                        break;
                    case 3:
                        day = "Thursday";
                        break;
                    case 4:
                        day = "Friday";
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Class");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),clas.class));
                finish();
            }
        });
        fab_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(clas.this,"Clicked !",Toast.LENGTH_SHORT).show();

                TextInputEditText string_subject = (TextInputEditText) findViewById(R.id.sub) ;
                subject=string_subject.getText().toString();
                TextInputEditText string_teacher = (TextInputEditText) findViewById(R.id.teacher);
                teacher=string_teacher.getText().toString();
                TextInputEditText string_room = (TextInputEditText) findViewById(R.id.room);
                room=string_room.getText().toString();

                boolean e = false;

                if(subject.isEmpty())
                {
                    string_subject.setError("Please Enter Subject !");
                    e=true;
                }
                if(teacher.isEmpty())
                {
                    string_teacher.setError("Please Enter Teacher !");
                    e=true;
                }
                if(room.isEmpty())
                {
                    string_room.setError("Please Enter Room !");
                    e=true;
                }
                if(from.isEmpty())
                {
                    button_from.setError("Please Select Start Time !");
                    e=true;
                }
                if(to.isEmpty())
                {
                    button_to.setError("Please Select End Time !");
                    e=true;
                }

                //day = getIntent().getStringExtra("day");

                /*Toast.makeText(class_add.this,day,Toast.LENGTH_SHORT).show();
                Toast.makeText(class_add.this,subject,Toast.LENGTH_SHORT).show();
                Toast.makeText(class_add.this,teacher,Toast.LENGTH_SHORT).show();
                Toast.makeText(class_add.this,room,Toast.LENGTH_SHORT).show();
                Toast.makeText(class_add.this,from,Toast.LENGTH_SHORT).show();
                Toast.makeText(class_add.this,to,Toast.LENGTH_SHORT).show();*/


                    if(e==false) {
                        //dh is a DatabaseHelper Class where all the Database Related Operations are done.
                        if(dh.class_insert(subject,teacher,room,from,to,day))
                        {
                            Toast.makeText(class_add.this,"Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), clas.class));
                        }
                        else
                        {
                            Toast.makeText(class_add.this,"Unsuccessful Try Again",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        });


    }
}
