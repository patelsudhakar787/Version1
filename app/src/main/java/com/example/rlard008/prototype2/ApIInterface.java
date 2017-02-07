package com.example.rlard008.prototype2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import retrofit2.Call;
import retrofit2.http.GET;

import static android.content.Context.MODE_PRIVATE;
import static java.security.AccessController.getContext;

/**
 * Created by sudhakar on 1/26/17.
 */

public interface ApIInterface {


    @GET("SelectServlet?clientId=A1001&machine_id=M1001")
    Call<SelectPojo> getData();

}
