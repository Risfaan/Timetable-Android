package com.timetable.risfan_shekh.timetable;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class teacher_add extends AppCompatActivity {

    FloatingActionButton fab;
    String name="",post="",email="",phone="",office="";
    DatabaseHelper dh = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Teacher");
        fab =(FloatingActionButton) findViewById(R.id.fab);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),teacher.class));
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(clas.this,"Clicked !",Toast.LENGTH_SHORT).show();
                boolean e=false;

                TextInputEditText string_name = (TextInputEditText) findViewById(R.id.tnam);
                name = string_name.getText().toString();
                TextInputEditText string_post = (TextInputEditText) findViewById(R.id.post);
                post = string_post.getText().toString();
                TextInputEditText string_phone = (TextInputEditText) findViewById(R.id.phone);
                phone = string_phone.getText().toString();
                TextInputEditText string_email = (TextInputEditText) findViewById(R.id.email);
                email = string_email.getText().toString();
                TextInputEditText string_office = (TextInputEditText) findViewById(R.id.office);
                office= string_office.getText().toString();

                if(name.isEmpty())
                {
                    e=true;
                    string_name.setError("Please Enter Teacher's Name !");
                }
                if(post.isEmpty())
                {
                    e=true;
                    string_post.setError("Please Enter Teacher's Post !");
                }
                if(phone.isEmpty())
                {
                    e=true;
                    string_phone.setError("Please Enter Teacher's Phone Number !");
                }
                if(email.isEmpty())
                {
                    e=true;
                    string_email.setError("Please Enter Teacher's Email !");
                }
                if(office.isEmpty())
                {
                    e=true;
                    string_office.setError("Please Enter Office Address !");
                }

                /*Toast.makeText(teacher_add.this,name,Toast.LENGTH_SHORT).show();
                Toast.makeText(teacher_add.this,post,Toast.LENGTH_SHORT).show();
                Toast.makeText(teacher_add.this,phone,Toast.LENGTH_SHORT).show();
                Toast.makeText(teacher_add.this,email,Toast.LENGTH_SHORT).show();
                Toast.makeText(teacher_add.this,office,Toast.LENGTH_SHORT).show();*/

                if(e==false) {
                    if(dh.teacher_insert(name,post,phone,email,office))
                    {
                        startActivity(new Intent(getApplicationContext(), teacher.class));
                    }
                    else
                    {
                        Toast.makeText(teacher_add.this,"Unsuccessful. Please Try Again !",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
