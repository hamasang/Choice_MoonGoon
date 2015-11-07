package com.gms.moongoon.choice_moongoon;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gms.moongoon.choice_moongoon.GCM_Manage.GcmListenerService;
import com.gms.moongoon.choice_moongoon.GCM_Manage.GcmQuickStartPreference;
import com.gms.moongoon.choice_moongoon.GCM_Manage.GcmRegisterIntentService;
import com.gms.moongoon.choice_moongoon.GET_POST.GetServer;
import com.gms.moongoon.choice_moongoon.GET_POST.PostServer;
import com.gms.moongoon.choice_moongoon.Libraries.MatchingSecretTextView_Package.AutofitHelper;
import com.gms.moongoon.choice_moongoon.Libraries.MatchingSecretTextView_Package.SecretMatchingTextView;
import com.gms.moongoon.choice_moongoon.splash.guidesplash;
import com.gms.moongoon.choice_moongoon.splash.inksplash;
import com.gms.moongoon.choice_moongoon.splash.sendsplash;
import com.gms.moongoon.choice_moongoon.splash.splash;
import com.gms.moongoon.choice_moongoon.tools.DecodeJson;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import com.gms.moongoon.choice_moongoon.GCM_Manage.GcmListenerService;

public class dapjang extends AppCompatActivity {

    public static String userRes;
    Toolbar toolbar;
    View view;
    ViewPager pager;
    MaterialTabHost tabHost;
    Resources res;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    String sex = null;
    String age = null;
    String token = null;
    SharedPreferences guidecheck;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    public static final String KEY_MY_PREFERENCE = "my_preference";


    Intent sendIntent;
    Bundle sendExtra;
    int splash = 0;
    int index = 1;
    SecretMatchingTextView secretMatchingTextView;
    EditText sendEditText;
    public static String naeyoung;
    Button cancle,send;
    public static Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dapjang);
        Intent intent = new Intent(dapjang.this,MainActivity.class);
        startActivity(intent);

        if(splash <= 0){
            Intent intents = new Intent(dapjang.this, inksplash.class);
            startActivity(intents);
            splash++;
        }else{

        }

        sendIntent = new Intent();
        sendExtra = new Bundle();

        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        this.secretMatchingTextView = (SecretMatchingTextView) findViewById(R.id.matchingSecretTextViews);
        this.secretMatchingTextView.setIsVisible(true);
        this.secretMatchingTextView.setLines(10);
        this.secretMatchingTextView.setText("내용입니다. \n" + naeyoung);

        sendEditText = (EditText) findViewById(R.id.sendEditTexts);

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
                secretMatchingTextView.setText( charSequence + "\n=====답변=====\n");

//                Log.e("length", secretMatchingTextView.getText().length() + "");
                if (secretMatchingTextView.getText().length() == (30 * index)) {
                    secretMatchingTextView.setMaxLines(index + 1);
                    index++;
//                    Log.e("length", secretMatchingTextView.getText().length() + " / " + index);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });

        cancle = (Button)findViewById(R.id.send_cancles);
        send = (Button)findViewById(R.id.send_commits);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendExtra.putString("res",secretMatchingTextView.getText().toString());
                sendExtra.putBoolean("isQuestion",true);
                sendIntent.putExtras(sendExtra);
                setResult(0,sendIntent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(GcmQuickStartPreference.REGISTRATION_READY));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(GcmQuickStartPreference.REGISTRATION_GENERATING));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(GcmQuickStartPreference.REGISTRATION_COMPLETE));
    }

    public void getInstanceIDToken() {
        if (checkPlayService()) {
            startService(new Intent(this, GcmRegisterIntentService.class));
        }
    }
    private boolean checkPlayService() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {

            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 90000).show();

            } else {
                Log.i("MainActivity", "This device is not supported.");
                //finish();
            }
            return false;
        }
        return true;
    }

    public void registBroadcastReceiver() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();

                if (action.equals(GcmQuickStartPreference.REGISTRATION_READY)) {
                    // 액션이 READY일 경우

                } else if (action.equals(GcmQuickStartPreference.REGISTRATION_GENERATING)) {
                    // 액션이 GENERATING일 경우

                } else if (action.equals(GcmQuickStartPreference.REGISTRATION_COMPLETE)) {
                    // 액션이 COMPLETE일 경우
                    token = intent.getStringExtra("token");
                    String[] params = new String[3];
                    params[0] = token;
                    params[1] = sex;
                    params[2] = age;


                    Log.e("params", params[0] + "/" + params[1] + "/" + params[2]);


                    if (token != null) {
                        edit.putBoolean("Enable", true);
                        edit.putString("GCM", token);
                        edit.putString("sex", sex);
                        edit.putString("age", age);

                        edit.commit();

                        new PostServer().execute(params);
                    } else {
                        Snackbar.make(getWindow().getDecorView(), "GCM코드를 받아오지 못했습니다. (2초 뒤 종료)", Snackbar.LENGTH_SHORT).show();
                        edit.putBoolean("Enable", false);
                        edit.commit();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }


                }
            }
        };
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        Snackbar.make(view, "onActivityResult", Snackbar.LENGTH_SHORT).show();
        try {
            new DecodeJson().decodeJson(MainActivity.userRes, view, data.getExtras().getString("res"),data.getExtras().getBoolean("isQuestion"));
        }
        catch (Exception e)
        {
            return;
        }

        if (resultCode == RESULT_OK) {
            sex = data.getStringExtra("sex");
            age = data.getStringExtra("age");

            Log.e("params", sex + "/" + age);

            registBroadcastReceiver();
            getInstanceIDToken();
        }
    }
}

