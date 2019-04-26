package com.linearlayout.chatbot20182;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.linearlayout.chatbot20182.data.DBManager;

public class MainActivityChinh extends AppCompatActivity



{
    private Button btnLaw;
    private Button btnSign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chinh);
        final DBManager dbManager = new DBManager(this);
        initWiget();

        btnLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChinh.this, MainActivityLaw.class);
                startActivity(intent);
            }
        });
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChinh.this, MainActivitySign.class);
                startActivity(intent);
            }
        });


    }
    private void initWiget() {

        btnLaw = (Button) findViewById(R.id.btn_law);
        btnSign = (Button) findViewById(R.id.btn_sign);



    }

}

