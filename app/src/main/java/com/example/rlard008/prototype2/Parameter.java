package com.example.rlard008.prototype2;

import android.support.v4.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rlard008 on 19-12-2016.
 */

public class Parameter implements Serializable
{

    String clientId,timeStamp,value;
    ArrayList<String>channelName;
    ArrayList<String>channelValue;
    String channelNames;
    String channelValues;
    Fragment frg;
    int id;

    public Parameter(String channelNames, String channelValues)
    {
        this.channelNames=channelNames;
        this.channelValues=channelValues;
    }
    public Parameter(String channelNames, Fragment frg)
    {
        this.channelNames=channelNames;
        this.frg=frg;
    }
    public Parameter(String channelNames, int id)
    {
        this.channelNames=channelNames;
        this.id=id;
    }




    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getChannelNames() {
        return channelNames;
    }

    public void setChannelNames(String channelNames) {
        this.channelNames = channelNames;
    }

    public String getChannelValues() {
        return channelValues;
    }

    public void setChannelValues(String channelValues) {
        this.channelValues = channelValues;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "clientId='" + clientId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Parameter() {
    }

    public Parameter(String clientId, String timeStamp, String value) {
        this.clientId = clientId;
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public Parameter(String clientId) {
        this.clientId = clientId;
    }
}
