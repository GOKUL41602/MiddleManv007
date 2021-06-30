package com.example.middlemanv007;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class AuthorizedUserLogin extends AppCompatActivity {

    TextInputLayout email,password;
    Button loginButton,forgetPasswordButton;
    TextView newUserButton;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_user_login);

        try {
            userName=getIntent().getStringExtra("userName");
        }
        catch (Exception e)
        {
            userName=null;
        }
        initializeViews();
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AuthorizedUserLogin.this,AuthorizedUserRegistration.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
    }

    private void initializeViews()
    {
        email=findViewById(R.id.authorizedLoginPage_email);
        password=findViewById(R.id.authorizedLoginPage_password);
        loginButton=findViewById(R.id.authorizedLoginPage_loginButton);
        forgetPasswordButton=findViewById(R.id.authorizedLoginPage_forgetPasswordButton);
        newUserButton=findViewById(R.id.authorizedLoginPage_newUserButton);
    }
}