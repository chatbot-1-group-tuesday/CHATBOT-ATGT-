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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.linearlayout.chatbot20182.adapter.CustomAdapter;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Sign;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class FindSign extends AppCompatActivity {
    private List<Sign> SignByName;
    private ListView lvSign;
    private TextView tv_find_name;
    private Button btn_find_name;

    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_sign);
        final DBManager dbManager = new DBManager(this);
        init_Wiget();



        btn_find_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SignByName = dbManager.getAllSignByNameAndActivate(tv_find_name.getText().toString());
                    setAdapter();
                    Toast.makeText(getApplicationContext(), "đã hiện kết quả", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                SignByName = dbManager.getAllSignByName(tv_find_name.getText().toString());
            }
        });


    }





    public byte[] Image_To_Byte(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    public void init_Wiget() {
        lvSign = findViewById(R.id.lv_Sign);
        tv_find_name = findViewById(R.id.update_find_name);
        btn_find_name = findViewById(R.id.update_btn_tim_bien);


    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.row_show_sign, SignByName);
            lvSign.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvSign.setSelection(customAdapter.getCount() - 1);
        }

    }

}

