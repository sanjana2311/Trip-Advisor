package com.hp.tripmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDayWiseExpenseActivity extends AppCompatActivity
{
    TableLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_day_wise_expense);
        tl=(TableLayout)findViewById(R.id.tablelayout1);
        Intent intent = getIntent();
        String getId = intent.getStringExtra("id");
        Toast.makeText(this, getId, Toast.LENGTH_LONG).show();
        SQLiteDatabase db = openOrCreateDatabase("ExpenseDetailsDBMS", MODE_APPEND, null);
        TableRow tr;
        String q="Select DATE,sum(AMOUNT) from EXPENSE_DETAILS where TID='" + getId + "' group by DATE";
        Cursor c=db.rawQuery(q,null);
        String date,amount;
        tr=new TableRow(this);
        TextView tv3=new TextView(this);
        tv3.setTextSize(25);
        tv3.setWidth(260);
        tv3.setTextColor(Color.BLACK);
        //tv1.setBackgroundColor(Color.CYAN);
        tv3.setText("DATE");

        TextView tv4=new TextView(this);
        tv4.setTextSize(25);
        tv4.setTextColor(Color.BLACK);
        // tv2.setBackgroundColor(Color.CYAN);
        tv4.setText("  AMOUNT SPEND");
        tr.addView(tv3);
        tr.addView(tv4);
        tl.addView(tr);
        while(c.moveToNext())
        {
            date=c.getString(0);
            amount=c.getString(1);
            tr=new TableRow(this);
            TextView tv1=new TextView(this);
            tv1.setTextSize(25);
            tv1.setWidth(200);
            //tv1.setBackgroundColor(Color.CYAN);
            tv1.setText(""+date+" ");

            TextView tv2=new TextView(this);
            tv2.setTextSize(25);
            // tv2.setBackgroundColor(Color.CYAN);
            tv2.setText("           "+amount+" ");
            tr.addView(tv1);
            tr.addView(tv2);
            tl.addView(tr);
        }
        db.close();
    }
}
