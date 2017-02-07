package com.example.rlard008.prototype2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rlard008.prototype2.model.Data;
import com.example.rlard008.prototype2.ui.LeftNavAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class ListOfAllClientsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView listViewDrawer;
    TextView lblTitle;

    //String draweroption[]=new String[]{"C1001","C1002","about us","logout"};

    String temp = "";
    String url = "";
    String urlmachine = "";

    private static String Client_Id;

    public static ArrayList<String> hostmachines = new ArrayList<>();
    ArrayList<String> tempList = new ArrayList<>();
    ArrayList<String> clientList = new ArrayList<>();
    private static int i = 0;

    ArrayList<Data> al = new ArrayList<>();

    ActionBar actionBar = null;

    //String[] s = { "M1001" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_all_clients);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerAdmin);
        listViewDrawer = (ListView) findViewById(R.id.left_drawer_Admin);

        LinearLayout layout = (LinearLayout)findViewById(R.id.linearlayout1);

        View child = getLayoutInflater().inflate(R.layout.layout_child, null);
        layout.addView(child);

        url = "http://10.10.0.220:7070/VibeScopeV1/DisplayClients";

     //   Toast.makeText(ListOfAllClientsActivity.this, "url" + url, Toast.LENGTH_LONG).show();
        sendRequest();


        //enable back navigation

        View c = getLayoutInflater().inflate(R.layout.action_bar_logo, null);
        lblTitle = (TextView) c.findViewById(R.id.title);
        lblTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.line1, 0, 0, 0);

        c.findViewById(R.id.feed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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

        actionBar = getSupportActionBar();
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
                Client_Id = temp;

                SharedPreferences sp = getSharedPreferences("paramAdmin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("clientId", Client_Id);
                editor.commit();


                Toast.makeText(ListOfAllClientsActivity.this, "Client Id" + Client_Id, Toast.LENGTH_LONG).show();


                if (Client_Id.equals("About") || Client_Id.equals("Help") || Client_Id.equals("Logout")) {
                    Toast.makeText(ListOfAllClientsActivity.this, "Client Id" + Client_Id, Toast.LENGTH_LONG).show();

                    if (Client_Id.equals("About")) {
                        AboutUsFragment frg = new AboutUsFragment();
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.container, frg);
                        ft.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    if (Client_Id.equals("Logout")) {
                        Intent logout = new Intent(ListOfAllClientsActivity.this, LoginActivity.class);
                        startActivity(logout);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                    }
                    if (Client_Id.equals("Help")) {

                        }
                } else {
                    urlmachine = "http://10.10.0.220:7070/VibeScopeV1/ListMachines?clientId=" + Client_Id;

                  //  Toast.makeText(ListOfAllClientsActivity.this, "machineurl" + urlmachine, Toast.LENGTH_LONG).show();
                    sendMachineRequest();
              //      setupSpinner();
                }

            }
        });
    }
            @Override
            public void onBackPressed() {

            }


    public void sendRequest() {
        Log.e("Under Send Request", "Under");
        JsonObjectRequest jsonobject = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", "" + response);

                try {
                    JSONArray jarray = response.getJSONArray("data");
                    Log.e("JSONArray", "" + jarray);
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject jobj = jarray.getJSONObject(i);
                        String jobj2 = jobj.getString("clientId");
                        Log.e("Jobj", "" + jobj2);
                        //   Parameter parameter=new Parameter(jobj2);
                        clientList.add(jobj2);
                    }
                    if (clientList == null) {
                        Toast.makeText(ListOfAllClientsActivity.this, "Connection Problem", Toast.LENGTH_LONG).show();
                    } else {

                        //Toast.makeText(MainActivity.this,"arraylistmachine"+arrayListMachine,Toast.LENGTH_LONG).show();
                        String[] clientArr = clientList.toArray(new String[clientList.size()]);
                        //machineArr = arrayListMachine.toArray(machineArr);
                        //Toast.makeText(MainActivity.this,"machine array"+machineArr,Toast.LENGTH_LONG).show();
                        for (i = 0; i < clientArr.length; i++) {
                            al.add(new Data(clientArr[i], new int[]{
                                    R.drawable.ic_nav1, R.drawable.ic_nav1_sel}));

                        }
                        Toast.makeText(ListOfAllClientsActivity.this, "al" + al, Toast.LENGTH_LONG).show();

                        al.add(new Data("About", new int[]{
                                R.drawable.ic_nav5, R.drawable.ic_nav5_sel}));

                        al.add(new Data("Help", new int[]{
                                R.drawable.ic_nav6, R.drawable.ic_nav6_sel}));

                        al.add(new Data("Logout", new int[]{
                                R.drawable.ic_nav4, R.drawable.ic_nav4_sel}));

                        LeftNavAdapter adapter = new LeftNavAdapter(ListOfAllClientsActivity.this, al);

                        listViewDrawer.setAdapter(adapter);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error");

            }
        });

        RequestQueue requestqueue = Volley.newRequestQueue(this);
        requestqueue.add(jsonobject);

    }


    public void sendMachineRequest() {

        StringRequest stringRequest=new StringRequest(urlmachine, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("SendMachineRequest",""+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jarray=jsonObject.getJSONArray("data");
                    Log.e("JSonArray",""+jarray);
                    JSONObject jobj1;
                    for(int i=0;i<jarray.length();i++)
                    {
                        jobj1=jarray.getJSONObject(i);
                        String Machine_Id=jobj1.getString("machine_id");
                        hostmachines.add(Machine_Id);

                    }
                    Log.e("hostmachines",""+hostmachines);

                    setupSpinner();

                   // for(int i=1;i<=hostmachines.size();i++) {
                     //   hostmachines.remove(i);
                   // }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.e("Under Send Request", "Under");
    }

    public void setupSpinner(){

        //Log.e("InSetUpSpinner",""+hostmachines);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(ListOfAllClientsActivity.this,
                android.R.layout.simple_list_item_1, hostmachines);


        final Spinner sp1 = new Spinner(ListOfAllClientsActivity.this);
        sp1.setAdapter(adp);
        AlertDialog.Builder builder = new AlertDialog.Builder(ListOfAllClientsActivity.this);
        builder.setMessage("Please Select machine");
        builder.setView(sp1);
        builder.setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //get selected machine id from spinner

                        int pos = sp1.getSelectedItemPosition();

                        String machineId = hostmachines.get(pos);
                       // Log.e("machine id",""+machineId);


                        SharedPreferences sp = getSharedPreferences("paramAdmin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("machineId", machineId);
                        editor.commit();


                        loadMonitorFragment();

                        hostmachines.clear();
                        sp1.setAdapter(null);

                    }
                });
        builder.create().show();

    }

    public void loadMonitorFragment()
    {
       // Toast.makeText(ListOfAllClientsActivity.this,"Fragment is Loading.......",Toast.LENGTH_LONG).show();
        MonitorEditAsAdminFragment frg=new MonitorEditAsAdminFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container,frg);
        ft.commit();
        drawerLayout.closeDrawer(GravityCompat.START);

        // tempList.removeAll(hostmachines);

    }






            //                switch (position)
