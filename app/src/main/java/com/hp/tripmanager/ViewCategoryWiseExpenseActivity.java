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

public class ViewCategoryWiseExpenseActivity extends AppCompatActivity
{
   // TextView tv1, tv2,tv3;
   TableLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category_wise_expense);
      /*  tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);*/
        tl=(TableLayout)findViewById(R.id.tablelayout1);
        Intent intent = getIntent();
        String getId = intent.getStringExtra("id");
        Toast.makeText(this, getId, Toast.LENGTH_LONG).show();
        SQLiteDatabase db = openOrCreateDatabase("ExpenseDetailsDBMS", MODE_APPEND, null);
       // Cursor c;
      /*  String r = "Select * from TRIP_DETAIL where TRIP_ID='"+getId+"' ";
        c = db.rawQuery(r, null);
        String tripId,to,from;
        while(c.moveToNext()) {
            tripId = c.getString(0);
            tv1.append(tripId);
            to = c.getString(1);
            tv2.append(to);
            from = c.getString(2);
            tv3.append(from);
        }*/
        TableRow tr;
        String q="Select CATEGORY,sum(AMOUNT) from EXPENSE_DETAILS where TID='" + getId + "' group by CATEGORY";
        Cursor c=db.rawQuery(q,null);
        String category,amount;
        tr=new TableRow(this);
        TextView tv3=new TextView(this);
        tv3.setTextSize(25);
        tv3.setWidth(260);
        tv3.setTextColor(Color.BLACK);
        //tv1.setBackgroundColor(Color.CYAN);
        tv3.setText("CATEGORY");

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
            category=c.getString(0);
            amount=c.getString(1);
            tr=new TableRow(this);
            TextView tv1=new TextView(this);
            tv1.setTextSize(25);
            tv1.setWidth(200);
            //tv1.setBackgroundColor(Color.CYAN);
            tv1.setText("     "+category+" ");

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
