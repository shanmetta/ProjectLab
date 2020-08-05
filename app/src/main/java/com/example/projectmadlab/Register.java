package com.example.projectmadlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    databaseHelper db;

    private static final Pattern password_pattern = Pattern.compile("(?=.*[0-9])"+".{0,}");
    EditText fullnameTxt, emailTxt, passwordTxt, phoneTxt;
    Button regissBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new databaseHelper(this);
        fullnameTxt = findViewById(R.id.fullname_txt);
        emailTxt = findViewById(R.id.emailregis_txt);
        passwordTxt = findViewById(R.id.pwregis_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        regissBtn = findViewById(R.id.regiss_btn);

        regissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname = fullnameTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String mailpattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                String password = passwordTxt.getText().toString();
                String phone = phoneTxt.getText().toString();


                long val = db.addUser(email, password);


                if (!fullname.contains(" ")) {
                    fullnameTxt.setError("Name at least 2 words");
                } else if (!email.matches(mailpattern)) {
                    emailTxt.setError("Masukkan format email yang sesuai");
                } else if (password.length() < 5) {
                    passwordTxt.setError("Password must more than or equals 5 characters");
                } else if (!password.equals(password.toUpperCase()) && (password.equals(password.toLowerCase()))) {
                    passwordTxt.setError("Password harus huruf besar dan kecil");
                } else if (!password_pattern.matcher(password).matches()) {
                    passwordTxt.setError("Contain at least 1 number");
                } else if (!phone.startsWith("+62")) {
                    phoneTxt.setError("harus dimulai +62");
                }else if (val>0){

                    Toast.makeText(Register.this, "Registered Success", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                    Intent intent2 = new Intent(Register.this,MainActivity.class);
                    startActivity(intent2);
                }
                else {
                    Toast.makeText(Register.this, "Column must be filled", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

}

