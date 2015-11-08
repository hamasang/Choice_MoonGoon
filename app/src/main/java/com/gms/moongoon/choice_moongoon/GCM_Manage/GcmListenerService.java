package com.gms.moongoon.choice_moongoon.GCM_Manage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.gms.moongoon.choice_moongoon.MainActivity;
import com.gms.moongoon.choice_moongoon.R;

import java.net.URLDecoder;

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
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor=pref.edit();
        Intent intss = new Intent(this, MainActivity.class);
        Intent ints = new Intent(String.valueOf(editor.putString("titles", title)) + intss);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */,ints,
                PendingIntent.FLAG_ONE_SHOT);
        editor.apply();



        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //알림 on
        if (pref.getBoolean("sound", true)) {
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putBoolean("noti", false);
//            editor.apply();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.right_arrow)
                .setContentTitle(title = URLDecoder.decode(title))
                .setContentText("클릭후 앱을 실행하여 답장하세요!")
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
                    .setContentText("클릭하셔야 답장이 가능합니다.")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }
}
