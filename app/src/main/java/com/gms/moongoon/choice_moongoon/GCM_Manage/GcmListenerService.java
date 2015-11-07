package com.gms.moongoon.choice_moongoon.GCM_Manage;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.gms.moongoon.choice_moongoon.MainActivity;
import com.gms.moongoon.choice_moongoon.R;
import com.gms.moongoon.choice_moongoon.SendActivity;
import com.gms.moongoon.choice_moongoon.dapjang;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/*
* 수정일 2015-11-03
 */
public class GcmListenerService extends com.google.android.gms.gcm.GcmListenerService {
   private static final String TAG = "GcmListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String title = URLDecoder.decode(data.getString("msg"));
//        dapjang.naeyong = title;
        if (data.getString("isQuestion").equals("yes")){
            sendNotification(title,title);
        }else{
            sendNotification(title,title);
        }

    }

    private void sendNotification(String title, String message) {
        Intent intent = new Intent(this, dapjang.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);



        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //알림 on
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        if (pref.getBoolean("sound", true)) {
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putBoolean("noti", false);
//            editor.apply();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.right_arrow)
                .setContentTitle(title = URLDecoder.decode(title))
                .setContentText(message = URLDecoder.decode(message))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        }else{
            //알림off
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.right_arrow)
                    .setContentTitle(title = URLDecoder.decode(title))
                    .setContentText(message = URLDecoder.decode(message))
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }
}
