package com.example.rlard008.prototype2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminSelectAllChannel extends AppCompatActivity {


    TextView textviewclientId;
    ListView listViewChannelNameAndValues;

    ArrayList<String> arrayChannelName;
    ArrayList<String>arrayChannelValue;
    ArrayList<Parameter>arrayListParameter;

    private static String url="";
    Parameter p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_all_channel);

        textviewclientId=(TextView)findViewById(R.id.textviewClientId);
        listViewChannelNameAndValues=(ListView)findViewById(R.id.listview);


        arrayChannelName=new ArrayList<>();
        arrayChannelValue=new ArrayList<>();
        arrayListParameter=new ArrayList<>();

        //Adding Channel Names to The arraylist
        arrayChannelName.add("Channel_1");
        arrayChannelName.add("Channel_2");
        arrayChannelName.add("Channel_3");
        arrayChannelName.add("Channel_4");
        arrayChannelName.add("Channel_5");
        arrayChannelName.add("Channel_6");
        arrayChannelName.add("Channel_7");
        arrayChannelName.add("Channel_8");
        arrayChannelName.add("Channel_9");
        arrayChannelName.add("Channel_10");
        arrayChannelName.add("Channel_11");
        arrayChannelName.add("Channel_12");
        arrayChannelName.add("Channel_13");
        arrayChannelName.add("Channel_14");
        arrayChannelName.add("Channel_15");
        arrayChannelName.add("Channel_16");
        arrayChannelName.add("Channel_17");
        arrayChannelName.add("Channel_18");
        arrayChannelName.add("Channel_19");
        arrayChannelName.add("Channel_20");
        arrayChannelName.add("Channel_21");
        arrayChannelName.add("Channel_22");
        arrayChannelName.add("Channel_23");
        arrayChannelName.add("Channel_24");
        arrayChannelName.add("Channel_25");
        arrayChannelName.add("Channel_26");
        arrayChannelName.add("Channel_27");
        arrayChannelName.add("Channel_28");
        arrayChannelName.add("Channel_29");
        arrayChannelName.add("Channel_30");
        arrayChannelName.add("Channel_31");
        arrayChannelName.add("Channel_32");





        SharedPreferences sf =this.getSharedPreferences("paramAdmin",MODE_PRIVATE);
        String clientId = sf.getString("clientId", "");
        Log.e("ClientId",""+clientId);
        String machineId=sf.getString("machineId","");
        Log.e("MachineId",""+machineId);

        url="http://10.10.0.220:7070/VibeScopeV1/SelectEditChannel?clientId="+clientId+"&machine_id="+machineId;
        Log.e("Url",""+url);
        //calling Json Request Funtion
        sendRequest();   //ArrayListChannelName
        Log.e("Before for loop","");




        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < arrayChannelName.size() && i < arrayChannelValue.size(); i++) {

                    String channel1 = arrayChannelName.get(i);
                    Log.e("Channel1", "" + channel1);
                    String channel2 = arrayChannelValue.get(i);
                    Log.e("Channel", "" + channel2);
                    p = new Parameter(channel1, channel2);
                    arrayListParameter.add(p);
                    ListViewParameterAdapter adapter = new ListViewParameterAdapter();
                    listViewChannelNameAndValues.setAdapter(adapter);
                }

            }
        }, 1000);


        Log.e("ArrayListParameter",""+arrayListParameter);


        listViewChannelNameAndValues.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Parameter p=arrayListParameter.get(i);
                String ChannelValue=p.getChannelValues();
                String ChannelName=p.getChannelNames();
                if (ChannelName.equals("Channel_1") || ChannelName.equals("Channel_2") || ChannelName.equals("Channel_3") ||
                        ChannelName.equals("Channel_4") || ChannelName.equals("Channel_5") || ChannelName.equals("Channel_6") ||
                        ChannelName.equals("Channel_7") || ChannelName.equals("Channel_8"))
                {
                    Toast.makeText(AdminSelectAllChannel.this,"You dont have permisions to edit "+ChannelName,Toast.LENGTH_LONG).show();
                }
                else {

                    Intent intent = new Intent(AdminSelectAllChannel.this, AdminEditChannelActivity.class);
                    intent.putExtra("name", "" + ChannelName);
                    intent.putExtra("value", "" + ChannelValue);
                    startActivity(intent);
                }


            }
        });
        //calling Cutomized Adapter

    }
    public void sendRequest()
    {
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Response",""+response);
                    JSONObject jsonObject=new JSONObject(response);
                    JSONObject jsonData=jsonObject.getJSONObject("data");

                    String channel_1 = jsonData.getString("channel_1");
                    String channel_2 = jsonData.getString("channel_2");
                    String channel_3 = jsonData.getString("channel_3");
                    String channel_4 = jsonData.getString("channel_4");
                    String channel_5 = jsonData.getString("channel_5");
                    String channel_6 = jsonData.getString("channel_6");
                    String channel_7 = jsonData.getString("channel_7");
                    String channel_8 = jsonData.getString("channel_8");
                    String channel_9 = jsonData.getString("channel_9");
                    String channel_10 = jsonData.getString("channel_10");
                    String channel_11 = jsonData.getString("channel_11");
                    String channel_12 = jsonData.getString("channel_12");
                    String channel_13 = jsonData.getString("channel_13");
                    String channel_14 = jsonData.getString("channel_14");
                    String channel_15 = jsonData.getString("channel_15");
                    String channel_16 = jsonData.getString("channel_16");
                    String channel_17 = jsonData.getString("channel_17");
                    String channel_18 = jsonData.getString("channel_18");
                    String channel_19 = jsonData.getString("channel_19");
                    String channel_20 = jsonData.getString("channel_20");
                    String channel_21 = jsonData.getString("channel_21");
                    String channel_22 = jsonData.getString("channel_22");
                    String channel_23 = jsonData.getString("channel_23");
                    String channel_24 = jsonData.getString("channel_24");
                    String channel_25 = jsonData.getString("channel_25");
                    String channel_26 = jsonData.getString("channel_26");
                    String channel_27 = jsonData.getString("channel_27");
                    String channel_28 = jsonData.getString("channel_28");
                    String channel_29 = jsonData.getString("channel_29");
                    String channel_30 = jsonData.getString("channel_30");
                    String channel_31 = jsonData.getString("channel_31");
                    String channel_32 = jsonData.getString("channel_32");


                    arrayChannelValue.add(channel_1);
                    arrayChannelValue.add(channel_2);
                    arrayChannelValue.add(channel_3);
                    arrayChannelValue.add(channel_4);
                    arrayChannelValue.add(channel_5);
                    arrayChannelValue.add(channel_6);
                    arrayChannelValue.add(channel_7);
                    arrayChannelValue.add(channel_8);
                    arrayChannelValue.add(channel_9);
                    arrayChannelValue.add(channel_10);
                    arrayChannelValue.add(channel_11);
                    arrayChannelValue.add(channel_12);
                    arrayChannelValue.add(channel_13);
                    arrayChannelValue.add(channel_14);
                    arrayChannelValue.add(channel_15);
                    arrayChannelValue.add(channel_16);
                    arrayChannelValue.add(channel_17);
                    arrayChannelValue.add(channel_18);
                    arrayChannelValue.add(channel_19);
                    arrayChannelValue.add(channel_20);
                    arrayChannelValue.add(channel_21);
                    arrayChannelValue.add(channel_22);
                    arrayChannelValue.add(channel_23);
                    arrayChannelValue.add(channel_24);
                    arrayChannelValue.add(channel_25);
                    arrayChannelValue.add(channel_26);
                    arrayChannelValue.add(channel_27);
                    arrayChannelValue.add(channel_28);
                    arrayChannelValue.add(channel_29);
                    arrayChannelValue.add(channel_30);
                    arrayChannelValue.add(channel_31);
                    arrayChannelValue.add(channel_32);
                    Log.e("ArrayListOfValues",""+arrayChannelValue);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminSelectAllChannel.this,"JSonError"+error,Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestqueue= Volley.newRequestQueue(AdminSelectAllChannel.this);
        requestqueue.add(stringRequest);
    }


    //

    class ListViewParameterAdapter extends ArrayAdapter<Parameter>
    {

        public ListViewParameterAdapter() {
            super(AdminSelectAllChannel.this,R.layout.channel_name_value_name_underlistview_layout,arrayListParameter);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflator=getLayoutInflater();
            View view=inflator.inflate(R.layout.channel_name_value_name_underlistview_layout,parent,false);
            TextView textviewChannelName=(TextView)view.findViewById(R.id.textviewchannelname);
            TextView textviewChannelValue=(TextView)view.findViewById(R.id.textviewchannelvalue);

            //Parameter par=new Parameter(arrayChannelName,arrayChannelValue);
            Parameter par=arrayListParameter.get(position);
            textviewChannelName.setText(""+par.getChannelNames());
            textviewChannelValue.setText(""+par.getChannelValues());
            return view;
        }

    }

}
