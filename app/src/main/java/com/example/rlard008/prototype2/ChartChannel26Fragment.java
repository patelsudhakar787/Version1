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



public class ChartChannel26Fragment extends Fragment {
    GraphView graphView;
  //  TextView textView;
   // EditText edittextcurrentvalues;
    ArrayList<Double> minute=new ArrayList<>();
    ArrayList<Double>values=new ArrayList<>();
    ArrayList<DataPoint>qwerty;
    DataPoint[] datapointarray;
    LineGraphSeries<DataPoint> series;
    RealmDataBase re;
    Realm realm;
    private String url1="";
    double minutes;

    String clientId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView=inflater.inflate(R.layout.layout_graph,container,false);
        graphView=(GraphView)itemView.findViewById(R.id.graph);
        TextView textView=(TextView)itemView.findViewById(R.id.textviewchannel);
        textView.setText("Channel_26");
        qwerty=new ArrayList<>();

        RealmConfiguration config=new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(config);
        realm= Realm.getDefaultInstance();
        SharedPreferences sf =getContext().getSharedPreferences("param",MODE_PRIVATE);
        clientId = sf.getString("clientId", "");
        //ploting label on Y axis
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
                            minute.add(minutes);
                            getRequest();
                            int i = minute.size();
                            int j = values.size();
                            DataPoint dpoint = new DataPoint(minute.get(i - 1), values.get(j - 1));

                            qwerty.add(dpoint);
                            Log.e("Channel---26", "" + qwerty);
                    //        Log.e("ArrayList Of DataPoint", "" + qwerty);
                            DataPoint[] array = new DataPoint[qwerty.size()];
                            for (int k = 0; k < qwerty.size(); k++) {
                                array[k] = qwerty.get(k);
                            }
                            datapointarray = array;
                            series = new LineGraphSeries(datapointarray);
                            series.setThickness(5);
                            series.setAnimated(false);
                            graphView.addSeries(series);

                        } catch (Exception e) {
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
                String str1="channel_26";


                re = new RealmDataBase();
                realm.beginTransaction();
                re.setChannel_Name(str1);
                realm.copyToRealmOrUpdate(re);
                realm.commitTransaction();

                Intent in=new Intent(getContext(),ShowEnlargeChart25_32Activity.class);
                in.putExtra("min",minute);
                in.putExtra("val",values);
                startActivity(in);
            }
        });


        return itemView;
    }

    public void sendRequest1()
    {
        StringRequest stringRequest=new StringRequest(url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("UnderResponse",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void getRequest()
    {

        Call<SelectPojo> response=service.getData();
        response.enqueue(new Callback<SelectPojo>() {
            @Override
            public void onResponse(Call<SelectPojo> call, retrofit2.Response<SelectPojo> response) {
               // Log.e("ResponseRetrofit",""+response.body().getData());

               try {
                   DataPojo d = response.body().getData();
                   //   Log.e("DataPojo",""+d.toString());
                   String channel1 = d.getChannel_26();

                   values.add(Double.parseDouble(channel1));
                   Log.e("Channel26", "" + channel1);

                   double data=Double.parseDouble(channel1);
                   if(data>100){
                       String message="Channel26ValueReachedThreshold";
                       String id=clientId;//"A1010";
                       url1="http://10.10.0.220:7070/VibeScopeV1/SendGCMToUser?id="+clientId+"&msg="+message;
                       sendRequest1();

                   }
               }
               catch (NullPointerException e)
               {
              //     values.add(0.0);
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
