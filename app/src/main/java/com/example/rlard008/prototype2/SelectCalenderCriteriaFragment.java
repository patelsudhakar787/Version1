package com.example.rlard008.prototype2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sudhakar on 1/19/17.
 */

public class SelectCalenderCriteriaFragment extends Fragment {
    ListView listView;
   private ArrayList<String>arrayList=new ArrayList<>();
    ArrayAdapter<String>adapter;
//    private Date date;
    Realm realm;
    private String channel="";
    RealmResults<RealmDataBase> result;
    private String url="";
    private ArrayList<Double>arrayListTime=new ArrayList<>();
    private ArrayList<Double>arrayListValues=new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.criteria_layout,container,false);
        listView=(ListView)view.findViewById(R.id.listView);
        arrayList.add("perDay");
        arrayList.add("perHour");
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
     //   date=new Date();
        //getting data Channel_Name from Realm Local DataBase
        RealmConfiguration config = new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
        result = realm.where(RealmDataBase.class).findAll();
        RealmDataBase s = result.get(0);
        channel = s.getChannel_Name();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        String date1="2017-01-19";
                        url="http://10.10.0.220:7070/VibeScopeV1/PerDayServlet?channel="+channel+"&date="+date1;
                        sendRequest();
                        Log.e("UrlSelected",""+url);
                        Log.e("ChannelValue",""+arrayListValues);
                        Log.e("ChannelTiem",""+arrayListTime);
                        sendRequest();
                        break;

                    case 1:
                        String date2="2017-01-20";
                        url="http://10.10.0.221:7070/VibeScopeV1/PerHourServlet?channel="+channel+"&date="+date2;
                        sendRequest1();
                        break;


                }
            }
        });
        return view;
    }



    //sending Request
    public void sendRequest()
    {
        Toast.makeText(getContext(),"Calling",Toast.LENGTH_LONG).show();
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
           //     Log.e("ResponseUnderSelected ",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    Log.e("JSonArray",""+jsonArray);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                       jsonObject1=jsonArray.getJSONObject(i);
                        String channelvalue=jsonObject1.getString("_name");
                        String time=jsonObject1.getString("_value");
                        arrayListTime.add(Double.parseDouble(time));
                        arrayListValues.add(Double.parseDouble(channelvalue));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }


    public void sendRequest1()
    {
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("ResponseText2",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        jsonObject1=jsonArray.getJSONObject(i);
                        String time=jsonObject1.getString("time");
                        String channelvalue=jsonObject1.getString("values");
                        arrayListTime.add(Double.parseDouble(time));
                        arrayListValues.add(Double.parseDouble(channelvalue));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}
