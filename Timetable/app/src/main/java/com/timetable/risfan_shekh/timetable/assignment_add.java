package com.timetable.risfan_shekh.timetable;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class assignment_add extends AppCompatActivity {

    FloatingActionButton fab;
    String date_ass="";
    public String title_ass="",desc ="",subject="",weightage="";
    DatabaseHelper dh = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_add);
        TextInputEditText title = (TextInputEditText) findViewById(R.id.title1);

        fab =(FloatingActionButton) findViewById(R.id.fab);

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
                        monthOfYear+=1;
                        String myFormat="dd/MM/yyyy";
                        date_ass = dayofmonth+"/"+monthOfYear+"/"+year;

//                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
//                        cal_button.setText(sdf.format(myCalender.getTime()));
//                        date_ass = sdf.format(myCalender.getTime()).toString();
                        cal_button.setText(date_ass);
                        //cal_button.setText(myCalender.getTime().toString());
                    }
                };
                new DatePickerDialog(assignment_add.this,datePickerListener,myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_MONTH)).show();

            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Assignment");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),assignment.class));
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean e = false;
                //Toast.makeText(clas.this,"Clicked !",Toast.LENGTH_SHORT).show();

                TextInputEditText string_subject = (TextInputEditText) findViewById(R.id.subject);
                subject = string_subject.getText().toString();
                TextInputEditText string_title = (TextInputEditText) findViewById(R.id.title1);
                title_ass = string_title.getText().toString();
                TextInputEditText string_weightage = (TextInputEditText) findViewById(R.id.weightage);
                weightage = string_weightage.getText().toString();
                TextInputEditText string_desc = (TextInputEditText) findViewById(R.id.desc);
                desc = string_desc.getText().toString();
                if(date_ass=="")
                {
                    cal_button.setError("Please Select Date !");
                    e=true;

                }
                if (subject.isEmpty())
                {
                    string_subject.setError("Please Enter Subject !");
                    e=true;
                }
                if(title_ass.isEmpty())
                {
                    string_title.setError("Please Enter Title !");
                    e=true;
                }
                if(weightage.isEmpty())
                {
                    string_weightage.setError("Please Enter Weightage !");
                    e=true;
                }
                if(desc.isEmpty())
                {
                    string_desc.setError("Please Enter Description !");
                    e=true;
                }
                /*Toast.makeText(assignment_add.this,subject,Toast.LENGTH_SHORT).show();
                Toast.makeText(assignment_add.this,title_ass,Toast.LENGTH_SHORT).show();
                Toast.makeText(assignment_add.this,weightage,Toast.LENGTH_SHORT).show();
                Toast.makeText(assignment_add.this,desc,Toast.LENGTH_SHORT).show();*/




                if(e==false) {
                    if(dh.assignment_insert(subject,title_ass,weightage,desc,date_ass))
                    {
                        startActivity(new Intent(getApplicationContext(), assignment.class));
                    }
                    else
                    {
                        Toast.makeText(assignment_add.this,"Unsuccessful Try Again",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
