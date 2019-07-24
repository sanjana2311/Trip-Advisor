package com.hp.tripmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView tv1;
    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView1);

        SharedPreferences sp =getSharedPreferences("Demon File",0);
        String msg =sp.getString("STATUS","Not Initialized");
        if(msg.equals("Initialized"))
        {
            Intent intent = new Intent(MainActivity.this,SplashActivity.class);
            startActivity(intent);
        }
        else if(msg.equals("Not Initialized"))
        {
            t = new Thread()
            {
                public void run()
                {
                    try {
                        SharedPreferences sp =getSharedPreferences("Demon File", 0);

                        SharedPreferences.Editor editor =sp.edit();
                        editor.putString("STATUS", "Initialized");
                        editor.commit();
                        Thread.sleep(3000);
                        Intent intent = new Intent(MainActivity.this,SplashActivity.class);
                        startActivity(intent);

                    }
                    catch (Exception e)
                    {


                    }
                }//end of run()

            };//end of thread
            t.start();
        }//end of if loop
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
