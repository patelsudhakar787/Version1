package com.example.rlard008.prototype2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowEnlargeChart25_32Activity extends AppCompatActivity {

    GraphView graphView;
    TextView textView;
    EditText editText;
    ArrayList<Double> minute=new ArrayList<>();
    ArrayList<Double>values=new ArrayList<>();
    ArrayList<DataPoint>qwerty=new ArrayList<>();
    DataPoint[] datapointarray;
    LineGraphSeries<DataPoint> series;
    String url="";
    RealmResults<RealmDataBase> result;
    Realm realm;
    private static String channel;
    DataPoint dpoint;
    LinearLayout linear,linear1;
    TextView textView1,textView2,textView3;
    Calendar calendar = Calendar.getInstance();   // Gets the current date and time
    ListView listviewenlargechart;
    ArrayList<Parameter>arrayList=new ArrayList<>();
    String urllistView="";
    String machineId="";
    double minutes;
    Date date;

    int flag=0;
    Double val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_enlarge_chart);
        graphView=(GraphView)findViewById(R.id.graph);
        textView=(TextView)findViewById(R.id.current_value);
        editText=(EditText)findViewById(R.id.edittextvalue);
        qwerty= new ArrayList<>();
        linear = (LinearLayout) findViewById(R.id.con);
        linear1=(LinearLayout)findViewById(R.id.undertextview);
        textView1=(TextView)findViewById(R.id.textview1);
        textView2=(TextView)findViewById(R.id.textview2);
        textView3=(TextView)findViewById(R.id.textview3);
        listviewenlargechart=(ListView)findViewById(R.id.listViewEnlargeChart);


        Intent intent=getIntent();
        minute= (ArrayList<Double>) intent.getSerializableExtra("min");
        values= (ArrayList<Double>) intent.getSerializableExtra("val");



        RealmConfiguration config=new RealmConfiguration.Builder(ShowEnlargeChart25_32Activity.this).build();
        Realm.setDefaultConfiguration(config);
        realm= Realm.getDefaultInstance();

        SharedPreferences sf =getSharedPreferences("param",MODE_PRIVATE);
        String clientId = sf.getString("clientId", "");
        Log.e("ClientId",""+clientId);
        machineId=sf.getString("machineId","");
        Log.e("MachineId",""+machineId);
        //email="+email+"&password="+password;

        url="http://10.10.0.220:7070/VibeScopeV1/SelectServlet?clientId="+clientId+"&machine_id="+machineId;


        result=realm.where(RealmDataBase.class).findAll();
        RealmDataBase s=result.get(0);
        channel=s.getChannel_Name();
      //  Toast.makeText(ShowEnlargeChart25_32Activity.this,"RealmDataBase->"+channel,Toast.LENGTH_LONG).show();


        Viewport viewport = graphView.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(255);
        viewport.setScrollable(true);


        //ploting lable on X axis
        Viewport viewport1 = graphView.getViewport();
        viewport1.setXAxisBoundsManual(true);
        viewport1.setMinX(0);
        viewport1.setMaxX(60);
        viewport1.setScrollable(true);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if(minute !=null && values!=null) {
                        for(int l=0;l<minute.size()-1 &&l<values.size()-1;l++) {
                            dpoint = new DataPoint(minute.get(l), values.get(l));
                            qwerty.add(dpoint);
                        }


                        Log.e("ArrayList Of DataPoint", "" + qwerty);
                        DataPoint[] array = new DataPoint[qwerty.size()];
                        for (int k = 0; k < qwerty.size(); k++) {
                            array[k] = qwerty.get(k);
                        }


                        datapointarray = array;


                        Log.e("ToArray", "" + datapointarray);


                        series = new LineGraphSeries<DataPoint>(datapointarray);
                        graphView.setSoundEffectsEnabled(true);
                        graphView.addSeries(series);
                        getListView();
                    }
                    else
                    {
                        Toast.makeText(ShowEnlargeChart25_32Activity.this,"Nothing  To Show On Graph",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    // error, do something
                }


            }
        },1*1000);




        final Handler handler = new Handler();
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            Date d = new Date();
                            minutes = d.getMinutes();
                            minute.add(minutes);
                       //     Log.e("ArrayList of Minute", "" + minute);
                         //   sendRequest();

                            if (flag==0) {
                                sleepMethod();

                                val=values.get(values.size()-2);

                                editText.setText("" + val);
                            }
                            else {
                                setRequest();
                            }
                            //setRequest();
                            int i = minute.size()-1;
                            int j = values.size()-1;
                            DataPoint dpoint = new DataPoint(minute.get(i), values.get(j));

                            qwerty.add(dpoint);

                   //         Log.e("ArrayList Of DataPoint", "" + qwerty);
                            DataPoint[] array = new DataPoint[qwerty.size()];
                            for (int k = 0; k < qwerty.size(); k++) {
                                array[k] = qwerty.get(k);
                            }


                            datapointarray = array;
                            series = new LineGraphSeries<DataPoint>(datapointarray);
                            series.setThickness(5);
                            series.setAnimated(false);
                            graphView.addSeries(series);
                            if(minutes==59.0)
                            {
                                Intent intent3=new Intent(ShowEnlargeChart25_32Activity.this,MainActivity.class);
                                startActivity(intent3);

                            }


                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };//end of timer task
        timer.schedule(task, 0, 60*1000); //calling schduler function to schdule the time interval



        //clickListener On TextView
        //for today data on graph
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=new Date(System.currentTimeMillis()-(24*60*60*2000));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String twodaysago = sdf.format(date);
             //   Toast.makeText(ShowEnlargeChart25_32Activity.this,"Data2daysago"+twodaysago,Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(ShowEnlargeChart25_32Activity.this, GraphViewCalenderActivity.class);
                intent1.putExtra("date", twodaysago);
                startActivity(intent1);


            }
        });


        //for yesterday data on graph
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=new Date(System.currentTimeMillis()-(24*60*60*1000));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String yesterday=sdf.format(date);
            //    Toast.makeText(ShowEnlargeChart25_32Activity.this,"Yesterday"+yesterday,Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(ShowEnlargeChart25_32Activity.this, GraphViewCalenderActivity.class);
                intent2.putExtra("date", yesterday);
                startActivity(intent2);
            }
        });
        //for customize data on graph
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowEnlargeChart25_32Activity.this);
                final DatePicker picker = new DatePicker(ShowEnlargeChart25_32Activity.this);
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
                        String date="";
                        if(month<10 || day<10) {
                            date = "" + year + "-0"+ month + "-"+day;
                        }
                        else
                        {
                            date = "" + year + "-0"+ month + "-"+day;

                        }
                        Intent intent=new Intent(ShowEnlargeChart25_32Activity.this,GraphViewCalenderActivity.class);
                        intent.putExtra("date",date);
                        startActivity(intent);

                    }
                });
                builder.show();

            }
        });

    }
    void sleepMethod(){

        final Handler handler = new Handler();
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {

                            setRequest();
                            flag=1;

                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };//end of timer task
        timer.schedule(task, 60 * 1000); //calling schduler function to schdule the time interval
    }
    public void sendRequest()
    {
        StringRequest stringRequest=new StringRequest(url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


         //       Log.e("Response",""+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONObject jobj1=jsonObject.getJSONObject("data");

                    if(channel.equals("channel_25")) {
                     //   Toast.makeText(ShowEnlargeChart25_32Activity.this,"Hello",Toast.LENGTH_LONG).show();
                        double d=jobj1.getDouble("channel_25");
                        editText.setText(""+d);
                        values.add(d);

                    }

                    if(channel.equals("channel_26"))
                    {
                        double d=jobj1.getDouble("channel_26");
                        editText.setText(""+d);
                        values.add(d);

                    }
                    if(channel.equals("channel_27"))
                    {
                        double d=jobj1.getDouble("channel_27");
                        editText.setText(""+d);
                        values.add(d);

                    }

                    if(channel.equals("channel_28"))
                    {
                        double d=jobj1.getDouble("channel_28");
                        editText.setText(""+d);
                        values.add(d);
                    }

                    if(channel.equals("channel_29"))
                    {
                        double d=jobj1.getDouble("channel_29");
                        editText.setText(""+d);
                        values.add(d);

                    }

                    if(channel.equals("channel_30"))
                    {
                        double d=jobj1.getDouble("channel_30");
                        editText.setText(""+d);
                        values.add(d);
                    }

                    if(channel.equals("channel_31"))
                    {
                        double d=jobj1.getDouble("channel_31");
                        editText.setText(""+d);
                        values.add(d);

                    }

                    if(channel.equals("channel_32"))
                    {
                        double d=jobj1.getDouble("channel_32");
                        editText.setText(""+d);
                        values.add(d);

                    }

                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowEnlargeChart25_32Activity.this,"Error"+error,Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestqueue= Volley.newRequestQueue(this);
        requestqueue.add(stringRequest);


    }


    //loading fragment
    public void loadFragment()
    {
        MonitorDataPerDayFragment frg=new MonitorDataPerDayFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.content1,frg);
        ft.commit();
    }

    public void loadSelectCalenderCriteriaFragment()
    {
        SelectCalenderCriteriaFragment frg=new SelectCalenderCriteriaFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.undertextview,frg);
        ft.commit();
    }

    //distroying fragments
    public void destroySelectcalenderFragment()
    {
        Toast.makeText(ShowEnlargeChart25_32Activity.this,"Distroying Select Calender Fragment",Toast.LENGTH_LONG).show();
        SelectCalenderCriteriaFragment frg=new SelectCalenderCriteriaFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.remove(frg);
        ft.commit();
    }


    public void destroyMonitorDatePerDayFragment()
    {
        Toast.makeText(ShowEnlargeChart25_32Activity.this,"Distroying Monitor fragment",Toast.LENGTH_LONG).show();
        MonitorDataPerDayFragment frg=new MonitorDataPerDayFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.remove(frg);
        ft.commit();
    }
    @Override
    public void onBackPressed()
    {
        minute=null;
        values=null;
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        super.onBackPressed();  // optional depending on your needs
    }




    //populating arraylist by calling request
    public void getListView()
    {
        int hour=calendar.get(Calendar.HOUR);
        Date date1=new Date(System.currentTimeMillis()-(24*60*60*1000));
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = sdf1.format(date1);

        Date date2=new Date(System.currentTimeMillis()-(24*60*60*2000));
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        String twodaysago = sdf2.format(date2);

        Date date3=new Date(System.currentTimeMillis()-(24*60*60*7000));
        SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MM-dd");
        String lastweek=sdf3.format(date3);

        urllistView="http://10.10.0.220:7070/VibeScopeV1/EnlargeListView?yesterday="+yesterday+"&daysago="+twodaysago+"&lastweek="+lastweek+"&hour="+hour+"&machine_id="+machineId+"&channel="+channel;
        sendRequest1();
        ListViewAdapter adapter=new ListViewAdapter();
        listviewenlargechart.setAdapter(adapter);

    }
    public void sendRequest1()
    {
        StringRequest stringRequest=new StringRequest(urllistView, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("ResponseofListView",""+response);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jobject = jsonObject.getJSONObject("data");
                        String lw = jobject.getString("LastWeek");
                        Parameter p1 = new Parameter("LastWeek", lw);
                        String ys = jobject.getString("YesterDay");
                        Parameter p2 = new Parameter("YesterDay", ys);
                        String da = jobject.getString("DaysAgo");
                        Parameter p3 = new Parameter("DaysAgo", da);
                        arrayList.add(p1);
                        arrayList.add(p2);
                        arrayList.add(p3);


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowEnlargeChart25_32Activity.this,"Limited Connection",Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    //custom Adapter
    public class ListViewAdapter extends ArrayAdapter<Parameter>
    {

        public ListViewAdapter() {
            super(ShowEnlargeChart25_32Activity.this,R.layout.listview_enlarge_chart,arrayList);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.listview_enlarge_chart,parent,false);
            TextView textViewday=(TextView)view.findViewById(R.id.textviewname);
            TextView textViewvalue=(TextView)view.findViewById(R.id.textviewvalue);
            Parameter p=arrayList.get(position);
            textViewday.setText(""+p.getChannelNames());
            textViewvalue.setText(""+p.getChannelValues());
            return view;
        }
    }

    public void setRequest()
    {

        Call<SelectPojo> response=service.getData();
        response.enqueue(new Callback<SelectPojo>() {
            @Override
            public void onResponse(Call<SelectPojo> call, retrofit2.Response<SelectPojo> response) {
                //   Log.e("ResponseRetrofit",""+response.body().getData());
            try {
                DataPojo d = response.body().getData();
                //    Log.e("DataPojo",""+d.toString());


                if (channel.equals("channel_25")) {

                    String channel25 = d.getChannel_25();

                    values.add(Double.parseDouble(channel25));
                    editText.setText("" + channel25);

                }

                if (channel.equals("channel_26")) {

                    String channel26 = d.getChannel_26();

                    values.add(Double.parseDouble(channel26));
                    editText.setText("" + channel26);

                }

                if (channel.equals("channel_27")) {

                    String channel27 = d.getChannel_27();

                    values.add(Double.parseDouble(channel27));
                    editText.setText("" + channel27);

                }

                if (channel.equals("channel_28")) {

                    String channel28 = d.getChannel_28();

                    values.add(Double.parseDouble(channel28));
                    editText.setText("" + channel28);

                }

                if (channel.equals("channel_29")) {

                    String channel29 = d.getChannel_29();

                    values.add(Double.parseDouble(channel29));
                    editText.setText("" + channel29);

                }
                if (channel.equals("channel_30")) {

                    String channel30 = d.getChannel_30();

                    values.add(Double.parseDouble(channel30));
                    editText.setText("" + channel30);

                }

                if (channel.equals("channel_31")) {

                    String channel31 = d.getChannel_31();

                    values.add(Double.parseDouble(channel31));
                    editText.setText("" + channel31);

                }

                if (channel.equals("channel_32")) {

                    String channel32 = d.getChannel_32();

                    values.add(Double.parseDouble(channel32));
                    editText.setText("" + channel32);

                }
            }
            catch (NullPointerException e)
            {
            //    values.add(0.0);
            }

            }

            @Override
            public void onFailure(Call<SelectPojo> call, Throwable t) {

            }
        });
    }
    Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.10.0.220:7070/VibeScopeV1/").addConverterFactory(GsonConverterFactory.create()).build();
    ApIInterface service=retrofit.create(ApIInterface.class);


}
