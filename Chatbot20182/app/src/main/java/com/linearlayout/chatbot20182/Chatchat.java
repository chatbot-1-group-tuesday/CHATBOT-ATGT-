package com.linearlayout.chatbot20182;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


//Thực hiện chức năng Chat ATGT
public class Chatchat extends AppCompatActivity {

    TextView messagesTextView;
    EditText inputEditText;
    Button sendButton;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_chat);

        context=this;
        //ánh xạ thuộc tính cho các view trong layout
        messagesTextView=findViewById(R.id.messagesTextView);
        inputEditText=findViewById(R.id.inputEditText);
        sendButton=findViewById(R.id.sendButton);



        //Tạo bắt sự kiện cho nút sendButton
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input =inputEditText.getText().toString();
                messagesTextView.append(Html.fromHtml("<p><b>USER:</b> " +input+ "</p>"));
                inputEditText.setText("");

                getResponse(input);
            }
        });

    }

    private void getResponse(String input) {
//Thiết lập kết nối API cho app
        String workspaceId="9c498c33-11cb-4bf5-8ea1-3dc33b358548";
        String urlAssistant = "https://gateway.watsonplatform.net/assistant/api/v1/workspaces/"+workspaceId+"/message?version=2019-05-02";

        String authentication ="YXBpa2V5OnhaUGo0SlpEdmdCR0JUTEtZMEptcXJaZTgwOUJZcVp0WVJGV19BY0tWeE9h";

        //đẩy dữ liệu vô đối tượng Jason
        JSONObject inputJsonObject = new JSONObject();
        try {
            inputJsonObject.put("text",input);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonBody =new JSONObject();
        try {
            jsonBody.put("input",inputJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//kiểm tra dòng thứ 79
        AndroidNetworking.post(urlAssistant)
//Thêm các headers

                .addHeaders("Content-Type","application/json")
                .addHeaders("Authorization","Basic " + authentication)
                .addJSONObjectBody(jsonBody)
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // String outputAssistant="";

                        try {
                            String outputJasonObject=response.getJSONObject("output").getJSONArray("text").getString(0);
                            messagesTextView.append(Html.fromHtml("<p><b>CHATBOT:</b> " + outputJasonObject +"</p>"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        //Giờ phút của định mệnh
                        Toast.makeText(context,"Hiện tại không thể kết nối đến hệ thống", Toast.LENGTH_LONG).show();


                    }
                });



    }
}
