package com.example.rlard008.prototype2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MonitorEditAsAdminFragment extends Fragment {


    Button buttonMonitor,buttonEdit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_monitor_edit_as_admin,container,false);
        buttonMonitor = (Button) v.findViewById(R.id.AdminMonitor);
        buttonEdit = (Button) v.findViewById(R.id.AdminEdit);

        buttonMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent monitor = new Intent(getContext(),MainActivity.class);
//                startActivity(monitor);

               allFragments();

            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent monitor = new Intent(getContext(),AdminSelectAllChannel.class);
                startActivity(monitor);

            }
        });

        return v;
    }

    public void allFragments()
    {
        //  Toast.makeText(MainActivity.this,"hey dude",Toast.LENGTH_LONG).show();
        MainActivityFragment frg=new MainActivityFragment();
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container,frg);
        ft.commit();

    }
}
