package com.example.projectmadlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    databaseHelper db;

    EditText emailTxt, passwordTxt;
    Button loginBtn, regisBtn;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new databaseHelper(this);

        emailTxt = findViewById(R.id.email_txt);
        passwordTxt = findViewById(R.id.password_txt);
        loginBtn = findViewById(R.id.login_btn);
        regisBtn = findViewById(R.id.regis_btn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                Boolean res = db.checkUser(email, password);

                if (email.isEmpty()) {
                    emailTxt.setError("Email must be filled");
                }else if (password.isEmpty()) {
                    passwordTxt.setError("Password must be filled");
                } else if (res==true){
                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Homepages.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "You Must Registered", Toast.LENGTH_SHORT).show();
                }

            }
        });

        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

    }
}

