package com.linearlayout.chatbot20182;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;

import android.widget.ListView;
//Thử nghiệm truyền dữ liệu vào textview
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;



import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.OutputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.Workspace;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText inputEditText;
    ListView list;
    Button buttonClick;
    Context context;

    public static final String WORKSPACEID = "9c498c33-11cb-4bf5-8ea1-3dc33b358548";
    Assistant assistant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText= (EditText) findViewById(R.id.inputEditText);
        list=(ListView) findViewById(R.id.list);
        buttonClick=(Button) findViewById(R.id.buttonClick);

        //Thử nghiệm truyền dữ liệu vào listview

        ArrayList<String>arrList=null;
        ArrayAdapter<String> adapter=null;


        // Tạo ArrayList object
        arrList =new ArrayList<String>();

        // Gán Data Source (ArrayList object) vào ArrayAdapter
        adapter=new ArrayAdapter<String>
                (this,
                        android.R.layout.simple_list_item_1,
                        arrList);

        //gán Adapter vào ListView
        list.setAdapter(adapter);
        buttonClick=(Button) findViewById(R.id.buttonClick);


        final ArrayAdapter<String> finalAdapter = adapter;
        final ArrayList<String> finalArrList = arrList;

        //tạo biến input data với dữ liệu từ inputEdittexr
        String inputData = inputEditText.getText().toString();

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputData = inputEditText.getText().toString();

                finalArrList.add(inputEditText.getText().toString()+"");
                finalAdapter.notifyDataSetChanged();

                RequestData requestData= new RequestData(inputData);
                requestData.execute();
            }

        });

        //Kết nối lấy API của IBM Watson
        IamOptions options = new IamOptions.Builder()
                .apiKey("s4BJ7rNo2d0RKm3BUDCObk9O0q5SBS6GNzOkwNVIA0NR")
                .build();

        assistant = new Assistant("2019-04-03", options);

        assistant.setEndPoint("https://gateway.watsonplatform.net/assistant/api");


        //RequestData requestData = new RequestData("Điều 1");
        //Truyền Request Data lên hệ thống server với dữ liệu truyền vào là input data.
        RequestData requestData=new RequestData(inputData);
        requestData.execute();





    }



    private class RequestData extends AsyncTask<Void,Void,Void> {

        String request;


        public RequestData(String request) {
            this.request = request;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InputData inputData = new InputData.Builder().text(request).build();
            MessageOptions messageOptions;

            messageOptions = new MessageOptions.Builder().workspaceId(WORKSPACEID).input(inputData).build();


            MessageResponse messageResponse = assistant.message(messageOptions).execute();

            //Get context của Message Response
            context= messageResponse.getContext();
            Log.d("ID", context.getConversationId());

            Log.d("Answer:",messageResponse.getOutput().getText().get(0));
            return null;



        }



    }
}
