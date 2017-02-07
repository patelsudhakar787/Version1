package com.example.rlard008.prototype2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


public class MonitorDataPerDayFragment extends Fragment {
    EditText editTextFromDate;
    EditText editTextToDate;
    Button buttonmonitor;
    String fromdate;
    String todate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.monitorcalenderfragment, container, false);
        editTextFromDate = (EditText) itemView.findViewById(R.id.edittextfromdate);
        editTextToDate = (EditText) itemView.findViewById(R.id.edittexttodate);
        buttonmonitor=(Button)itemView.findViewById(R.id.buttonMonitor);

        editTextFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final DatePicker picker = new DatePicker(getContext());
                picker.setCalendarViewShown(false);

                builder.setTitle("Create Year");
                builder.setView(picker);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = picker.getYear();
                        int month = picker.getMonth() + 1;
                        int day = picker.getDayOfMonth();
                        String fromDate="";
                        if(month<10 || day<10) {
                            fromDate = "" + year + "-"+"0"+ month + "-" +"0"+day;
                        }
                        else
                        {
                            fromDate = "" + year + "-"+ month + "-"+day;

                        }
                            editTextFromDate.setText("" + fromDate);
                    }
                });
        builder.show();

            }
        });

        editTextToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final DatePicker picker = new DatePicker(getContext());
                picker.setCalendarViewShown(false);

                builder.setTitle("Create Year");
                builder.setView(picker);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = picker.getYear();
                        int month = picker.getMonth() + 1;
                        int day = picker.getDayOfMonth();
                        String toDate="";
                        if(month<10 || day<10) {
                            toDate = "" + year + "-"+"0"+ month + "-" +"0"+day;
                        }
                        else
                        {
                            toDate = "" + year + "-"+ month + "-"+day;

                        }   editTextToDate.setText("" + toDate);

                    }
                });

                builder.show();
            }
        });


        buttonmonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fromdate=editTextFromDate.getText().toString();
                    todate=editTextToDate.getText().toString();

                Intent in=new Intent(getContext(),GraphViewCalenderActivity.class);
                in.putExtra("fromdate",fromdate);
                in.putExtra("todate",todate);
                startActivity(in);

            }
        });


        return itemView;

    }//eof On Create
}//eof Fragment

