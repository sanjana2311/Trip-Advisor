package com.hp.tripmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteTripDetailsActivity extends AppCompatActivity
{
    Spinner sp1;
    String arr[];
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_trip_details);
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
    public void delete(View v)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DeleteTripDetailsActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                SQLiteDatabase db=openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
                String q="Delete from TRIP_DETAIL where TRIP_ID='"+s+"' ";
                db.execSQL(q);
                db.close();
            }
        });//stmt termination
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(DeleteTripDetailsActivity.this, "Record Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });//end of stmt
        builder.setMessage("ARE U SURE TO DELETE ?");
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
