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

import java.util.ArrayList;
import java.util.List;

public class assignment extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Context context;
    FloatingActionButton fab;
    DatabaseHelper dh = new DatabaseHelper(this);
    public List<assignments_mon> ass_mon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_ass);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter("abc",ass_mon);

        initializeData();
        initializeAdapter();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab =(FloatingActionButton) findViewById(R.id.fab);



        ///getActionBar().setTitle("Class");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Assignments");

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
                startActivity(new Intent(getApplicationContext(),assignment_add.class));
            }
        });

    }

    private void initializeData() {

        ass_mon = new ArrayList<>();
        Cursor c = dh.assignment_getall_notdone();
        if(c.getCount()==0)
        {
            showMessage("Error","Nothing Found !");
        }
        else {


            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {

                ass_mon.add(new assignments_mon(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(0)));
            }
            //showMessage("Data", buffer.toString());
        }
    }
    private void initializeAdapter(){

        adapter = new RecyclerViewAdapter("abc",ass_mon);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener(){


            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                final String t = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.id_a)).getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(assignment.this);
                builder
                        .setMessage("Are you Sure to Delete this Assignment Record ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dh.assignment_delete(t);
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

    private void showMessage(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}

class assignments_mon {

    String sub_mon,title_mon,weightage_mon,desc_mon,status_done,ass_date,a_id;

    public assignments_mon(String sub, String title, String weightage, String desc, String ass_date, String comp,String a_id) {

        this.sub_mon = sub;
        this.title_mon = title;
        this.weightage_mon = weightage;
        this.desc_mon = desc;
        this.ass_date=ass_date;
        this.status_done = comp;
        this.a_id = a_id;

        //Toast.makeText(this,sub_mon,Toast.LENGTH_SHORT);



    }
}
