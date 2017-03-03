package com.example.balogunal_amin.rrsfeeds;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by balogunal-amin on 2016-04-02.
 */
public class SplashActivity extends Activity {
    private Animation  imageSwitcher1;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh_activity);
    ImageView image = (ImageView) findViewById(R.id.imageView);
        TextView text = (TextView)findViewById(R.id.textView);
        //final Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
       // image.startAnimation(animation);
        //text.startAnimation(animation);
       Button  button1=(Button)findViewById(R.id.feed1);
        Button button2=(Button)findViewById(R.id.feed2);
        Button button3=(Button)findViewById(R.id.feed3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), TabActivity.class);
                startActivity(myIntent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), ScrollActivity.class);
                startActivity(myIntent);
            }
        });


    }

}
