package com.example.middlemanv007;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class AuthorizedUserLogin extends AppCompatActivity {

    TextInputLayout email, password;
    Button loginButton, forgetPasswordButton;
    TextView newUserButton;
    String userName, emailText, passwordText;
    DatabaseReference reference;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_user_login);

        try {
            userName = getIntent().getStringExtra("userName");
        } catch (Exception e) {
            userName = null;
        }
        initializeViews();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStrings();
                if (validateEmail()) {
                    if (validatePassword()) {
                        verifyUser();
                    } else {
                        validatePassword();
                    }
                } else {
                    validateEmail();
                }

            }
        });

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStrings();
                if (validateEmail()) {
                    verifyEmailForForgetPassword();
                } else {
                    validateEmail();
                }

            }
        });
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthorizedUserLogin.this, AuthorizedUserRegistration.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });
    }

    private void verifyEmailForForgetPassword() {
        reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
        Query query = reference.orderByChild("userName").startAt(userName).endAt(userName + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String emailFromDB = snapshot.child(userName).child("email").getValue(String.class);
                    if (emailText.equals(emailFromDB)) {
                        email.setError(null);
                        email.setErrorEnabled(false);
                        Intent intent = new Intent(AuthorizedUserLogin.this, ForgetPassword.class);
                        intent.putExtra("userName", userName);
                        intent.putExtra("userMode", "Authorized");
                        startActivity(intent);
                    } else {
                        email.setError("Email doesn't exists");
                        email.setErrorEnabled(true);
                    }
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void verifyUser() {
        reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
        Query query = reference.orderByChild("userName").startAt(userName).endAt(userName + "\uf8ff");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String emailFromDB = snapshot.child(userName).child("email").getValue(String.class);
                    if (emailText.equals(emailFromDB)) {
                        email.setError(null);
                        email.setErrorEnabled(false);
                        String passwordFromDB = snapshot.child(userName).child("password").getValue(String.class);
                        String userType = snapshot.child(userName).child("userType").getValue(String.class);
                        if (passwordText.equals(passwordFromDB)) {
                            password.setError(null);
                            password.setErrorEnabled(false);
                            Intent intent = new Intent(AuthorizedUserLogin.this, AuthorizedUserProfile.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("userType", userType);
                            startActivity(intent);
                        } else {
                            password.setError("Incorrect Password");
                            password.setErrorEnabled(true);
                        }
                    } else {
                        email.setError("Email doesn't exists");
                        email.setErrorEnabled(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean validateEmail() {
        if (emailText.equals("")) {
            email.setError("Enter email");
            email.setErrorEnabled(true);
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        if (passwordText.equals("")) {
            password.setError("Enter Password");
            password.setErrorEnabled(true);
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void initializeStrings() {
        emailText = email.getEditText().getText().toString();
        passwordText = password.getEditText().getText().toString();
    }

    private void initializeViews() {
        email = findViewById(R.id.authorizedLoginPage_email);
        password = findViewById(R.id.authorizedLoginPage_password);
        loginButton = findViewById(R.id.authorizedLoginPage_loginButton);
        forgetPasswordButton = findViewById(R.id.authorizedLoginPage_forgetPasswordButton);
        newUserButton = findViewById(R.id.authorizedLoginPage_newUserButton);
        relativeLayout = findViewById(R.id.authorizedLoginPage_relLayout);
    }
}