package com.example.rlard008.prototype2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

public class ChartChannel1Fragment extends Fragment {
    GraphView graphView;
    TextView textView;

    ArrayList<Double> minute1 = new ArrayList<>();
    ArrayList<Double> values1 = new ArrayList<>();
    ArrayList<DataPoint> qwerty1;

    DataPoint[] datapointarray1;

    LineGraphSeries<DataPoint> series;


    Realm realm;
    RealmDataBase re;
    private String str1 = "";
    private Date d;
    double minutes;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.layout_graph, container, false);

        graphView = (GraphView) itemView.findViewById(R.id.graph);
        textView = (TextView) itemView.findViewById(R.id.textviewchannel);
        textView.setText("Channel_1");
        qwerty1 = new ArrayList<>();

        RealmConfiguration config = new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        //gettingClientID and MachineID from SharedPreferences


        final Handler handler = new Handler();
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            refresh();

                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };//end of timer task
        timer.schedule(task, 0, 60 * 1000); //calling schduler function to schdule the time interval


        graphView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = "channel_1";


                re = new RealmDataBase();
                realm.beginTransaction();
                re.setChannel_Name(str1);
                realm.copyToRealmOrUpdate(re);
                realm.commitTransaction();

                Intent in = new Intent(getContext(), ShowEnlargeChart1_8Activity.class);
                in.putExtra("min1", minute1);
                in.putExtra("val", values1);

                startActivity(in);

            }
        });

        return itemView;
    }


    public void getRequest() {

        Call<SelectPojo> response = service.getData();
        response.enqueue(new Callback<SelectPojo>() {
            @Override
            public void onResponse(Call<SelectPojo> call, retrofit2.Response<SelectPojo> response) {
                Log.e("ResponseRetrofit", "" + response.body().getData());

                try {
                    DataPojo d = response.body().getData();

                    String channel1 = d.getChannel_1();
                    switch (channel1) {
                        case "Yes":
                            values1.add(1.0);
                            break;
                        case "No":
                            values1.add(0.0);
                            break;
                        default:
                            Toast.makeText(getContext(), "NoValue", Toast.LENGTH_LONG).show();
                            break;
                    }

                } catch (NullPointerException e) {

                }
                Log.e("ValuesChannel1", "" + values1);
            }

            @Override
            public void onFailure(Call<SelectPojo> call, Throwable t) {

            }
        });
    }

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.10.0.220:7070/VibeScopeV1/").addConverterFactory(GsonConverterFactory.create()).build();
    ApIInterface service = retrofit.create(ApIInterface.class);

    public void refresh() {
        //ploting label on Y axis
        Viewport viewport = graphView.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(3);
        //   viewport.setScrollable(true);


        //ploting lable on X axis
        Viewport viewport1 = graphView.getViewport();
        viewport1.setXAxisBoundsManual(true);
        viewport1.setMinX(0);
        viewport1.setMaxX(60);
//        viewport1.setScrollable(true);


        d = new Date();
        minutes = d.getMinutes();
        minute1.add(minutes);
        //            Log.e("ArrayList of Minute", "" + minute);
        getRequest();


        int i = minute1.size() - 1;
        int j = values1.size() - 1;
        DataPoint dpoint = new DataPoint(minute1.get(i), values1.get(j));


        qwerty1.add(dpoint);

        Log.e("Channel1---", "" + qwerty1);
        DataPoint[] array = new DataPoint[qwerty1.size()];
        for (int k = 0; k < qwerty1.size(); k++) {
            array[k] = qwerty1.get(k);
        }

        datapointarray1 = array;
        series = new LineGraphSeries<DataPoint>(datapointarray1);
        series.setThickness(5);
        series.setAnimated(false);

        graphView.addSeries(series);



    }
}