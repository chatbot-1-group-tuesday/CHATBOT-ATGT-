package com.linearlayout.chatbot20182;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.linearlayout.chatbot20182.Adapter.CustomAdapter;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Law;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AddLaw extends AppCompatActivity {

    private EditText edtName;
    private EditText editDes;
    private EditText editActivate;
  //  private ImageView editImage;
    private Button btnSave;
//Thêm luật vô CSDL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_law);
        final DBManager dbManager = new DBManager(this);
        initWiget();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Law law = createLaw();
                    if (law != null) {
                        dbManager.addLaw(law);
                    }
                    Toast.makeText(getApplicationContext(), "Đã thêm luật thành công", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private Law createLaw() {
        String name = edtName.getText().toString();
        String des = editDes.getText().toString();
        String activate = editActivate.getText().toString();
     //   byte[] imageByte =Image_To_Byte(editImage);

        Law law = new Law(name, des, activate);

        return law;
    }

    private void initWiget() {
        edtName = (EditText) findViewById(R.id.edit_name);
        editDes = (EditText) findViewById(R.id.edit_description);
        editActivate = (EditText) findViewById(R.id.edit_activate);
        btnSave = (Button) findViewById(R.id.btn_save);
       // editImage = (ImageView) findViewById(R.id.edit_image);

    }

   /* public byte[] Image_To_Byte(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }*/


}
