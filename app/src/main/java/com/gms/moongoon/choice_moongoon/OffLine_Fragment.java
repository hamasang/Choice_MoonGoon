package com.gms.moongoon.choice_moongoon;

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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2015-08-08.
 */
public class OffLine_Fragment extends Fragment {

    View view;
    Button btn1;
    ImageView inside,outside;
    Random mRand;
    EditText fd1,fd2,fd3,fd4,fd5,fd6,fd7,fd8;
    public  String[] food = {};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.offline, container, false);
        fd1 = (EditText)view.findViewById(R.id.food1);
        fd2 = (EditText)view.findViewById(R.id.food2);
        fd3 = (EditText)view.findViewById(R.id.food3);
        fd4 = (EditText)view.findViewById(R.id.food4);
        fd5 = (EditText)view.findViewById(R.id.food5);
        fd6 = (EditText)view.findViewById(R.id.food6);
        fd7 = (EditText)view.findViewById(R.id.food7);
        fd8 = (EditText)view.findViewById(R.id.food8);

        inside = (ImageView)view.findViewById(R.id.spinner_inside);
        outside = (ImageView)view.findViewById(R.id.spinner_outside);
        //재자리회전
        btn1 = (Button) view.findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(fd1.getText().toString()==""){
//
//                }
//                else if(fd2.getText().toString()==""){
//
//                }
//                else if(fd3.getText().toString()==""){
//
//                }
//                else if(fd4.getText().toString()==""){
//
//                }
//                else if(fd5.getText().toString()==""){
//
//                }
//                else if(fd6.getText().toString()==""){
//
//                }
//                else if(fd7.getText().toString()==""){
//
//                }
//                else if(fd8.getText().toString()==""){
//
//                }else{
//
//                    for(int i = 1;i<=8;i++) {
//                        food[i] = String.valueOf("fd" + i);
//
//                    }
//                }
                Snackbar.make(getView(),"아직 서비스 되지 않습니다.",Snackbar.LENGTH_LONG).show();

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
                        Snackbar.make(getView(), "이(가) 선택되었습니다.", Snackbar.LENGTH_LONG).show();
                    }
                };
                timer.schedule(myTask, 5000);


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
