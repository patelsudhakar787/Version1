package com.example.rlard008.prototype2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sandy on 10/7/2016.
 */
public class MyMessageService extends FirebaseMessagingService
{
    private String str="";
    public MyMessageService()
    {

    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e("RemoteMessage",""+remoteMessage.toString());
        if(remoteMessage.getData().size()>0)
        {
            Log.e("msg","Messagedata"+remoteMessage.getData());
            JSONObject jsonObject=new JSONObject(remoteMessage.getData());
            try {
                 str=jsonObject.getString("msg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sendNotification(str);

        }
        Log.e("Notification",""+ remoteMessage.getNotification());
        if(remoteMessage.getNotification()!=null)
        {
            Log.e(" ","messagebody"+remoteMessage.getNotification().getBody());
            sendNotification(String.valueOf(remoteMessage.getData()));
        }

    }

    public void sendNotification(String body)
    {
        Intent intent=new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pending= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
        Uri notificationsound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        NotificationCompat.Builder noti_builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo).setContentTitle("VibeScopeAlertMessage")
                .setContentText(body).setAutoCancel(true).setSound(notificationsound).setContentIntent(pending);
        NotificationManager notimanager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notimanager.notify(1,noti_builder.build());
    }





}
