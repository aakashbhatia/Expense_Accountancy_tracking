package com.example.aakashbhatia.demo_2_textbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity implements View.OnClickListener {

    public Button register;
    public EditText username;
    public EditText email;
    public EditText password;
    public EditText copassword;
    public EditText salary;
    public EditText unused;
    public EditText contact;
    db_handler handler;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.Name);
        email = (EditText) findViewById(R.id.Email_id);
        password = (EditText) findViewById(R.id.Password_main);
        copassword = (EditText) findViewById(R.id.Password_co);
        salary = (EditText) findViewById(R.id.Salary);
        unused = (EditText) findViewById(R.id.UnusedSalary);
        contact = (EditText) findViewById(R.id.Contact);
        register.setOnClickListener(this);
        handler = new db_handler(this, null, null, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if(TextUtils.isEmpty(username.getText().toString())){
                    Toast.makeText(this, "Username should not be empty", Toast.LENGTH_SHORT).show(); return;}
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(this, "Email id should not be empty", Toast.LENGTH_SHORT).show(); return;}
                if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches())){
                    Toast.makeText(this, "Please enter valid Email-Id", Toast.LENGTH_SHORT).show(); return;}
                if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(this, "Password should not be empty", Toast.LENGTH_SHORT).show(); return;}
                if(TextUtils.isEmpty(copassword.getText().toString())){
                    Toast.makeText(this, "Confirm Password should not be empty", Toast.LENGTH_SHORT).show(); return;}
                if(!(password.getText().toString().matches(copassword.getText().toString()))){
                    Toast.makeText(this, "Password and Confirm Passwrod Doesn't match", Toast.LENGTH_SHORT).show(); return;}
                if((Integer.parseInt(salary.getText().toString())) < (Integer.parseInt(unused.getText().toString()))){
                    Toast.makeText(this, "Monthly salary cannot be less than currently unused salary", Toast.LENGTH_SHORT).show(); return;}

                table_user_details user = new table_user_details();
                user.setFamilyid("");
                user.setSalary(Integer.parseInt(salary.getText().toString()));
                user.setCurrentunusedsalary(Integer.parseInt(unused.getText().toString()));
                user.setUsername(username.getText().toString());
                user.setHead(0);
                encryption enc = new encryption();
                user.setPassword(enc.encryptIt(password.getText().toString()));
                user.setEmail(email.getText().toString());
                handler.adduser(user);

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("username", username.getText().toString());
                editor.putInt("userid", handler.getuserid());
                editor.putInt("salary", Integer.parseInt(salary.getText().toString()));
                editor.putInt("unused", Integer.parseInt(unused.getText().toString()));
                editor.putInt("head", 0);
                editor.putString("password", password.getText().toString());
                editor.putString("email", email.getText().toString());
                editor.commit();
                Intent i1 = new Intent(this, home.class);
                finish();
                startActivity(i1);
                break;
        }
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
