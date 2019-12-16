package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.calculator.database.AppDatabase;
import com.example.calculator.database.User;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullName, etNickName, etUsername, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.full_name_edit_text);
        etNickName = findViewById(R.id.nick_name_edit_text);
        etUsername = findViewById(R.id.username_edit_text);
        etPassword = findViewById(R.id.password_edit_text);
        btnRegister = findViewById(R.id.register_button);

        final String fullName = etFullName.getText().toString();
        final String nickName = etNickName.getText().toString();
        final String userName = etUsername.getText().toString();
        final String password = etPassword.getText().toString();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(getApplicationContext()).userDao().insertUser(new User(fullName, nickName, userName, password));
                    }
                });

                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });
    }
}
