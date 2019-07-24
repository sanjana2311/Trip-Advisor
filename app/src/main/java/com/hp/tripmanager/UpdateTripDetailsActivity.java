package com.hp.tripmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hp.tripmanager.R;

import java.util.ArrayList;

public class UpdateTripDetailsActivity extends AppCompatActivity
{
    Spinner sp1;
    String s;
    String arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip_details);
        sp1= (Spinner) findViewById(R.id.spinner1);
        SQLiteDatabase db=openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
        String q="Select * from TRIP_DETAIL";
        Cursor c=db.rawQuery(q,null);
        int n=0;
        if(c.moveToNext())
        {
            n=c.getCount();
        }
        arr=new String[n];
        int i=0;
        c=db.rawQuery(q,null);
        while (c.moveToNext())
        {
            arr[i]=c.getString(0);
            i++;
        }
        db.close();

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s=arr[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//stmt termination


    }
    public void update(View v)
    {
        Intent intent=new Intent(UpdateTripDetailsActivity.this,UpdateActivity.class);
        String str= s ;
        intent.putExtra("id", str);
        startActivity(intent);
    }
}
