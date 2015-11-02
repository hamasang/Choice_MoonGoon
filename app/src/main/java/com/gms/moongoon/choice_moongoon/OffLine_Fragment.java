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
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by user on 2015-08-08.
 */
public class OffLine_Fragment extends Fragment {

    View view;
    Button btn1;
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
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setDuration(5000);
                inside.startAnimation(rotateAnimation);//회전 에니메이션

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

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(enter && nextAnim == R.drawable.roulette_spin) {    // 특정 animation detect
            Animation anim = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Toast.makeText(getActivity(),"결과는 1입니다!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Toast.makeText(getActivity(),"결과는 2입니다!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    Toast.makeText(getActivity(),"결과는 ~입니다!",Toast.LENGTH_SHORT).show();
                }
            });

            return anim;
        }

        return super.onCreateAnimation(transit, enter, nextAnim);
    }

}
