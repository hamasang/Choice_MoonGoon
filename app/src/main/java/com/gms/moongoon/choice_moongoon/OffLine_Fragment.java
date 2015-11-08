package com.gms.moongoon.choice_moongoon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gms.moongoon.choice_moongoon.splash.rulletsplash;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.google.android.gms.internal.zzhl.runOnUiThread;

/**
 * Created by user on 2015-08-08.
 * 수정일 2015-11-04
 */
public class OffLine_Fragment extends Fragment {
//    static ArrayList<String> food = new ArrayList<String>();
    static String food[] = new String[8];
    int foodcnt = 0;

    View view;
    Button btn1,btn2;
    ImageView inside,outside;
    TextView tv1,tv2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.offline, container, false);
        inside = (ImageView)view.findViewById(R.id.spinner_inside);
        outside = (ImageView)view.findViewById(R.id.spinner_outside);
        tv1 = (TextView)view.findViewById(R.id.textView);
        tv2 = (TextView)view.findViewById(R.id.textView2);

        //재자리회전
        btn1 = (Button) view.findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[0] == null) {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            tv2.setVisibility(View.VISIBLE);
                            tv2.setText("입력부터하렴!");
                        }
                    });

                    final Timer timer1 = new Timer();

                    TimerTask myTask1 = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    tv2.setVisibility(getView().GONE);
                                    tv2.setText("");
                                }
                            });
                        }
                    };
                    timer1.schedule(myTask1, 1800);

                } else {
                    RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    rotateAnimation.setRepeatCount(0);
                    rotateAnimation.setDuration(2000);
                    inside.startAnimation(rotateAnimation);//회전 에니메이션
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv2.setVisibility(View.VISIBLE);
                            tv2.setText("뭐가나올까요?");
                        }
                    });

                    final Timer timer2 = new Timer();

                    TimerTask myTask2 = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    tv2.setVisibility(getView().GONE);
                                    tv2.setText("");
                                }
                            });
                        }
                    };
                    timer2.schedule(myTask2, 1800);
                    final Timer timer = new Timer();

                    final TimerTask myTask = new TimerTask() {
                        @Override
                        public void run() {
//                            timer.cancel();
                            for(int i = 0 ; i < 8 ; i++)
                                if(!food[i].equals("")) foodcnt++;
                            Random r = new Random();
                            final int a = r.nextInt(foodcnt);

                            try {

                                runOnUiThread(new Runnable(){
                                    @Override
                                    public void run(){
                                        if(food[a].length() <= 0){
                                            tv1.setVisibility(View.VISIBLE);
                                            tv1.setText("다시~!");
                                        }else{
                                            tv1.setVisibility(View.VISIBLE);
                                            tv1.setText(food[a]+"이(가) 선택되었습니다!");
                                        }
                                    }
                                });

                                final Timer timer3 = new Timer();

                                TimerTask myTask3 = new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable(){
                                            @Override
                                            public void run(){
                                                tv1.setVisibility(getView().GONE);
                                                tv1.setText("");
                                            }
                                        });
                                    }
                                };
                                timer3.schedule(myTask3, 2300);
                            }catch (Exception e){
                                Toast.makeText(getActivity(), (CharSequence) e,Toast.LENGTH_LONG).show();
                            }
                            foodcnt = 0;
                        }

                    };
                    timer.schedule(myTask, 2000);
                }
            }
        });
        btn2 = (Button)view.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), input.class);
                startActivity(intent);
                Intent intents = new Intent(getActivity(), rulletsplash.class);
                startActivity(intents);
            }
        });


        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view.findViewById(R.id.backGround_offline).setBackground(null);
        System.gc();
    }

}