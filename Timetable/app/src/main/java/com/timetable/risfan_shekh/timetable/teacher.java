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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class teacher extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Context context;
    public List<teacher_mon> t_mon;
    FloatingActionButton fab;
    DatabaseHelper dh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_teacher);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter("abc","abc",t_mon);

        initializeData();
        initializeAdapter();
        //count_items();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab =(FloatingActionButton) findViewById(R.id.fab);


        ///getActionBar().setTitle("Class");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Teachers");

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
                startActivity(new Intent(getApplicationContext(),teacher_add.class));
            }
        });
    }

    void count_items()
    {

        Toast.makeText(this,t_mon.get(1).t_name,Toast.LENGTH_SHORT).show();
    }

    private void initializeAdapter() {

        adapter = new RecyclerViewAdapter("abc","abc",t_mon);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener(){


            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                final String t = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.id_t)).getText().toString();


                AlertDialog.Builder builder = new AlertDialog.Builder(teacher.this);
                builder
                        .setMessage("Are you Sure to Delete this Teacher Record ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dh.teacher_delete(t);
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

                //Toast.makeText(teacher.this,t,Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void initializeData() {

        t_mon = new ArrayList<>();
        Cursor c = dh.teacher_getall();
        if(c.getCount()==0)
        {
            showMessage("Error","Nothing Found !");
        }
        else {


            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {

                t_mon.add(new teacher_mon(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(0)));
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

class teacher_mon {

    String t_name,t_post,t_phone,t_email,t_office,t_id;

    public teacher_mon(String t_name, String t_post, String t_phone, String t_email, String t_office,String t_id) {

        this.t_name = t_name;
        this.t_post = t_post;
        this.t_phone = t_phone;
        this.t_email = t_email;
        this.t_office=t_office;
        this.t_id = t_id;


        //Toast.makeText(this,sub_mon,Toast.LENGTH_SHORT);



    }
}
