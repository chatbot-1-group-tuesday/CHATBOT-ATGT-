package com.linearlayout.chatbot20182;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.linearlayout.chatbot20182.Adapter.CustomAdapter;
import com.linearlayout.chatbot20182.Model.Law;
import com.linearlayout.chatbot20182.data.DBManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText edtName;
    private EditText edtDescription;
    private Button btnSave;
    private Button btnUpdate;
    private ListView lvLaw;

    private CustomAdapter customAdapter;
    private List<Law> lawList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBManager dbManager = new DBManager(this);
        initWidget();
        lawList=dbManager.getAllLaw();


        setAdapter();
        //Ánh xạ biến vào giao dien thong qua id
        //Bat su kien cua nut Button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Law law =createLaw();
                 if (law !=null)
                 {
                     dbManager.addLaw(law);
                 }

            }
        });
    }
        private Law createLaw()
    {
        String name = edtName.getText().toString();
        String description =edtDescription.getText().toString();

        Law law =new Law(name,description);
        return law;



    }

    private void initWidget()
    {
        //Ánh xạ biến vào giao dien thong qua id
        edtName = (EditText) findViewById(R.id.edt_name);
        edtDescription = (EditText) findViewById(R.id.edt_description);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnUpdate = (Button) findViewById(R.id.btn_update);

        lvLaw = (ListView) findViewById(R.id.lv_law);
    }

    private void setAdapter()
    {
        if (customAdapter==null)
        {
            CustomAdapter customAdapter=new CustomAdapter(this,R.layout.item_listviewlaw,lawList);
            lvLaw.setAdapter(customAdapter);
        }
        lvLaw.setAdapter(customAdapter);
    }

    }

