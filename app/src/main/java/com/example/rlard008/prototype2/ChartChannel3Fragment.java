package com.example.rlard008.prototype2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ChartChannel3Fragment extends Fragment {
    GraphView graphView;
    //TextView textView;
    //EditText edittextcurrentvalues;

    ArrayList<Double> minute3=new ArrayList<>();
    ArrayList<Double>values3=new ArrayList<>();
    ArrayList<DataPoint>qwerty3;

    DataPoint[] datapointarray3;

    LineGraphSeries<DataPoint> series;

    RealmDataBase re;
    Realm realm;
    double minutes;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView=inflater.inflate(R.layout.layout_graph,container,false);
        graphView=(GraphView)itemView.findViewById(R.id.graph);
        TextView textView=(TextView)itemView.findViewById(R.id.textviewchannel);
        textView.setText("Channel_3");
        qwerty3=new ArrayList<>();
        RealmConfiguration config=new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(config);
        realm= Realm.getDefaultInstance();


        //ploting label on Y axis
        Viewport viewport = graphView.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(3);
        viewport.setScrollable(true);

        //ploting lable on X axis
        Viewport viewport1 = graphView.getViewport();
        viewport1.setXAxisBoundsManual(true);
        viewport1.setMinX(0);
        viewport1.setMaxX(60);
        viewport1.setScrollable(true);

        final android.os.Handler handler = new android.os.Handler();
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            Date d = new Date();
                            minutes = d.getMinutes();
                            minute3.add(minutes);
                            getRequest();
                            int i = minute3.size();
                            int j = values3.size();
                            DataPoint dpoint = new DataPoint(minute3.get(i - 1), values3.get(j - 1));
                            qwerty3.add(dpoint);
                            Log.e("Channel---3", "" + qwerty3);
                            DataPoint[] array = new DataPoint[qwerty3.size()];
                            for (int k = 0; k < qwerty3.size(); k++) {
                                array[k] = qwerty3.get(k);
                            }
                            datapointarray3= array;
                            series = new LineGraphSeries<DataPoint>(datapointarray3);
                            series.setThickness(5);
                            series.setAnimated(false);
                            graphView.addSeries(series);} catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };//end of timer task
        timer.schedule(task, 0, 60*1000); //calling schduler function to schdule the time interval


        graphView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1="channel_3";
                re = new RealmDataBase();
                realm.beginTransaction();
                re.setChannel_Name(str1);
                realm.copyToRealmOrUpdate(re);
                realm.insertOrUpdate(re);
                realm.commitTransaction();


                Intent in=new Intent(getContext(),ShowEnlargeChart1_8Activity.class);
                in.putExtra("min3",minute3);
                in.putExtra("val",values3);
                startActivity(in);
            }
        });
        return itemView;
    }

    public void getRequest()
    {
        Call<SelectPojo> response=service.getData();
        response.enqueue(new Callback<SelectPojo>() {
            @Override
            public void onResponse(Call<SelectPojo> call, retrofit2.Response<SelectPojo> response) {
                try {
                    DataPojo d = response.body().getData();
                    String channel3 = d.getChannel_3();
                    switch (channel3)
                    {
                        case "Yes":  values3.add(1.0);
                            break;
                        case "No":values3.add(0.0);
                            break;
                        default:
                            Toast.makeText(getContext(),"NoValue",Toast.LENGTH_LONG).show();
                            break;
                    }
                    }
                catch (NullPointerException e)
                {
                   // values3.add(0.0);
                }
            }@Override
            public void onFailure(Call<SelectPojo> call, Throwable t) {

            }
        });
    }
    Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.10.0.220:7070/VibeScopeV1/").addConverterFactory(GsonConverterFactory.create()).build();
    ApIInterface service=retrofit.create(ApIInterface.class);

}
