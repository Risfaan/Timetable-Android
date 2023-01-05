package com.timetable.risfan_shekh.timetable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class exam_add extends AppCompatActivity {

    FloatingActionButton fab;
    String sub="",room="",from="",to="",exam_date="";
    DatabaseHelper dh = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button cal_button = (Button) findViewById(R.id.select_date) ;

        cal_button.setOnClickListener(new View.OnClickListener() {
            private Calendar myCalender = Calendar.getInstance();
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener datePickerListener=new DatePickerDialog.OnDateSetListener() {
                    private Calendar myCalender = Calendar.getInstance();
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayofmonth) {

                        String myFormat="d/M/y";
                        myCalender.set(year,monthOfYear,dayofmonth);
                        myCalender.set(year,monthOfYear,dayofmonth);
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                        cal_button.setText(sdf.format(myCalender.getTime()));
                        exam_date=sdf.format(myCalender.getTime());
                        //cal_button.setText(myCalender.getTime().toString());
                    }
                };
                new DatePickerDialog(exam_add.this,datePickerListener,myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        final Button button_from = (Button) findViewById(R.id.time_from) ;

        button_from.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(java.util.Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(exam_add.this,
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

                TimePickerDialog timePickerDialog = new TimePickerDialog(exam_add.this,
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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Exam");
        fab =(FloatingActionButton) findViewById(R.id.fab);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),exam.class));
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean e=false;

                //Toast.makeText(clas.this,"Clicked !",Toast.LENGTH_SHORT).show();
                TextInputEditText string_subject = (TextInputEditText)findViewById(R.id.sub);
                sub=string_subject.getText().toString();

                TextInputEditText string_room = (TextInputEditText)findViewById(R.id.room);
                room=string_room.getText().toString();

                if(sub.isEmpty())
                {
                    string_subject.setError("Please Enter Subject !");
                    e=true;
                }
                if(room.isEmpty())
                {
                    string_room.setError("Please Enter Room number !");
                    e=true;
                }
                if(exam_date.isEmpty())
                {
                    cal_button.setError("Please Choose Exam Date !");
                    e=true;
                }
                if(from.isEmpty())
                {
                    button_from.setError("Please Choose Start Time !");
                    e=true;
                }
                if(to.isEmpty())
                {
                    button_to.setError("Please Choose Exam Finish Time !");
                    e=true;
                }

                /*Toast.makeText(exam_add.this,sub,Toast.LENGTH_SHORT).show();
                Toast.makeText(exam_add.this,room,Toast.LENGTH_SHORT).show();
                Toast.makeText(exam_add.this,exam_date,Toast.LENGTH_SHORT).show();
                Toast.makeText(exam_add.this,from,Toast.LENGTH_SHORT).show();
                Toast.makeText(exam_add.this,to,Toast.LENGTH_SHORT).show();*/



                if(e==false) {
                    if(dh.exam_insert(sub,room,from,to,exam_date))
                    {
                        startActivity(new Intent(getApplicationContext(), exam.class));
                    }
                    else
                    {
                        Toast.makeText(exam_add.this,"Unsuccessful, Try Again !",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
