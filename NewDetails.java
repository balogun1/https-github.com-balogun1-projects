package com.example.balogunal_amin.rrsfeeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewDetails extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_details);
        webView=(WebView)findViewById(R.id.web_view);
        Bundle bundle= getIntent().getExtras();
        webView.loadUrl(bundle.getString("Link"));
    }
}
