package com.linearlayout.chatbot20182;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.linearlayout.chatbot20182.Adapter.CustomAdapter;
import com.linearlayout.chatbot20182.data.DBManager;
import com.linearlayout.chatbot20182.model.Law;
import java.util.List;

public class DeleteLaw extends AppCompatActivity {
    private List<Law> lawByName;
    private  List<Law> allLaw;
    private ListView lvLaw;
    private TextView tv_find_name;
    private Button btn_find_name;
    private Button btn_delete;
    private TextView delete_id;

    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_law);
        final DBManager dbManager = new DBManager(this);
        init_Wiget();

        btn_find_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    lawByName = dbManager.getAllLawByName(tv_find_name.getText().toString());
                    setAdapter();
                    Toast.makeText(getApplicationContext(), "Đã tìm thấy luật", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                lawByName = dbManager.getAllLawByName(tv_find_name.getText().toString());
            }
        });
        lvLaw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Law law = lawByName.get(position);
                delete_id.setText(String.valueOf(law.getmId()));
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(delete_id.getText().toString());
                dbManager.deleteLaw(id);
                customAdapter.notifyDataSetChanged();
            }
        });
    }
    public void init_Wiget() {
        lvLaw = findViewById(R.id.lv_law);
        tv_find_name = findViewById(R.id.delete_find_name);
        btn_find_name = findViewById(R.id.delete_btn_tim_bien);
        btn_delete = findViewById(R.id.btn_delete);
        delete_id = findViewById(R.id.delete_id);
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

