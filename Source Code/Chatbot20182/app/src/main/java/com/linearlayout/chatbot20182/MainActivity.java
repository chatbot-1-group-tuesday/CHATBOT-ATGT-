package com.linearlayout.chatbot20182;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.linearlayout.chatbot20182.data.DBManager;


public class MainActivity extends AppCompatActivity {


    private Button btnShowLaw;
    private Button btnAddLaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBManager dbManager = new DBManager(this);
        initWiget();

        btnShowLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowLaw.class);
                startActivity(intent);
            }
        });
        btnAddLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddLaw.class);
                startActivity(intent);
            }
        });

    }
    private void initWiget() {

        btnShowLaw = (Button) findViewById(R.id.btn_show_law);
        btnAddLaw = (Button) findViewById(R.id.btn_add_law);
    }
}
