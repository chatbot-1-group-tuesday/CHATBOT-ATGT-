package com.linearlayout.chatbot20182;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.linearlayout.chatbot20182.Adapter.CustomAdapterSign;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Sign;

import java.util.List;

public class ShowSign extends AppCompatActivity {
    private List<Sign> Sign;
    private ListView lvSign;
    private CustomAdapterSign customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_sign);
        DBManager dbManager = new DBManager(this);
        init_Wiget();
        Sign = dbManager.getAllSign();
        setAdapter();
    }
    public void init_Wiget() {
        lvSign = findViewById(R.id.lv_Sign);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapterSign(this, R.layout.row_show_sign, Sign);
        }
        lvSign.setAdapter(customAdapter);
    }

}
