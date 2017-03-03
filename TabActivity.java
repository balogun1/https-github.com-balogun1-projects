package com.example.balogunal_amin.rrsfeeds;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class TabActivity extends FragmentActivity {
    RecyclerView recyclerView;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview2);
        ReadRss2 readRss2 = new ReadRss2(this,recyclerView);
        readRss2.execute();

        button2 = (Button)findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(), SplashActivity.class);
                startActivity(intent2);
            }
        });


    }

}
