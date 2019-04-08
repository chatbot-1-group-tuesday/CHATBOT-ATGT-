package com.linearlayout.chatbot20182;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.linearlayout.chatbot20182.Adapter.CustomAdapter;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Law;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class UpdateLaw extends AppCompatActivity {
    private List<Law> lawByName;
    private ListView lvLaw;
    private TextView tv_find_name;
    private Button btn_find_name;
    private Button btn_save;
    private TextView update_id;
    private TextView update_name;
    private TextView update_des;
    private TextView update_activate;


    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_law);
        final DBManager dbManager = new DBManager(this);
        init_Wiget();
       /* update_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1111);
            }
        });*/
        btn_save.setEnabled(false);
       // update_image.setEnabled(false);
        update_activate.setEnabled(false);
        update_des.setEnabled(false);
        update_name.setEnabled(false);



        btn_find_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    lawByName = dbManager.getAllLawByName(tv_find_name.getText().toString());
                    setAdapter();
                    Toast.makeText(getApplicationContext(), "ok nhe", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                lawByName = dbManager.getAllLawByName(tv_find_name.getText().toString());
            }
        });
        lvLaw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn_save.setEnabled(true);

                update_activate.setEnabled(true);
                update_des.setEnabled(true);
                update_name.setEnabled(true);
                Law law = lawByName.get(position);
                update_id.setText(String.valueOf(law.getmId()));
                update_name.setText(law.getmName());
                update_des.setText(law.getmDescription());
                update_activate.setText(law.getmActivate());

                btn_save.setEnabled(true);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(update_id.getText().toString());
                String name = update_name.getText().toString();
                String des = update_des.getText().toString();
                String activate = update_activate.getText().toString();
               // byte[] imageByte = Image_To_Byte(update_image);
                Law law = new Law(id, name, des,  activate);
                dbManager.updateLaw(law);
                customAdapter.notifyDataSetChanged();
            }
        });

    }

    public void init_Wiget() {
        lvLaw = findViewById(R.id.lv_law);
        tv_find_name = findViewById(R.id.update_find_name);
        btn_find_name = findViewById(R.id.update_btn_tim_bien);
        btn_save = findViewById(R.id.btn_save);
        update_id = findViewById(R.id.update_id);
        update_name = findViewById(R.id.update_name);
        update_des = findViewById(R.id.update_description);
        update_activate = findViewById(R.id.update_activate);


    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.row_show_law, lawByName);
            lvLaw.setAdapter(customAdapter);
        } else {
                customAdapter.notifyDataSetChanged();
                lvLaw.setSelection(customAdapter.getCount() - 1);
        }

    }

}

