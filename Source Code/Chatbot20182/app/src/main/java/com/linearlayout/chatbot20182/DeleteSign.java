package com.linearlayout.chatbot20182;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.linearlayout.chatbot20182.Adapter.CustomAdapterSign;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Sign;

import java.util.List;

public class DeleteSign extends AppCompatActivity {
    private List<Sign> SignByName;
    private List<Sign> allSign;
    private ListView lvSign;
    private TextView tv_find_name;
    private Button btn_find_name;
    private Button btn_delete;
    private TextView delete_id;

    private CustomAdapterSign customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_sign);
        final DBManager dbManager = new DBManager(this);
        init_Wiget();
        btn_delete.setEnabled(false);
        btn_find_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SignByName = dbManager.getAllSignByName(tv_find_name.getText().toString());
                    setAdapter();
                    Toast.makeText(getApplicationContext(), "đã hiện danh sách", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                SignByName = dbManager.getAllSignByName(tv_find_name.getText().toString());
            }
        });
        lvSign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sign Sign = SignByName.get(position);
                delete_id.setText(String.valueOf(Sign.getmId()));
                btn_delete.setEnabled(true);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(delete_id.getText().toString());
                dbManager.deleteSign(id);
                customAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void init_Wiget() {
        lvSign = findViewById(R.id.lv_sign);
        tv_find_name = findViewById(R.id.delete_find_name);
        btn_find_name = findViewById(R.id.delete_btn_tim_bien);
        btn_delete = findViewById(R.id.btn_delete);
        delete_id = findViewById(R.id.delete_id);
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
