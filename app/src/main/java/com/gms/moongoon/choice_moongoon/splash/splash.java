package com.gms.moongoon.choice_moongoon.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gms.moongoon.choice_moongoon.MainActivity;
import com.gms.moongoon.choice_moongoon.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sang on 2015-11-05.
 */
public class splash extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Holo_Light_NoActionBar_TranslucentDecor);
        setContentView(R.layout.splash);
        final Timer timer = new Timer();

        final TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(myTask, 1500);

    }
}
