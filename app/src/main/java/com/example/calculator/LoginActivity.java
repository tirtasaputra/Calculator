package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculator.database.AppDatabase;
import com.example.calculator.database.User;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    TextView tvError;

    public String username, password;
    public boolean isDatabaseEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.username_edit_text);
        etPassword = findViewById(R.id.password_edit_text);
        btnLogin = findViewById(R.id.login_button);
        btnRegister = findViewById(R.id.register_button);
        tvError = findViewById(R.id.error_text_view);

        tvError.setText("");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        isDatabaseEmpty = AppDatabase.getInstance(getApplicationContext()).userDao().selectByUsernameAndPassword(username, password) != null;
                    }
                });

                if ( isDatabaseEmpty == true){
                    Intent loginIntent =  new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                } else if (username.equals("") || password.equals("")){
                    tvError.setText("Blank Field, please enter username and password");
                } else {
                    tvError.setText("Wrong username or password, please enter username and password correctly");
                }


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent =  new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                finish(); //untuk kill activity
            }
        });

    }
}
