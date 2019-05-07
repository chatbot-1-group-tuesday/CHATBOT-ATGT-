package com.linearlayout.chatbot20182;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.linearlayout.chatbot20182.Adapter.CustomAdapter;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Law;

import java.util.List;

public class ShowLaw extends AppCompatActivity {
    private List<Law> law;
    private ListView lvLaw;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_law);
        DBManager dbManager = new DBManager(this);
        init_Wiget();
        law = dbManager.getAllLaw();
        setAdapter();
    }
    public void init_Wiget() {
        lvLaw = findViewById(R.id.lv_law);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.row_show_law, law);
        }
        lvLaw.setAdapter(customAdapter);



    }

}
