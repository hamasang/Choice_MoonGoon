package com.gms.moongoon.choice_moongoon.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gms.moongoon.choice_moongoon.MainActivity;
import com.gms.moongoon.choice_moongoon.R;

/**
 * Created by sang on 2015-11-05.
 */
public class guidesplash extends AppCompatActivity {
    Button btn1;
    String guide = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Holo_Light_NoActionBar_TranslucentDecor);
        setContentView(R.layout.guide);

        btn1 = (Button)findViewById(R.id.button5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}