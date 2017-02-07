package com.example.rlard008.prototype2;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.iid.zzd;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sudhakar on 1/14/17.
 */

public class MessingIdService  extends FirebaseInstanceIdService{


    @Override
    public void onTokenRefresh() {
        String refreshToken=FirebaseInstanceId.getInstance().getToken();
   Log.e("TokenId",""+refreshToken);
      //        Toast.makeText(MessingIdService.this,"TokenId"+refreshToken,Toast.LENGTH_LONG).show();
        super.onTokenRefresh();
    }



}
