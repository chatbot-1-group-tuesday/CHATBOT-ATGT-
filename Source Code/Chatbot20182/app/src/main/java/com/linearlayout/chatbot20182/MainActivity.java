package com.linearlayout.chatbot20182;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.linearlayout.chatbot20182.data.DBManager;


public class MainActivity extends AppCompatActivity {
    private Button btn_tim_kiem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBManager dbManager = new DBManager(this);
        initWiget();


        btn_tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindSign.class);
                startActivity(intent);
            }
        });

    }
    private void initWiget() {


        btn_tim_kiem=(Button)findViewById(R.id.btn_update);

    }
}
