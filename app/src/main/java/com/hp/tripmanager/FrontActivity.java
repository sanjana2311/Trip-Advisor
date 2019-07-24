package com.hp.tripmanager;

import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FrontActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        Intent intent=getIntent();
    }

    public void add(View v)
    {
        Intent intent = new Intent(this,TripDetailsActivity.class);
        startActivity(intent);
    }

  public void view(View v)
    {
        SQLiteDatabase td = openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
        Cursor cursor = td.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'TRIP_DETAIL'", null);
        if(cursor!=null) {
            if (cursor.getCount() > 0) {
                // table exist
                Intent intent = new Intent(this, ViewDetailsActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Add Trip First", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void expense(View v)
    {
        SQLiteDatabase td = openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
        Cursor cursor = td.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'TRIP_DETAIL'", null);
        if(cursor!=null) {
            if (cursor.getCount() > 0) {
                // table exist
                Intent intent = new Intent(this, ExpenseDetailsActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Add Trip First", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void balance(View v)
    {
        SQLiteDatabase td = openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
        Cursor cursor = td.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'TRIP_DETAIL'", null);
        if(cursor!=null) {
            if (cursor.getCount() > 0) {
                // table exist
                Intent intent = new Intent(this,ViewBalanceActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Add Trip First", Toast.LENGTH_LONG).show();
            }
        }

    }

}

