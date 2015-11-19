package com.gms.moongoon.choice_moongoon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gms.moongoon.choice_moongoon.GCM_Manage.GCM_SERVER;
import com.gms.moongoon.choice_moongoon.GET_POST.GetServer;
import com.gms.moongoon.choice_moongoon.splash.guidesplash;
import com.gms.moongoon.choice_moongoon.tools.DecodeJson;
import com.gms.moongoon.choice_moongoon.tools.Loading_Image;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import static com.google.android.gms.internal.zzhl.runOnUiThread;

/**
 * Created by user on 2015-08-08.
 */
public class OnLine_Fragment extends Fragment implements View.OnClickListener {
    ImageView imageView,img;
    ImageView character_online, fish_online;
    TextView tv1,tv2;
    Button btn1,btn2;
    AnimationDrawable character_online_frameAnimationDrawable, fish_online_frameAnimationDrawable;

    Button mainSend,mainSends, receiveAnswer, receiveQuestion;
    View view;
    String[] mtalks = {"아야!","나는 건드리지 마!", "무슨 일 있어?", "내 위에 있는 물고기를 눌러줘!"};

    public static int mssg = 0;
    public static Handler handler;
    public static String pus = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.online, container, false);
        img=(ImageView)view.findViewById(R.id.character_online);

        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        tv1 = (TextView)view.findViewById(R.id.mtalk);
        tv2 = (TextView)view.findViewById(R.id.textView3);
        init();
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor=pref.edit();
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setVisibility(View.GONE);
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    MainActivity.userRes = msg.getData().getString("res");
                    Snackbar.make(view, "유저목록로딩완료", Snackbar.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().startActivityForResult(new Intent(OnLine_Fragment.this.getActivity(), SendActivity.class), 0);
                        }
                    }, 1000);
                } else if(msg.what == 1) {
                    MainActivity.userRes = msg.getData().getString("");
                    Snackbar.make(view, "유저목록을 가져오지 못했습니다.", Snackbar.LENGTH_SHORT).show();
                } else if(msg.what == 2){
                    MainActivity.userRes = msg.getData().getString("res");
                    Snackbar.make(view, "유저목록로딩완료", Snackbar.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().startActivityForResult(new Intent(OnLine_Fragment.this.getActivity(), resend.class), 2);
                        }
                    }, 1000);
                }
            }
        };

        return view;
    }

    private void init() {
        imageView = (ImageView) view.findViewById(R.id.backGround_online);
//
        character_online = (ImageView) view.findViewById(R.id.character_online);
//
        character_online_frameAnimationDrawable = (AnimationDrawable) character_online.getDrawable();
        character_online_frameAnimationDrawable.start();
//        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
//        Boolean enable = pref.getBoolean("Enable", false);
//        {
//            fish_online = (ImageView) view.findViewById(R.id.fish_online);
//            fish_online.setBackgroundResource(R.drawable.fish_anim);
//            fish_online_frameAnimationDrawable = (AnimationDrawable) fish_online.getBackground();
//            fish_online_frameAnimationDrawable.start();
//        }
        character_online = (ImageView)view.findViewById(R.id.character_online);
        character_online.setOnClickListener(this);
        mainSend = (Button) view.findViewById(R.id.main_send);
        mainSend.setOnClickListener(this);
        receiveAnswer = (Button) view.findViewById(R.id.receive_answer);
        receiveAnswer.setOnClickListener(this);
        receiveQuestion = (Button) view.findViewById(R.id.receive_question);
        receiveQuestion.setOnClickListener(this);
        btn1 = (Button)view.findViewById(R.id.receive_answer);
        btn2 = (Button)view.findViewById(R.id.receive_question);
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        String asas = pref.getString("titles", "");

        if(pref.getBoolean("first", true)) {
            if (asas.toString().equals("")) {
                receiveQuestion.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
            } else {
                receiveQuestion.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv2.setText(tv2.getText() + asas.replaceAll("======답장======",""));
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_send:
                Snackbar.make(view, "유저목록을 가져오고 있습니다. 기다려주십시오", Snackbar.LENGTH_SHORT).show();
                new GetServer().execute();
                break;
            case R.id.receive_answer:
                Snackbar.make(view, "답변받기는 아직 지원되지 않습니다.", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.receive_question:
                mssg = 1;

                SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
                String dsdf = pref.getString("titles","");
                if(dsdf.length() > 0){

                    SharedPreferences.Editor editor = pref.edit();
                    editor.remove("titles");
                    editor.apply();
                    Snackbar.make(view, "유저목록을 가져오고 있습니다. 기다려주십시오", Snackbar.LENGTH_SHORT).show();
                    new GetServer().execute();

                }else{
                    Snackbar.make(view, "답할 답변이 없습니다.", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.character_online:
                tv1.setVisibility(View.VISIBLE);
                int b = (int) (Math.random() * mtalks.length);
                tv1.setText(mtalks[b]);
                try {
                    final Timer timer = new Timer();

                    TimerTask myTask = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    tv1.setVisibility(View.GONE);
                                    tv1.setText("");
                                }
                            });
                        }
                    };
                    timer.schedule(myTask, 1800);
                }catch (Exception e){
                    Toast.makeText(getActivity(), (CharSequence) e,Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view.findViewById(R.id.backGround_online).setBackground(null);
        System.gc();
    }

}
