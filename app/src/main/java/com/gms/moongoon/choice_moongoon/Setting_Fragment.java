package com.gms.moongoon.choice_moongoon;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Setting_Fragment extends Fragment implements AdapterViewCompat.OnItemClickListener, AdapterView.OnItemClickListener {

    View view;
    ListView listView;
    ImageView inside,outside;
    private ListView monthsListView;
    private ArrayAdapter arrayAdapter;
    String[] data = {"프로필", "진동", "푸시알림", "카톡으로 문군추천" ,"버전정보: "};
    //셋팅창

    private ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting, container, false);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return view;


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView)getActivity().findViewById(R.id.listView);
        data[4] = data[4] + getResources().getString(R.string.check_version);
        
        // 리스트뷰 초기화
        monthsListView = (ListView)view.findViewById(R.id.listView);
        // 어댑터 설정
        arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,data);
        // setAdapter 를 이용해서 monthsListView 에 어댑터 설정
        monthsListView.setAdapter(arrayAdapter);
        monthsListView.setOnItemClickListener(Setting_Fragment.this);





    }


    @Override
    public void onItemClick(AdapterViewCompat<?> parent, View view, int position, long id) {
        //있어도 안쓰는곳 지우는건 ㄴㄴ
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
//                Toast.makeText(getActivity(),"1번",Toast.LENGTH_SHORT).show();
            case 1:
                Toast.makeText(getActivity(),"기능구현중",Toast.LENGTH_SHORT).show();
            case 2:
                Toast.makeText(getActivity(),"기능구현중",Toast.LENGTH_SHORT).show();
            case 3:
                Toast.makeText(getActivity(),"기능구현중",Toast.LENGTH_SHORT).show();
            case 4:
                Toast.makeText(getActivity(),getString(R.string.check_version),Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}