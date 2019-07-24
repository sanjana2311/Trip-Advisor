package com.hp.tripmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class ViewTripWiseExpenseActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_wise_expense);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        tv7 = (TextView) findViewById(R.id.textView7);
        Intent intent = getIntent();
        String getId = intent.getStringExtra("id");
        Toast.makeText(this,getId,Toast.LENGTH_LONG).show();
        SQLiteDatabase db = openOrCreateDatabase("TripDetailDBS", MODE_APPEND, null);
        Cursor c;
        String r = "Select * from TRIP_DETAIL where TRIP_ID='"+getId+"' ";
        c = db.rawQuery(r, null);
        String tripId, to, sdate, edate, abudget, aleft;
        int aspend;
        while(c.moveToNext()) {
            tripId = c.getString(0);
            tv1.append(tripId);
            to = c.getString(1);
            tv2.append(to);
            sdate = c.getString(4);
            tv3.append(sdate);
            edate = c.getString(5);
            tv4.append(edate);
            abudget = c.getString(3);
            tv5.append(abudget);
            aleft = c.getString(6);
            tv7.append(aleft);
            int a = Integer.parseInt(abudget);
            int b = Integer.parseInt(aleft);
            aspend = a - b;
            tv6.append(String.valueOf(aspend));

        }
        db.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                String str="com.hp.tripmanager.UpdateTripDetailsActivity";
                try
                {
                    Class c1 = Class.forName(str);
                    Intent intent = new Intent(this, c1);
                    startActivity(intent);
                }
                catch(Exception e)
                {

                }
                break;

            case R.id.item2:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                String str1="com.hp.tripmanager.DeleteTripDetailsActivity";
                try
                {
                    Class c = Class.forName(str1);
                    Intent intent = new Intent(this, c);
                    startActivity(intent);
                }
                catch(Exception e)
                {

                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
