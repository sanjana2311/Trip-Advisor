package com.hp.tripmanager;



import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class TripDetailsActivity extends AppCompatActivity
{
    Spinner s1,s2;
    EditText et1,et2;
    ImageButton imageButton,imageButton2;
    static TextView tv5,tv6;
    TableLayout tl;

    String g,f;
    String source[]={"Select","Agra","Bhopal","Delhi","Noida","Surat","Jaipur","Mysore","Chennai","Banglore","Hyderabad","Shimla","Kashmir","Chandigarh","Dehradun"};
    String  destination[]={"Select","Agra","Bhopal","Delhi","Noida","Surat","Jaipur","Mysore","Chennai","Banglore","Hyderabad","Shimla","Kashmir","Chandigarh","Dehradun"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        Intent intent=getIntent();
        s1= (Spinner) findViewById(R.id.spinner1);
        s2= (Spinner) findViewById(R.id.spinner2);
        et1= (EditText) findViewById(R.id.editText1);
        et2= (EditText) findViewById(R.id.editText2);
        //et3= (EditText) findViewById(R.id.editText3);
        imageButton= (ImageButton) findViewById(R.id.imageButton);
        imageButton2= (ImageButton) findViewById(R.id.imageButton2);
        tv5= (TextView) findViewById(R.id.textView5);
        tv6= (TextView) findViewById(R.id.textView6);
        Date d = new Date();
        CharSequence s  = DateFormat.format("yyyy/MM/dd", d.getTime());
        tv5.setText(s);
        tv6.setText(s);

        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,source);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);
        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,destination);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter2);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                g=source[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                Toast.makeText(TripDetailsActivity.this,"Spinner Not Selected ",Toast.LENGTH_SHORT).show();
            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                f=destination[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                Toast.makeText(TripDetailsActivity.this,"Spinner Not Selected ",Toast.LENGTH_SHORT).show();
            }
        });

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
            tv5.setText(s);

            //Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
        }
    }
    public void datechose(View v)
    {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public static class DatePickerFrag extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            // month=month+1;
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
            tv6.setText(s);

            //Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
        }
    }
    public void datechoice(View v)
    {
        DialogFragment newFragment = new DatePickerFrag();
        newFragment.show(getFragmentManager(), "datePicker");

    }




    public void submit(View v)
    {
        int flag=0;
        if( et1.getText().toString().trim().length()==0)
        {
            Toast.makeText(this,"Enter Trip ID",Toast.LENGTH_LONG).show();
            flag=1;
        }
       SQLiteDatabase td = openOrCreateDatabase("TripDetailDBS",MODE_APPEND,null);
        Cursor cursor = td.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'TRIP_DETAIL'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                // table created
                String q = "Select * from TRIP_DETAIL";
                Cursor c = td.rawQuery(q, null);
                String i;
                while (c.moveToNext()) {
                    i = c.getString(0);
                    if(i.equals(et1.getText().toString()))
                    {
                        Toast.makeText(this,"Enter Unique Trip ID",Toast.LENGTH_LONG).show();
                        flag=1;
                        break;
                    }
                }
               // cursor.close();

            }
            else
            {

            }
            // table doesnot exist

            //cursor.close();
        }

/*
       String q = "Select * from TRIP_DETAIL";
        Cursor n=td.rawQuery("IF EXISTS(Select * from TRIP_DETAIL) ", null);

        int j=0;
        if(n!=null)
        {
           // j=n.getCount();
         }
        else
        {
            Cursor c = td.rawQuery(q, null);
            String i;
            while (c.moveToNext()) {
                i = c.getString(0);
                if(i.equals(et1.getText().toString()))
                {
                    Toast.makeText(this,"Enter Unique Trip ID",Toast.LENGTH_LONG).show();
                    flag=1;
                    break;
                }
            }
        }*/
        if(g.equals("Select"))
        {
            Toast.makeText(this,"Select Source",Toast.LENGTH_LONG).show();
            flag=1;
        }
        if(f.equals("Select"))
        {
            Toast.makeText(this,"Select Destination",Toast.LENGTH_LONG).show();
            flag=1;
        }
        if( et2.getText().toString().trim().length()==0)
        {
            Toast.makeText(this,"Enter Budget",Toast.LENGTH_LONG).show();
            flag=1;
        }
        if(flag==0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Save Expense!!");
            builder.setMessage("Are you sure you want to save ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String a = et1.getText().toString();
                    String b = et2.getText().toString();
                    // String c=et3.getText().toString();
                    String d = tv5.getText().toString();
                    String e = tv6.getText().toString();
                    SQLiteDatabase db = openOrCreateDatabase("TripDetailDBS", MODE_APPEND, null);
                    db.execSQL("create table if not exists TRIP_DETAIL(TRIP_ID varchar,Source varchar,Destination varchar,Approved varchar,Start varchar,End varchar,Balance varchar)");
                    db.execSQL("insert into TRIP_DETAIL(TRIP_ID,Source,Destination,Approved,Start,End,Balance)values('" + a + "','" + g + "','" + f + "','" + b + "','" + d + "','" + e + "','" + b + "')");

                    String q = "Select * from TRIP_DETAIL";
                    Cursor C = db.rawQuery(q, null);
                    String t, name, phone, ko, kot, kote;

                   /* while (C.moveToNext()) {
                        t = C.getString(0);
                        name = C.getString(1);
                        phone = C.getString(2);
                        ko = C.getString(3);
                        kot = C.getString(4);
                        kote = C.getString(5);
                        Toast.makeText(TripDetailsActivity.this, t + " " + name + " " + phone + " " + ko + " " + kot + " " + kote, Toast.LENGTH_SHORT).show();
                    }*/

                    db.close();


                }

            });//end of + button
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(TripDetailsActivity.this, "NOT SAVED", Toast.LENGTH_LONG).show();

                }
            });//end of -ve button


            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }//onClick end
}



