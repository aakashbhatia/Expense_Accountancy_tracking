package com.example.aakashbhatia.demo_2_textbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME = 1600;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.splashScreenTheme);
        setContentView(R.layout.activity_splashscreen);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(logo.getDrawable().getIntrinsicWidth(),logo.getDrawable().getIntrinsicWidth());
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        logo.setLayoutParams(layoutParams);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
                int uid = prefs.getInt("userid", -1);
                if(uid < 0) {
                    Intent i = new Intent(splashscreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                Intent intent = new Intent(splashscreen.this, home.class);
                startActivity(intent);
                finish();}
            }
        }, SPLASH_TIME);
    }
}
