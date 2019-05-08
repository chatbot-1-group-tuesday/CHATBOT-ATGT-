package com.linearlayout.chatbot20182;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class chatbotapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Lấy context ban đầu của ứng dụng
        AndroidNetworking.initialize(getApplicationContext());

//vượt đèn đỏ phạt bao nhiêu tiền vậy

    }
}
