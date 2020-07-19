package com.example.aakashbhatia.demo_2_textbox;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class home extends AppCompatActivity implements View.OnClickListener {

    ImageButton member;
    TextView textmember;
    ImageButton expense;
    TextView textexpense;
    ImageButton saving;
    TextView textsaving;
    ImageButton home;
    TextView texthome;
    ImageButton report;
    TextView textreport;
    TextView title;
    String email;
    boolean doubleBackToExitPressedOnce = false;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = getIntent().getStringExtra("email");
        member = (ImageButton) findViewById(R.id.member);
        textmember = (TextView) findViewById(R.id.textmember);
        expense = (ImageButton) findViewById(R.id.expense);
        textexpense = (TextView) findViewById(R.id.textexpense);
        saving = (ImageButton) findViewById(R.id.saving);
        textsaving = (TextView) findViewById(R.id.textsaving);
        home = (ImageButton) findViewById(R.id.home);
        texthome = (TextView) findViewById(R.id.texthome);
        report = (ImageButton) findViewById(R.id.report);
        textreport = (TextView) findViewById(R.id.textreport);

        member.setOnClickListener(this);
        textmember.setOnClickListener(this);
        expense.setOnClickListener(this);
        textexpense.setOnClickListener(this);
        saving.setOnClickListener(this);
        textsaving.setOnClickListener(this);
        home.setOnClickListener(this);
        texthome.setOnClickListener(this);
        report.setOnClickListener(this);
        report.setOnClickListener(this);

        title = (TextView) findViewById(R.id.title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_home()).addToBackStack(null).commit();
                title.setText("Home");
                break;
            case R.id.texthome:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_home()).addToBackStack(null).commit();
                title.setText("Home");
                break;
            case R.id.member:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_member()).addToBackStack(null).commit();
                title.setText("Member");
                break;
            case R.id.textmember:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_member()).addToBackStack(null).commit();
                title.setText("Member");
                break;
            case R.id.expense:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_add_expense()).addToBackStack(null).commit();
                title.setText("Add Expense");
                break;
            case R.id.textexpense:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_add_expense()).addToBackStack(null).commit();
                title.setText("Add Expense");
                break;
            case R.id.report:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_report()).addToBackStack(null).commit();
                title.setText("Report");
                break;
            case R.id.textreport:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_report()).addToBackStack(null).commit();
                title.setText("Report");
                break;
            case R.id.saving:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_savings()).addToBackStack(null).commit();
                title.setText("Savings");
                break;
            case R.id.textsaving:
                getFragmentManager().beginTransaction().replace(R.id.fragment3, new fragment_savings()).addToBackStack(null).commit();
                title.setText("Savings");
                break;
            case R.id.fab:
                animateFAB();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            android.os.Process.killProcess(android.os.Process.myPid());
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void animateFAB(){
        if(isFabOpen){
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;

        }
    }
}