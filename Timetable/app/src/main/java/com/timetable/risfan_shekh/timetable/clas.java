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
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class clas extends AppCompatActivity {

    FloatingActionButton fab_cl;
    DatabaseHelper dh = new DatabaseHelper(this);
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Context context;
    public List<Classes_mon> class_mon;

    TabHost host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clas);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(class_mon);

        initializeData();
        initializeAdapter();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //host = (TabHost)findViewById(R.id.tabhost);




        //host.setup();
        fab_cl =(FloatingActionButton) findViewById(R.id.fab);

        ///getActionBar().setTitle("Czlass");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Class");

        /*TabSpec spec = host.newTabSpec("Monday");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Monday");
        host.addTab(spec);

        spec = host.newTabSpec("Tuesday");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tuesday");
        host.addTab(spec);

        spec = host.newTabSpec("Wednesday");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Wednesday");
        host.addTab(spec);

        spec = host.newTabSpec("Thursday");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Thursday");
        host.addTab(spec);

        spec = host.newTabSpec("Friday");
        spec.setContent(R.id.tab5);
        spec.setIndicator("Friday");
        host.addTab(spec);

*/


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        fab_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(clas.this,host.getCurrentTabTag(),Toast.LENGTH_SHORT).show();

                //host.getCurrentTabTag();
                Intent intent=new Intent(getApplicationContext(),class_add.class);
                //intent.putExtra( "day",host.getCurrentTabTag());
                startActivity(intent);

                //startActivity(new Intent(getApplicationContext(),class_add.class));
            }
        });


    }

    private void initializeAdapter() {
        adapter = new RecyclerViewAdapter(class_mon);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener(){


            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                final String t = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.id_c)).getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(clas.this);
                builder
                        .setMessage("Are you Sure to Delete this Class Record ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dh.class_delete(t);
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
        class_mon = new ArrayList<>();

        Cursor c = dh.class_getall();
        if(c.getCount()==0)
        {
            showMessage("Error","Nothing Found !");
        }
        else {

            while (c.moveToNext()) {


                class_mon.add(new Classes_mon(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(0)));


            }

            //class_mon.add(new Classes_mon("a","b","c","s","e","f"));
            //
            //showMessage("ABC",buffer.toString());
        }


    }

    public void showMessage(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();

    }
}

class Classes_mon extends clas{

    String sub_mon,tech_mon,room_mon,from_to_mon,day_mon,c_id;

    public Classes_mon(String sub, String tech, String room, String from, String to, String day, String c_id) {

        this.sub_mon = sub;
        this.tech_mon = tech;
        this.room_mon = room;
        this.from_to_mon = from + " - " + to;
        this.day_mon = day;
        this.c_id = c_id;

        //Toast.makeText(this,sub_mon,Toast.LENGTH_SHORT);



    }
}
