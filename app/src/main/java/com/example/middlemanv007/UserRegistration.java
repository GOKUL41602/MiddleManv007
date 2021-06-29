package com.example.middlemanv007;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration extends AppCompatActivity {

    TextInputLayout userName, password, reEnterPassword;
    String userNameText, passwordText, reEnterPasswordText;
    Button registerBtn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        initializeViews();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStrings();
                if (validateUserName()) {
                    if (validatePassword()) {
                        if (validateReEnterPassword()) {
                            reference= FirebaseDatabase.getInstance().getReference("UsersDto");
                            UserDto userDto=new UserDto(userNameText,reEnterPasswordText);
                            reference.child(userNameText).setValue(userDto);
                        } else {
                            validateReEnterPassword();
                        }
                    } else {
                        validatePassword();
                    }
                } else {
                    validateUserName();
                }
            }
        });

    }

    private boolean validateUserName() {
        if (userNameText.equals("")) {
            userName.setError("Enter valid User name");
            userName.setErrorEnabled(true);
            return false;
        } else {
            userName.setErrorEnabled(false);
            userName.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        if (passwordText.equals("")) {
            password.setError("Enter valid 6 digit password");
            password.setErrorEnabled(true);
            return false;
        } else {
            password.setErrorEnabled(false);
            password.setError(null);
            return true;
        }
    }

    private boolean validateReEnterPassword() {
        if (reEnterPasswordText.equals("")) {
            reEnterPassword.setError("Enter valid 6 digit password");
            reEnterPassword.setErrorEnabled(true);
            return false;
        } else {
            if (passwordText.equals(reEnterPasswordText)) {
                reEnterPassword.setErrorEnabled(false);
                reEnterPassword.setError(null);
                return true;
            } else {
                reEnterPassword.setErrorEnabled(true);
                reEnterPassword.setError("Password doesn't match");
                return false;
            }

        }
    }

    private void initializeStrings() {
        userNameText = userName.getEditText().getText().toString();
        passwordText = password.getEditText().getText().toString();
        reEnterPasswordText = reEnterPassword.getEditText().getText().toString();
    }

    private void initializeViews() {
        userName = findViewById(R.id.userRegistrationPage_userName);
        password = findViewById(R.id.userRegistrationPage_password);
        reEnterPassword = findViewById(R.id.userRegistrationPage_reEnterPassword);
        registerBtn = findViewById(R.id.userRegistrationPage_registerButton);
    }
}