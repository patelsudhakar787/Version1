package com.example.rlard008.prototype2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rlard008.prototype2.model.Data;
import com.example.rlard008.prototype2.ui.LeftNavAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.rlard008.prototype2.R.id.drawerClient;


public class MainActivity extends AppCompatActivity  {

    DrawerLayout drawerLayout;
    ListView listViewDrawer;
    TextView lblTitle;

    String temp = "";
    String url ="";
    ArrayList<String>arrayListMachine=new ArrayList<>();
    private static int i=0;


    //String draweroption[]=new String[]{"M1001","M1002","about us","logout"};

    ArrayList<Data> al = new ArrayList<>();


    ActionBar actionBar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        drawerLayout =(DrawerLayout)findViewById(drawerClient);
        listViewDrawer=(ListView)findViewById(R.id.left_drawer_Client);
        loadFragment();

        SharedPreferences sf =this.getSharedPreferences("param",MODE_PRIVATE);
        String clientId = sf.getString("clientId", "");
       // Log.e("ClientId",""+clientId);
        url="http://10.10.0.220:7070/VibeScopeV1/ListMachines?clientId="+clientId;

      //  Toast.makeText(MainActivity.this,"url"+url,Toast.LENGTH_LONG).show();
        sendRequest();

        //reloadFragments
        final Handler handler = new Handler();
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            Date date=new Date();
                            int minute=date.getMinutes();
                            if(minute==59)
                            {
                                allFragments();
                            }
                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };//end of timer task
        timer.schedule(task, 0, 60 * 1000);


        //enable back navigation

        View c = getLayoutInflater().inflate(R.layout.action_bar_logo, null);
        lblTitle = (TextView) c.findViewById(R.id.title);
        lblTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.line1,0,0,0);

        c.findViewById(R.id.feed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (view.getId() == R.id.feed)
//                    //startActivity(new Intent(this, SalesFeed.class));
//                    Toast.makeText(MainActivity.this,"r.id.feed",Toast.LENGTH_LONG).show();
//                else if (view == lblTitle)
//                {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START))
                        drawerLayout.closeDrawers();
                    else
                        drawerLayout.openDrawer(GravityCompat.START);
             //   }
            }
        });

        lblTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (view.getId() == R.id.feed)
//                    //startActivity(new Intent(this, SalesFeed.class));
//                    Toast.makeText(MainActivity.this,"r.id.feed",Toast.LENGTH_LONG).show();
//                else if (view == lblTitle)
//                {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START))
                        drawerLayout.closeDrawers();
                    else
                        drawerLayout.openDrawer(GravityCompat.START);
               // }

            }
        });

        actionBar=getSupportActionBar();
        actionBar.setCustomView(c);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        //---load  fragment on option selected----

        listViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Data d = al.get(position);
                temp = d.getTexts();
                String Machine_Id=temp;


                if (Machine_Id.equals("About") || Machine_Id.equals("Help") || Machine_Id.equals("Logout"))
                {
                    //Toast.makeText(MainActivity.this,"Machine id"+Machine_Id,Toast.LENGTH_LONG).show();
                    if (Machine_Id.equals("About")) {

                        AboutUsFragment frg=new AboutUsFragment();
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.container,frg);
                        ft.commit();

                      //  al.clear();
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    if (Machine_Id.equals("Logout")) {
                        Intent logout = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(logout);
                        drawerLayout.closeDrawer(GravityCompat.START);
                      //  al.clear();
                        finish();
                    }
                    if (Machine_Id.equals("Help")) {

                     }
                }
                else {
                    SharedPreferences sp = getSharedPreferences("param", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("machineId", Machine_Id);
                    editor.commit();
                    allFragments();
                   // al.clear();

                    drawerLayout.closeDrawer(GravityCompat.START);
                }

            }
        });


    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)

        {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else
                drawerLayout.openDrawer(GravityCompat.START);
        }


        return super.onOptionsItemSelected(item);
    }

    //functions for loading fragments
    public void allFragments()
    {
      //  Toast.makeText(MainActivity.this,"hey dude",Toast.LENGTH_LONG).show();
        MainActivityFragment frg=new MainActivityFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container,frg);
        ft.commit();

    }

    public void sendRequest()
    {
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jo;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jo = jsonArray.getJSONObject(i);
                        String machineID = jo.getString("machine_id");
                        arrayListMachine.add(machineID);
                    }
                    if(arrayListMachine==null)
                    {
                        Toast.makeText(MainActivity.this,"Connection Problem",Toast.LENGTH_LONG).show();
                    }
                    else {

                       // Toast.makeText(MainActivity.this,"arraylistmachine"+arrayListMachine,Toast.LENGTH_LONG).show();
                        String[] machineArr = arrayListMachine.toArray(new String[arrayListMachine.size()]);
                        //machineArr = arrayListMachine.toArray(machineArr);
                        //Toast.makeText(MainActivity.this,"machine array"+machineArr,Toast.LENGTH_LONG).show();
                        for(i=0;i<machineArr.length;i++) {
                            al.add(new Data(machineArr[i], new int[] {
                                    R.drawable.ic_nav1, R.drawable.ic_nav1_sel }));

                        }
                       // Toast.makeText(MainActivity.this,"al"+al,Toast.LENGTH_LONG).show();

                        al.add(new Data("About" , new int[] {
                                R.drawable.ic_nav5, R.drawable.ic_nav5_sel }));

                        al.add(new Data("Help" , new int[] {
                                R.drawable.ic_nav6, R.drawable.ic_nav6_sel }));

                        al.add(new Data("Logout" , new int[] {
                                R.drawable.ic_nav4, R.drawable.ic_nav4_sel }));

                        LeftNavAdapter adapter = new LeftNavAdapter(MainActivity.this,al);

                        listViewDrawer.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error "+error,Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


//loading Fragment

    public void loadFragment() {


        MapViewFragment frg = new MapViewFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container,frg);
        ft.commit();
    }




}
