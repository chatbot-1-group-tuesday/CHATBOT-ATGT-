package com.linearlayout.chatbot20182;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.linearlayout.chatbot20182.data.DBManager;

public class MainActivityChinh extends AppCompatActivity



{
    private Button btnLaw;
    private Button btnSign;
    private Button btnChat;
    private Button btnThongtin;
    private Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chinh);
        final DBManager dbManager = new DBManager(this);
        initWiget();
        ControlButton();

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

       btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChinh.this, Chatchat.class);
                startActivity(intent);
            }
        });


        btnThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChinh.this, MainActivityThongTin.class);
                startActivity(intent);
            }
        });
    }
// Phần Code thêm chức năng Đăng Xuất cho hệ thống
    private void ControlButton() {
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityChinh.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc muốn thoát khỏi ChatBot không ?");
                builder.setMessage("Bạn hãy lựa chọn bên dưới để xác nhận ! ");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void initWiget() {

        btnLaw = (Button) findViewById(R.id.btn_law);
        btnSign = (Button) findViewById(R.id.btn_sign);
        btnChat=  (Button) findViewById(R.id.btn_chat);
        btnThongtin=(Button) findViewById(R.id.btn_thongtin);
        btnDangXuat=(Button) findViewById(R.id.btn_dangxuat);

    }

}

