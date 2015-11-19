package com.gms.moongoon.choice_moongoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Setting_Fragment extends Fragment {

    View view;
    Switch sw1;
    TextView tv1,tv2,tv3;
    Button btn1;
    //셋팅창

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor=pref.edit();
        view = inflater.inflate(R.layout.setting, container, false);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        String ver = getResources().getString(R.string.check_version);;
        tv3 = (TextView)view.findViewById(R.id.textView9);
        tv3.setText(tv3.getText() + ver);

        final String sendmsg = "친구야~ 선택의신 문군앱으로 너의 결정장애를 극복해봐~!";
        btn1 = (Button)view.findViewById(R.id.button6);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sendmsg);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        sw1 = (Switch)view.findViewById(R.id.switch1);
        tv1 = (TextView)view.findViewById(R.id.textView6);
        tv2 = (TextView)view.findViewById(R.id.textView8);
        int sex = pref.getInt("sex",0);
        int age0 = pref.getInt("age",0);
        if(sex == 0) {
            tv1.setText("여성");
        }else if(sex == 1){
            tv1.setText("남성");
        }
        if(age0 == 0){
            tv2.setText("초등학생");
        }else if(age0 == 1){
            tv2.setText("중학생");
        }else if(age0 == 2){
            tv2.setText("고등학생");
        }else if(age0 == 3){
            tv2.setText("청년");
        }else if(age0 == 4){
            tv2.setText("중년");
        }else if(age0 == 5){
            tv2.setText("노년");
        }
        if(pref.getBoolean("sound",true)){
            sw1.setChecked(true);
        }else{
            sw1.setChecked(false);
        }
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putBoolean("sound",true);
                    editor.apply();
                    Toast.makeText(getActivity(),"알림 켜짐",Toast.LENGTH_SHORT).show();
                }else{
                    editor.putBoolean("sound",false);
                    editor.apply();
                    Toast.makeText(getActivity(),"알림 꺼짐",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
//        if(pref.getBoolean("sound",false)&&pref.getBoolean("notice",false)){
//            data[1] = "진동 켜짐";
//            Toast.makeText(getActivity(),"진동 켜짐",Toast.LENGTH_SHORT).show();
//        }else if(pref.getBoolean("sound",true)&&pref.getBoolean("notice",true)){
//            data[1] = "진동 꺼짐";
//            Toast.makeText(getActivity(),"진동 꺼짐",Toast.LENGTH_SHORT).show();
//        }

    }


}