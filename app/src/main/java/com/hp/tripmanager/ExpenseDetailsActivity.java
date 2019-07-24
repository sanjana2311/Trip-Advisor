package com.hp.tripmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class ExpenseDetailsActivity extends AppCompatActivity
{
    EditText ed1,ed2;
    ImageButton imagebutton3;
    static TextView tv21;
    Spinner sp1,sp2;
    String cat[]={"Select","Travel","Lodge","Meal","Others"};
    String ido[];
    String a,b,c;
    static String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);
        Intent intent=getIntent();
        ed1= (EditText) findViewById(R.id.editText1);
        ed2= (EditText) findViewById(R.id.editText2);
        imagebutton3= (ImageButton) findViewById(R.id.imageButton3);
        tv21= (TextView) findViewById(R.id.textView21);
        Date d = new Date();
        CharSequence s  = DateFormat.format("yyyy/MM/dd", d.getTime());
        tv21.setText(s);
        sp1 = (Spinner) findViewById(R.id.spinner1);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter adp=new ArrayAdapter(this,android.R.layout.simple_list_item_1,cat);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                a=cat[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ExpenseDetailsActivity.this,"Nothing Selected",Toast.LENGTH_SHORT).show();

            }
        });//spinner 1 set
        int n1=0;
        SQLiteDatabase td = openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
        String q = "Select * from TRIP_DETAIL";
        Cursor n=td.rawQuery("Select * from TRIP_DETAIL", null);
        int j=0;
        if(n.moveToNext())
        {
            j=n.getCount();
            Toast.makeText(this,"Count: "+j,Toast.LENGTH_LONG).show();
        }

        ido = new String[j];

        Cursor c=td.rawQuery(q, null);
        int i=0;
        while(c.moveToNext())
        {
            ido[i] = c.getString(0);
            i++;
        }
        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,ido);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(ad);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                b = ido[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ExpenseDetailsActivity.this,"Nothing Selected",Toast.LENGTH_SHORT).show();

            }
        });//spinner2 set


    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            month=month+1;
            String s="";
            s=s+year+"/";
            s=s+month+"/";
            s=s+day;
            tv21.setText(s);
            //Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
        }
    }

    public void datepick(View v)
    {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public void particulars(View v)
    {

        if(a.equals("Meal"))
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Choose option :");
            final String options[]={"Breakfast","Lunch","Dinner","Miscellaneous"};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    c=options[which];
                    ed1.setText(c);
                    //set text in expense details
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();}
        else if(a.equals("Travel"))
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Choose option :");
            final String options[]={"Car","Flight","Train","Miscellaneous"};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    c=options[which];
                    ed1.setText(c);
                    //set text in expense details
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }

    public void addToDB(View view)
    {
        int flag=0;
        if(a.equals("Select"))
        {
            Toast.makeText(this,"Select Category",Toast.LENGTH_LONG).show();
            flag=1;
        }
        if( ed1.getText().toString().trim().length()==0)
        {
            Toast.makeText(this,"Enter Particulars",Toast.LENGTH_LONG).show();
            flag=1;
        }
        if( ed2.getText().toString().trim().length()==0)
        {
            Toast.makeText(this,"Enter Amount",Toast.LENGTH_LONG).show();
            flag=1;
        }
        if(flag==0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Save Expense??");
            builder.setMessage("Are you sure you want to save ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String edit1 = ed1.getText().toString();
                    String edit2 = ed2.getText().toString();
                    String edit3 = tv21.getText().toString();

                    SQLiteDatabase db = openOrCreateDatabase("ExpenseDetailsDBMS", MODE_APPEND, null);
                   // db.execSQL("drop table if exists EXPENSE_DETAILS");
                    db.execSQL("create table if not exists EXPENSE_DETAILS(ID integer primary key autoincrement,CATEGORY varchar,PARTICULAR varchar,AMOUNT varchar,DATE varchar,TID varchar)");
                    // Log.d("TESTING" , "************Table created**********");
                    db.execSQL("insert into EXPENSE_DETAILS(CATEGORY,PARTICULAR,AMOUNT,DATE,TID)values('" + a + "','" + edit1 + "','" + edit2 + "','" + edit3 + "','" + b + "');");

              /*  String q="Select * from EXPENSE_DETAILS";
                Cursor C=db.rawQuery(q,null);
                String name,phone,ko;
                int t;
                while(C.moveToNext())
                {
                    t=C.getInt(0);
                    name=C.getString(1);
                    phone=C.getString(2);
                    ko=C.getString(3);
                    Toast.makeText(ExpenseDetailsActivity.this,t+" "+name+" "+phone+" "+ko, Toast.LENGTH_SHORT).show();

                }*/


                    db.close();
                    SQLiteDatabase db1 = openOrCreateDatabase("TripDetailDBS", MODE_APPEND, null);
                    String q = "Select * from TRIP_DETAIL where TRIP_ID='" + b + "' ";
                    Cursor c = db1.rawQuery(q, null);
                    String balance = "0";
                    if (c.moveToNext()) {
                        balance = c.getString(6);
                    }
                    int bal = Integer.parseInt(balance);
                    bal = bal - Integer.parseInt(edit2);
                    db1.execSQL("update TRIP_DETAIL set Balance='" + bal + "' where TRIP_ID='" + b + "' ");
                    db1.close();


                }
            });//end of + button
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ExpenseDetailsActivity.this, "NOT SAVED", Toast.LENGTH_LONG).show();

                }
            });//end of -ve button


            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }//end of onClich button


}


