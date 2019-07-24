package com.hp.tripmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewBalanceDetailsActivity extends AppCompatActivity
{
    TextView tv1, tv2, tv3, tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_balance_details);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        Intent intent = getIntent();
        String getId = intent.getStringExtra("id");
        Toast.makeText(this, getId, Toast.LENGTH_LONG).show();
        SQLiteDatabase db = openOrCreateDatabase("TripDetailDBS", MODE_APPEND, null);
        Cursor c;
        String r = "Select * from TRIP_DETAIL where TRIP_ID='"+getId+"' ";
        c = db.rawQuery(r, null);
        String tripId,abudget,aleft;
        int aspend;
        while(c.moveToNext()) {
            tripId = c.getString(0);
            tv1.append(tripId);
            abudget = c.getString(3);
            tv2.append(abudget);
            aleft = c.getString(6);
            tv4.append(aleft);
            int a = Integer.parseInt(abudget);
            int b = Integer.parseInt(aleft);
            aspend = a - b;
            tv3.append(String.valueOf(aspend));

        }
    }
}
