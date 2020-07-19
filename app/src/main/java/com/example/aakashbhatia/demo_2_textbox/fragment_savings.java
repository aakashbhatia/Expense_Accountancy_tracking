package com.example.aakashbhatia.demo_2_textbox;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class fragment_savings extends Fragment implements View.OnClickListener
{
    public Button add_saving_button;
    public EditText saving_amount;
    public EditText saving_description;
    public Spinner saving_type;
    public EditText start_date;
    public EditText end_date;
    private Calendar startcalendar;
    private Calendar endcalendar;
    db_handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_savings, container, false);

        add_saving_button= (Button) view.findViewById(R.id.savebutton);
        saving_amount = (EditText) view.findViewById(R.id.amount);
        saving_type = (Spinner) view.findViewById(R.id.savingtype);
        saving_description= (EditText) view.findViewById(R.id.savingdesc);
        start_date = (EditText) view.findViewById(R.id.startdate);
        end_date = (EditText) view.findViewById(R.id.enddate);
        add_saving_button.setOnClickListener(this);
        handler=new db_handler(getActivity(),null,null,1);

        startcalendar = Calendar.getInstance();
        endcalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener startdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                startcalendar.set(Calendar.YEAR, year);
                startcalendar.set(Calendar.MONTH, monthOfYear);
                startcalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatestartdate();
            }
        };
        final DatePickerDialog.OnDateSetListener enddate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                endcalendar.set(Calendar.YEAR, year);
                endcalendar.set(Calendar.MONTH, monthOfYear);
                endcalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateenddate();
            }
        };
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), startdate, startcalendar.get(Calendar.YEAR), startcalendar.get(Calendar.MONTH),
                        startcalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        start_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                start_date.performClick();}
            }
        });
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), enddate, endcalendar.get(Calendar.YEAR), endcalendar.get(Calendar.MONTH),
                        endcalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        end_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                end_date.performClick();}
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savebutton:
                table_savings savings = new table_savings();
                savings.setFamilyid("");
                savings.setUserid(1616);
                int savingamt;
                if(TextUtils.isEmpty(saving_amount.getText().toString())){savingamt=0;}
                else{savingamt=Integer.parseInt(saving_amount.getText().toString());}
                savings.setSaving_amount(savingamt);
                savings.setSaving_type(saving_type.getSelectedItem().toString());
                savings.setStartdate(start_date.getText().toString());
                savings.setEnddate(end_date.getText().toString());
                savings.setDescription(saving_description.getText().toString());
                handler.addsavings(savings);
                Intent i1 = new Intent(getActivity(), content_home.class);
                String extratext = handler.databasetostring_saving();
                i1.putExtra("string", extratext);
                startActivity(i1);
                break;
        }
    }

    private void updatestartdate() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        start_date.setText(sdf.format(startcalendar.getTime()));
    }

    private void updateenddate() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        end_date.setText(sdf.format(endcalendar.getTime()));
    }
}
