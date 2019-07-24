package com.hp.tripmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TripWiseViewActivity extends ListActivity
{
    ArrayList screens;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        SQLiteDatabase db=openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
        String q="Select * from TRIP_DETAIL";
        Cursor c=db.rawQuery(q,null);
        String id;
        screens=new ArrayList();
        while (c.moveToNext())
        {
            id=c.getString(0);
            screens.add(id);
        }
        db.close();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,screens);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Intent intent=new Intent(TripWiseViewActivity.this,ViewTripWiseExpenseActivity.class);
        String str= (String) screens.get(position);
        intent.putExtra("id", str);

        startActivity(intent);
    }
}
