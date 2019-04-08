package com.example.minht.chatbotlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper db;
    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIView();
        db = new DatabaseHelper(this);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){
                    //Upload data to database
                }

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });

    }

    private void setupUIView(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);

    regButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = userName.getText().toString().trim();
            String pwd = userPassword.getText().toString().trim();
            String email = userEmail.getText().toString().trim();

            if (pwd.equals(userPassword)){
                long val = db.addUser(user,pwd);
                if (val >0) {
                    Toast.makeText(Register.this, "Succesfully registed ", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(Register.this, MainActivity.class);
                    startActivity(moveToLogin);
                }
            }
            else {
                Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();

            }
        }
    });
    }

    private boolean validate(){
        Boolean result = false;

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "please enter all the information", Toast.LENGTH_SHORT).show();

        }else {
        result = true;
        }
        return false;
    }

}

