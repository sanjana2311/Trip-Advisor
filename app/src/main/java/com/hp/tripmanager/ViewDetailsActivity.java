package com.hp.tripmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
    }
    public void tripWise(View view)
    {
        Intent intent = new Intent(this, TripWiseViewActivity.class);
        startActivity(intent);
    }
    public void categoryWise(View view)
    {
        Intent intent = new Intent(this, TripWiseView1Activity.class);
        startActivity(intent);
    }
    public void dayWise(View view)
    {
        Intent intent = new Intent(this, TripWiseView2Activity.class);
        startActivity(intent);
    }
}
