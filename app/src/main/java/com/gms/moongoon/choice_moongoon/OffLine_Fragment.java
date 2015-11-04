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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2015-08-08.
 */
public class OffLine_Fragment extends Fragment {
    static ArrayList<String> food = new ArrayList<String>();
    View view;
    Button btn1,btn2;
    ImageView inside,outside;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.offline, container, false);

        inside = (ImageView)view.findViewById(R.id.spinner_inside);
        outside = (ImageView)view.findViewById(R.id.spinner_outside);
        //재자리회전
        btn1 = (Button) view.findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food.size() <= 0) {
                    OffLine_Fragment.food.clear();
                    Snackbar.make(getView(), "입력부터 하세요!", Snackbar.LENGTH_LONG).show();
                } else {
                    RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    rotateAnimation.setRepeatCount(0);
                    rotateAnimation.setDuration(5000);
                    inside.startAnimation(rotateAnimation);//회전 에니메이션
                    final Timer timer = new Timer();

                    final TimerTask myTask = new TimerTask() {
                        @Override
                        public void run() {
                            timer.cancel();
                            String a = String.valueOf((int) (Math.random() * food.size()));
                            if (a.equals("0")) {
                                Snackbar.make(getView(), food.get(0) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            } else if (a.equals("1")) {
                                Snackbar.make(getView(), food.get(1) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            } else if (a.equals("2")) {
                                Snackbar.make(getView(), food.get(2) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            } else if (a.equals("3")) {
                                Snackbar.make(getView(), food.get(3) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            } else if (a.equals("4")) {
                                Snackbar.make(getView(), food.get(4) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            } else if (a.equals("5")) {
                                Snackbar.make(getView(), food.get(5) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            } else if (a.equals("6")) {
                                Snackbar.make(getView(), food.get(6) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            } else if (a.equals("7")) {
                                Snackbar.make(getView(), food.get(7) +
                                        "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                            }

                        }

                    };
                    timer.schedule(myTask, 5000);


                }
            }
        });
        btn2 = (Button)view.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), input.class);
                startActivity(intent);
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
