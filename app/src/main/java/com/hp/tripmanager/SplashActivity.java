package com.hp.tripmanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity
{
    TextView tv1;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.tripicon);
        tv1 = (TextView) findViewById(R.id.textView1);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, FrontActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}
