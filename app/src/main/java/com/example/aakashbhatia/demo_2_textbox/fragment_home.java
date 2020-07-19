package com.example.aakashbhatia.demo_2_textbox;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class fragment_home extends Fragment implements View.OnClickListener, OnChartValueSelectedListener{
    TextView t;
    View view;
    PieChart expensechart;
    TextView initial;
    TextView total;
    TextView current;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home,
                container, false);
        t = (TextView) view.findViewById(R.id.today);
        ImageButton settings = (ImageButton) view.findViewById(R.id.settings);
        Button detail = (Button) view.findViewById(R.id.detail);
        initial = (TextView) view.findViewById(R.id.initialbalance1);
        total = (TextView) view.findViewById(R.id.totalexpense1);
        current = (TextView) view.findViewById(R.id.currentbalance1);
        settings.setOnClickListener(this);
        detail.setOnClickListener(this);
        initial.setText("840162");
        total.setText("101774");
        current.setText("738388");

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        int uid = prefs.getInt("userid", -1);

        if(uid>-1){
            initial.setText(String.valueOf(prefs.getInt("salary", 0)));
            current.setText(String.valueOf(prefs.getInt("unused", 0)));
            total.setText(String.valueOf(prefs.getInt("salary", 0)-prefs.getInt("unused", 0)));
            /*db_handler handler = new db_handler(getActivity(), null, null, 1);
            int total = handler.count_expense(prefs.getInt("userid", -1),"all");
            Toast.makeText(getActivity(), "Total = " + String.valueOf(total), Toast.LENGTH_SHORT).show();*/
        }
        else {
            expensechart = (PieChart) view.findViewById(R.id.chart);
            expensechart.setUsePercentValues(true);
            ArrayList<Entry> yvalues = new ArrayList<Entry>();
            yvalues.add(new Entry(18f, 0));
            yvalues.add(new Entry(5f, 1));
            yvalues.add(new Entry(22f, 2));
            yvalues.add(new Entry(10f, 3));
            yvalues.add(new Entry(33f, 4));
            yvalues.add(new Entry(3f, 5));
            yvalues.add(new Entry(9f, 6));
            PieDataSet dataSet = new PieDataSet(yvalues, "These all are Expense Categories");
            ArrayList<String> xVals = new ArrayList<String>();
            xVals.add("Food");
            xVals.add("Tax charges");
            xVals.add("Rent");
            xVals.add("Utilities");
            xVals.add("Insurance");
            xVals.add("Fees");
            xVals.add("Grocery");
            PieData data = new PieData(xVals, dataSet);
            data.setValueFormatter(new PercentFormatter());
            expensechart.setData(data);
            expensechart.setDescription("This is Expense Pie Chart");
            expensechart.setDrawHoleEnabled(true);
            expensechart.setTransparentCircleRadius(58f);
            expensechart.setHoleRadius(58f);
            TypedArray ta = view.getContext().getResources().obtainTypedArray(R.array.rainbow);
            int[] colors = new int[ta.length()];
            for (int i = 0; i < ta.length(); i++) {
                colors[i] = ta.getColor(i, 0);
            }
            dataSet.setColors(colors);
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.WHITE);
            expensechart.setOnChartValueSelectedListener(this);
            expensechart.animateXY(1600, 1600);

            Legend legend = expensechart.getLegend();
            legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
            legend.setForm(Legend.LegendForm.SQUARE);
            legend.setFormSize(6f);
            legend.setXEntrySpace(16f);
            legend.setYEntrySpace(6f);
            legend.setWordWrapEnabled(true);
            // set what type of form/shape should be used legend.setXEntrySpace(8f); legend.setYEntrySpace(6f); legend.setXOffset(0f);
        }
        return view;
    }
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.settings:
                String[] array = {"item01","item02","item03","item04"};
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setTitle("Type Of Total Expense");
                builder1.setIcon(R.drawable.settings);
                builder1.setMessage("Select type of total expense from below list");
                builder1.setCancelable(true);
                LinearLayout parent = new LinearLayout(v.getContext());

                final TextView type1 = new TextView(v.getContext());
                final TextView type2 = new TextView(v.getContext());
                final TextView type3 = new TextView(v.getContext());
                final TextView type4 = new TextView(v.getContext());

                type1.setText("1. Today");
                type1.setTextColor(Color.parseColor("#0C77B4"));
                type1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                type1.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {
                    t.setText("Today's total expense");
                    type1.setBackgroundColor(Color.parseColor("#0C77B4"));
                    type1.setTextColor(Color.parseColor("#FFFFFF"));
                    type2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type2.setTextColor(Color.parseColor("#0C77B4"));
                    type3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type3.setTextColor(Color.parseColor("#0C77B4"));
                    type4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type4.setTextColor(Color.parseColor("#0C77B4"));
                }});
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT);
                lp1.setMargins(240,0,240,0);
                type1.setGravity(Gravity.CENTER_HORIZONTAL);
                type1.setLayoutParams(lp1);
                type1.setPadding(0,0,0,6);
                parent.addView(type1);

                type2.setText("2. Weekly");
                type2.setTextColor(Color.parseColor("#0C77B4"));
                type2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                type2.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {
                    t.setText("This week total expense");
                    type1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type1.setTextColor(Color.parseColor("#0C77B4"));
                    type2.setBackgroundColor(Color.parseColor("#0C77B4"));
                    type2.setTextColor(Color.parseColor("#FFFFFF"));
                    type3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type3.setTextColor(Color.parseColor("#0C77B4"));
                    type4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type4.setTextColor(Color.parseColor("#0C77B4"));
                }});
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT);
                lp2.setMargins(240,0,240,0);
                type2.setGravity(Gravity.CENTER_HORIZONTAL);
                type2.setLayoutParams(lp2);
                type2.setPadding(0,0,0,6);
                parent.addView(type2);

                type3.setText("3. Monthly");
                type3.setTextColor(Color.parseColor("#0C77B4"));
                type3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                type3.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {
                    t.setText("This month total expense");
                    type1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type1.setTextColor(Color.parseColor("#0C77B4"));
                    type2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type2.setTextColor(Color.parseColor("#0C77B4"));
                    type3.setBackgroundColor(Color.parseColor("#0C77B4"));
                    type3.setTextColor(Color.parseColor("#FFFFFF"));
                    type4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type4.setTextColor(Color.parseColor("#0C77B4"));
                }});
                LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT);
                lp3.setMargins(240,0,240,0);
                type3.setGravity(Gravity.CENTER_HORIZONTAL);
                type3.setLayoutParams(lp3);
                type3.setPadding(0,0,0,6);
                parent.addView(type3);

                type4.setText("4. Yearly");
                type4.setTextColor(Color.parseColor("#0C77B4"));
                type4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                type4.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v) {
                    t.setText("This year total expense");
                    type1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type1.setTextColor(Color.parseColor("#0C77B4"));
                    type2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type2.setTextColor(Color.parseColor("#0C77B4"));
                    type3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    type3.setTextColor(Color.parseColor("#0C77B4"));
                    type4.setBackgroundColor(Color.parseColor("#0C77B4"));
                    type4.setTextColor(Color.parseColor("#FFFFFF"));
                }});
                LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT);
                lp4.setMargins(240,0,240,0);
                type4.setGravity(Gravity.CENTER_HORIZONTAL);
                type4.setLayoutParams(lp4);
                type4.setPadding(0,0,0,6);
                parent.addView(type4);

                builder1.setView(parent);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                params.weight = 1.0f;
                params.gravity = Gravity.CENTER_HORIZONTAL;
                parent.setLayoutParams(params);
                parent.setOrientation(LinearLayout.VERTICAL);
                parent.setGravity(LinearLayout.HORIZONTAL);
                parent.setPadding(0,0,0,60);

                builder1.setPositiveButton(
                        "Apply",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                break;
            case R.id.detail:
                //Toast.makeText(getActivity(), "enter", Toast.LENGTH_SHORT).show();
                db_handler handler = new db_handler(getActivity(), null, null, 1);
                //Toast.makeText(getActivity(), "after db", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getActivity(), content_home.class);
                String extratext = handler.databasetostring_expense();
                //Toast.makeText(getActivity(), extratext, Toast.LENGTH_SHORT).show();
                if(extratext.equals("")){extratext="No expenses";}
                i1.putExtra("string", extratext);
                startActivity(i1);
                //Toast.makeText(getActivity(), "exit", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}