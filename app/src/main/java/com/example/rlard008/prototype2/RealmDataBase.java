package com.example.rlard008.prototype2;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmDataBase extends RealmObject {

    @PrimaryKey
    private String channel_Name;



    //default Constructor
    public RealmDataBase()
    {  }

    //Parameterized Constructor

    public RealmDataBase(String channel_Name)
    {
        this.channel_Name=channel_Name;
    }

    //getter and Setter
    public String getChannel_Name() {
        return channel_Name;
    }

    public void setChannel_Name(String channel_Name) {
        this.channel_Name = channel_Name;
    }


    @Override
    public String toString() {
        return "RealmDataBase{" +
                "channel_Name='" + channel_Name + '\'' +
                '}';
    }
}
