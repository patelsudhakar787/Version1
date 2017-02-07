package com.example.rlard008.prototype2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AdminEditChannelActivity extends AppCompatActivity {

    Button buttonEdit;
    TextView textViewClientId;
    EditText edittextvalues;
    String url="";
    String saveurl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_channel);

        textViewClientId = (TextView) findViewById(R.id.textViewClientIDEdit);
        edittextvalues = (EditText) findViewById(R.id.editTextChannelValue);
        buttonEdit = (Button) findViewById(R.id.buttonEdit);

        Intent in=getIntent();
        final String channel_Name=in.getStringExtra("name");
        String channel_Value=in.getStringExtra("value");

        textViewClientId.setText(""+channel_Name);
        edittextvalues.setText(""+channel_Value);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sf =getSharedPreferences("paramAdmin",MODE_PRIVATE);
                String clientId = sf.getString("clientId", "");
                Log.e("ClientId",""+clientId);
                String machineId=sf.getString("machineId","");
                Log.e("MachineId",""+machineId);

                String editChannelValue=edittextvalues.getText().toString();

                url="http://10.10.0.220:7070/VibeScopeV1/EditParameterServlet?clientId="+clientId+"&channelName="+channel_Name+"&channelValue="+editChannelValue+"&machine_id="+machineId;
                Toast.makeText(AdminEditChannelActivity.this,"Url"+url,Toast.LENGTH_LONG).show();

                sendRequest();

                saveurl="http://10.10.0.220:7070/VibeScopeV1/SaveEditChannel?clientId="+clientId+"&channelName="+channel_Name+"&channelValue="+editChannelValue+"&machine_id="+machineId;
              //  Toast.makeText(AdminEditChannelActivity.this,"Url"+url,Toast.LENGTH_LONG).show();

                sendSaveRequest();

            }
        });
    }

    public void sendRequest()
    {
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean result = jsonObject.getBoolean("result");

                    if (result) {

                        Toast.makeText(AdminEditChannelActivity.this,"DataPojo has been Updated",Toast.LENGTH_LONG).show();

                    }
                    else {

                        Toast.makeText(AdminEditChannelActivity.this,"Unable To Update DataPojo",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminEditChannelActivity.this,"Error"+error,Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestqueue= Volley.newRequestQueue(AdminEditChannelActivity.this);
        requestqueue.add(stringRequest);
    }

    public void sendSaveRequest()
    {
        StringRequest stringRequest=new StringRequest(saveurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean result = jsonObject.getBoolean("result");

                    if (result) {

                        Toast.makeText(AdminEditChannelActivity.this,"DataPojo has been Saved",Toast.LENGTH_LONG).show();

                    }
                    else {

                        Toast.makeText(AdminEditChannelActivity.this,"Unable To Save DataPojo",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminEditChannelActivity.this,"Error"+error,Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestqueue= Volley.newRequestQueue(AdminEditChannelActivity.this);
        requestqueue.add(stringRequest);
    }

}
