package com.linearlayout.chatbot20182;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.linearlayout.chatbot20182.data.DBManager;


public class MainActivitySign extends AppCompatActivity {
    private Button btnShowSign;
    private Button btnAddSign;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign);
        final DBManager dbManager = new DBManager(this);
        initWiget();

        btnShowSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySign.this, ShowSign.class);
                startActivity(intent);
            }
        });
        btnAddSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySign.this, AddSign.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySign.this, UpdateSign.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySign.this, DeleteSign.class);
                startActivity(intent);
            }
        });

    }
    private void initWiget() {

        btnShowSign = (Button) findViewById(R.id.btn_show_sign);
        btnAddSign = (Button) findViewById(R.id.btn_add_Sign);
        btnUpdate=(Button)findViewById(R.id.btn_update);
        btnDelete=(Button)findViewById(R.id.btn_delete);
    }
}
