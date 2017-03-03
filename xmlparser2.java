package com.example.balogunal_amin.rrsfeeds;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by balogunal-amin on 2016-04-14.
 */
public class xmlparser2 extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        WebView w1=(WebView)findViewById(R.id.webView);
        w1.loadUrl("http://www.sciencemag.org/rss/news_current.xml");
    }
}
