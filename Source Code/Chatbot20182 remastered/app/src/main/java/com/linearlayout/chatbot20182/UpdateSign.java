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

import com.linearlayout.chatbot20182.Adapter.CustomAdapterSign;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Sign;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class UpdateSign extends AppCompatActivity {
    private List<Sign> SignByName;
    private ListView lvSign;
    private TextView tv_find_name;
    private Button btn_find_name;
    private Button btn_save;
    private TextView update_id;
    private TextView update_name;
    private TextView update_des;
    private TextView update_activate;
    private ImageView update_image;
    private Switch update_swActivate;

    private CustomAdapterSign customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_sign);
        final DBManager dbManager = new DBManager(this);
        init_Wiget();
        update_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1111);
            }
        });
        btn_save.setEnabled(false);
        update_image.setEnabled(false);
        update_activate.setEnabled(false);
        update_des.setEnabled(false);
        update_name.setEnabled(false);



        btn_find_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SignByName = dbManager.getAllSignByName(tv_find_name.getText().toString());
                    setAdapter();
                    Toast.makeText(getApplicationContext(), "ok nhe", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                SignByName = dbManager.getAllSignByName(tv_find_name.getText().toString());
            }
        });
        lvSign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn_save.setEnabled(true);
                update_image.setEnabled(true);
                update_activate.setEnabled(true);
                update_des.setEnabled(true);
                update_name.setEnabled(true);
                Sign Sign = SignByName.get(position);
                update_id.setText(String.valueOf(Sign.getmId()));
                update_name.setText(Sign.getmName());
                update_des.setText(Sign.getmDescription());
                update_activate.setText(Sign.getmActivate());
                update_image.setImageBitmap(BitmapFactory.decodeByteArray(Sign.getmImage(), 0, Sign.getmImage().length));
                btn_save.setEnabled(true);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Sign sign = createSign();
                    if (sign != null) {
                        dbManager.updateSign(sign);
                    }
                    Toast.makeText(getApplicationContext(), "đã cập nhật", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getApplicationContext(), "Activate :" + ActivateStatus() + "\n" , Toast.LENGTH_LONG).show();
                    customAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private String ActivateStatus() {
        if (update_swActivate.isChecked()) {
            return  "1";
        } else {
            return  "0";
        }
    }
    private Sign createSign() {
        int id = Integer.parseInt(update_id.getText().toString());
        String name = update_name.getText().toString();
        String des = update_des.getText().toString();
        String activate = update_activate.getText().toString(); //ActivateStatus();
        byte[] imageByte = Image_To_Byte(update_image);
        Sign Sign = new Sign(id, name, des, imageByte, activate);

        return Sign;
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1111 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            update_image.setImageBitmap(image);
        }
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
        btn_save = findViewById(R.id.btn_save);
        update_id = findViewById(R.id.update_id);
        update_name = findViewById(R.id.update_name);
        update_des = findViewById(R.id.update_description);
        update_activate = findViewById(R.id.update_description);
        update_image = findViewById(R.id.update_image);
        update_swActivate= findViewById(R.id.sw_activate);

    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapterSign(this, R.layout.row_show_sign, SignByName);
            lvSign.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvSign.setSelection(customAdapter.getCount() - 1);
        }

    }

}

