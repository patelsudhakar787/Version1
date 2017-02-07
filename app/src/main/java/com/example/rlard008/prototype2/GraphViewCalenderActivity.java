package com.example.rlard008.prototype2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class GraphViewCalenderActivity extends AppCompatActivity {
    RealmResults<RealmDataBase> result;
    Realm realm;

    private static String channel;
    private static String url="";
    private ArrayList<String> minute;
    private  ArrayList<Float>values;
    private ArrayList<Entry>entries;

    LineDataSet dataset;
    LineData data;
    LineChart linechart;
    private  String date="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_calender2);
        linechart=(LineChart)findViewById(R.id.chart);
        linechart.setVisibility(View.GONE);
        //creating Objects
        minute=new ArrayList<>();
        values=new ArrayList<>();
        entries=new ArrayList<>();




        //getting data Channel_Name from Realm Local DataBase
        RealmConfiguration config = new RealmConfiguration.Builder(GraphViewCalenderActivity.this).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
        result = realm.where(RealmDataBase.class).findAll();
        RealmDataBase s = result.get(0);
        channel = s.getChannel_Name();

        //getting data from Intent
        Intent intent = getIntent();
        date=intent.getStringExtra("date");
        Toast.makeText(GraphViewCalenderActivity.this,"Date---"+date,Toast.LENGTH_LONG).show();

        //getting ClientId and MachineID from SharedPreferences Local DataBase
        SharedPreferences sf = getSharedPreferences("param", MODE_PRIVATE);
        String clientId = sf.getString("clientId", "");
        Log.e("ClientId", "" + clientId);
        final String machineId = sf.getString("machineId", "");
        Log.e("MachineId", "" + machineId);

        url = "http://10.10.0.220:7070/VibeScopeV1/RangeServlet?machine_id=" + machineId + "&date="+date+ "&channel=" +channel;
        sendRequest();


        new Handler().postDelayed(
new Runnable() {

            @Override
            public void run() {
                linechart.setVisibility(View.VISIBLE);
                for(int i=0;i<minute.size();i++)
                {
                    entries.add(new Entry(values.get(i),i));

                }
                dataset = new LineDataSet(entries, "On X Axis : Time  MinInutes && On Y Axis : Values");
                data=new LineData(minute,dataset);

                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);

                linechart.setData(data);
                linechart.animateY(5000);

            }
        }, 500);

    }
    //volley Request Function
    public void sendRequest()
    {
        StringRequest stringrequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response->2",""+response);
                try {
                    JSONObject jo=new JSONObject(response);
                    JSONArray jarray=jo.getJSONArray("data");
                    for(int i=0;i<jarray.length();i++)
                    {
                        JSONObject job=jarray.getJSONObject(i);
                        String value=job.getString("values");
                        String time=job.getString("time");
                        if(value.equals("Yes"))
                        {
                         values.add(1f);
                        }
                        else if(value.equals("No"))
                        {
                            values.add(0f);
                        }
                        else {
                            values.add(Float.parseFloat(value));

                        }
                        minute.add(time);





                    }
                    Log.e("Minute",""+minute);
                    Log.e("Values",""+values);

                    //     Toast.makeText(GraphViewCalenderActivity.this,"Minute"+minute+"values"+values,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(ShowEnlargeChart1_8Activity.this,"Error"+error,Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);
    }





    }

