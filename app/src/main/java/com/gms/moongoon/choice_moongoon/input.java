package com.gms.moongoon.choice_moongoon;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sang on 2015-11-03.
 */

public class input extends AppCompatActivity {

    Button btn4;
    EditText edt1,edt2,edt3,edt4,edt5,edt6,edt7,edt8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);
        OffLine_Fragment.food.clear();
        edt1 = (EditText)findViewById(R.id.editText);
        edt2 = (EditText)findViewById(R.id.editText2);
        edt3 = (EditText)findViewById(R.id.editText3);
        edt4 = (EditText)findViewById(R.id.editText4);
        edt5 = (EditText)findViewById(R.id.editText5);
        edt6 = (EditText)findViewById(R.id.editText6);
        edt7 = (EditText)findViewById(R.id.editText7);
        edt8 = (EditText)findViewById(R.id.editText8);
        btn4 = (Button)findViewById(R.id.button3);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt1.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt1.getText()));
                }
                if(!edt2.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt2.getText()));
                }
                if(!edt3.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt3.getText()));
                }
                if(!edt4.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt4.getText()));
                }
                if(!edt5.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt5.getText()));
                }
                if(!edt6.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt6.getText()));
                }
                if(!edt7.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt7.getText()));
                }
                if(!edt8.getText().equals("")){
                    OffLine_Fragment.food.add(String.valueOf(edt8.getText()));
                }
                finish();
            }
        });
    }
}
