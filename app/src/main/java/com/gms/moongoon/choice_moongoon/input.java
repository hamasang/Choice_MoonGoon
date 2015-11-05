package com.gms.moongoon.choice_moongoon;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sang on 2015-11-03.
 */

public class input extends AppCompatActivity {

    Button btn4;
    EditText edt[] = new EditText[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);


        edt[0] = (EditText)findViewById(R.id.editText);
        edt[1] = (EditText)findViewById(R.id.editText2);
        edt[2] = (EditText)findViewById(R.id.editText3);
        edt[3] = (EditText)findViewById(R.id.editText4);
        edt[4] = (EditText)findViewById(R.id.editText5);
        edt[5] = (EditText)findViewById(R.id.editText6);
        edt[6]= (EditText)findViewById(R.id.editText7);
        edt[7] = (EditText)findViewById(R.id.editText8);
        btn4 = (Button)findViewById(R.id.button3);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0 ; i < 8 ; i++){
                    if(!edt[i].getText().equals(""))
                        OffLine_Fragment.food[i] = (edt[i].getText().toString());
                }

                /*
                if(!edt1.getText().equals("")){
                    OffLine_Fragment.food.add(edt1.getText().toString());
                }else if(!edt2.getText().equals("")){
                    OffLine_Fragment.food.add(edt2.getText().toString());
                }else if(!edt3.getText().equals("")){
                    OffLine_Fragment.food.add(edt3.getText().toString());
                }else if(!edt4.getText().equals("")){
                    OffLine_Fragment.food.add(edt4.getText().toString());
                }else if(!edt5.getText().equals("")){
                    OffLine_Fragment.food.add(edt5.getText().toString());
                }else if(!edt6.getText().equals("")){
                    OffLine_Fragment.food.add(edt6.getText().toString());
                }else if(!edt7.getText().equals("")){
                    OffLine_Fragment.food.add(edt7.getText().toString());
                }else if(!edt8.getText().equals("")){
                    OffLine_Fragment.food.add(edt8.getText().toString());
                }*/
                finish();

            }
        });
    }
}
