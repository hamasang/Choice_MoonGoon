package com.gms.moongoon.choice_moongoon.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gms.moongoon.choice_moongoon.R;
import com.gms.moongoon.choice_moongoon.SendActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sang on 2015-11-05.
 */
public class inksplash extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Holo_Light_NoActionBar_TranslucentDecor);
        setContentView(R.layout.inksplash);
        final Timer timer = new Timer();

        final TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(inksplash.this, SendActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(myTask, 1300);

    }
}
