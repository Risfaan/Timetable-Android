package com.timetable.risfan_shekh.timetable;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class exam extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Context context;
    public List<exam_mon> e_mon;
    FloatingActionButton fab;
    DatabaseHelper dh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_exam);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter("abc","abc","abc",e_mon);

        initializeData();
        initializeAdapter();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab =(FloatingActionButton) findViewById(R.id.fab);



        ///getActionBar().setTitle("Class");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Exam");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(assignment.this,"Clicked !",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),exam_add.class));
            }
        });


    }



    private void initializeAdapter() {

        adapter = new RecyclerViewAdapter("abc","abc","abc",e_mon);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener(){


            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                final String t = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.id_e)).getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(exam.this);
                builder
                        .setMessage("Are you Sure to Delete this Exam Record ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dh.exam_delete(t);
                                initializeData();
                                initializeAdapter();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });

    }

    private void initializeData() {

        e_mon = new ArrayList<>();
        Cursor c = dh.exam_getall();
        if(c.getCount()==0)
        {
            showMessage("Error","Nothing Found !");
        }
        else {


            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {

                e_mon.add(new exam_mon(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(0)));
            }
            //showMessage("Data", buffer.toString());
        }

    }

    private void showMessage(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
class exam_mon {

    String e_sub,e_room,e_from_to,e_date,e_id;

    public exam_mon(String t_name, String t_post, String t_phone, String t_email, String t_office,String e_id) {

        this.e_sub = t_name;
        this.e_room = t_post;
        this.e_from_to = t_phone+" - "+t_email;
        this.e_date=t_office;
        this.e_id = e_id;


        //Toast.makeText(this,sub_mon,Toast.LENGTH_SHORT);



    }
}