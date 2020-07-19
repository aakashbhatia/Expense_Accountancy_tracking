package com.example.aakashbhatia.demo_2_textbox;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class fragment_add_expense extends Fragment{

    public EditText expense_amount;
    public EditText expense_note;
    public Image bill_image;
    public Spinner expense_category;

    public static final int RESULT_OK = -1;
    public final int SELECT_PHOTO = 1;
    public ImageView imageView;
    db_handler handler;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        Button browsebutton = (Button) view.findViewById(R.id.browsebutton);
        Button clickButton = (Button) view.findViewById(R.id.add_expense);
        expense_amount = (EditText) view.findViewById(R.id.amount);
        expense_category = (Spinner) view.findViewById(R.id.listcategory);
        //bill_image = (EditText) view.findViewById(R.id.);
        expense_note = (EditText) view.findViewById(R.id.note);
        handler = new db_handler(getActivity(), null, null, 1);

        imageView = (ImageView) view.findViewById(R.id.imageView);
        browsebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        clickButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(expense_amount.getText().toString())){
                    Toast.makeText(getActivity(), "Amount should not be empty", Toast.LENGTH_SHORT).show(); return;
                }
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
                int temp = prefs.getInt("unused", 0);
                temp=temp-Integer.parseInt(expense_amount.getText().toString());
                editor.putInt("unused", temp);
                editor.commit();
                table_expense expense = new table_expense();
                expense.setFamilyid(prefs.getString("familyid", ""));
                expense.setUserid(prefs.getInt("userid", -1));
                expense.setExpense_amount(Integer.parseInt(expense_amount.getText().toString()));
                Spinner spinner = (Spinner) getActivity().findViewById(R.id.listcategory);
                String text = spinner.getSelectedItem().toString();
                expense.setExpense_category(text.toString());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String date = df.format(new Date());
                expense.setDate_time(date.toString());
                //user.setBill_image(bill_image.getText().toString());
                expense.setNote(expense_note.getText().toString());
                handler.addexpense(expense);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setTitle("Expense added Succesfully");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                LinearLayout parent = new LinearLayout(v.getContext());

                final ImageView image1 = new ImageView(v.getContext());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    image1.setImageDrawable(getResources().getDrawable(R.drawable.like, getActivity().getApplicationContext().getTheme()));
                } else {
                    image1.setImageDrawable(getResources().getDrawable(R.drawable.like));
                }
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(500,500);
                lp1.gravity=Gravity.CENTER;
                image1.setLayoutParams(lp1);
                parent.addView(image1);
                builder1.setView(parent);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                params.weight = 1.0f;
                params.gravity = Gravity.CENTER_HORIZONTAL;
                parent.setLayoutParams(params);
                parent.setOrientation(LinearLayout.VERTICAL);
                parent.setGravity(LinearLayout.HORIZONTAL);
                parent.setPadding(16,64,16,16);
                AlertDialog alert11 = builder1.create();
                alert11.show();
                /*Intent i1 = new Intent(getActivity(), content_home.class);
                String extratext = handler.databasetostring_expense();
                i1.putExtra("string", extratext);
                startActivity(i1);*/
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
}
