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
    TextView tv1,g1,g2,g3,g4;
    Button btn1,btn2;
    AnimationDrawable character_online_frameAnimationDrawable, fish_online_frameAnimationDrawable;

    Button mainSend,mainSends, receiveAnswer, receiveQuestion;
    View view;
    String[] mtalks = {"아야!","나는 건드리지 마!", "무슨 일 있어?", "내 위에 있는 물고기를 눌러줘!"};


    public static Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.online, container, false);
        img=(ImageView)view.findViewById(R.id.character_online);
        g1 = (TextView)view.findViewById(R.id.guide1);
        g2 = (TextView)view.findViewById(R.id.guide2);
        g3 = (TextView)view.findViewById(R.id.guide3);
        g4 = (TextView)view.findViewById(R.id.guide4);

        SharedPreferences pref = this.getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        if (pref.getBoolean("first", true)) {
            SharedPreferences.Editor editor=pref.edit();
            editor.putBoolean("first", false);
            editor.apply();

            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    g1.setVisibility(View.VISIBLE);
                }
            });

            final Timer timer1 = new Timer();

            TimerTask myTask1 = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            g1.setVisibility(getView().GONE);
                        }
                    });
                }
            };
            timer1.schedule(myTask1, 9000);
            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    g1.setVisibility(View.VISIBLE);
                }
            });



            //두번째;; 힘들다;
            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    g2.setVisibility(View.VISIBLE);
                }
            });


            final Timer timer2 = new Timer();

            TimerTask myTask2 = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            g2.setVisibility(getView().GONE);
                        }
                    });
                }
            };
            timer2.schedule(myTask2, 7000);


            //세번째
            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    g3.setVisibility(View.VISIBLE);
                }
            });
            final Timer timer3 = new Timer();

            TimerTask myTask3 = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            g3.setVisibility(getView().GONE);
                        }
                    });
                }
            };
            timer3.schedule(myTask3, 6000);


            //네번째
            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    g4.setVisibility(View.VISIBLE);
                }
            });

            final Timer timer4 = new Timer();

            TimerTask myTask4 = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            g4.setVisibility(getView().GONE);
                        }
                    });
                }
            };
            timer1.schedule(myTask4, 5000);




//            Intent intent=new Intent(getActivity(), guidesplash.class);
//            startActivity(intent);
        }

        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        tv1 = (TextView)view.findViewById(R.id.mtalk);
        init();

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
                } else {
                    MainActivity.userRes = msg.getData().getString("");
                    Snackbar.make(view, "유저목록을 가져오지 못했습니다.", Snackbar.LENGTH_SHORT).show();
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

        fish_online = (ImageView) view.findViewById(R.id.fish_online);
        fish_online.setBackgroundResource(R.drawable.fish_anim);

        fish_online_frameAnimationDrawable = (AnimationDrawable) fish_online.getBackground();
        fish_online_frameAnimationDrawable.start();
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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "답변받기는 아직 지원되지 않습니다.", Snackbar.LENGTH_SHORT).show();
            }
        });
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        String asas = pref.getString("titles", "");
        if(asas.toString().equals("")){
            receiveQuestion.setVisibility(View.GONE);
        }else if(pref.getBoolean("first", true)) {
            receiveQuestion.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_send:
                Snackbar.make(view, "유저목록을 가져오고 있습니다. 기다려주십시오", Snackbar.LENGTH_SHORT).show();
                new GetServer().execute();
                break;
            case R.id.receive_question:
                Snackbar.make(view, "유저목록을 가져오고 있습니다. 기다려주십시오", Snackbar.LENGTH_SHORT).show();
                new GetServer().execute();
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
