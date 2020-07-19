package com.example.aakashbhatia.demo_2_textbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class content_home extends AppCompatActivity {
    TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_home);
        temp = (TextView) findViewById(R.id.textView);
        String s = getIntent().getStringExtra("string");
        temp.setText(s);
    }
}