//                {
//
//                    case 0://load settings fragment
////                        FragmentManager fm=getSupportFragmentManager();
////                        FragmentTransaction ft=fm.beginTransaction();
////                        ft.replace(R.id.container,new Fragmentsettings());
////                        ft.commit();
////                        drawerLayout.closeDrawer(GravityCompat.START);
//
//                        Toast.makeText(ListOfAllClientsActivity.this,"A1001",Toast.LENGTH_LONG).show();
//                        break;
//
//                    case 1://load contact fragment
////                        FragmentManager fm1=getSupportFragmentManager();
////                        FragmentTransaction ft1=fm1.beginTransaction();
////                        ft1.replace(R.id.container,new FragmentControl());
////                        ft1.commit();
////                        drawerLayout.closeDrawer(GravityCompat.START);
//                        Toast.makeText(ListOfAllClientsActivity.this,"A1002",Toast.LENGTH_LONG).show();
//                        break;
//                    case 2://load about us fragment
//
////                        FragmentManager fm2=getSupportFragmentManager();
////                        FragmentTransaction ft2=fm2.beginTransaction();
////                        ft2.replace(R.id.container,new FragmentControl());
////                        ft2.commit();
////                        drawerLayout.closeDrawer(GravityCompat.START);
//                        Toast.makeText(ListOfAllClientsActivity.this,"about us",Toast.LENGTH_LONG).show();
//
//                        break;
//                    case  3:
//                        //finish();
//                        Toast.makeText(ListOfAllClientsActivity.this,"Logout",Toast.LENGTH_LONG).show();
//                        break;
//                }


  //      }
   // });


}
