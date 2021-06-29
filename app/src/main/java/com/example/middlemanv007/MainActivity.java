package com.example.middlemanv007;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout userName, password;
    Button loginButton;
    String userNameText, passwordText;
    TextView newUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeStrings();

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserRegistration.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    private void initializeStrings() {
        userNameText = userName.getEditText().getText().toString();
        passwordText = password.getEditText().getText().toString();
    }

    private void initializeViews() {
        userName = findViewById(R.id.loginPage_userName);
        password = findViewById(R.id.loginPage_password);
        loginButton = findViewById(R.id.loginPage_loginButton);
        newUserButton = findViewById(R.id.loginPage_newUserButton);
    }
}