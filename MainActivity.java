package com.example.minht.chatbotlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Signup;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.LogIn);

        db = new DatabaseHelper(this);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.etboard);
        Login = (Button) findViewById(R.id.buttonLogin);
        Signup = (Button) findViewById(R.id.buttonSignup);


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Name.getText().toString().trim();
                String pwd = Password.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if (res == true)
                {
                    Toast.makeText(MainActivity.this, "Succesfully Logged in", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }








}
