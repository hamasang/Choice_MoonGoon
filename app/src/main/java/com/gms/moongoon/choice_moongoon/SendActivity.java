package com.gms.moongoon.choice_moongoon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gms.moongoon.choice_moongoon.Libraries.MatchingSecretTextView_Package.AutofitHelper;
import com.gms.moongoon.choice_moongoon.Libraries.MatchingSecretTextView_Package.SecretMatchingTextView;
import com.gms.moongoon.choice_moongoon.splash.inksplash;
import com.gms.moongoon.choice_moongoon.splash.sendsplash;

/**
 * Created by user on 2015-09-07.
 * 수정일 2015-11-08
 */
public class SendActivity extends Activity {

    Intent sendIntent;
    Bundle sendExtra;
    int splash = 0;
    int index = 1;
    SecretMatchingTextView secretMatchingTextView;
    EditText sendEditText;
    Button cancle,send,dapjang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        if(splash <= 0){
            Intent intents = new Intent(SendActivity.this, inksplash.class);
            startActivity(intents);
            splash++;
        }else{

        }

        sendIntent = new Intent();
        sendExtra = new Bundle();

        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        this.secretMatchingTextView = (SecretMatchingTextView) findViewById(R.id.matchingSecretTextView);
        this.secretMatchingTextView.setIsVisible(true);
        this.secretMatchingTextView.setLines(10);


        sendEditText = (EditText) findViewById(R.id.sendEditText);

        this.secretMatchingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                secretMatchingTextView.toggle();
                sendEditText.requestFocus();
            }
        });
            AutofitHelper.create(secretMatchingTextView);
            sendEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    // do nothing
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                    secretMatchingTextView.setText(charSequence);
                    if (secretMatchingTextView.getText().length() == (30 * index)) {
                        secretMatchingTextView.setMaxLines(index + 1);
                        index++;
                        //                  Log.e("length", secretMatchingTextView.getText().length() + " / " + index);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // do nothing
            }
        });

        cancle = (Button)findViewById(R.id.send_cancle);
        send = (Button)findViewById(R.id.send_commit);
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
            }
            });
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intents = new Intent(SendActivity.this, sendsplash.class);
                    startActivity(intents);
                    sendExtra.putString("res", secretMatchingTextView.getText().toString());
                    sendExtra.putBoolean("isQuestion", true);
                    sendIntent.putExtras(sendExtra);
                    setResult(0, sendIntent);
                    finish();
            }
        });
    }
}
