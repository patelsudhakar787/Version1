package com.example.rlard008.prototype2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainActivityFragment extends Fragment {
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10,cardView11,cardView12,cardView13,cardView14,cardView15,cardView16,cardView17,cardView18,cardView19,cardView20,cardView21,cardView22,cardView23,cardView24,cardView25,cardView26,cardView27,cardView28,cardView29,cardView30,cardView31,cardView32;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v=inflater.inflate(R.layout.card_view_graph_view,container,false);
        cardView1=(CardView)v.findViewById(R.id.linear1);
        cardView2=(CardView)v.findViewById(R.id.linear2);
        cardView3=(CardView)v.findViewById(R.id.linear3);
        cardView4=(CardView)v.findViewById(R.id.linear4);
        cardView5=(CardView)v.findViewById(R.id.linear5);
        cardView6=(CardView)v.findViewById(R.id.linear6);
        cardView7=(CardView)v.findViewById(R.id.linear7);
        cardView8=(CardView)v.findViewById(R.id.linear8);
        cardView9=(CardView)v.findViewById(R.id.linear9);
        cardView10=(CardView)v.findViewById(R.id.linear10);
        cardView11=(CardView)v.findViewById(R.id.linear11);
        cardView12=(CardView)v.findViewById(R.id.linear12);
        cardView13=(CardView)v.findViewById(R.id.linear13);
        cardView14=(CardView)v.findViewById(R.id.linear14);
        cardView15=(CardView)v.findViewById(R.id.linear15);
        cardView16=(CardView)v.findViewById(R.id.linear16);
        cardView17=(CardView)v.findViewById(R.id.linear17);
        cardView18=(CardView)v.findViewById(R.id.linear18);
        cardView19=(CardView)v.findViewById(R.id.linear19);
        cardView20=(CardView)v.findViewById(R.id.linear20);
        cardView21=(CardView)v.findViewById(R.id.linear21);
        cardView22=(CardView)v.findViewById(R.id.linear22);
        cardView23=(CardView)v.findViewById(R.id.linear23);
        cardView24=(CardView)v.findViewById(R.id.linear24);
        cardView25=(CardView)v.findViewById(R.id.linear25);
        cardView26=(CardView)v.findViewById(R.id.linear26);
        cardView27=(CardView)v.findViewById(R.id.linear27);
        cardView28=(CardView)v.findViewById(R.id.linear28);
        cardView29=(CardView)v.findViewById(R.id.linear29);
        cardView30=(CardView)v.findViewById(R.id.linear30);
        cardView31=(CardView)v.findViewById(R.id.linear31);
        cardView32=(CardView)v.findViewById(R.id.linear32);


        loadChannel_1();
        loadChannel_2();
        loadChannel_3();
        loadChannel_4();
        loadChannel_5();
        loadChannel_6();
        loadChannel_7();
        loadChannel_8();
        loadChannel_9();
        loadChannel_10();
        loadChannel_11();
        loadChannel_12();
        loadChannel_13();
        loadChannel_14();
        loadChannel_15();
        loadChannel_16();
        loadChannel_17();
        loadChannel_18();
        loadChannel_19();
        loadChannel_20();
        loadChannel_21();
        loadChannel_22();
        loadChannel_23();
        loadChannel_24();
        loadChannel_25();
        loadChannel_26();
        loadChannel_27();
        loadChannel_28();
        loadChannel_29();
        loadChannel_30();
        loadChannel_31();
        loadChannel_32();

        return v;
    }



    //loading fragments
    public void loadChannel_1()
    {
        ChartChannel1Fragment frg=new ChartChannel1Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear1,frg);
        ft.commit();
    }
    public void loadChannel_2()
    {
        ChartChannel2Fragment frg=new ChartChannel2Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear2,frg);
        ft.commit();
    }
    public void loadChannel_3()
    {
        ChartChannel3Fragment frg=new ChartChannel3Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear3,frg);
        ft.commit();
    }
    public void loadChannel_4()
    {
        ChartChannel4Fragment frg=new ChartChannel4Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear4,frg);
        ft.commit();
    }
    public void loadChannel_5()
    {
        ChartChannel5Fragment frg=new ChartChannel5Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear5,frg);
        ft.commit();
    }
    public void loadChannel_6()
    {
        ChartChannel6Fragment frg=new ChartChannel6Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear6,frg);
        ft.commit();
    }
    public void loadChannel_7()
    {
        ChartChannel7Fragment frg=new ChartChannel7Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear7,frg);
        ft.commit();
    }
    public void loadChannel_8()
    {
        ChartChannel8Fragment frg=new ChartChannel8Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear8,frg);
        ft.commit();
    }
    public void loadChannel_9()
    {
        ChartChannel9Fragment frg=new ChartChannel9Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear9,frg);
        ft.commit();
    }
    public void loadChannel_10()
    {
        ChartChannel10Fragment frg=new ChartChannel10Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear10,frg);
        ft.commit();
    }
    public void loadChannel_11()
    {
        ChartChannel11Fragment frg=new ChartChannel11Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear11,frg);
        ft.commit();
    }
    public void loadChannel_12()
    {
        ChartChannel12Fragment frg=new ChartChannel12Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear12,frg);
        ft.commit();
    }
    public void loadChannel_13()
    {
        ChartChannel13Fragment frg=new ChartChannel13Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear13,frg);
        ft.commit();
    }
    public void loadChannel_14()
    {
        ChartChannel14Fragment frg=new ChartChannel14Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear14,frg);
        ft.commit();
    }
    public void loadChannel_15()
    {
        ChartChannel15Fragment frg=new ChartChannel15Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear15,frg);
        ft.commit();
    }
    public void loadChannel_16()
    {
        ChartChannel16Fragment frg=new ChartChannel16Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear16,frg);
        ft.commit();
    }
    public void loadChannel_17()
    {
        ChartChannel17Fragment frg=new ChartChannel17Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear17,frg);
        ft.commit();
    }

    public void loadChannel_18()
    {
        ChartChannel18Fragment frg=new ChartChannel18Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear18,frg);
        ft.commit();
    }

    public void loadChannel_19()
    {
        ChartChannel19Fragment frg=new ChartChannel19Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear19,frg);
        ft.commit();
    }

    public void loadChannel_20()
    {
        ChartChannel20Fragment frg=new ChartChannel20Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear20,frg);
        ft.commit();
    }

    public void loadChannel_21()
    {
        ChartChannel21Fragment frg=new ChartChannel21Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear21,frg);
        ft.commit();
    }

    public void loadChannel_22()
    {
        ChartChannel22Fragment frg=new ChartChannel22Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear22,frg);
        ft.commit();
    }

    public void loadChannel_23()
    {
        ChartChannel23Fragment frg=new ChartChannel23Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear23,frg);
        ft.commit();
    }

    public void loadChannel_24()
    {
        ChartChannel24Fragment frg=new ChartChannel24Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear24,frg);
        ft.commit();
    }
    public void loadChannel_25()
    {
        ChartChannel25Fragment frg=new ChartChannel25Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear25,frg);
        ft.commit();
    }

    public void loadChannel_26()
    {
        ChartChannel26Fragment frg=new ChartChannel26Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear26,frg);
        ft.commit();
    }

    public void loadChannel_27()
    {
        ChartChannel27Fragment frg=new ChartChannel27Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear27,frg);
        ft.commit();
    }

    public void loadChannel_28()
    {
        ChartChannel28Fragment frg=new ChartChannel28Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear28,frg);
        ft.commit();
    }

    public void loadChannel_29()
    {
        ChartChannel29Fragment frg=new ChartChannel29Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear29,frg);
        ft.commit();
    }

    public void loadChannel_30()
    {
        ChartChannel30Fragment frg=new ChartChannel30Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear30,frg);
        ft.commit();
    }

    public void loadChannel_31()
    {
        ChartChannel31Fragment frg=new ChartChannel31Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear31,frg);
        ft.commit();
    }

    public void loadChannel_32()
    {
        ChartChannel32Fragment frg=new ChartChannel32Fragment();
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.linear32,frg);
        ft.commit();
    }


}
