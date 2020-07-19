package com.example.aakashbhatia.demo_2_textbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView logo;
    EditText email;
    Button signin;
    TextView forgotpassword;
    TextView newuser;
    boolean doubleBackToExitPressedOnce = false;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo) ;
        email = (EditText) findViewById(R.id.email) ;
        signin = (Button) findViewById(R.id.signin);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        newuser = (TextView) findViewById(R.id.newuser);
        signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(MainActivity.this);
        forgotpassword.setOnClickListener(MainActivity.this);
        newuser.setOnClickListener(MainActivity.this);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int restoredText = prefs.getInt("idName", 0);
        if (restoredText != 0) {
            String name = prefs.getString("name", "No name defined");
            int idName = prefs.getInt("idName", 0);
            Intent i = new Intent(this, home.class);
            finish();
            startActivity(i);
        }

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int deviceWidth = metrics.widthPixels;
            int deviceHeight = metrics.heightPixels;

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) logo.getLayoutParams();
            params.topMargin=(int)((10*deviceHeight)/100);
            params.height=(int)((45*deviceWidth)/100);
            params.width=(int)((45*deviceWidth)/100);
            double x = Math.pow(deviceWidth/metrics.xdpi,2);
            double y = Math.pow(deviceHeight/metrics.ydpi,2);
            double screenInches = Math.sqrt(x+y);
            if(screenInches<=5){params.topMargin=(int)((screenInches*deviceHeight)/100);}
            if(screenInches<=4){params.topMargin=(int)(((screenInches/5)*deviceHeight)/100);}
                logo.setLayoutParams(params);

            params=(ViewGroup.MarginLayoutParams)email.getLayoutParams();
            params.topMargin=(int)((6.3*deviceHeight)/100);
            email.setLayoutParams(params);

            params=(ViewGroup.MarginLayoutParams)signin.getLayoutParams();
            params.topMargin=(int)((3.2*deviceHeight)/100);
            signin.setLayoutParams(params);

            params=(ViewGroup.MarginLayoutParams)forgotpassword.getLayoutParams();
            params.topMargin=(int)((2.1*deviceHeight)/100);
            forgotpassword.setLayoutParams(params);

            params=(ViewGroup.MarginLayoutParams)newuser.getLayoutParams();
            params.topMargin=(int)((0.6*deviceHeight)/100);
            newuser.setLayoutParams(params);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin:
                EditText email_text = (EditText) findViewById(R.id.email);
                EditText password_text = (EditText) findViewById(R.id.password);
                String email = email_text.getText().toString();
                String password = password_text.getText().toString();
                db_handler handler=new db_handler(this,null,null,1);
                encryption enc = new encryption();
                if(handler.login(email,enc.encryptIt(password))){
                    Toast.makeText(this, "Here1", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, home.class);
                    finish();
                    startActivity(i);
                }
                else if(email.equals("parent")||email.equals("child")){
                Intent i = new Intent(this, home.class);
                i.putExtra("email", email);
                i.putExtra("password", password);
                finish();
                startActivity(i);}
                else{
                    Toast.makeText(this, "Please enter VALID email id & password combination ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forgotpassword:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Are You Sure? You Forgotten password?\n\nBy clicking on yes your password will be sent to your email-id");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String fromEmail = "quickjob627@gmail.com";
                                String fromPassword = "aapassword6";
                                String toEmails = "aakashbhatia2535@gmail.com,krutikamahavadia@gmail.com";
                                List<String> toEmailList = Arrays.asList(toEmails
                                        .split("\\s*,\\s*"));
                                String emailSubject = "This Is The First Mail From Expense Accountancy Tracking";
                                String emailBody = "Hello we had added the mailing facility in &quotExpense Accountancy Trackinh&quot\nthis facility is needed to be added in forgot password module and here users password will be fetched and sent in mail";
                                new SendMailTask(MainActivity.this).execute(fromEmail,
                                        fromPassword, toEmailList, emailSubject, emailBody);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                break;
            case R.id.newuser:
                Intent i1 = new Intent(this, registration.class);
                finish();
                startActivity(i1);
                break;
            default:
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
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

class GMail {

    final String emailPort = "587";// gmail's smtp port
    final String smtpAuth = "true";
    final String starttls = "true";
    final String emailHost = "smtp.gmail.com";


    String fromEmail;
    String fromPassword;
    List<String> toEmailList;
    String emailSubject;
    String emailBody;

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    public GMail() {

    }

    public GMail(String fromEmail, String fromPassword,
                 List<String> toEmailList, String emailSubject, String emailBody) {
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmailList = toEmailList;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);
        Log.i("GMail", "Mail server properties set.");
    }

    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        for (String toEmail : toEmailList) {
            Log.i("GMail", "toEmail: " + toEmail);
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");// for a html email
        // emailMessage.setText(emailBody);// for a text email
        Log.i("GMail", "Email Message created.");
        return emailMessage;
    }

    public void sendEmail() throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
        Log.i("GMail", "allrecipients: " + emailMessage.getAllRecipients());
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        Log.i("GMail", "Email sent successfully.");
    }

}

class SendMailTask extends AsyncTask {

    private ProgressDialog statusDialog;
    private Activity sendMailActivity;
    public boolean flag=false;
    public SendMailTask(Activity activity) {
        sendMailActivity = activity;
    }

    protected void onPreExecute() {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Getting ready...");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

    @Override
    protected Object doInBackground(Object... args) {
        try {
            Log.i("SendMailTask", "About to instantiate GMail...");
            publishProgress("Processing input....");
            GMail androidEmail = new GMail(args[0].toString(),
                    args[1].toString(), (List) args[2], args[3].toString(),
                    args[4].toString());
            publishProgress("Preparing mail message....");
            androidEmail.createEmailMessage();
            publishProgress("Sending email....");
            androidEmail.sendEmail();
            publishProgress("Email Sent.");
            Log.i("SendMailTask", "Mail Sent.");
            flag=true;
        } catch (Exception e) {

            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void onProgressUpdate(Object... values) {
        statusDialog.setMessage(values[0].toString());
    }

    @Override
    public void onPostExecute(Object result) {
        statusDialog.dismiss();
        if(!flag){
        Toast toast = Toast.makeText(sendMailActivity, "Error while sending email\nPlease check your internet connection and try again", Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();}
    }

}