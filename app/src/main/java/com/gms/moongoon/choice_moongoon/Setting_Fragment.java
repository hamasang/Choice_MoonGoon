package com.gms.moongoon.choice_moongoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Setting_Fragment extends Fragment {

    View view;

    ImageView inside,outside;
    //셋팅창


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting, container, false);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return view;


    }
}