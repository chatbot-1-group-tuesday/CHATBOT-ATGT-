package com.linearlayout.chatbot20182;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.linearlayout.chatbot20182.adapter.CustomAdapter;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Law;

import java.util.List;

public class AddLaw extends AppCompatActivity {

    private EditText edtName;
    private EditText editDes;
    private EditText editActivate;
    private  EditText editImageURL;
    private Button btnSave;
    private CustomAdapter customAdapter;
    private List<Law> laws;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_law);
        final DBManager dbManager = new DBManager(this);
        initWiget();


        //  laws= dbManager.getAllLaw();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Law law = createLaw();
                if (law != null) {
                    dbManager.addLaw(law);
                }

            }
        });
    }

    private Law createLaw() {
        String name = edtName.getText().toString();
        String des = editDes.getText().toString();
        String activate = editActivate.getText().toString();
        String imageURL=editImageURL.getText().toString();
        Law law = new Law(name, des, imageURL,activate );

        return law;
    }

    private void initWiget() {
        edtName = (EditText) findViewById(R.id.edit_name);
        editDes = (EditText) findViewById(R.id.edit_description);
        editActivate = (EditText) findViewById(R.id.edit_activate);
        btnSave = (Button) findViewById(R.id.btn_save);
        editImageURL=(EditText) findViewById(R.id.edit_image_url);

    }
}
