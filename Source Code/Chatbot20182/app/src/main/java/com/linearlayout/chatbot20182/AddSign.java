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
import android.widget.Switch;
import android.widget.Toast;

import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Sign;

import java.io.ByteArrayOutputStream;

public class AddSign extends AppCompatActivity {

    private EditText edtName;
    private EditText editDes;
    private ImageView editImage;
    private Button btnSave;
    private Switch swActivate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sign);
        final DBManager dbManager = new DBManager(this);
        initWiget();
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1111);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Sign sign = createSign();
                    dbManager.addSign(sign);
                    Toast.makeText(getApplicationContext(), "đã thêm", Toast.LENGTH_SHORT).show();


                    // Toast.makeText(getApplicationContext(), "Activate :" + ActivateStatus() + "\n" , Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String ActivateStatus() {
        if (swActivate.isChecked()) {
            return "1";
        } else {
            return "0";
        }
    }

    private Sign createSign() {
        String name = edtName.getText().toString();
        String des = editDes.getText().toString();
        String activate = ActivateStatus();
        byte[] imageByte = Image_To_Byte(editImage);

        Sign Sign = new Sign(name, des, imageByte, activate);

        return Sign;
    }

    private void initWiget() {
        edtName = findViewById(R.id.edit_name);
        editDes = findViewById(R.id.edit_description);
        btnSave = findViewById(R.id.btn_save);
        editImage = findViewById(R.id.edit_image);
        swActivate = findViewById(R.id.sw_activate);

    }

    public byte[] Image_To_Byte(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    //mo may anh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1111 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            editImage.setImageBitmap(image);
        }
    }
}
