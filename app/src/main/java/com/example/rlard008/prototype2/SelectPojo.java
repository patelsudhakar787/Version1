package com.example.rlard008.prototype2;

/**
 * Created by sudhakar on 1/27/17.
 */

public class SelectPojo
{
    private String result;

    private DataPojo data;

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public DataPojo getData ()
    {
        return data;
    }

    public void setData (DataPojo data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", data = "+data+"]";
    }
}

