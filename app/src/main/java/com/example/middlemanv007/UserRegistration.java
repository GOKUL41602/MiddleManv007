package com.example.middlemanv007;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class UserRegistration extends AppCompatActivity {

    TextInputLayout userName, password, reEnterPassword;
    String userNameText, passwordText, reEnterPasswordText;
    Button registerBtn;
    RelativeLayout relativeLayout;
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
                            try {
                                userName.setError(null);
                                userName.setErrorEnabled(false);
                                verifyUser();

                            } catch (Exception e) {
                                userName.setError("Please use letters and numbers");
                                userName.setErrorEnabled(true);
                            }
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

    private void verifyUser() {
        reference = FirebaseDatabase.getInstance().getReference("UsersDto");
        Query query = reference.orderByChild("userName").startAt(userNameText).endAt(userNameText + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    userName.setError("User name already exists");
                    userName.setErrorEnabled(true);
                } else {
                    userName.setError(null);
                    userName.setErrorEnabled(false);
                    UserDto userDto = new UserDto(userNameText, reEnterPasswordText);
                    reference.child(userNameText).setValue(userDto);
                    Snackbar.make(relativeLayout, "User successfully created", Snackbar.LENGTH_INDEFINITE).setAction("Login", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserRegistration.this, MainActivity.class);
                            startActivity(intent);
                            UserRegistration.this.finish();
                        }
                    }).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

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
        relativeLayout = findViewById(R.id.userRegistration_relLayout);
    }
}